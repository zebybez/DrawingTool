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
public class Oval extends DrawingItem{
    private double width;
    private double height;

    //contructor
    
    public Oval(double width, double height, Color color, Point anchor){
        super(anchor, color);
        this.width = width;
        this.height = height;
    }
    
    //getters en setters
    
    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public void setWidth(double width) {
        this.width = width;
        this.setPreviousState(new Oval(this.width, this.height, this.getColor(), this.getAnchor()));
    }

    public void setHeight(double height) {
        this.height = height;
    }
    
    //methods
    
    @Override
    public String toString(){
        return "Oval, width: " + Double.toString(width) +", height: " + Double.toString(height);
    }
    
    @Override
    public void paint(Paintable paintable){

        paintable.paintOval(this);
        paintBoundingBoxForTest(paintable);
    }

    @Override
    public Point[] getBoundingBox() {
        Point[] box = new Point[2];
        box[0] = getAnchor();
        box[1] = new Point((int)(getAnchor().getX() + width), (int)( getAnchor().getY() + height));
        return box;
    }


}
