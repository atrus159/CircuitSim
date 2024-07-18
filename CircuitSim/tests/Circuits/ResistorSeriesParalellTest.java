package Circuits;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.la4j.Vector;


class ResistorSeriesParalellTest {

	Graph graph;
	Node n0;
	Node n1;
	Node n2;
	VoltageSource b1;
	Resistor r1;
	Resistor r2;
	Resistor r3;
	Vector results;
	double delta;
	
	@BeforeEach
	public void setUp() throws Exception{
		graph = new Graph();
		n0 = new Node();
		n1 = new Node();
		n2 = new Node();
		b1 = new VoltageSource(n0,n1,5);
		r1 = new Resistor(n1,n2, 3);
		r2 = new Resistor(n2,n0, 2);
		r3 = new Resistor(n2,n0, 4);
		graph.AddElement(n0);
		graph.AddElement(n1);
		graph.AddElement(n2);
		graph.AddElement(b1);
		graph.AddElement(r1);
		graph.AddElement(r2);
		graph.AddElement(r3);
		results = graph.calcVals();
		delta = 0.001;
	}
	
	@Test
	void test() {
		org.junit.Assert.assertEquals(n0.getVarVal(results), 0, delta);
		org.junit.Assert.assertEquals(n1.getVarVal(results), 5, delta);
		org.junit.Assert.assertEquals(n2.getVarVal(results), 1.5384, delta);
		org.junit.Assert.assertEquals(b1.getVarVal(results), 1.1538, delta);
		org.junit.Assert.assertEquals(r1.getVarVal(results), 1.1538, delta);
		org.junit.Assert.assertEquals(r2.getVarVal(results), 0.7692, delta);
		org.junit.Assert.assertEquals(r3.getVarVal(results), 0.3846, delta);
	}

}
