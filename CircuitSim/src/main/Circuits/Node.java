package Circuits;
import java.util.ArrayList;

//Represents a node (mutually interconnected region between two components)
public class Node extends Element{
	
	ArrayList<Component> connections; //List of components attached to the node
	
	public Node() {
		super();
		this.connections = new ArrayList<Component>();
	}
	
	public void attach(Component toAttach) {
		this.connections.add(toAttach);
	}
	
	ArrayList<Component> getConnections(){
		return this.connections;
	}
	
	
	// Returns the KCL equation for all currents going into and out of the node
	public Equation getEquation() {
		Equation toReturn = new Equation();
		for(Component comp : this.connections) {
			toReturn.addTerm(comp.getCurrentSign(this), comp.getVarIndex());
		}
		toReturn.setEquals(0);
		return toReturn;
	}
	
}
