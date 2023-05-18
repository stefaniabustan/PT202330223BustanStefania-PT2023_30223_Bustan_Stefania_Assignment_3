package Model;

public class Orders {
    private int idorders;
    private String numeclient;
    private String numeprodus;
    private int cantitate;

    @Override
    public String toString() {
        return "Order " + idorders +
                ".      CLIENT: " + numeclient +
                "       PRODUCT: " + numeprodus+
                "       STOCK: " + cantitate;
    }

    public int getIdorder() {
        return idorders;
    }

    public void setIdorder(int idorder) {
        this.idorders = idorder;
    }

    public String getNumeClient() {
        return numeclient;
    }

    public void setNumeClient(String numeClient) {
        this.numeclient = numeClient;
    }

    public String getNumeProduct() {
        return numeprodus;
    }

    public void setNumeProduct(String numeProduct) {
        this.numeprodus = numeProduct;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public Orders(int idorder, String numeProduct,String numeClient,  int cantitate) {
        this.idorders = idorder;
        this.numeclient = numeClient;
        this.numeprodus = numeProduct;
        this.cantitate = cantitate;
    }

    public Orders( String numeProduct,String numeClient, int cantitate) {
        this.numeclient = numeClient;
        this.numeprodus = numeProduct;
        this.cantitate = cantitate;
    }
}
