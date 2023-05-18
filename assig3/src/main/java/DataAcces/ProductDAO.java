package DataAcces;


import Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ProductDAO extends AbstractDAO<Product>{
    public ProductDAO() {
    }

    /**
     * method used to find a person by id
     * @param id of int type
     * @return String
     * @throws SQLException
     */
    public  Product findById(int id) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/assig3db", "root", "12mii2020");
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM product WHERE idproduct = ?");
        statement.setInt(1, id);
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            String nume=rs.getString("nume");
            int cantitate=rs.getInt("cantitate");
            Double pret=rs.getDouble("pret");
            Product c=new Product(nume,cantitate,pret);
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
    public  String addProduct(Product people)  {
        return "("+people.getIdproduct()+", "+"'"+people.getNume()+"'"+", "+people.getCantitate()+", "+people.getPret()+")";
    }
    public  String idProduct(Product people)  {
        return String.valueOf(people.getIdproduct());
    }
    public  String updateProduct(Product people)  {
        return "idproduct = "+people.getIdproduct()+", nume = "+"'"+people.getNume()+"'"+", cantitate= "+people.getCantitate()+", pret="+people.getPret();
    }
    public ObservableList viewProducts(int tip) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/assig3db", "root", "12mii2020");

        Statement slc = connection.createStatement();
        slc.execute("Select * from Product");
        ResultSet rs = slc.getResultSet();
        ObservableList produse;
        produse = FXCollections.observableArrayList();

        while (rs.next()) {
            Product cc= new Product(rs.getInt("idproduct"), rs.getString("nume"),rs.getInt("cantitate"),
           rs.getDouble("pret"));
            if(tip==1)
            produse.add(cc);
            else produse.add(cc.getNume().toString());
            //System.out.println();

        }


        connection.close();
        return produse;
    }
}
