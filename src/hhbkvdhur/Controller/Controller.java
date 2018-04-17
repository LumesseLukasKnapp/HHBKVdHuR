package hhbkvdhur.Controller;

import hhbkvdhur.Model.HHBKVdHuRModel;
import hhbkvdhur.Model.Hardware;
import hhbkvdhur.Model.Raum;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public ListView<Raum> RaumListview;
    public ListView<Hardware> HardwareListview;

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
            txtDruckerBetriebsmittel,
            txtHardwareArt,
            txtRaumId,
            txtHardwareId;


    private ObservableList<Raum> raumListe = FXCollections.observableArrayList();
    private ObservableList<Hardware> hardwareListe = FXCollections.observableArrayList();

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        this.RaumListview.setItems(this.raumListe);
        this.HardwareListview.setItems(this.hardwareListe);

        BooleanBinding drucker = txtHardwareTyp.textProperty().isEqualTo("Drucker");
        txtDruckerBetriebsmittel.disableProperty()
                .bind((HardwareListview.getSelectionModel()
                        .selectedItemProperty()
                        .isNotNull())
                        .or(drucker.not()));

        BooleanBinding computer = txtHardwareTyp.textProperty().isEqualTo("Computer");
        txtRechnerImagepfad.disableProperty()
                .bind((HardwareListview.getSelectionModel()
                        .selectedItemProperty()
                        .isNotNull())
                        .or(computer.not()));
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

    @FXML
    public void handleBtnRaumAendernAction(ActionEvent actionEvent) {
        Raum r = RaumListview.getSelectionModel().getSelectedItem();

        //List<Hardware> hardwareList = HHBKVdHuRModel.getHardware(r);

        hardwareListe.removeAll();
        //hardwareListe.addAll(hardwareList);
        HardwareListview.getSelectionModel().select(-1);
    }

    @FXML
    public void handleBtnResetAction(ActionEvent actionEvent) {
        RaumListview.getSelectionModel().select(-1);
    }

    @FXML
    public void handleBtnHardwareHinzufuegenAction(ActionEvent actionEvent) {
    }

    @FXML
    public void handleBtnHardwareAendernAction(ActionEvent actionEvent) {
        //Do stuff
    }

    @FXML
    public void handleBtnRaumHinzufuegenAction(ActionEvent actionEvent) {
        Raum r = new Raum();
        int anzahlArbeitsplaetze;

        try{
            anzahlArbeitsplaetze = Integer.parseInt(txtRaumAnzahlArbeitsplaetze.getText());

            r.setBezeichnung(txtRaumBezeichnung.getText());
            r.setTyp(txtRaumTyp.getText());
            r.setAnzahlArbeitsplaetze(anzahlArbeitsplaetze);

            //HHBKVdHuRModel.addRaum(r);

            initRaumDetails();
        }
        catch(NumberFormatException ne){
            System.out.println(ne.getMessage());
        }
    }

    public void handleRaumSelected(KeyEvent keyEvent) {
        this.setRaumDetails((Raum) this.RaumListview.getSelectionModel().getSelectedItem());
    }

    private void setRaumDetails(Raum raumDetails) {
        this.initRaumDetails();
        this.txtRaumId.setText(String.valueOf(raumDetails.getRaumid()));
        this.txtRaumBezeichnung.setText(raumDetails.getBezeichnung());
        this.txtRaumTyp.setText(raumDetails.getTyp());
        this.txtRaumAnzahlArbeitsplaetze.setText(Integer.toString(raumDetails.getAnzahlArbeitsplaetze()));
    }

    private void initRaumDetails() {
        this.txtRaumBezeichnung.setText("");
        this.txtRaumTyp.setText("");
        this.txtRaumAnzahlArbeitsplaetze.setText("");
    }

    public void handleHardwareSelected(KeyEvent keyEvent) {
        this.setHardwareDetails((Hardware) this.HardwareListview.getSelectionModel().getSelectedItem());
    }

    public void setHardwareDetails(Hardware hardwareDetails){
        this.initHardwareDetails();
        this.txtHardwareId.setText(String.valueOf(hardwareDetails.getId()));
        this.txtHardwareArt.setText(hardwareDetails.getArt());
        this.txtHardwareTyp.setText(hardwareDetails.getTyp());
        this.txtHardwareSeriennummer.setText(hardwareDetails.getSeriennummer());
        this.txtHardwareInventarnummer.setText(hardwareDetails.getInventarnummer());
        this.txtHardwareHersteller.setText(hardwareDetails.getHersteller());
        this.txtHardwareModell.setText(hardwareDetails.getModell());
        this.txtHardwareStatus.setText(Integer.toString(hardwareDetails.getStatus()));
        this.txtDruckerBetriebsmittel.setText(hardwareDetails.getBetriebsmittel());
        this.txtRechnerImagepfad.setText(hardwareDetails.getImagepfad());

    }

    private void initHardwareDetails() {
        this.txtHardwareArt.setText("");
        this.txtHardwareTyp.setText("");
        this.txtHardwareSeriennummer.setText("");
        this.txtHardwareInventarnummer.setText("");
        this.txtHardwareHersteller.setText("");
        this.txtHardwareModell.setText("");
        this.txtHardwareStatus.setText("");
        this.txtDruckerBetriebsmittel.setText("");
        this.txtRechnerImagepfad.setText("");
    }
}
