package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.CommandAdd;

public class ToolRect implements Tool {
	private ClipRect rect ;
	private double fixedX, fixedY;
	private Color color;
	private CommandAdd addC ;
	
	public ToolRect() {
		rect = new ClipRect(0,0, 0, 0, Color.BLACK);
		setColorAleatoire();
	}
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		/*double red = Math.random();
		double green = Math.random();
		double blue = Math.random();
		double opacity = 1;
		Color color = new Color(red, green, blue, opacity);*/
		rect = new ClipRect(e.getX(), e.getY(), 0, 0, color);
		fixedX = e.getX();
		fixedY = e.getY();

	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		double x = e.getX();
		double y = e.getY();
		double left = Math.min(x, fixedX);
		double right = Math.max(x, fixedX);
		double top = Math.min(y, fixedY);
		double bottom = Math.max(y, fixedY);
		rect.setGeometry(left, top, right, bottom);
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		//i.getBoard().addClip(rect);
		addC = new CommandAdd(i,rect);
		i.getUndoStack().addCommand(addC);
		addC.execute();
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		gc.setStroke(rect.getColor());
		gc.strokeRect(rect.getLeft(), rect.getTop(), rect.getRight()-rect.getLeft(), rect.getBottom()-rect.getTop());
	}

	@Override
	public String getName(EditorInterface editor) {
		return "filled rectangle tool";
	}
	
	@Override
	public void setColor(Color color) {
		this.color = color;
		
	}
	@Override
	public void setColorAleatoire() {
		double red = Math.random();
		double green = Math.random();
		double blue = Math.random();
		double opacity = 1;
		Color color = new Color(red, green, blue, opacity);
		this.color=color;
	}

}
