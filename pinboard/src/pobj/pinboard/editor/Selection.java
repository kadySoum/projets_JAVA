package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;

public class Selection {
	private List<Clip> listClips;
	
	public Selection() {
		listClips = new ArrayList<Clip>();
	}
	
	public void select(Board board, double x, double y) {
		listClips.clear();
		for (Clip clip: board.getContents()) {
			if (clip.isSelected(x, y)) {
				listClips.add(clip);
				return;
			}
		}
	}
	
	public void toogleSelect(Board board, double x, double y) {
		List<Clip> listTemp = new ArrayList<Clip>(listClips);
		for (Clip clip: board.getContents()) {
			if (clip.isSelected(x, y)) {
				if (listClips.contains(clip))
					listTemp.remove(clip);
				else
					listTemp.add(clip);
			}
		}
		listClips = listTemp;
	}
	
	public void clear() {
		listClips.clear();
	}
	
	public List<Clip> getContents(){
		return listClips;
	}
	
	public void drawFeedback(GraphicsContext gc) {
		for (Clip clip : listClips) {
			gc.setStroke(Color.BLUE);
			gc.strokeRect(clip.getLeft(), clip.getTop(), clip.getRight()-clip.getLeft(), clip.getBottom()-clip.getTop());
		}
	}
}
