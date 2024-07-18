package Circuits;

//Represents an ideal constant current source
public class CurrentSource extends Component{
	
	float I; //The rated current of the source
	
	public CurrentSource(Node cat, Node an, float I){
		super(cat, an);
		this.I = I;
	}
	
	//Returns an equation saying that the current associated with this component is equal to its rated current
	public Equation getEquation() {
		Equation toReturn = new Equation();

		toReturn.addTerm(1, this.getVarIndex());
		toReturn.setEquals(this.I);
		return toReturn;
	}
	
}
