package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class AbstractClip implements Clip {
	private double left;
	private double top; 
	private double right;
	private double bottom;
	private Color color;
	
	public abstract void draw(GraphicsContext ctx) ;
	
	public double getTop() {
		return top;
	}

	
	public double getLeft() {
		return left;
	}


	public double getBottom() {
		return bottom;
	}


	public double getRight() {
		return right;
	}
	
	public void setGeometry(double left, double top, double right, double bottom) {
		this.left = left;
		this.top = top;
		this.right = right;
		this.bottom = bottom;
	}


	public void move(double x, double y) {
		left = left + x;
		top = top+ y;
		right = right + x;
		bottom = bottom+y;
	}

	
	public boolean isSelected(double x, double y) {
		
		return (x>=left && x<=right && y>=top && y<=bottom);
	}

	public void setColor(Color c) {
		color = c;
	}

	public Color getColor() {
		return color;
	}
	
	public abstract  Clip copy();

}
