/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.persistency;

import drawing.domain.Drawing;
import java.util.Properties;

/**
 *
 * @author yannic
 */
public class DatabaseMediator implements PersistencyMediator{
    @Override
    public Drawing load(String drawingName){
        //load from database and assemble to drawing using objectInputStream
        Drawing drawing = new Drawing("drawingName", 0, 0);
        return drawing;
    }
    @Override
    public boolean save(Drawing drawing){
        //save drawing to database using objectoutputstream
        return false;
    }
    
    @Override
    public boolean init(Properties props){
        //i dont know what init has to do yet...........yea...
        return false;
    }
}
