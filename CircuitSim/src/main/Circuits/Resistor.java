package Circuits;

//represents a resistor component
public class Resistor extends Component{
	
	float R; //the resistance of the resistor
	
	public Resistor(Node cat, Node an, float R){
		super(cat, an);
		this.R = R;
	}
	
	//Returns V = IR, using the component's associated current, and the voltage difference of its two neighboring nodes
	public Equation getEquation() {
		Equation toReturn = new Equation();
		
		toReturn.addTerm(1, this.getCathode().getVarIndex());
		toReturn.addTerm(-1, this.getAnnode().getVarIndex());
		toReturn.addTerm(-this.R, this.getVarIndex());
		toReturn.setEquals(0);
		return toReturn;
	}

}
