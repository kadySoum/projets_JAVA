package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;
import pobj.pinboard.editor.commands.Command;

public class CommandStack {
	List<Command> undo  = new ArrayList<Command>();
	List<Command> redo  = new ArrayList<Command>();
	
	/**
	 * empile une commande dans la pile undo ; elle vide également la pile redo, puisque refaire la
	dernière action annulée peut entrer en conflit avec la commande que nous venons d’ajouter ;
	 * @param cmd
	 */
	public void addCommand(Command cmd) {
		undo.add(cmd);
		redo.clear();
	}
	
	/**
	 * dépile la dernière commande de la pile undo, exécute sa méthode undo, et empile la commande
	dans la pile redo ;
	 */
	public void undo() {
		if (!isUndoEmpty()) {
			Command d =undo.get(undo.size()-1);
			d.undo();
			redo.add(d);
			undo.remove(undo.size()-1);
		}
	}
	
	/**
	 * dépile la dernière commande de la pile redo, exécute sa méthode execute, et empile la commande
	dans la pile undo ;
	 */
	public void redo() {
		if (!isRedoEmpty()) {
	
			Command d =redo.get(redo.size()-1);
			d.execute();
			undo.add(d);
			redo.remove(redo.size()-1);
		}
	}
	/**
	 * si vide
	 * @return
	 */
	public boolean isUndoEmpty() {
		return undo.isEmpty();
	}
	public boolean isRedoEmpty() {
		return redo.isEmpty();
	}

}
