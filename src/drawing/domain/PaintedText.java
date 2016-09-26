/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.domain;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

/**
 *
 * @author yannic
 */
public class PaintedText extends DrawingItem{
    private String content;
    private Font font;
    
    //constructor
    public PaintedText(String content, Font font, Color color, Point anchor){
        super(anchor, color);
        this.content = content;
        this.font = font;
    }
    
    //getters en setters

    public String getContent() {
        return content;
    }

    public Font getFont() {
        return font;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFont(Font font) {
        this.font = font;
    }
    
    //methods
    
    @Override
    public String toString(){
        return "Text, content: "+ content+ ", font: "+ font;
    }
    
    @Override
    public void paint(Paintable paintable){
        paintable.paintText(this);
        paintBoundingBoxForTest(paintable);
    }

    @Override
    public Point[] getBoundingBox() {

        AffineTransform affTrans = font.getTransform();
        FontRenderContext frc = new FontRenderContext(affTrans, true, true);
        Rectangle2D stringBounds = font.getStringBounds(content, frc);
        int width = (int) stringBounds.getWidth();
        int height = (int) stringBounds.getHeight();

        Point p1 = new Point(getAnchor().x, getAnchor().y - height);
        Point p2 = new Point(p1.x + width, p1.y + height);

        return new Point[]{p1,p2};
    }


}
