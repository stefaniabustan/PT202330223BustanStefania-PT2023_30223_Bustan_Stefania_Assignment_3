package Model;

public class Order {
    private int idorder;
    private String numeClient;
    private String numeProduct;
    private int cantitate;

    public Order(int idorder, String numeClient, String numeProduct, int cantitate) {
        this.idorder = idorder;
        this.numeClient = numeClient;
        this.numeProduct = numeProduct;
        this.cantitate = cantitate;
    }

    public Order(String numeClient, String numeProduct, int cantitate) {
        this.numeClient = numeClient;
        this.numeProduct = numeProduct;
        this.cantitate = cantitate;
    }
}
