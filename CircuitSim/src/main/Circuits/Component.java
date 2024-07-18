package Circuits;

//Represents a two-pole component, such as a resistor or battery
//All components have an anode and cathode, even if they are bidirectional
public abstract class Component extends Element {
	
	Node anode; //output node
	Node cathode; //input node
	
	public Component(Node cat, Node an){
		super();
		this.cathode = cat;
		this.anode = an;
		//automatically attaches its nodes to itself
		this.cathode.attach(this);
		this.anode.attach(this);
	}
	
	public Node getAnnode() {
		return this.anode;
	}
	public Node getCathode() {
		return this.cathode;
	}
	
	//Used by nodes to work out of a component's positive-direction current goes into or out of the node
	//NOTE: DOES NOT NECECARILY REPRESENT THE ACTUAL DIRECTION OF CURRENT, ONLY THE DIRECTION IF CURRENT IS POSITIVE
	public int getCurrentSign(Node nodeInto) {
		if(nodeInto == this.anode) {
			return 1;
		}else if(nodeInto == this.cathode) {
			return -1;
		}else {
			return 0;
		}
	}
	
}
