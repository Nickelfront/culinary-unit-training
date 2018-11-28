/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Novvai
 */
public class BaseDBConnector {
    private final String dbName = "uber_cheff.db";
    protected Connection db;

    /**
     * @throws SQLException
     */
    public BaseDBConnector() throws SQLException {
        this.connect();
    }
    
    /**
     * Make initial connection to the internal DB
     * @throws java.sql.SQLException
     */
    protected final void connect() throws SQLException{
            String url = "jdbc:sqlite:"+dbName;
            db = DriverManager.getConnection(url);
            System.out.println("Connection to "+dbName+" has been established!");
    }
    
}
