package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;

public class Clipboard {
	private List<Clip> clips; 
	private static Clipboard clipboard = new Clipboard();
	private List<ClipboardListener> cibles;
	
	private Clipboard () {
		clips = new ArrayList<>();
		cibles = new ArrayList<ClipboardListener>();
	}
	
	public void copyToClipboard(List<Clip> clips) {
		for (Clip c: clips) {
			this.clips.add(c.copy());
		}
		for (ClipboardListener listener: cibles)
			listener.clipboardChanged();
	}
	
	public List<Clip> copyFromClipboard(){
		List<Clip> cpy = new ArrayList<>();
		for (Clip c: clips) {
			cpy.add(c.copy());
		}
		return cpy; 
	}
	
	public void clear() {
		clips.clear(); 
		for (ClipboardListener listener: cibles)
			listener.clipboardChanged();
	}
	
	public boolean isEmpty() {
		return clips.isEmpty();
	}
	
	public static Clipboard getInstance() {
		return clipboard;
	}
	
	public void addListener(ClipboardListener listener) {
		cibles.add(listener);
	}
	
	public void removeListener(ClipboardListener listener) {
		cibles.remove(listener);
	}
}
