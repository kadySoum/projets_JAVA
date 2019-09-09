package pobj.expr;

public class Constant implements Expression{
	private int constante; 
	
	public Constant(int constante) {
		this.constante = constante;
	}
	
	public Constant(){
		this(0);
		
	}
	
	public int getValue() {
		return constante;
	}
	
	public boolean equals(Object o) {
		if (o ==null)return false;
		if (! (o instanceof Constant)) {
			return false;
		}
		Constant c = (Constant) o; 
		return c.getValue() == constante;
	}
	
	public String toString() {
		return String.valueOf(constante);
	}

	@Override
	public int eval() {
		return constante;
	}

	@Override
	public <T> T accept(IVisitor<T> vi) {
		return vi.visit(this);
	}
}