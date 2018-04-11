package hhbkvdhur.Model;

public class Rechner extends Hardware{

    private String imagepfad;

    public Rechner(){

    }

    public Rechner(String imagepfad) {
        this.imagepfad = imagepfad;
    }

    public Rechner(int id, String typ, String seriennummer, String inventarnummer, String hersteller, String modell, int status, String imagepfad) {
        super(id, typ, seriennummer, inventarnummer, hersteller, modell, status);
        this.imagepfad = imagepfad;
    }

    public String getImagepfad() {
        return imagepfad;
    }

    public void setImagepfad(String imagepfad) {
        this.imagepfad = imagepfad;
    }
}
