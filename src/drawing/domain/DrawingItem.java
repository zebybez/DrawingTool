/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.domain;

import java.awt.*;
import java.io.Serializable;

/**
 *
 * @author yannic
 */
public abstract class DrawingItem implements Comparable<DrawingItem>, Serializable{
    private Point anchor;
    private Color color;
    private DrawingItem previousState;

    //constructor
    
    public DrawingItem(Point anchor, Color color){
        this.anchor = anchor;
        this.color = color;
    }
    
    //getters en setters
    
    public Point getAnchor() {
        return anchor;
    }
    public Color getColor() {
        return color;
    }
    public void setAnchor(Point anchor) {
        this.anchor = anchor;
    }
    public void setColor(Color color) {
        this.color = color;
    }
    public DrawingItem getPreviousState() {
        return previousState;
    }
    public void setPreviousState(DrawingItem value){
        previousState = value;
    }

    //methods

    /**
     * returns the distance of the anchor of this drawingItem to the origin (0, 0)
     * @return
     */
    public double getDistance(){
        Point o = new Point();
        Point p = anchor;
        double distance = Math.sqrt(Math.pow((p.y - o.y), 2)+Math.pow((p.x - o.x), 2));
        return distance;
    }
    @Override
    public int compareTo(DrawingItem o) {
        int comp = Double.compare(getDistance(), o.getDistance());
        return comp;
    }
    public abstract void paint(Paintable paintable);
    public abstract boolean isInBounds(Point point); //TODO: implement method in all subclasses;

    public boolean overlaps(DrawingItem item){
        //check using new method isInBounds whether or not this and the item overlaps
        //TODO: implement method;
        return false;
    }
}
