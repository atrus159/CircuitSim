package Circuits;

import org.la4j.Vector;

public class Main {

	public static void main(String[] args) {
		
		Graph graph = new Graph();
		Node n0 = new Node();
		Node n1 = new Node();
		VoltageSource b1 = new VoltageSource(n0,n1,5);
		Resistor r1 = new Resistor(n1,n0, 3);
		Resistor r2 = new Resistor(n1,n0, 2);
		
		graph.AddElement(n0);
		graph.AddElement(n1);
		graph.AddElement(b1);
		graph.AddElement(r1);
		graph.AddElement(r2);
		
		Vector results = graph.calcVals();
		System.out.println(results);
		
	}

}
