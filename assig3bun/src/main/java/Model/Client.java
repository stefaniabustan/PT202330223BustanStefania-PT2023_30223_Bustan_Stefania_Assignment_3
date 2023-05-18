package Model;

public class Client {
    private int id;
    private String nume;
    private String adresa;

    public Client(String nume, String adresa) {
        this.nume = nume;
        this.adresa = adresa;
    }

    public Client(int id, String nume, String adresa) {
        this.id = id;
        this.nume = nume;
        this.adresa = adresa;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }
}
