package pobj.pinboard.editor.commands;

import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;

public class CommandAdd implements Command{
	
	private EditorInterface editor;
	private Clip  toAdd;
	public CommandAdd(EditorInterface editor, Clip toAdd) {
		this.editor = editor;
		this.toAdd = toAdd;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		editor.getBoard().addClip(toAdd);
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		editor.getBoard().removeClip(toAdd);
	}

}
