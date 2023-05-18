package DataAcces;


import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import Connection.ConnectionFactory;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 * @Source http://www.java-blog.com/mapping-javaobjects-database-reflection-generics
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    public List<T> findAll() {
        // TODO:
        return null;
    }

    public T findById(int id) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void insert(T t) {
        // TODO:
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO ");
        Class c;
        try {
            c = Class.forName("Model." + t.getClass().getSimpleName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        query.append(c.getSimpleName().toLowerCase());
        query.append(" (");
        int i=0;
        for (Field f : t.getClass().getDeclaredFields())
        {
            query.append(f.getName());
            if(t.getClass().getDeclaredFields().length!=i+1)
            query.append(", ");
            i++;
        }
        query.append(") VALUES ");
        StringBuilder cName=new StringBuilder();
        cName.append("DataAcces.");
        cName.append(c.getSimpleName());
        cName.append("DAO");
        Class cc;
        try {
            cc = Class.forName(cName.toString());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try{
            Class [] attr=new Class[1];
            attr[0]=t.getClass();
            StringBuilder meth=new StringBuilder();
            meth.append("add");
            meth.append(t.getClass().getSimpleName());
            String nameMeth= String.valueOf(meth);
            query.append(cc.getMethod(nameMeth,attr).invoke(cc.getDeclaredConstructor().newInstance(),t));
        } catch (InvocationTargetException | InstantiationException | NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        System.out.println(query.toString());
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.executeUpdate();

           // return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
           // ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

     //   return t;
    }

    public void delete(T t) {
        // TODO:
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM ");
        Class c;
        try {
            c = Class.forName("Model." + t.getClass().getSimpleName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        query.append(c.getSimpleName().toLowerCase());
        if(t.getClass().getSimpleName().equals("Product"))
            query.append(" WHERE idproduct=");
        else if(t.getClass().getSimpleName().equals("Orders"))
            query.append(" WHERE idorders=");
        else   query.append(" WHERE id=");

        StringBuilder cName=new StringBuilder();
        cName.append("DataAcces.");
        cName.append(c.getSimpleName());
        cName.append("DAO");
        Class cc;
        try {
            cc = Class.forName(cName.toString());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try{
            Class [] attr=new Class[1];
            attr[0]=t.getClass();
            StringBuilder meth=new StringBuilder();
            meth.append("id");
            meth.append(t.getClass().getSimpleName());
            String nameMeth= String.valueOf(meth);
            query.append(cc.getMethod(nameMeth,attr).invoke(cc.getDeclaredConstructor().newInstance(),t));
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException | InstantiationException e) {
            throw new RuntimeException(e);
        }
        System.out.println(query.toString());
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.executeUpdate();

            // return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally {
            // ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }

        //   return t;
    }

    public T update(T t) {
        // TODO:
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        StringBuilder query = new StringBuilder();
        query.append("UPDATE ");
        Class c;
        try {
            c = Class.forName("Model." + t.getClass().getSimpleName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        query.append(c.getSimpleName().toLowerCase());
        query.append(" SET ");

        StringBuilder cName=new StringBuilder();
        cName.append("DataAcces.");
        cName.append(c.getSimpleName());
        cName.append("DAO");
        Class cc;
        try {
            cc = Class.forName(cName.toString());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try{
            Class [] attr=new Class[1];
            attr[0]=t.getClass();
            StringBuilder meth=new StringBuilder();
            meth.append("update");
            meth.append(t.getClass().getSimpleName());
            String nameMeth= String.valueOf(meth);
            query.append(cc.getMethod(nameMeth,attr).invoke(cc.getDeclaredConstructor().newInstance(),t));
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException | InstantiationException e) {
            throw new RuntimeException(e);
        }
        if(t.getClass().getSimpleName().equals("Product"))
            query.append(" WHERE idproduct=");
        else if(t.getClass().getSimpleName().equals("Orders"))
            query.append(" WHERE idorders=");
        else   query.append(" WHERE id=");
        try{
            Class [] attr=new Class[1];
            attr[0]=t.getClass();
            StringBuilder meth=new StringBuilder();
            meth.append("id");
            meth.append(t.getClass().getSimpleName());
            String nameMeth= String.valueOf(meth);
            query.append(cc.getMethod(nameMeth,attr).invoke(cc.getDeclaredConstructor().newInstance(),t));
        } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException | InstantiationException e) {
            throw new RuntimeException(e);
        }
        System.out.println(query.toString());
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.executeUpdate();

            // return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally {
            // ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;
    }
}
