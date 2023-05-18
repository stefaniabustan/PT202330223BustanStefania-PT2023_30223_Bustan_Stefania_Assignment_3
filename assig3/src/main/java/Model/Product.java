package Model;

public class Product {
    private int idproduct;
    private String nume;
    private int cantitate;
    private Double pret;

    public int getIdproduct() {
        return idproduct;
    }

    public void setIdproduct(int idproduct) {
        this.idproduct = idproduct;
    }

    public Product(String nume, int cantitate, Double pret) {
        this.nume = nume;
        this.cantitate = cantitate;
        this.pret = pret;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public Double getPret() {
        return pret;
    }

    public void setPret(Double pret) {
        this.pret = pret;
    }

    public Product(int idproduct, String nume, int cantitate, Double pret) {
        this.idproduct = idproduct;
        this.nume = nume;
        this.cantitate = cantitate;
        this.pret = pret;
    }

    @Override
    public String toString() {
        return "Product  " +
                 idproduct +
                ".  " + nume +
                "                   " + cantitate +
                " Stock                               " + pret +
                " RON";
    }
}
