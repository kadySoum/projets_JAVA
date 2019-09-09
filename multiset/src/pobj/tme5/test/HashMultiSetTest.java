package pobj.tme5.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pobj.tme5.HashMultiSet;
import pobj.tme5.MultiSet;

public class HashMultiSetTest {
	
	private MultiSet<String> m;
	
	@Before
	public void before() {
		m = new HashMultiSet<>();
	}
	
	@Test 
	public void testAdd1() {
		m.add("a");
		m.add("a",5);
		assertEquals(6, m.count("a"));
	}

	@Test(expected = IllegalArgumentException.class) 
	public void testAdd2() {
		m.add("a");
		m.add("a",-1);
	}
	
	@Test 
	public void testRemove1() {
		m.add("a");
		m.add("a",5);
		m.remove("a");
		m.remove("a",3);
		assertEquals(2, m.count("a"));
	}

	@Test(expected = IllegalArgumentException.class) 
	public void testRemove2() {
		m.remove("a");
		m.remove("a",-1);
	}
	
	@Test 
	public void testClear1() {
		m.add("a");
		m.add("b",5);
		m.clear();
		assertEquals(0, m.size());
		assertEquals(0, m.count("a"));
		assertEquals(0, m.count("b"));
	}
	
	@Test 
	public void testToString() {
		m.add("a");
		m.add("b",5);
		System.out.println("m : " + m);
	}
}
