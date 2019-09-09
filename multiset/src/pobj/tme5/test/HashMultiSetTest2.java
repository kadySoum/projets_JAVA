package pobj.tme5.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import pobj.tme5.HashMultiSet;
import pobj.tme5.MultiSet;

public class HashMultiSetTest2 {
	private MultiSet<String> m;

	@Before
	public void before() {
		m = new HashMultiSet<>();
	}
	
	@Test 
	public void testAdd3() {		
		m.add("b");
		m.add("c",5);
		assertEquals(1, m.count("b"));
		/*for(String s: m) {
			System.out.println("s : "+ s);
		}*/
	}
	
	@Test 
	public void testRemove() {
		m.add("a");
		m.add("b",5);
		m.clear();
		assertEquals(0, m.size());
		assertEquals(0, m.count("a"));
		assertEquals(0, m.count("b"));
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void testq3() {
		m.add("b");
		//m.add("c", 0);
		m.remove("a");
		m.remove("c",0);
		assertEquals(0, m.count("c"));
	}
	
	@Test (expected = IllegalArgumentException.class) 
	public void testRemove2() {
		m.add("b");
		m.add("c");
		m.remove("b",-1);
		assertEquals(1, m.count("b"));
	}
	
	@Test
	public void testSize(){   
		MultiSet<String> m = new HashMultiSet<String>();
		m.add("a",4);
		m.add("b",1);
		assertEquals(m.size(), 5);
	}

	@Test
	public void testClear(){   
		m.add("a",4);
		m.add("b",1);
		m.clear();
		m.add("d",9);
		m.add("e");
		assertEquals(m.size(),10);
	}
	@Test
	public void testToString(){   
		m.add("a",4);
		m.add("b",1);
		m.clear();
		m.add("d",4);
		m.add("e");
		System.out.println(m.toString());
		//assertEquals(0, m.toString().compareTo("[d:4, e:1]"));
	}
	
	@Test
	public void testSize2() {
		m.add("b");
		m.add("c", 5);
		m.add("a",4);
		m.remove("c",3);
		assertEquals(7, m.size());
		m.remove("a", 100);
		m.add("a", 1);
		assertEquals(4, m.size());
		m.add("b", 7);
		m.remove("b", 8);
		assertEquals(3, m.size());
	}

	@Test (expected = IllegalArgumentException.class) 
	public void testCasParticuliers1() {
		m.add("a", 0);
	}
	
	/*@Test
	public void testCasParticuliers2() {
		m.add("a", 5);
		m.remove("a", 5);
		assertEquals(false, m.contains("a"));
	}*/
	
	@Test 
	public void testCasParticuliers3() {
		assertEquals(false, m.remove("a"));
	}
}
