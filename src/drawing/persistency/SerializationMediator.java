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
public class SerializationMediator implements PersistencyMediator{

    Properties props;

    @Override
    public Drawing load(String drawingName){
        //load from file and assemble to drawing using objectInputStream
        //take the files location from the properties
        Drawing drawing = new Drawing("drawingName", 0, 0);
        return drawing;
    }
    @Override
    public boolean save(Drawing drawing){
        //save drawing to file using objectoutputstream
        //save the file to the location key in properties
        return false;
    }
    
    @Override
    public boolean init(Properties props){
        this.props = props;
        return false;
    }
}
