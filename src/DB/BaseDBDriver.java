/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.sql.ResultSet;

/**
 *
 * @author bozhidar
 */
public interface BaseDBDriver {
    BaseDBDriver execute(String query);
    ResultSet getAllFrom(String tableName);
    ResultSet executeQuery(String query);
}
