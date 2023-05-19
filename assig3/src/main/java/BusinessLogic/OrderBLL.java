package BusinessLogic;

import BusinessLogic.Validators.Validator;
import DataAcces.OrdersDAO;
import Model.Orders;
import Model.Product;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
/**
 * folosit pe post de server pentru clasa Order
 * realizeaza legatura intre OrderDao si OrderController
 * are ca atribute o lista de comenzi, un OrderDAO, si un ObservableList pentru afisarea in interfata a tuturor comenzilor
 */
public class OrderBLL {
    private List<Validator<Orders>> validators;
    private OrdersDAO orderDAO;

    private ObservableList list;

    public OrderBLL( ObservableList list) {

        this.orderDAO = new OrdersDAO();
        this.list = list;
    }

    public OrderBLL( ) {

        this.orderDAO = new OrdersDAO();
    }

    public Orders findOrderById(int id) throws SQLException {
        Orders st = orderDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The Order with id =" + id + " was not found!");
        }
        return st;
    }
    /**
     * se apeleaza pentru afisarea comenzilor
     */
    public ObservableList viewOrders()
    {
        try {
            this.list= orderDAO.viewOrders();
            return this.list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    /**
     * pentru operatiile pe comenzi: adaugare
     */
    public void addOrders(Orders c)
    {
        orderDAO.insert(c);
    }
    /**
     * pentru operatiile pe comenzi: stergere
     */
    public void deleteOrders(Orders c)
    {
        orderDAO.delete(c);
    }
    /**
     * pentru operatiile pe comenzi: update
     */
    public void updateOrders(Orders c)
    {
        orderDAO.update(c);
    }
    public Product productOrder(String name) throws SQLException {
        Product p=orderDAO.getProductOrder(name);
        return  p;
    }
}
