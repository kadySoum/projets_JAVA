package pobj.expr;

import java.util.HashMap;
import java.util.Map;

public class Question8 {
	
	public static Map<String,Integer> env1() {
		return new  HashMap<String,Integer>();
	}
	
	public static Map<String,Integer> env2() {
		Map<String,Integer> environnement = new HashMap<String,Integer>();
		environnement.put("x", 10);
		environnement.put("y", 20);
		return environnement;
	}
		
	public static Map<String,Integer> env3() {
		Map<String, Integer> environnement =  new HashMap<String,Integer>();
		environnement.put("z", 9);
		return environnement;
	}
}

