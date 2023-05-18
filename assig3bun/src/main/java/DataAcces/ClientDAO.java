package DataAcces;

import Model.Client;

import java.sql.*;

/**
 *
 */
public class ClientDAO {


    public ClientDAO() {
    }

    /**
     * method used to find a person by id
     * @param id of int type
     * @return String
     * @throws SQLException
     */
    public static String getclientById(int id) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/tema3_test", "root", "root");
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM people WHERE id = ?");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            return rs.getString("nume");
        } else {
            rs.close();
            statement.close();
            connection.close();
            return null;
        }
    }


    /**
     *
     * @param people
     * @throws SQLException
     */
    public static void saveClient(Client people) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/tema3_test", "root", "root");
        PreparedStatement statement = connection.prepareStatement("INSERT INTO client (id, nume, adresa) VALUES (?, ?,?)");
        statement.setInt(1, people.getId());
        statement.setString(2, people.getNume());
        statement.setString(3, people.getAdresa());
        statement.executeUpdate();
        statement.close();
        connection.close();
    }
}