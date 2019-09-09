package pobj.expr;

public class Question4 {
	 public static Expression e1() {
		 return new Mult(new Add(new Constant(2), new Constant(3)),new Constant(4));
	 }
	 
	 public static Expression e2() {
		 Var x = new Var("x");
		 return new Mult(new Add (x , new Constant(3)), new Add (x, new Constant(4)));
	 }
	 public static Expression e3() {
		 Var x = new Var("x");
		 Var y = new Var("y");
		 return new Mult(new Add (x , new Constant(10)), new Add (y, new Constant(-8)));
	
	 }
}
