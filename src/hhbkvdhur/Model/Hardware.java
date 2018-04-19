package hhbkvdhur.Model;

public class Hardware {

    private int id;
    private String typ;
    private String seriennummer;
    private String inventarnummer;
    private String hersteller;
    private String modell;
    private int status;
    private String art;
    private int raumFkId;

    public Hardware() {
    }

    public Hardware(int id, String typ, String seriennummer, String inventarnummer, String hersteller,
                    String modell, int status, String art) {
        this.id = id;
        this.typ = typ;
        this.seriennummer = seriennummer;
        this.inventarnummer = inventarnummer;
        this.hersteller = hersteller;
        this.modell = modell;
        this.status = status;
        this.art = art;
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

    public String getArt() {
        return art;
    }

    public int getRaumFkId() {
        return raumFkId;
    }

    public void setRaumFkId(int raumFkId) {
        this.raumFkId = raumFkId;
    }
}
