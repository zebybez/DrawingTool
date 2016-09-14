/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.domain;

import drawing.javafx.JavaFXPaintable;
import java.util.*;

/**
 *
 * @author yannic
 */
public class Drawing {
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

    void paint(Paintable paintable) {
        for(DrawingItem i : drawingItems){
            i.paint(paintable);
        }
    }
}
