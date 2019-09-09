package pobj.expr;

public abstract class BinOp implements Expression {
	private final Expression left;
	private final Expression right;
	
	public BinOp(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}

	/**
	 * @return the left
	 */
	public Expression getLeft() {
		return left;
	}

	/**
	 * @return the right
	 */
	public Expression getRight() {
		return right;
	} 
	
	

}
