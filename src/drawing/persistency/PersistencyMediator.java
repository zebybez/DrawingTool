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
public interface PersistencyMediator {
    public Drawing load(String drawingName);
    public boolean save(Drawing drawing);
    public boolean init(Properties props); 
}
