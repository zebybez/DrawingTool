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
public class Spline extends DrawingItem{
    private final Point[] points;
    private int weight;
    private int degree;
    
    //constructor
    public Spline(Point[] points, int weight, int degree, Color color, Point anchor){
        super(anchor, color);
        this.weight = weight;
        this.degree = degree;
        this.points = points.clone();
    }

    //getters en setters

    public Point[] getPoints() {
        return points.clone();
    }

    public int getWeight() {
        return weight;
    }

    public int getDegree() {
        return degree;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }
    
    //methods
    
    @Override
    public String toString(){
        String pointString;
        pointString = "";
        for(Point p : points){
            String toAdd = p.toString();
            pointString = pointString.concat(toAdd);
        }
        return "Spline, degree: " + Integer.toString(degree) + "weight: " + Integer.toString(weight) + ", points: " + pointString;
    }
    
    @Override
    public void paint(Paintable paintable){
        //paint a spline using only lines in a foreach loop
        //throw new NotImplementedException();
        //paintable.paintLine(from, to, weight);
        
        for(int i = 0; i<points.length-1; i++){
            paintable.paintLine(points[i], points[i+1], weight);
        }
    }
   
    
}
