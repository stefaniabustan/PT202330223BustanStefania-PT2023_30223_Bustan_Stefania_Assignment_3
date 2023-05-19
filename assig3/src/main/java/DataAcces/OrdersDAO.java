package DataAcces;



import Model.Client;
import Model.Orders;
import Model.Product;
import Presentation.OrderView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class OrdersDAO extends AbstractDAO<Orders> {
    public OrdersDAO() {
    }

    /**
     * method used to find a person by id
     * @param id of int type
     * @return String
     * @throws SQLException
     */
    public  String addOrders(Orders o)  {
        return "("+o.getIdorder()+", "+"'"+o.getNumeClient()+"'"+", '"+o.getNumeProduct()+"'"+", "+o.getCantitate()+")";
    }
    public Orders findById(int id) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/assig3db", "root", "12mii2020");
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM order WHERE idorder = ?");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            String numeClient=rs.getString("numeClient");
            String numeProduct=rs.getString("numeProduct");
            int cantitate=rs.getInt("cantitate");
            Orders c=new Orders(numeClient, numeProduct,cantitate);
            return c;
        } else {
            rs.close();
            statement.close();
            connection.close();
            return null;
        }
    }

    public Product getProductOrder(String name)throws SQLException
    {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/assig3db", "root", "12mii2020");
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM product WHERE nume = ?");
        statement.setString(1, name);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            int idp=rs.getInt("idproduct");
            int cantitate=rs.getInt("cantitate");
            Double pret=rs.getDouble("pret");
            Product p=new Product(idp,name, cantitate,pret);
            return p;
        } else {
            rs.close();
            statement.close();
            connection.close();
            return null;
        }
    }




    public  String idOrders(Orders o)  {
        return String.valueOf(o.getIdorder());
    }
    public  String updateOrders(Orders o)  {
        return "numeclient = "+"'"+o.getNumeClient()+"' ,"+"numeprodus = "+"'"+o.getNumeProduct()+"' , cantitate = "+o.getCantitate();
    }
    public ObservableList viewOrders() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/assig3db", "root", "12mii2020");

        Statement slc = connection.createStatement();
        slc.execute("Select * from orders");
        ResultSet rs = slc.getResultSet();
        ObservableList comenzi;
        comenzi = FXCollections.observableArrayList();

        while (rs.next()) {
            Orders cc= new Orders(rs.getInt("idorders"), rs.getString("numeprodus"), rs.getString("numeclient"),
                    rs.getInt("cantitate"));
            comenzi.add(cc);

        }


        connection.close();
        return comenzi;
    }
}

