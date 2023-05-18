package Connection;

import DataAcces.ClientDAO;
import Model.Client;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author: Technical University of Cluj-Napoca, Romania Distributed Systems
 *          Research Laboratory, http://dsrl.coned.utcluj.ro/
 * @Since: Apr 03, 2017
 * @Source: http://theopentutorials.com/tutorials/java/jdbc/jdbc-mysql-create-database-example/
 */
public class ConnectionFactory {
    public static void main(String[] args) throws SQLException {

        try {
           // Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.cj.jdbc.Driver");

            // connect way #1
//            String url1 = "jdbc:mysql://localhost:3306/assig3db";
//            String user = "root";
//            String password = "12mii2020";
//
//           Connection conn1 = DriverManager.getConnection(url1, user, password);
//            if (conn1 != null) {
//                System.out.println("Connected to the database test1");
//            }

            // connect way #2
//            String url2 = "jdbc:mysql://localhost:3306/assig3db?user=root&password=12mii2020";
//          Connection  conn2 = DriverManager.getConnection(url2);
//            if (conn2 != null) {
//                System.out.println("Connected to the database test2");
//            }

            // connect way #3
            String url3 = "jdbc:mysql://localhost:3306/assig3db";
            Properties info = new Properties();
            info.put("user", "root");
            info.put("password", "12mii2020");

            Connection conn3 = DriverManager.getConnection(url3, info);
            if (conn3 != null) {
                System.out.println("Connected to the database test3");
            }
        } catch (SQLException ex) {
            System.out.println("An error occurred. Maybe user/password is invalid");
            ex.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



}
