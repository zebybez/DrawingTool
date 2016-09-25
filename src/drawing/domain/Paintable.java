/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.domain;

import drawing.domain.Image;
import drawing.domain.Oval;
import drawing.domain.PaintedText;

import java.awt.Color;
import java.awt.Point;
import java.io.File;


/**
 *
 * @author yannic
 */
public interface Paintable {
    
    void setColor(Color color);
    void paintOval(Oval oval);
    void paintLine(Point from, Point to, int weight);
    void paintText(PaintedText text);
    void paintImage(Image image);
    void clear();
    
}
