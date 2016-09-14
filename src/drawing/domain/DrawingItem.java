/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.domain;

import java.awt.*;

/**
 *
 * @author yannic
 */
public class DrawingItem implements Comparable<DrawingItem>{
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

    public void paint(Paintable paintable) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
