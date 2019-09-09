package pobj.expr;

public class VisitorSimplify implements IVisitor<Expression> {

	@Override
	public Expression visit(Constant c) {
		return c;
	}

	@Override
	public Expression visit(Add e) {
		Expression left = e.getLeft();
		Expression right = e.getRight();
		
		if (Question10.isConstant(e)) // L'expression est une constante
			return new Constant(Question10.evalConstantExpression(e));
		
		if (Question10.isConstant(e.getLeft())){ // L'expression de gauche une constante
			int leftConst = Question10.evalConstantExpression(left);
			
			// Simplification du 0
			if (leftConst == 0) // élément neutre
				return right.accept(this);
			else 
				left = new Constant(leftConst);
		}
		if (Question10.isConstant(e.getRight())){ // L'expression de droite est une constante
			int rightConst = Question10.evalConstantExpression(right);
			
			// Simplification du 0
			if (rightConst == 0) // élément neutre
				return left.accept(this);
			else 
				right = new Constant(rightConst);
		}

		return new Add(left, right);
	}

	@Override
	public Expression visit(Mult e) {
		Expression left = e.getLeft();
		Expression right = e.getRight();
		
		if (Question10.isConstant(e)) // L'expression est une constante
			return new Constant(Question10.evalConstantExpression(e));
		
		if (Question10.isConstant(e.getLeft())){ // L'expression de gauche une constante
			int leftConst = Question10.evalConstantExpression(left);
			
			// Simplification du 0 et du 1
			if (leftConst == 1) // élément neutre
				return right.accept(this);
			if (leftConst == 0)
				return new Constant(0); // élément absorbant
			left = new Constant(leftConst);
		}
		if (Question10.isConstant(e.getRight())){ // L'expression de droite est une constante
			int rightConst = Question10.evalConstantExpression(right);
			
			// Simplification du 0 et du 1
			if (rightConst == 1) // élément neutre
				return left.accept(this);
			if (rightConst == 0)
				return new Constant(0); // élément absorbant
			right = new Constant(rightConst);
		}

		return new Mult(left, right);
	}

	@Override
	public Expression visit(Var v) {
		return v;
	}

}
