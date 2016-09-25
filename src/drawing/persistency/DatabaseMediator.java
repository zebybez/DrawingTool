/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawing.persistency;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.MySQLConnection;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import drawing.domain.Drawing;

import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 *
 * @author yannic
 */
public class DatabaseMediator implements PersistenceMediator {

    private Properties props;
    private MysqlDataSource dataSource;
    private java.sql.Connection conn;
    @Override
    public Drawing load(String drawingName){
        //load from database and assemble to drawing using objectInputStream
        //TODO: implement load
        Drawing drawing = null;
        String sql = "SELECT drawing FROM drawingtool.drawings WHERE name = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, drawingName);

            ResultSet result = statement.executeQuery();
            while (result.next()){
                InputStream binIn = result.getBinaryStream("drawing");
                ObjectInputStream objIn = new ObjectInputStream(binIn);
                drawing = (Drawing) objIn.readObject();
            }


        } catch (SQLException e) {
            System.out.println("something went wrong with querying the database");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("something went wrong with reading your drawing");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("something went wrong with converting your drawing");
            e.printStackTrace();
        }

        return drawing;
    }
    @Override
    public boolean save(Drawing drawing){

        boolean success;
        String sql = "INSERT INTO drawingtool.drawings (name, drawingBinary) VALUES (?, ?)";

        if(!connect()) return false;

        try {
            //Create byteinputstream out of drawing for upload to the database
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream objOut = new ObjectOutputStream(byteOut);
            objOut.writeObject(drawing);
            byte[] binDrawing= byteOut.toByteArray();
            ByteArrayInputStream byteIn = new ByteArrayInputStream(binDrawing);

            //Create the statement
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, props.getProperty("name"));
            statement.setBinaryStream(2, byteIn, binDrawing.length);
            statement.execute();

            success = true;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("something has gone wrong with querrying the database");
            success = false;
        }
        catch (IOException x){
            x.printStackTrace();
            System.out.println("something has gone wrong with encoding your drawing");
            success = false;
        }
        finally {
            disconnect();
        }
        //TODO: test save
        return success;
    }
    
    @Override
    public boolean init(Properties props){
        //TODO: test connection with db
        System.out.println("initializing database");
        boolean success;
        dataSource = new MysqlDataSource();
        dataSource.setUser(props.getProperty("user"));
        dataSource.setPassword(props.getProperty("pass"));
        String url = "jdbc:mysql://"+props.getProperty("URL")+"/drawingtool";
        dataSource.setURL(url);


        if(connect()){
            System.out.println("test connection successful");
            this.props = props;
            success = true;
        }
        else {
            System.out.println("failed to create datasource");
            success = false;
        }
        disconnect();
        return success;
    }
    private boolean connect(){
        boolean success;
        try{
            conn = dataSource.getConnection();
            success = true;
        }
        catch (SQLException x){
            System.out.println("something went wrong with connecting to the database");
            x.printStackTrace();
            success = false;
        }
        return success;
    }
    private void disconnect(){
        try{
            conn.close();
        }
        catch (SQLException x){
            System.out.println("could not disconnect from db");
            x.printStackTrace();
        }
    }
}
