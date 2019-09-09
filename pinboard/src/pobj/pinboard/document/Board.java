package pobj.pinboard.document;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

public class Board {
	private List<Clip> clips; 
	
	public Board()
	{
		clips = new ArrayList<Clip>();
	}
	
	public List<Clip> getContents(){
		return clips;
	}
	
	public void addClip(Clip clip) {
		clips.add(clip);
	}
	public void addClip(List<Clip> clip) {
		clips.addAll(clip);
	}
	public void removeClip(Clip clip) {
		clips.remove(clip);
	}
	public void removeClip(List<Clip> clip) {
		clips.removeAll(clip);
	}
	
	public void draw(GraphicsContext gc) {
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		for (Clip clip: clips) {
			clip.draw(gc);
		}
	}
}
