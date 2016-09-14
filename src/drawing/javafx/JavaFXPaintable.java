/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.javafx;

import drawing.domain.*;
import java.awt.Color;
import java.awt.Point;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author yannic
 */
public class JavaFXPaintable implements Paintable{
    
    private GraphicsContext gc;

    public GraphicsContext getGc() {
        return gc;
    }
    public JavaFXPaintable(GraphicsContext gc){
        this.gc = gc;
    }
    
    @Override
    public void setColor(Color color){
        gc.setStroke(convertToPaint(color));
        gc.setFill(convertToPaint(color));
        
    }
    @Override
    public void paintOval(Oval oval){
        setColor(oval.getColor());
        gc.strokeOval(oval.getAnchor().x, oval.getAnchor().y, oval.getWidth(), oval.getHeight());
        
    }
    @Override
    public void paintLine(Point from, Point to, int weight){
        gc.strokeLine(from.x, from.y, to.x, to.y);
    }
    @Override
    public void paintText(PaintedText text){
        setColor(text.getColor());
        gc.setFont(new javafx.scene.text.Font(text.getFont().getFontName(), text.getFont().getSize()));
        gc.strokeText(text.getContent(), text.getAnchor().x, text.getAnchor().y);
        
    }
    @Override
    public void paintImage(Image image){
        gc.drawImage(new javafx.scene.image.Image(image.getFile().getPath()), image.getAnchor().x, image.getAnchor().y);
    }
    @Override
    public void clear(){
        //not done
        double w = gc.getCanvas().getWidth();
        double h = gc.getCanvas().getHeight();
        gc.clearRect(0, 0, w, h);
        
    }

    private javafx.scene.paint.Color convertToPaint(Color color) {
        double red = color.getRed();
        double green = color.getGreen();
        double blue = color.getBlue();
        
        javafx.scene.paint.Color returnColor = javafx.scene.paint.Color.color(red/255.0, green/255.0, blue/255.0);
        return returnColor;
    }
}
