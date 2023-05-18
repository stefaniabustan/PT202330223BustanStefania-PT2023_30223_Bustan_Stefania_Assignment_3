package Presentation;

import BusinessLogic.OrderBLL;
import Model.Orders;
import javafx.collections.ObservableList;

public class OrderController {
    private ObservableList list;
    private OrderBLL service;
    private String operation;

    public OrderController(ObservableList list,String operation) {
        this.list = list;
        this.service = new OrderBLL();
        this.operation = operation;
    }



    public ObservableList viewList()
    {
        return this.service.viewOrders();

    }
    public void OperationOrder (Orders comanda)
    {
        switch(this.operation) {
            case "add":
                this.service.addOrders(comanda);
                break;
            case "delete":
                this.service.deleteOrders(comanda);
                break;
            default:
                this.service.updateOrders(comanda);
                // code block
        }
    }
}
