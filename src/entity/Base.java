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
    protected Map<String, String> relationships = new HashMap<>();

    Base() {
        dbDriver = BaseDBConnector.getInstance();
        configure();
    }

    abstract protected void configure();

    public Base delete() {
        Class model = this.getClass();
        String deleteQuery = "DELETE FROM " + tableName + " WHERE ";
        for (Map.Entry<String, String> entry : fields.entrySet()) {
            String[] splitedDBFieldName = entry.getValue().split(":");
            if (splitedDBFieldName.length >= 3 && splitedDBFieldName[2].equals("deletable")) {
                try {
                    Method getter = model.getMethod(getMethodName(entry.getKey()));
                    deleteQuery += " " + splitedDBFieldName[1] + "='" + getter.invoke(this) + "' AND";
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
            }
        }
        deleteQuery = deleteQuery.substring(0, deleteQuery.length() - 3);

        dbDriver.execute(deleteQuery);

        return this;
    }

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

        dbDriver.execute(query);

        return this;
    }

    protected String getMethodName(String name) {
        String[] nameSegments = name.split("_");
        String result = "get";

        for (int i = 0; i < nameSegments.length; i++) {
            result += Character.toUpperCase(nameSegments[i].charAt(0)) + nameSegments[i].substring(1);
        }

        return result;
    }

    protected String getSetMethodName(String name) {
        return "set" + Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }

    public List<Base> all() {
        return this.buildResponse(dbDriver.getAllFrom(tableName), this);
    }

    public List<Base> buildResponse(ResultSet res, Base instance) {
        List<Base> result = new ArrayList<Base>();
        // Instantiated object class name
        String className = instance.getClass().getName();

        Class responseClass = res.getClass();

        try {
            Class cls = Class.forName(className);
            Base classInstance;
            Method[] arrayOfModelMethods = instance.getClass().getMethods();

            while (res.next()) {
                classInstance = (Base) cls.newInstance();
                for (Map.Entry<String, String> entry : instance.fields.entrySet()) {
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
                        modelClassMethod.invoke(classInstance, responseClassMethod.invoke(res, dbFieldName));
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

    /**
     *
     * @param relationship
     * @param id
     * @throws Exception
     */
    public void attach(String relationship, int id) throws Exception {
        String relation = this.relationships.get(relationship);

        if (relation == null) {
            throw new Exception("Relationsipt is not speciefied");
        }
        String[] relationOptions = relation.split(":");

        Method idGetter = this.getClass().getMethod(getMethodName(relationOptions[0]));

        String relationCreator = "INSERT INTO " + relationOptions[2] + "("
                + relationOptions[0] + "," + relationOptions[1] + ") VALUES ( "
                + "'" + idGetter.invoke(this) + "','" + id + "');";

        dbDriver.execute(relationCreator);
    }

    public List<Base> belongsToMany(String relationship, Base relClass, String customIdentifier) throws Exception {
        
        String relation = this.relationships.get(relationship);

        if (relation == null) {
            throw new Exception("Relationsipt is not speciefied");
        }

        String[] relationOptions = relation.split(":");

        Method idGetter = this.getClass().getMethod(getMethodName(relationOptions[0]));

        String query = "SELECT * FROM " + relationOptions[2]
                + " INNER JOIN " + relClass.tableName
                + " ON " + relationOptions[2] + "." + relationOptions[1] + "=" + relClass.tableName + "."+customIdentifier
                + " WHERE "+relationOptions[0]+"="+idGetter.invoke(this);

        return this.buildResponse(dbDriver.executeQuery(query), relClass);
    }
    
    public List<Base> belongsToMany(String relationship, Base relClass) throws Exception {
        return this.belongsToMany(relationship, relClass, "id");
    }
}
