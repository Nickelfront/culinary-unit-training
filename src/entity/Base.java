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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableArray;

/**
 *
 * @author bozhidar
 */
public abstract class Base {

    protected BaseDBDriver dbDriver;

    protected String tableName;

    protected Map<String, String> fields = new HashMap<>();

    Base() {
        dbDriver = BaseDBConnector.getInstance();
        configure();
    }

    abstract protected void configure();

    public Base save() {
        String query = "INSERT INTO " + tableName + " ( ";
        for (Map.Entry<String, String> entry : fields.entrySet()) {
            String field = entry.getValue().split(":")[1];
            query += field + ",";
        }

        query = query.substring(0, query.length() - 1) + ") VALUES (";

        for (Map.Entry<String, String> entry : fields.entrySet()) {
            String methodName = getMethodName(entry.getKey());
            String value = "";
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
            query += "'" + value + "',";
        }

        query = query.substring(0, query.length() - 1) + ")";

        dbDriver.save(query);

        return this;
    }

    protected String getMethodName(String name) {
        return "get" + Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }

    protected String getSetMethodName(String name) {
        return "set" + Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }

    public List<Base> all() {
        ResultSet res = dbDriver.getAllFrom(tableName);
        List<Base> result= new ArrayList<Base>();
        // Instantiated object class name
        String className = this.getClass().getName();
        
        Class responseClass = res.getClass();

        try {
            Class cls = Class.forName(className);
            Base classInstance;
            Method[] arrayOfModelMethods = this.getClass().getMethods();
            while (res.next()) {
                classInstance = (Base) cls.newInstance();
                for (Map.Entry<String, String> entry : fields.entrySet()) {
                    String setterMethod = getSetMethodName(entry.getKey());
                    String[] field = entry.getValue().split(":");
                    String getDBResMethod = getMethodName(field[0]);
                    String dbFieldName = field[1];
                    Method modelClassMethod = null;
                    for (int i = 0; i < arrayOfModelMethods.length; i++) {
                        if (arrayOfModelMethods[i].getName().equals(setterMethod)) {
                            modelClassMethod = arrayOfModelMethods[i];
                            break;
                        }
                    }
                    
                    Method responseClassMethod = responseClass.getMethod(getDBResMethod, String.class);
                    if (modelClassMethod != null) {
                         modelClassMethod.invoke(classInstance,responseClassMethod.invoke(res, dbFieldName));
                    }
                }
                
                result.add(classInstance);  
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvocationTargetException ex) {
            Logger.getLogger(Base.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return result;
    }
}
