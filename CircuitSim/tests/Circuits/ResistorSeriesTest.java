package Circuits;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.la4j.Vector;


class ResistorSeriesTest {

	Graph graph;
	Node n0;
	Node n1;
	VoltageSource b1;
	Node n2;
	Resistor r1;
	Resistor r2;
	Vector results;
	double delta;
	
	@BeforeEach
	public void setUp() throws Exception{
		graph = new Graph();
		n0 = new Node();
		n1 = new Node();
		b1 = new VoltageSource(n0,n1,5);
		n2 = new Node();
		r1 = new Resistor(n1,n2, 3);
		r2 = new Resistor(n2,n0, 2);
		graph.AddElement(n0);
		graph.AddElement(n1);
		graph.AddElement(n2);
		graph.AddElement(b1);
		graph.AddElement(r1);
		graph.AddElement(r2);
		results = graph.calcVals();
		delta = 0.001;
	}
	
	@Test
	void test() {
		org.junit.Assert.assertEquals(n0.getVarVal(results), 0, delta);
		org.junit.Assert.assertEquals(n1.getVarVal(results), 5, delta);
		org.junit.Assert.assertEquals(n2.getVarVal(results), 2, delta);
		org.junit.Assert.assertEquals(b1.getVarVal(results), 1, delta);
		org.junit.Assert.assertEquals(r1.getVarVal(results), 1, delta);
		org.junit.Assert.assertEquals(r2.getVarVal(results), 1, delta);
	}

}
