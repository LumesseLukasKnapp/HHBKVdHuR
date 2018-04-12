package hhbkvdhur.Controller;

import hhbkvdhur.Model.HHBKVdHuRModel;
import hhbkvdhur.Model.Hardware;
import hhbkvdhur.Model.Raum;
import hhbkvdhur.Model.Rechner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private ListView<Raum> raumListView;
    private ListView<Hardware> hardwareListView;

    @FXML
    private TextField
            txtRaumBezeichnung,
            txtRaumTyp,
            txtRaumAnzahlArbeitsplaetze,
            txtHardwareTyp,
            txtHardwareSeriennummer,
            txtHardwareInventarnummer,
            txtHardwareHersteller,
            txtHardwareModell,
            txtHardwareStatus,
            txtRechnerImagepfad,
            txtDruckerBetriebsmittel;

    @FXML
    private ComboBox
            cbHardwareSelect;

    private ObservableList<Raum> raumListe = FXCollections.observableArrayList();
    private ObservableList<Hardware> hardwareListe = FXCollections.observableArrayList();

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        this.raumListView.setItems(this.raumListe);
        this.hardwareListView.setItems(this.hardwareListe);
    }

    @FXML
    public void handleRaumHinzufuegen(ActionEvent e){
        Raum r = new Raum();
        int anzahlArbeitsplaetze;

        try{
            anzahlArbeitsplaetze = Integer.parseInt(txtRaumAnzahlArbeitsplaetze.getText());

            r.setBezeichnung(txtRaumBezeichnung.getText());
            r.setTyp(txtRaumTyp.getText());
            r.setAnzahlArbeitsplaetze(anzahlArbeitsplaetze);

            HHBKVdHuRModel.addRaum(r);
        }
        catch(NumberFormatException ne){
            System.out.println(ne.getMessage());
        }
    }

    @FXML
    public void handleHardwareHinzufuegen(ActionEvent e){
        if(cbHardwareSelect.getValue()=="Rechner"){
            try{

                Rechner h = generateGenericHardware(new Rechner());
                h.set

                HHBKVdHuRModel.addHardware(h);
            }
            catch(NumberFormatException ne){
                System.out.println(ne.getMessage());
            }
        }


    }

    private Hardware generateGenericHardware(Hardware h){
        
        h.setTyp(txtHardwareTyp.getText());
        h.setSeriennummer(txtHardwareSeriennummer.getText());
        h.setInventarnummer(txtHardwareInventarnummer.getText());
        h.setHersteller(txtHardwareHersteller.getText());
        h.setModell(txtHardwareModell.getText());
        h.setStatus(Integer.parseInt(txtHardwareStatus.getText()));

        return h;
    }

}
