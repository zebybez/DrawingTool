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
    }
    
    
}
