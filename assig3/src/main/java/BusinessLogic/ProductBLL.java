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

/**
 * folosit pe post de server pentru clasa Product
 * realizeaza legatura intre ProductDao si ProductController
 * are ca atribute o lista de produse, un ProductDAO, si un ObservableList pentru afisarea in interfata a tuturor produselor
 */
public class ProductBLL {

    private List<Validator<Product>> validators;
    private ProductDAO productDAO;

    private ObservableList list;

    public ProductBLL( ObservableList list) {

        this.productDAO = new ProductDAO();
        this.list = list;
    }


    public Product findProductById(int id) throws SQLException {
        Product st = productDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return st;
    }
    /**
     * se apeleaza pentru afisarea produselor
     */
    public ObservableList viewProduct(int tip)
    {
        try {
            this.list= productDAO.viewProducts(tip);
            return this.list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
    /**
     * pentru operatiile pe produse: adaugare
     */
    public void addProduct(Product c)
    {
        productDAO.insert(c);
    }
    /**
     * pentru operatiile pe produse: stergere
     */
    public void deleteProduct(Product c)
    {
        productDAO.delete(c);
    }
    /**
     * pentru operatiile pe produse: update
     */
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
