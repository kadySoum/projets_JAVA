package pobj.expr;

public interface Expression {
	public int eval();
	public <T> T accept(IVisitor<T> visitor);
}
