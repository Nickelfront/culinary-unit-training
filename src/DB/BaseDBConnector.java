/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.Connection;
import java.sql.DriverManager;
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
                + "id int Primary Key,"
                + "first_name varchar(255),"
                + "last_name varchar(255),"
                + "email varchar(255) unique,"
                + "phone varchar(32),"
                + "birth_date text"
                + ")");
    //    CREATE TABLE IF NOT EXISTS [schema_name].table_name (
//            column_1 data_type PRIMARY KEY,
//            column_2 data_type NOT NULL,
//            column_3 data_type DEFAULT 0,
//            table_constraint
//        )
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

}
