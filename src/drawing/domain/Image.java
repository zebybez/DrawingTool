/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.domain;

import java.awt.*;
import java.io.*;

/**
 *
 * @author yannic
 */
public class Image extends DrawingItem{
    private File file;
    private double size;
    
    //constructor
    public Image(File file, double size, Color color, Point anchor){
        super(anchor, color);
        this.file = file;
        this.size = size;
    }
    
    //getters en setters

    public File getFile() {
        return file;
    }

    public double getSize() {
        return size;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public void setSize(double size) {
        this.size = size;
    }
    
    //methods
    
    @Override
    public String toString(){
        return "Image, size: " + Double.toString(size);
    }
    
    @Override
    public void paint(Paintable paintable){
        paintable.paintImage(this);
    }
    
}
