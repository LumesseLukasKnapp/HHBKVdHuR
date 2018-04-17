package hhbkvdhur.Model;

import java.util.ArrayList;
import java.util.List;

public class AllItems
{
    public ArrayList<Hardware> hardwareArrayList;

    public ArrayList<Rechner> rechnerArrayList;

    public ArrayList<Drucker> druckerArrayList;

    public ArrayList<Hardware> getHardwareArrayList() {
        return hardwareArrayList;
    }

    public void setHardwareArrayList(ArrayList<Hardware> hardwareArrayList) {
        this.hardwareArrayList = hardwareArrayList;
    }

    public ArrayList<Rechner> getRechnerArrayList() {
        return rechnerArrayList;
    }

    public void setRechnerArrayList(ArrayList<Rechner> rechnerArrayList) {
        this.rechnerArrayList = rechnerArrayList;
    }

    public ArrayList<Drucker> getDruckerArrayList() {
        return druckerArrayList;
    }

    public void setDruckerArrayList(ArrayList<Drucker> druckerArrayList) {
        this.druckerArrayList = druckerArrayList;
    }
}
