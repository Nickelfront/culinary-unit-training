/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import entity.Base;
import java.util.List;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Pair;

/**
 *
 * @author Novvai
 */
public class TableFactory {

    /**
     * Configurate list of rows
     * 
     * @param values
     */
    public static void configurateAll(List<Pair<TableColumn, String>> values){
        values.forEach((value) -> {
            value.getKey().setCellValueFactory(new PropertyValueFactory<>(value.getValue()));
        });
    }
    
    /**
     * Configurate single row
     * 
     * @param tableId
     * @param classProperty
     */
    public static void configureRow(TableColumn tableId, String classProperty){
       tableId.setCellValueFactory(new PropertyValueFactory<>(classProperty));
    }
    /**
     * 
     * @param table
     * @param rows 
     */
    public static void fill(TableView table, List rows){
        for (Object cl : rows) {
            table.getItems().add(cl);
        }
    };
    /**
     * 
     * @param table
     * @param rows 
     */
    public static void fillComboBox(ComboBox box, List rows){
        for (Object cl : rows) {
            box.getItems().add(cl);
        }
    };
}
