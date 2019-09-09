package pobj.expr;

public class VisitorDerive implements IVisitor<Expression> {
	private Var var;
	
	public VisitorDerive(Var var) {
		this.var = var;
	}

	@Override
	public Expression visit(Constant c) {
		return new Constant(0);
	}

	@Override
	public Expression visit(Add e) {
		return new Add(e.getLeft().accept(this), e.getRight().accept(this));
	}

	@Override
	public Expression visit(Mult e) {
		Expression left = new Mult(e.getLeft(), e.getRight().accept(this));
		Expression right = new Mult(e.getLeft().accept(this), e.getRight());
		return new Add(left, right);
	}

	@Override
	public Expression visit(Var v) {
		if (var.getName() == v.getName())
			return new Constant(1);
		else 
			return new Constant(0);
	}


}
