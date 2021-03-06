package hhbkvdhur.Model;

public class Drucker extends Hardware{

    private String betriebsmittel;

    public Drucker(String betriebsmittel) {
        this.betriebsmittel = betriebsmittel;
    }

    public Drucker(int id, String typ, String seriennummer, String inventarnummer, String hersteller, String modell, int status, String betriebsmittel, String art) {
        super(id, typ, seriennummer, inventarnummer, hersteller, modell, status, art);
        this.betriebsmittel = betriebsmittel;
    }

    public Drucker() {
    }

    public String getBetriebsmittel() {
        return betriebsmittel;
    }

    public void setBetriebsmittel(String betriebsmittel) {
        this.betriebsmittel = betriebsmittel;
    }
}
