/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.domain;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.io.File;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @author yannic
 */
public class Drawing implements Serializable{
    private String name;
    private int width;
    private int height;
    private final ArrayList<DrawingItem> drawingItems; 
    
    //constructor
    
    public Drawing(String name, int width, int height){
        this.name = name;
        this.width = width;
        this.height = height;
        drawingItems = new ArrayList<>();
        
    }
    
    //getters en setters
    
    public String getName(){
        return name;
    }
    public void setName(String value){
        name = value;
    }
    
    public int getWidth(){
        return width;
    }
    public void setWidth(int value){
        width = value;
    }
    
    public int getHeight(){
        return height;
    }
    public void setHeight(int value){
        height = value;
    }
    
    //methods
    
    public ArrayList<DrawingItem> getDrawingItems(){
        
        ArrayList<DrawingItem> templist = new ArrayList<>(drawingItems);
        Collections.sort(templist);

        return templist;
    }
    
    public void addItem(DrawingItem item){
        drawingItems.add(item);
    }
    
    public void replaceItem(DrawingItem replaceThis, DrawingItem item ){
        drawingItems.remove(replaceThis);
        item.setPreviousState(replaceThis);
        drawingItems.add(item);
    }
    
    public void undoItem(DrawingItem item){
        drawingItems.remove(item);
        if(item.getPreviousState() != null){
            drawingItems.add(item.getPreviousState());
        }
            
        
    }

    public void paint(Paintable paintable) {
        for(DrawingItem i : drawingItems){
            i.paint(paintable);
        }
    }

    public void clear(Paintable paintable){
        paintable.clear();
    }
    
    public void addTestItems(){
        addItem(new Image(new File("file:///C:/Users/yannic/Pictures/johnny-test.jpg"), 0, Color.yellow, new Point()));
        addItem(new Oval(getWidth(), getHeight(), Color.yellow, new Point()));
        addItem(new Oval(200, 400, Color.red, new Point(30, 40)));
        addItem(new Polygon(5, new Point[]{new Point(4,5), new Point(16,23), new Point(36, 100)}, Color.green, new Point(9,8)));
        addItem(new Spline(new Point[]{new Point(50, 50), new Point(33, 44), new Point(100, 56)}, 0, 0, Color.black, new Point(70, 88)));
        addItem(new PaintedText("this is painted text", new Font("myfont", 4, 16), Color.orange, new Point(20, 90)));
        Point[] testPoly = new Point[]{new Point(427,120),new Point(334,399),new Point(265,232),new Point(426,192),new Point(47,302)};
        addItem(new Polygon(5, testPoly, Color.green, new Point(0,0)));


    }
}
