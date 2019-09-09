package pobj.expr;

public class Question13 {
	public static <T> T compose(IVisitor<T> f, IVisitor<Expression> g, Expression e) {
	      Expression resG = e.accept(g);
	      return resG.accept(f);
	} 
}
