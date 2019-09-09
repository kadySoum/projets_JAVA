package pobj.pinboard.editor.commands;


import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;



public class CommandMove implements Command{
	
	private EditorInterface editor;
	private Clip  toAdd;
	private double x;
	private double y;
	public CommandMove(EditorInterface editor, Clip toAdd, double x, double y) {
		this.editor = editor;
		this.toAdd = toAdd;
		this.x = x;
		this.y = y;
	}
	@Override
	public void execute() {
		toAdd.move(x, y);
	}

	@Override
	public void undo() {
		toAdd.move(-x, -y);
	}
}
