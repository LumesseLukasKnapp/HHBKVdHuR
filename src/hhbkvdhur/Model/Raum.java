package hhbkvdhur.Model;

import java.util.List;

public class Raum {

    private int raumid;
    private String bezeichnung;
    private String typ;
    private int anzahlArbeitsplaetze;
    private List<Hardware> hardwareList;

    public Raum(int raumid, String bezeichnung, String typ, int anzahlArbeitsplaetze, List<Hardware> hardwareList) {
        this.raumid = raumid;
        this.bezeichnung = bezeichnung;
        this.typ = typ;
        this.anzahlArbeitsplaetze = anzahlArbeitsplaetze;
        this.hardwareList = hardwareList;
    }

    public Raum(int raumid, String bezeichnung, String typ, int anzahlArbeitsplaetze) {
        this.raumid = raumid;
        this.bezeichnung = bezeichnung;
        this.typ = typ;
        this.anzahlArbeitsplaetze = anzahlArbeitsplaetze;
    }

    public Raum() {
    }

    public int getRaumid() {
        return raumid;
    }

    public void setRaumid(int raumid) {
        this.raumid = raumid;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public int getAnzahlArbeitsplaetze() {
        return anzahlArbeitsplaetze;
    }

    public void setAnzahlArbeitsplaetze(int anzahlArbeitsplaetze) {
        this.anzahlArbeitsplaetze = anzahlArbeitsplaetze;
    }

    public List<Hardware> getHardwareList() {
        return hardwareList;
    }

    public void setHardwareList(List<Hardware> hardwareList) {
        this.hardwareList = hardwareList;
    }

    public void addToHardwareList(Hardware h){
        this.hardwareList.add(h);
    }

    public void removeFromHardwareList(Hardware h){
        this.hardwareList.remove(h);
    }

    public void removeFromHarwareListAt(int i){
        this.hardwareList.remove(i);
    }

    public void clearHardwareList(){
        this.hardwareList.clear();
    }

    public String toString(){
        return this.getRaumid()+": "+this.getBezeichnung()+", "+this.getTyp()+", "+this.getAnzahlArbeitsplaetze();
    }
}
