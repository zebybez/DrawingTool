/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.domain;

import drawing.javafx.JavaFXPaintable;
import drawing.persistency.DatabaseMediator;
import drawing.persistency.PersistenceMediator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import drawing.persistency.SerializationMediator;
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
       
    private PersistenceMediator pm;
    private Drawing drawing;
    private Properties props;
    private GraphicsContext gc;
    private JavaFXPaintable paintable;

    public DrawingTool() {
        props = new Properties();
        FileInputStream fileIn = null;
        try{
            fileIn = new FileInputStream("persisency.properties");
            props.load(fileIn);

        }catch (IOException x){
            System.out.println("kon properties niet vinden, zet de opties onder \"save to\"");
            x.printStackTrace();
        }finally {
            if(fileIn != null){
                try{
                    fileIn.close();
                }
                catch (IOException x){
                    x.printStackTrace();
                }
            }
        }

        //TODO: test of properties uit file worden gelezen;
        //todo: implementeer het oplsaan van properties na het setten ervan;
    }

    @Override
    public void start(Stage primaryStage) {
        
        drawing = new Drawing("test", 500, 500);
        Canvas canvas = new Canvas(drawing.getWidth(), drawing.getHeight());
        
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
        
        saveAction = event -> save();
        setSerialAction = event -> setSerializer();
        setDatabaseAction = event -> setDatabaser();
        loadAction = event -> load();
        clearAction = event -> clear();
        
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
        
        drawing.addTestItems();
        gc = canvas.getGraphicsContext2D();
        paintable = new JavaFXPaintable(gc);
        draw();
    }

    private void setDatabaser() {
        //todo test method
        Properties tmpProps = new Properties();
        tmpProps.setProperty("URL", "127.0.0.1");
        tmpProps.setProperty("user", "student");
        tmpProps.setProperty("pass", "student");
        PropertiesDialog propDialog = new PropertiesDialog("set folder options", tmpProps);
        Optional<Properties> tProps = propDialog.show();
        if (tProps.isPresent()) {
            props.clear();
            props.putAll(tProps.get());
            pm = new DatabaseMediator();
            pm.init(props);
            System.out.println("set database");
        } else {
            System.out.println("cancelled");
        }
    }

    private void setSerializer() {

        Properties tmpProps = new Properties();
        tmpProps.setProperty("location", "./drawing/images");
        PropertiesDialog propDialog = new PropertiesDialog("set folder options", tmpProps);
        Optional<Properties> tProps = propDialog.show();
        if (tProps.isPresent()) {
            props.clear();
            props.putAll(tProps.get());
            pm = new SerializationMediator();
            pm.init(props);
            System.out.println("set folder!");
        } else {
            System.out.println("cancelled");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);


    }
    
    public void draw(){
        drawing.paint(paintable);
    }
    
    public void save() {

        TextInputDialog textDialog = new TextInputDialog();
        Optional<String> result = textDialog.showAndWait();
        String s;
        boolean success;

        if(result.isPresent()){
            s = result.get();
            props.setProperty("name", s);
            pm.init(props);
            success = pm.save(this.drawing);
        }
        else{
            success = false;
        }
        if(success){
            System.out.println("Saving successful!");
        }
        else{
            System.out.println("something went wrong with saving the drawing");
        }
    }
    // todo: implement quicksave;
    
    public void load(){


        TextInputDialog textDialog = new TextInputDialog();
        Optional<String> result = textDialog.showAndWait();
        Drawing loaded = null;
        if(result.isPresent() && result.get() != null){
            loaded = pm.load(result.get());
            if(loaded != null){
                drawing = loaded;
                draw();
            }
            else{
                System.out.println("something went wrong with loading the drawing");
            }
        }
        else {
            System.out.println("please enter name of drawing to load");
        }

    }

    private void clear(){
        drawing.clear(paintable);
    }
}
