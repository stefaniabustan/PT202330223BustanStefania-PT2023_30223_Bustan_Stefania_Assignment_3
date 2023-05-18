package BusinessLogic;

import BusinessLogic.Validators.Validator;
import DataAcces.OrdersDAO;
import Model.Orders;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

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

//    public OrderBLL() {
//        validators = new ArrayList<Validator<Order>>();
//        validators.add(new AdresaValidator());
//        validators.add(new NumeValidator());
//
//        OrderDAO = new OrderDAO();
//    }

    public Orders findOrderById(int id) throws SQLException {
        Orders st = orderDAO.findById(id);
        if (st == null) {
            throw new NoSuchElementException("The Order with id =" + id + " was not found!");
        }
        return st;
    }
    public ObservableList viewOrders()
    {
        try {
            this.list= orderDAO.viewOrders();
            return this.list;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
    public void addOrders(Orders c)
    {
        orderDAO.insert(c);
    }
    public void deleteOrders(Orders c)
    {
        orderDAO.delete(c);
    }
    public void updateOrders(Orders c)
    {
        orderDAO.update(c);
    }
}
