package pobj.expr;

public class Question10 {
	
	// rend vrai si e est un arbre d’expression constant
	public static boolean isConstant(Expression e) {
		VisitorConstant vcons= new VisitorConstant();
		return e.accept(vcons);
	}
	
	// rend la valeur d’une expression constante
	// signale une exception si l’expression n’est pas constante 
	public static int evalConstantExpression (Expression e) {
		VisitorEval vev = new VisitorEval();
		return e.accept(vev);
	}
}
