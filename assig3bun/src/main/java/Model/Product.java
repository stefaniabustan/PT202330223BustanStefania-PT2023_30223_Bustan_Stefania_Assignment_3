package Model;

public class Product {
    private int idproduct;
    private String nume;
    private int cantitate;
    private Double pret;

    public Product(String nume, int cantitate, Double pret) {
        this.nume = nume;
        this.cantitate = cantitate;
        this.pret = pret;
    }

    public Product(int idproduct, String nume, int cantitate, Double pret) {
        this.idproduct = idproduct;
        this.nume = nume;
        this.cantitate = cantitate;
        this.pret = pret;
    }
}
