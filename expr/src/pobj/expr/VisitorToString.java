package pobj.expr;

public class VisitorToString implements IVisitor<String> {

	public String visit(Constant c) {
		return Integer.toString(c.getValue());
	}
	
	@Override
	public String visit(Add e) {
		String s1 = e.getLeft().accept(this);
		String s2 = e.getRight().accept(this);
		return "( "+ s1 + " + " + s2 + " )";
	}

	@Override
	public String visit(Mult e) {
		String s1 = e.getLeft().accept(this);
		String s2 = e.getRight().accept(this);
		return s1 + " * " + s2;
	}

	@Override
	public String visit(Var v) {
		return v.getName();
	}
	
}