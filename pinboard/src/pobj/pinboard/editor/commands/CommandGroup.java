package pobj.pinboard.editor.commands;


import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.editor.EditorInterface;

public class CommandGroup implements Command{
	
	private EditorInterface editor;
	ClipGroup group ;
	private List<Clip> clips;
	public CommandGroup(EditorInterface editor, List<Clip> clips) {
		this.editor = editor;
		group = new ClipGroup();
		group.addAllClip(clips);
		this.clips = clips;
	}
	@Override
	public void execute() {
		editor.getBoard().removeClip(clips);
		editor.getBoard().addClip(group);
		editor.getSelection().getContents().add(group);
	}

	@Override
	public void undo() {
		editor.getBoard().addClip(group.getClips());
		editor.getBoard().removeClip(group);
	}
	

}
