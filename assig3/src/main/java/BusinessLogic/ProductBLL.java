package BusinessLogic;

import BusinessLogic.Validators.NumeValidator;
import BusinessLogic.Validators.NumeValidatorProduct;

import Model.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import BusinessLogic.Validators.AdresaValidator;
import BusinessLogic.Validators.Validator;
import DataAcces.ProductDAO;
import Model.Product;
import javafx.collections.ObservableList;

public class ProductBLL {

    private List<Validator<Product>> validators;
    private ProductDAO productDAO;

    private ObservableList list;

    public ProductBLL( ObservableList list) {

        this.productDAO = new ProductDAO();
        this.list = list;
    }

//    public ProductBLL() {
//        validators = new ArrayList<Validator<Product>>();
//        validators.add(new AdresaValidator());
//        validators.add(new NumeValidator());
//
//        clientDAO = new ClientDAO();
//    }

    public Product findProductById(int id) throws SQLException {
        Product st = productDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return st;
    }
    public ObservableList viewProduct(int tip)
    {
        try {
            this.list= productDAO.viewProducts(tip);
            return this.list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
    public void addProduct(Product c)
    {
        productDAO.insert(c);
    }
    public void deleteProduct(Product c)
    {
        productDAO.delete(c);
    }
    public void updateProduct(Product c)
    {
        productDAO.update(c);
    }
    public ProductBLL() {
        validators = new ArrayList<Validator<Product>>();
        validators.add(new NumeValidatorProduct());

        productDAO = new ProductDAO();
    }


}
