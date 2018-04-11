package hhbkvdhur.Model;

public class Hardware {

    private int id;
    private String typ;
    private String seriennummer;
    private String inventarnummer;
    private String hersteller;
    private String modell;
    private int status;

    public Hardware() {
    }

    public Hardware(int id, String typ, String seriennummer, String inventarnummer, String hersteller, String modell, int status) {
        this.id = id;
        this.typ = typ;
        this.seriennummer = seriennummer;
        this.inventarnummer = inventarnummer;
        this.hersteller = hersteller;
        this.modell = modell;
        this.status = status;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public String getSeriennummer() {
        return seriennummer;
    }

    public void setSeriennummer(String seriennummer) {
        this.seriennummer = seriennummer;
    }

    public String getInventarnummer() {
        return inventarnummer;
    }

    public void setInventarnummer(String inventarnummer) {
        this.inventarnummer = inventarnummer;
    }

    public String getHersteller() {
        return hersteller;
    }

    public void setHersteller(String hersteller) {
        this.hersteller = hersteller;
    }

    public String getModell() {
        return modell;
    }

    public void setModell(String modell) {
        this.modell = modell;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
