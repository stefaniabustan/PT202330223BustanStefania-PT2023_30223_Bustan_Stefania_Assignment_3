package Presentation;

import BusinessLogic.ProductBLL;
import Model.Product;
import javafx.collections.ObservableList;

public class ProductController {
    private ObservableList list;
    private ProductBLL service;
    private String operation;

    public ProductController(ObservableList list,String operation) {
        this.list = list;
        this.service = new ProductBLL();
        this.operation = operation;
    }



    public ObservableList viewList(int tip)
    {
        return this.service.viewProduct(tip);

    }
    public void OperationProduct (Product produs)
    {
        switch(this.operation) {
            case "add":
                this.service.addProduct(produs);
                break;
            case "delete":
                this.service.deleteProduct(produs);
                break;
            default:
                this.service.updateProduct(produs);
                // code block
        }
    }

}
