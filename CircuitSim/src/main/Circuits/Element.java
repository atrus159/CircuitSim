package Circuits;
import org.la4j.Vector;
//Represents any object in the circuit (either a node or component)
//Every element has one corresponding variable (either a voltage for a node or a current for a component)
public abstract class Element {
	
	int varIndex; //Column index representing the element's associated variable
	boolean visitedFlag; //Flag used by Graph while performing depth-first search
	
	public Element() {
		this.varIndex = -1;
		this.visitedFlag = false;
	}
	
	public int getVarIndex() {
		return this.varIndex;
	}
	public void setVarIndex(int varIndex) {
		this.varIndex = varIndex;
	}
	
	public void setVisitedFlag(boolean toSet) {
		this.visitedFlag = toSet;
	}
	
	public boolean getVisitedFlag() {
		return this.visitedFlag;
	}
	
	public float getVarVal(Vector result) { // Given a results vector produced by Graph.calcVals(), returns its associated variable value
		return (float) result.get(varIndex);
	}
	
	
	//Implemented by each element to return an equation describing the associated variables of it or nearby elements
	//Nodes will use KCL to create an equation using its neighboring components' currents
	//Components will create their own component-specific equations using their neighboring node's voltages
	abstract public Equation getEquation();
}
