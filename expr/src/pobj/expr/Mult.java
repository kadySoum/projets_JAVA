package pobj.expr;

public class Mult extends BinOp {
	
	public Mult(Expression left, Expression right) {
		super(left, right);
	}
	
	public String toString() {
		return  getLeft() + " * " + getRight() ;
	}

	@Override
	public int eval() {
		return getLeft().eval() * getRight().eval();
	}

	@Override
	public <T> T accept(IVisitor<T> vi) {
		return vi.visit(this);
	}

}
