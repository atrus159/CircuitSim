package Circuits;

import java.util.ArrayList;

import org.la4j.Vector;
import org.la4j.linear.GaussianSolver;
import org.la4j.matrix.sparse.CRSMatrix;
//Represents a complete circuit
//Stores one node as an access point into the circuit
//Circuit elements must be registered with the Graph using the AddElement() method, which will assign them a column index
//calcVals() calculates all variables associated with the circuit, and returns a vector which the circuit elements can use to retrieve their associated values using Element.getVarVals() 
public class Graph {
	
	Node rootNode; //Stores the first node registered, as an access point into the circuit
	int columnCount; //Running count of the number of variables registered
	boolean currentVisitedFlag; //Toggles between true and false every time a depth-first-search is performed
	
	//Used while calculating variables
	ArrayList<Equation> equations; //Stores all equations from all elements
	CRSMatrix A; //Stores the A matrix, with equation terms in their corresponding columns
	Vector B; //Stores the B matrix, with the right-hand side of all equations
	
	public Graph() {
		rootNode = null;
		columnCount = 0;
		currentVisitedFlag = true;
		equations = new ArrayList<Equation>();
	}
	
	public void AddElement(Element toAdd) {
		toAdd.setVarIndex(this.columnCount); //assign each registered element the next column index, then increment the column index
		this.columnCount ++;
		
		toAdd.setVisitedFlag(!this.currentVisitedFlag); //make sure each element starts in the unvisited state
		
		if(this.rootNode == null && toAdd instanceof Node) { //store the first node registered in rootNode
			Node toAddNode = (Node) toAdd;
			this.rootNode = toAddNode;
		}
	}

	//Performs a recursive depth-first-search on the circuit, getting every component to produce its equation and storing it in equations
	//Uses the value of currentVisitedFlag to mark which elements have been visited so far
	private void generateEquations(Node curNode) {
		this.equations.add(curNode.getEquation());
		curNode.setVisitedFlag(this.currentVisitedFlag);
		for(Component nextComp : curNode.getConnections()) {
			if(nextComp.getVisitedFlag() != this.currentVisitedFlag) {
				this.equations.add(nextComp.getEquation());
				nextComp.setVisitedFlag(this.currentVisitedFlag);
				Node nextNode = null;
				if(curNode == nextComp.getAnnode()) {
					nextNode = nextComp.getCathode();
				}else {
					nextNode = nextComp.getAnnode();
				}
				if(nextNode.getVisitedFlag() != this.currentVisitedFlag) {
					generateEquations(nextNode);
				}
			}
		}
	}
	
	//Gathers equations, converts them into matrix form, and toggles currentVisitedFlag so the next call will see a fully unvisited circuit
	private void generateMatrix() {
		this.equations.clear();
		generateEquations(this.rootNode);
		Equation initialVoltage = new Equation();
		initialVoltage.addTerm(1, this.rootNode.getVarIndex());
		initialVoltage.setEquals(0);
		this.equations.add(initialVoltage);
		this.A = CRSMatrix.zero(this.equations.size(), this.columnCount);
		this.B = Vector.zero(this.equations.size());
		int curRow = 0;
		for(Equation curEq : this.equations) {
			this.B.set(curRow, curEq.getEquals());
			ArrayList<Integer> vars = curEq.getVars();
			ArrayList<Float> coeficients = curEq.getCoefficients();
			for(int i = 0; i < vars.size(); i++) {
				this.A.set(curRow, vars.get(i), coeficients.get(i));
			}
			curRow ++;
		}
		this.currentVisitedFlag = !this.currentVisitedFlag;
	}
	
	//Performs Gaussian Elimination using the LA4J library to produce the vector of results
	public Vector calcVals() {
		generateMatrix();
		//Adds an additional column to the matrix to make it square (required for the LA4J GausianSolver)
		//The additional column must be linearly independent from all other columns, or else the matrix will not be solvable
		//So it iteratively tries different basis vectors until the solver doesn't throw an error
		//Since there are n other columns and n+1 rows, guaranteed to find one in at most n+1 steps
		for(int i = 0; i< this.equations.size(); i++) {
			Vector additionalColumn = Vector.zero(this.equations.size());
			additionalColumn.set(i, 1);
			this.A = (CRSMatrix) this.A.insertColumn(0, additionalColumn);
			try {
				GaussianSolver solver = new GaussianSolver(this.A);
				return solver.solve(this.B).slice(1, this.columnCount+1);
			}catch(Exception e) {
				continue;
			}
		}
		return null;
	}
	
}
