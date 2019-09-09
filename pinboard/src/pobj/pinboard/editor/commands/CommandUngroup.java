package pobj.pinboard.editor.commands;


import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.editor.EditorInterface;

	public class CommandUngroup implements Command{
		
		private EditorInterface editor;
		ClipGroup group ;
		private List<Clip> clips;
		
		public CommandUngroup(EditorInterface editor, ClipGroup group) {
			this.editor = editor;
			this.group = group;
			this.clips = group.getClips();
		}
		@Override
		public void undo() {
			editor.getBoard().removeClip(clips);
			editor.getBoard().addClip(group);
			editor.getSelection().getContents().add(group);
		}

		@Override
		public void execute() {
			editor.getBoard().addClip(group.getClips());
			editor.getBoard().removeClip(group);
		}
	}
