package Circuits;

//represents a constant voltage source or battery
public class VoltageSource extends Component{
	
	float V; // the rated voltage of the source
	
	public VoltageSource(Node cat, Node an, float V){
		super(cat, an);
		this.V = V;
	}
	
	//returns an equation saying that the voltage difference across the battery is equal to its rated voltage
	public Equation getEquation() {
		Equation toReturn = new Equation();

		//if(!this.getCathode().isRoot()) {
			toReturn.addTerm(-1, this.getCathode().getVarIndex());
		//}
		//if(!this.getAnnode().isRoot()) {
			toReturn.addTerm(1, this.getAnnode().getVarIndex());
		//}
		toReturn.setEquals(this.V);
		return toReturn;
	}
}
