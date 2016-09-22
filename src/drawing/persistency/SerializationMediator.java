/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.persistency;

import drawing.domain.Drawing;

import java.io.*;
import java.util.Properties;

/**
 *
 * @author yannic
 */
public class SerializationMediator implements PersistenceMediator {

    private Properties props;
    private String location;
    private String name;

    public Properties getProps() {
        return props;
    }

    @Override
    public Drawing load(String drawingName){
        //load from file and assemble to drawing using objectInputStream
        //take the files location from the properties
        Drawing drawing = null;
        try  {
            FileInputStream fileIn = new FileInputStream(location + "/" + name + ".ser");
            ObjectInputStream objIn = new ObjectInputStream(fileIn);
            drawing = (Drawing)objIn.readObject();
        }
        catch (IOException x){
            System.out.println("something went wrong while reading the file");
            x.printStackTrace();
        }
        catch (ClassNotFoundException e){
            System.out.println("could not convert file to drawing");
            e.printStackTrace();
        }


        return drawing;
    }
    @Override
    public boolean save(Drawing drawing){
        //save drawing to file using objectOutputStream
        //save the file to the location key in properties

        boolean success;
        try {
            FileOutputStream fileOut = new FileOutputStream(location+"/"+name+".ser");
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);
            objOut.writeObject(drawing);
            success = true;
        } catch (IOException e) {
            System.out.println("something went wrong while writing the file");
            System.err.println(e.getMessage());
            e.printStackTrace();
            success = false;
        }

        return success;
    }
    
    @Override
    public boolean init(Properties props){
        this.props = props;
        location = props.getProperty("location");
        name = props.getProperty("name");
        return false;
    }
}
