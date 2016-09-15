/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.domain;

import drawing.javafx.JavaFXPaintable;
import drawing.persistency.DatabaseMediator;
import drawing.persistency.PersistencyMediator;
import java.awt.*;
import java.io.*;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author yannic
 */
public class DrawingTool extends Application{
       
    private PersistencyMediator pm;  
    private Drawing drawing;
    private Canvas canvas;
    @Override
    public void start(Stage primaryStage) {
        
        drawing = new Drawing("test", 500, 500);
        canvas = new Canvas(drawing.getWidth(), drawing.getHeight());
        
        StackPane root = new StackPane();
        
        
        root.getChildren().add(canvas);
        
        Scene scene = new Scene(root, 500, 500);
        
        primaryStage.setTitle("Drawing tooltje");
        primaryStage.setScene(scene);
        primaryStage.show(); 
        
        drawing.addItem(new Image(new File("file:///C:/Users/yannic/Pictures/johnny-test.jpg"), 0, Color.yellow, new Point()));
        drawing.addItem(new Oval(drawing.getWidth(), drawing.getHeight(), Color.yellow, new Point()));
        drawing.addItem(new Oval(200, 400, Color.red, new Point(30, 40)));
        drawing.addItem(new Polygon(5, new Point[]{new Point(4,5), new Point(16,23), new Point(36, 100)}, Color.green, new Point(9,8)));
        drawing.addItem(new Spline(new Point[]{new Point(50, 50), new Point(33, 44), new Point(100, 56)}, 0, 0, Color.black, new Point(70, 88)));
        drawing.addItem(new PaintedText("this is painted text", new Font("myfont", 4, 16), Color.orange, new Point(20, 90)));

        
        draw();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        //drawing.addItem(new Image(new File(""), 12.34, Color.red, new Point(90, 20)));
        
        launch(args);

       
    }
    
    public void draw(){
        GraphicsContext gc;
        gc = canvas.getGraphicsContext2D();
        JavaFXPaintable paintable = new JavaFXPaintable(gc);
        drawing.paint(paintable);
    }
    
    public void save(){
        //if(foo.checked == true...
        pm.save(drawing);
        //else if(toCloud.checked == true...
        pm = new DatabaseMediator();
    }
    
    public Drawing load(String drawingName){
        Drawing loaded = pm.load(drawingName);
        return loaded;
    }
    
}
