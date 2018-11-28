/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

/**
 *
 * @author bozhidar
 */
public interface BaseDBDriver {
    BaseDBDriver save(String query);
    //BaseDBDriver update();
    //BaseDBDriver find();
}
