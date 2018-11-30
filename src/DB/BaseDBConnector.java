/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Novvai
 */
public class BaseDBConnector implements BaseDBDriver {

    private final String dbName = "uber_cheff.db";
    protected Connection db;

    static private BaseDBConnector instance;

    /**
     * @throws SQLException
     */
    private BaseDBConnector() throws SQLException {
        this.connect();
    }

    static public BaseDBConnector getInstance() {
        if (instance == null) {
            try {
                instance = new BaseDBConnector();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return instance;
    }

    /**
     * Make initial connection to the internal DB
     *
     * @throws java.sql.SQLException
     */
    protected final void connect() throws SQLException {
        String url = "jdbc:sqlite:" + dbName;
        db = DriverManager.getConnection(url);
        System.out.println("Connection to " + dbName + " has been established!");
        
        initialize();
    }
    
    private void initialize() throws SQLException {
        Statement script = db.createStatement();
        script.execute("CREATE TABLE IF NOT EXISTS clients("
                + "id integer,"
                + "first_name varchar(255),"
                + "last_name varchar(255),"
                + "email varchar(255),"
                + "phone varchar(32),"
                + "birth_date text,"
                + " primary key(id)"
                + ");");
  
             System.out.println(tableExists("clients"));
    }
    public boolean tableExists(String tableName){
        try{
            DatabaseMetaData md = db.getMetaData();
            ResultSet rs = md.getTables(null, null, tableName, null);
            rs.next();
            return rs.getRow() > 0;
        }catch(SQLException ex){
            ex.printStackTrace();

        }
        return false;
    }

    @Override
    public BaseDBDriver save(String query) {
        try {
            Statement statement = db.createStatement();
            statement.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this;
    }
    
    
    @Override
    public ResultSet getAllFrom(String table) {
        try {
            Statement statement = db.createStatement();
            return statement.executeQuery("SELECT * FROM "+ table);
        } catch (SQLException ex) {
            Logger.getLogger(BaseDBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
}
