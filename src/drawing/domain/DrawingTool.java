/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.domain;

import drawing.javafx.JavaFXPaintable;
import drawing.persistency.PersistencyMediator;

import java.util.Properties;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author yannic
 */
public class DrawingTool extends Application{
       
    private PersistencyMediator pm;  
    private Drawing drawing;
    private Canvas canvas;
    private Properties props;

    public DrawingTool() {

        //this.props = readFromFile;
    }

    @Override
    public void start(Stage primaryStage) {
        
        drawing = new Drawing("test", 500, 500);
        canvas = new Canvas(drawing.getWidth(), drawing.getHeight());
        
        BorderPane root = new BorderPane();
        
        MenuBar menu = new MenuBar();
            Menu fileMenu = new Menu("file");
                MenuItem miSave = new MenuItem("save");
                MenuItem miLoad = new MenuItem("load");
                MenuItem miClear = new MenuItem("clear");
            fileMenu.getItems().addAll(miSave, miLoad, miClear);
            Menu mediatorMenu = new Menu("save to...");
                MenuItem miSetDatabase = new MenuItem("database settings");
                MenuItem miSetSerialize = new MenuItem("Serialize settings");
            mediatorMenu.getItems().addAll(miSetDatabase, miSetSerialize);
        menu.getMenus().addAll(fileMenu, mediatorMenu);
        //TODO: add edit menu


        //set events and add to the buttons

        EventHandler<ActionEvent> saveAction;
        EventHandler<ActionEvent> setSerialAction;
        EventHandler<ActionEvent> setDatabaseAction;
        EventHandler<ActionEvent> clearAction;            
        EventHandler<ActionEvent> loadAction;    
        
        saveAction = event -> System.out.println("save action");
        setSerialAction = event -> System.out.println("set serialize action");
        setDatabaseAction = event -> System.out.println("set database action");
        loadAction = event -> System.out.println("load action");
        clearAction = event -> System.out.println("clear action");
        
            miSave.setOnAction(saveAction);
            miLoad.setOnAction(loadAction);
            miClear.setOnAction(clearAction);
            miSetDatabase.setOnAction(setDatabaseAction);
            miSetSerialize.setOnAction(setSerialAction);
        
        root.setTop(menu);
        root.setBottom(canvas);
        
        Scene scene = new Scene(root, 500, 500+menu.getHeight());
        
        primaryStage.setTitle("Drawing tooltje");
        primaryStage.setScene(scene);
        primaryStage.show(); 
        
        drawing.drawTestItems();
        
        draw();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {


        launch(args);
        //todo: write properties to file
       
    }
    
    public void draw(){
        GraphicsContext gc;
        gc = canvas.getGraphicsContext2D();
        JavaFXPaintable paintable = new JavaFXPaintable(gc);
        drawing.paint(paintable);
    }
    
    public void save()
    {
        //TODO: implement save
        System.out.println("TODO: implement save");
    }
    
    public Drawing load(String drawingName){

        //TODO: implement load
        System.out.println("TODO: implement load");
        return null;
    }
    
}
