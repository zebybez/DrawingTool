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
public class Polygon extends DrawingItem{
    private final Point[] vertices;
    private int weight;

    //constructor
    
    public Polygon(int weight, Point[] vertices, Color color, Point anchor){
        super(anchor, color);
        this.weight = weight;
        this.vertices = vertices.clone();
    }
    
    //getters en setters
    
    public Point[] getVertices() {
        return vertices.clone();
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
    
    //methods
    
    @Override
    public String toString(){
        String pointstring;
        pointstring = "";
        for(Point p : vertices){
            String toAdd = p.toString();
            pointstring = pointstring.concat(toAdd);
        }
        return "Polygon, weight: " + weight + ", points: " + pointstring;
    }
    
    @Override
    public void paint(Paintable paintable){
        //paint a polygon using only lines in a foreach loop.
        //paintable.paintLine(from, to, weight);
        for(int i = 0; i<vertices.length; i++){
            paintable.paintLine(vertices[i], vertices[(i+1)%vertices.length], weight);
        }

    }
}
