package DataAcces;

import Model.Client;
import Presentation.ClientView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.List;

public class ClientDAO extends AbstractDAO<Client>{
    public ClientDAO() {
    }

    /**
     * method used to find a person by id
     * @param id of int type
     * @return String
     * @throws SQLException
     */
    public  Client findById(int id) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/assig3db", "root", "12mii2020");
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM client WHERE id = ?");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            String nume=rs.getString("nume");
            String adresa=rs.getString("adresa");
            Client c=new Client(nume, adresa);
            return c;
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
    public  String addClient(Client people)  {
        return "("+people.getId()+", "+"'"+people.getNume()+"'"+", "+"'"+people.getAdresa()+"'"+")";
    }
    public  String idClient(Client people)  {
        return String.valueOf(people.getId());
    }
    public  String updateClient(Client people)  {
        return "id = "+people.getId()+", nume = "+"'"+people.getNume()+"'"+", adresa = "+"'"+people.getAdresa()+"'";
    }
    public  ObservableList viewClients(int tip) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/assig3db", "root", "12mii2020");

        Statement slc = connection.createStatement();
        slc.execute("Select * from client");
        ResultSet rs = slc.getResultSet();
        ObservableList clienti;
        clienti = FXCollections.observableArrayList();

        while (rs.next()) {
            Client cc= new Client(rs.getInt("id"), rs.getString("nume"),
                    rs.getString("adresa"));
            if(tip==1)
            clienti.add(cc);
            else clienti.add(cc.getNume().toString());

        }


        connection.close();
        return clienti;
    }
}
