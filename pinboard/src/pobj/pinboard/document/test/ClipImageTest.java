package pobj.pinboard.document.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import javafx.embed.swing.JFXPanel;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipImage;

public class ClipImageTest {
	File filename;
	Image image;
	
	@Before
	public void before() {
		JFXPanel fxPanel = new JFXPanel(); //initialise JavaFX
		filename = new File("src/image/java.png");
		image = new Image("file://" + filename.getAbsolutePath());
	}
	
	@Test
	public void testNew() {
		Clip c = new ClipImage(100, 200, filename);
		assertEquals(100., c.getLeft(), 0.);
		assertEquals(200., c.getTop(), 0.);
		assertEquals(100.+image.getWidth(), c.getRight(), 0.);
		assertEquals(200.+image.getHeight(), c.getBottom(), 0.);
		assertEquals(Color.BLACK.toString(), c.getColor().toString());
	}

	@Test
	public void testSetGeometry() {
		Clip c = new ClipImage(100., 200., filename);
		c.setGeometry(101., 201., 301., 401.);
		assertEquals(101., c.getLeft(), 0.);
		assertEquals(201., c.getTop(), 0.);
		assertEquals(301., c.getRight(), 0.);
		assertEquals(401., c.getBottom(), 0.);
	}

	@Test //test
	public void testMove() {
		Clip c = new ClipImage(100., 200., filename);
		c.move(1., 2.);
		assertEquals(101., c.getLeft(), 0.);
		assertEquals(202., c.getTop(), 0.);
		assertEquals(100.+image.getWidth() + 1., c.getRight(), 0.);
		assertEquals(200 + image.getHeight() + 2., c.getBottom(), 0.);
	}


	@Test
	public void testIsSelected() {
		Clip c = new ClipImage(100., 200. ,filename);
		assertTrue(c.isSelected(100 + image.getWidth()/2., 200. + image.getHeight()/2));
		assertFalse(c.isSelected(200.,100.));
		assertFalse(c.isSelected(200.,300. + image.getHeight() + 1));
		assertFalse(c.isSelected(0., 300.));
		assertFalse(c.isSelected(100.+image.getWidth()+1, 300.));
	}

	@Test
	public void testCopy() {
		Clip c = new ClipImage(100., 200. ,filename);
		Clip d = c.copy();
		assertTrue(d instanceof ClipImage);
		c.move(1., 2.);
		assertEquals(101., c.getLeft(), 0.);
		assertEquals(202., c.getTop(), 0.);
		assertEquals(100 + image.getWidth() +1., c.getRight(), 0.);
		assertEquals(200 +  + image.getHeight() +2., c.getBottom(), 0.);
		assertEquals(100., d.getLeft(), 0.);
		assertEquals(200., d.getTop(), 0.);
		assertEquals(100. + image.getWidth(), d.getRight(), 0.);
		assertEquals(200. + image.getHeight(), d.getBottom(), 0.);
	}
}