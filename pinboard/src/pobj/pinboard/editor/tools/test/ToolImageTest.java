package pobj.pinboard.editor.tools.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Test;

import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipImage;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.tools.Tool;
import pobj.pinboard.editor.tools.ToolImage;

public class ToolImageTest extends ToolTest {

	//JFXPanel jfxPanel = new JFXPanel(); //initialise JavaFX
	private File filename = new File("src/image/java.png");
	private Tool tool = new ToolImage(filename);
	private Image img = new Image("file://" + filename.getAbsolutePath());


	@Test
	public void testCreate() {
		assertTrue(editor.getBoard().getContents().isEmpty());
		tool.press(editor, makeMouseEvent(MouseEvent.MOUSE_PRESSED, 100, 200, false));
		assertTrue(editor.getBoard().getContents().isEmpty());
		tool.drag(editor, makeMouseEvent(MouseEvent.MOUSE_DRAGGED, 300, 400, false));
		assertTrue(editor.getBoard().getContents().isEmpty());
		tool.release(editor, makeMouseEvent(MouseEvent.MOUSE_RELEASED, 300, 400, false));
		List<Clip> list = editor.getBoard().getContents();
		assertEquals(1, list.size());
		assertTrue(list.get(0) instanceof ClipImage);
		ClipImage clip = (ClipImage) list.get(0);
		assertEquals(300., clip.getLeft(), 0.);
		assertEquals(400., clip.getTop(), 0.);
		assertEquals(300. + img.getWidth(), clip.getRight(), 0.);
		assertEquals(400. + img.getHeight(), clip.getBottom(), 0.);
	}
	
	// mouvement plus complexe de la souris
	@Test
	public void testCreate2() {
		tool.press(editor, makeMouseEvent(MouseEvent.MOUSE_PRESSED, 100, 200, false));
		tool.drag(editor, makeMouseEvent(MouseEvent.MOUSE_DRAGGED, 400, 500, false));
		tool.drag(editor, makeMouseEvent(MouseEvent.MOUSE_DRAGGED, 50, 50, false));
		tool.drag(editor, makeMouseEvent(MouseEvent.MOUSE_DRAGGED, 200, 50, false));
		tool.drag(editor, makeMouseEvent(MouseEvent.MOUSE_DRAGGED, 50, 600, false));
		tool.drag(editor, makeMouseEvent(MouseEvent.MOUSE_DRAGGED, 300, 400, false));
		tool.release(editor, makeMouseEvent(MouseEvent.MOUSE_RELEASED, 300, 400, false));
		List<Clip> list = editor.getBoard().getContents();
		assertEquals(1, list.size());
		assertTrue(list.get(0) instanceof ClipImage);
		ClipImage clip = (ClipImage) list.get(0);
		assertEquals(300., clip.getLeft(), 0.);
		assertEquals(400., clip.getTop(), 0.);
		assertEquals(300. + img.getWidth(), clip.getRight(), 0.);
		assertEquals(400. + img.getHeight(), clip.getBottom(), 0.);
	}
	
	// coins inversés
	@Test
	public void testCreate3() {
		tool.press(editor, makeMouseEvent(MouseEvent.MOUSE_PRESSED, 300, 400, false));
		tool.drag(editor, makeMouseEvent(MouseEvent.MOUSE_DRAGGED, 100, 200, false));
		tool.release(editor, makeMouseEvent(MouseEvent.MOUSE_RELEASED, 100, 200, false));
		List<Clip> list = editor.getBoard().getContents();
		assertEquals(1, list.size());
		assertTrue(list.get(0) instanceof ClipImage);
		ClipImage clip = (ClipImage) list.get(0);
		assertEquals(100., clip.getLeft(), 0.);
		assertEquals(200., clip.getTop(), 0.);
		assertEquals(100. + img.getWidth(), clip.getRight(), 0.);
		assertEquals(200. + img.getHeight(), clip.getBottom(), 0.);
	}	
}
