package pobj.pinboard.editor;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.editor.commands.CommandGroup;
import pobj.pinboard.editor.commands.CommandUngroup;
import pobj.pinboard.editor.tools.Tool;
import pobj.pinboard.editor.tools.ToolEllipse;
import pobj.pinboard.editor.tools.ToolImage;
import pobj.pinboard.editor.tools.ToolRect;
import pobj.pinboard.editor.tools.ToolSelection;

public class EditorWindow implements EditorInterface,ClipboardListener
{
	private Board board;
	private Tool tool;
	private Label status;
	private Canvas canvas;
	private final FileChooser fileChooser = new FileChooser();
	private Selection selection = new Selection();
	private MenuItem paste;
	private CommandStack commandStack= new CommandStack();
	public EditorWindow(Stage stage) {
		board = new Board();
		tool = new ToolRect();

		stage.setTitle("PinBoard");
		
		Clipboard.getInstance().addListener(this);
		
		VBox vbox = new VBox();
		
		// Barre de menu
		
		Menu file = new Menu("File");
		MenuItem newFile = new MenuItem("New");
		newFile.setOnAction( (e)-> { new EditorWindow(new Stage()) ;});
		MenuItem closeFile = new MenuItem("Close");
		closeFile.setOnAction( (e)-> { Clipboard.getInstance().removeListener(this);stage.close();});	
		file.getItems().addAll(newFile, closeFile);
		
		Menu edit = new Menu("Edit");
		MenuItem copy = new MenuItem("Copy"); 
		copy.setOnAction( (e)-> {Clipboard.getInstance().clear();Clipboard.getInstance().copyToClipboard(selection.getContents());});
		paste = new MenuItem("Paste"); 
		paste.setOnAction( (e)-> {board.addClip((Clipboard.getInstance().copyFromClipboard()));draw();});
		if (Clipboard.getInstance().isEmpty()) {
			paste.setDisable(true);
		}
		else {
			paste.setDisable(false);

		}
		
		
		MenuItem group  = new MenuItem("Group"); 
		group.setOnAction( (e)-> {CommandGroup c = new CommandGroup(this,selection.getContents()); commandStack.addCommand(c);c.execute();/*group();*/});
		MenuItem ungroup  = new MenuItem("Ungroup"); 
		ungroup.setOnAction( (e)-> {CommandUngroup c = new CommandUngroup(this,(ClipGroup)selection.getContents().get(0));commandStack.addCommand(c); c.execute();/*unGroup();*/});
		
		MenuItem delete = new MenuItem("delete");
		delete.setOnAction( (e)-> {board.removeClip(selection.getContents());selection.clear();draw();});
		/**
		 * UNDO REDO
		 */
		MenuItem redo  = new MenuItem("Redo");
		MenuItem undo  = new MenuItem("Undo"); 
		undo.setDisable(false);
		redo.setDisable(true);
		undo.setOnAction( (e)-> {commandStack.undo();draw();redo.setDisable(false);});//if (commandStack.isUndoEmpty()) {
			//undo.setDisable(true);} draw();});
		redo.setOnAction( (e)-> {commandStack.redo();draw();undo.setDisable(false);}); //if(commandStack.isRedoEmpty()) {
														//redo.setDisable();}});
														
		
		edit.getItems().addAll(copy, paste, delete,group, ungroup,undo,redo);
		
		
		Menu tools = new Menu("Tools");
		MenuItem rectangleItem = new MenuItem("Rectangle");
		rectangleItem.setOnAction( (e)-> {tool = new ToolRect() ; updateStatus(tool.getName(this));});	
		MenuItem ellipseItem = new MenuItem("Ellipse");
		ellipseItem.setOnAction( (e)-> {tool = new ToolEllipse() ; updateStatus(tool.getName(this));});	
		tools.getItems().addAll(rectangleItem, ellipseItem);
		
		MenuBar menuBar = new MenuBar(file,edit,tools);
		vbox.getChildren().add(menuBar);
		
		// Barre de Boutons 
		Button rect = new Button("Rectangle"); 
		rect.setOnAction( (e)-> { tool = new ToolRect() ; updateStatus(tool.getName(this));});
		Button ellipse = new Button("ellipse"); 
		ellipse.setOnAction( (e)-> { tool = new ToolEllipse() ; updateStatus(tool.getName(this));});
		Button img = new Button("img..."); 
		img.setOnAction( (e)-> {
            File filename = fileChooser.showOpenDialog(stage);
	        if (file != null) {
	        	tool = new ToolImage(filename) ; 
	        	updateStatus(tool.getName(this));
	        }
		});
		Button sel = new Button("Select");
		sel.setOnAction( (e) -> { tool = new ToolSelection(); updateStatus(tool.getName(this));});
		
		ToolBar toolsBar = new ToolBar(rect, ellipse, img, sel); 
		vbox.getChildren().addAll(toolsBar);
		
		// Barre des couleurs 
		
		final Rectangle color1 = new Rectangle(25,25);
		final Rectangle color2 = new Rectangle(25,25);
		final Rectangle color3 = new Rectangle(25,25);
		final Rectangle color4 = new Rectangle(25,25);
		final Rectangle color5 = new Rectangle(25,25);
		final Rectangle color6 = new Rectangle(25,25);
		
		color1.setFill(Color.YELLOW);
		color2.setFill(Color.BLUE);
		color3.setFill(Color.RED);
		color4.setFill(Color.GREEN);
		color5.setFill(Color.PURPLE);
		color6.setFill(Color.BLACK);
		
		Button aleatoire = new Button("Couleur aléatoire"); 
		aleatoire.setOnAction( (e)-> { tool.setColorAleatoire();});
		ColorPicker color = new ColorPicker();
		color.setOnAction( (e)-> { tool.setColor(color.getValue());});
		Button rect1 = new Button("",color1); 
		rect1.setOnAction( (e)-> { tool.setColor((Color) color1.getFill());});
		Button rect2 = new Button("",color2); 
		rect2.setOnAction( (e)-> { tool.setColor((Color) color2.getFill());});
		Button rect3 = new Button("",color3); 
		rect3.setOnAction( (e)-> { tool.setColor((Color) color3.getFill());});
		Button rect4 = new Button("",color4); 
		rect4.setOnAction( (e)-> { tool.setColor((Color) color4.getFill());});
		Button rect5 = new Button("",color5); 
		rect5.setOnAction( (e)-> { tool.setColor((Color) color5.getFill());});
		Button rect6 = new Button("",color6); 
		rect6.setOnAction( (e)-> { tool.setColor((Color) color6.getFill());});
		
		ToolBar toolColor = new ToolBar(aleatoire, color, rect1,rect2,rect3,rect4,rect5, rect6);
		vbox.getChildren().add(toolColor);
	
		// Zone de dessin
		canvas = new Canvas(850, 600);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		board.draw(gc);
		canvas.setOnMousePressed( (e) -> { tool.press(this, e);draw();});
		canvas.setOnMouseDragged( (e) -> { tool.drag(this, e);draw();});
		canvas.setOnMouseReleased( (e) -> { tool.release(this, e);draw();});
		vbox.getChildren().add(canvas);
		
		//Séparateur
		Separator separator = new Separator(); 
		vbox.getChildren().add(separator);
				
		// Bare de status
		status = new Label(tool.getName(this));
		vbox.getChildren().add(status);
		
		Scene scene = new Scene(vbox);
		stage.setScene(scene);
		stage.show(); 
	}


	@Override
	public Board getBoard() {
		return board;
	}

	@Override
	public Selection getSelection() {
		return selection;
	}

	@Override
	public CommandStack getUndoStack() {
		return commandStack;
	}
	
	private void updateStatus(String text) {
		status.setText(text);
	}
	
	public void draw() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		board.draw(gc);
		tool.drawFeedback(this, gc);
	}


	@Override
	public void clipboardChanged() {
		if (Clipboard.getInstance().isEmpty()) {
			paste.setDisable(true);
		}
		else {
			paste.setDisable(false);

		}
		
	}
	
	public void group() {
		ClipGroup group = new ClipGroup();
		group.addAllClip(selection.getContents());
		board.removeClip(selection.getContents());
		selection.clear();
		board.addClip(group);
		selection.getContents().add(group);
		draw();
	}
	
	public void unGroup() {
		for (Clip c: selection.getContents()){
			if (c instanceof ClipGroup) {
				ClipGroup group = (ClipGroup) c;
				board.addClip(group.getClips());
				board.removeClip(group);
			}
		}
		selection.clear();
		draw();
	}

}
