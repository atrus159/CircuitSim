package Circuits;

import java.util.ArrayList;
//Represents a single linear equation the right-hand side constant, as a list of terms.
//Variables are represented by their column index
public class Equation {

	ArrayList<Float> coefficients; //a list of coefficients for each term on the left-hand side
	ArrayList<Integer> vars;  //a list of variables for each term, in same order as coefficients
	float equals; //the constant value on the other right-hand of the equals sign
	
	public Equation() {
		this.coefficients = new ArrayList<Float>();
		this.vars = new ArrayList<Integer>();
		this.equals = 0;
	}
	
	public float getEquals() {
		return this.equals;
	}
	
	public ArrayList<Float> getCoefficients(){
		return this.coefficients;
	}
	
	public ArrayList<Integer> getVars(){
		return this.vars;
	}
	
	public void addTerm(float coefficient, int var) {
		this.coefficients.add(coefficient);
		this.vars.add(var);
	}
	
	public void setEquals(float equals) {
		this.equals = equals;
	}
	
	public String toString() {
		String toReturn = "";
		
		for(int i = 0; i< vars.size(); i++) {
			toReturn += coefficients.get(i) + "*Var" + vars.get(i) + " ";
			if(i != vars.size()-1) {
				toReturn += "+ ";
			}
		}
		toReturn += "= " + equals + "\n";
		
		return toReturn;
		
	}
	
}
