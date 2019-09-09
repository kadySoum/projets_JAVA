package pobj.pinboard.document;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
public class ClipGroup extends AbstractClip implements Composite{
	private List<Clip> l;
	
	public ClipGroup() {
		super.setGeometry(0, 0, 0, 0);
		l = new ArrayList<>();
	}
	
	@Override
	public List<Clip> getClips() {
		return l;
	}

	@Override
	public void addClip(Clip toAdd) {
		l.add(toAdd);
		calculRectEnglobant();
	}
    
	
	public void addAllClip(List<Clip> toAdd) {
		for(Clip c :toAdd) {
			 l.add(c);
		}
		calculRectEnglobant();
	}
	
	@Override
	public void removeClip(Clip toRemove) {
		l.remove(toRemove);		
		calculRectEnglobant();
	}

	@Override
	public void draw(GraphicsContext ctx) {
		for(Clip c: l) {
			c.draw(ctx);
		}
	}

	@Override
	public Clip copy() {
		ClipGroup copy = new ClipGroup();
		copy.setGeometry(getLeft(), getTop(),getRight(), getBottom());
		for(Clip c: l) {
			copy.addClip(c.copy());
		}
		return copy;
	} 
	public void setGeometry(double left, double top, double right, double bottom) {
		move(left, top);
		
	}
	
	public void move(double x, double y) {
		for (Clip c : l)
			c.move(x, y);
		calculRectEnglobant();
	}
	
	private void calculRectEnglobant() {
		double minLeft = Float.POSITIVE_INFINITY;
		double minTop = Float.POSITIVE_INFINITY;
		double maxRight = Float.NEGATIVE_INFINITY;
		double maxBottom = Float.NEGATIVE_INFINITY;
		for (Clip c : l) {
			double left = c.getLeft();
			double top = c.getTop();
			double right = c.getRight();
			double bottom = c.getBottom();
			if (left < minLeft)
				minLeft = left;
			if (top < minTop)
				minTop = top;
			if (right > maxRight)
				maxRight = right;
			if (bottom > maxBottom)
				maxBottom = bottom;
		}
		super.setGeometry(minLeft, minTop, maxRight, maxBottom);
	}

}
