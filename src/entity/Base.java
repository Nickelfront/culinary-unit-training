/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import DB.BaseDBConnector;
import DB.BaseDBDriver;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bozhidar
 */
public abstract class Base {
    protected BaseDBDriver dbDriver;
    
    protected String tableName;
    
    protected Map<String, String> fields = new HashMap<>();
    
    Base(){
        dbDriver = BaseDBConnector.getInstance();
        configure();
    }
    
    abstract protected void configure();
    
    public Base save(){
        String query = "INSERT INTO " + tableName+ "( ";
        for (Map.Entry<String, String> entry : fields.entrySet()) {
           query += entry.getValue()+",";
        }
        
        query = query.substring(0, query.length()-1) + ") VALUES (";
        
        for (Map.Entry<String, String> entry : fields.entrySet()) {
           String methodName = getMethodName(entry.getKey());
           String value  = "";
            try {
               Method method = this.getClass().getMethod(methodName);
               value = method.invoke(this).toString();
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
            }
           query += value+",";
        }
        
        query = query.substring(0, query.length()-1) + ")";

            dbDriver.save(query);
        
        return this;
    }
    
    private String getMethodName(String name){
        return "get"+ Character.toUpperCase(name.charAt(0))+name.substring(1);
    }
    
}
