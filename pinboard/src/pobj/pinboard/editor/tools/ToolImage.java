package pobj.pinboard.editor.tools;

import java.io.File;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.ClipImage;
import pobj.pinboard.editor.EditorInterface;

public class ToolImage implements Tool {
	private ClipImage img;
	private File filename;
	
	public ToolImage(File filename) {
		this.filename = filename;
	}
	
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		img = new ClipImage(e.getX(), e.getY(), filename);

	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		img.move(e.getX()-img.getLeft(), e.getY()-img.getTop());

	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		i.getBoard().addClip(img);

	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		if (img != null)
			img.draw(gc);

	}

	@Override
	public String getName(EditorInterface editor) {
		return "image tool";
	}

	@Override
	public void setColor(Color color) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void setColorAleatoire() {
		// TODO Auto-generated method stub
	}

}
