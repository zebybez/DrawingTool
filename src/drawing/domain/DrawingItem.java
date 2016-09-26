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

    public void paintBoundingBoxForTest(Paintable paintable){
        //todo: test this method in the subclasses;
        Point[] boxPoints = this.getBoundingBox();
        paintable.paintLine(boxPoints[0], boxPoints[1], 5);
    }

    /**
     * returns the top-left point and the bottom-right point of the smallest possible bounding box around this drawingItem as an Array of size 2
     * @return
     */
    public abstract Point[] getBoundingBox();//TODO: implement method in all subclasses;

    public boolean isInBounds(Point point){
        Point[] box = getBoundingBox();
        Point p1 = box[0];
        Point p2 = box[1];

        if(point.getX() >= p1.getX() && p2.getX() >= point.getX() && point.getY() >= p1.getY() && p2.getY() >= point.getY()){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean overlaps(DrawingItem item){
        //check using new method isInBounds whether or not this and the item overlaps
        //TODO: test method;
        //gets this boundingBox
        Point[] points = getBoundingBox();
        Point[] corners = new Point[4];
        corners[0] = points[0];
        corners[1] = new Point((int) points[0].getY(), (int) points[1].getX());
        corners[2] = points[1];
        corners[3] = new Point((int) points[1].getX(), (int) points[0].getY());

        //check if any of the corners are inside items boundingBox
        for (Point p: corners) {
            if(item.isInBounds(p)){
                return true;
            }
        }
        return false;
    }

    public int[] findExtremesPolygon(Point[] vertices){
        int sX = 0;
        int bX = 0;
        int sY = 0;
        int bY = 0;

        //find extremes
        for(Point p : vertices){
            if(p.x > bX){
                bX = p.x;
            }
            if(sX == 0 || p.x < sX){
                sX = p.x;
            }

            if(p.y > bY){
                bY = p.y;
            }
            if(sY == 0 || p.y < sY){
                sY = p.y;
            }
        }
        return new int[]{sX, sY ,bX, bY};
    }
}
