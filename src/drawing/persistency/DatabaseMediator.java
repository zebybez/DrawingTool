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
public class DatabaseMediator implements PersistenceMediator {

    private Properties props;
    @Override
    public Drawing load(String drawingName){
        //load from database and assemble to drawing using objectInputStream
        //TODO: implement load
        Drawing drawing = new Drawing("drawingName", 0, 0);
        return drawing;
    }
    @Override
    public boolean save(Drawing drawing){
        //save drawing to database using objectoutputstream
        //TODO: implement save
        return false;
    }
    
    @Override
    public boolean init(Properties props){
        this.props = props;
        return false;
    }
}
