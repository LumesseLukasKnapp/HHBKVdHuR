package hhbkvdhur.Controller;

import hhbkvdhur.Model.*;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public ListView<Raum> RaumListview;
    public ListView<Hardware> HardwareListview;
    public Button btnHardwareHinzufuegen,
    btnHardwareAendern,
    btnEnde,
    btnRaumHinzufuegen,
    btnRaumAendern,
    btnResetAuswahl;

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
            txtHardwareId,
            txtRaumFkId;


    private ObservableList<Raum> raumListe = FXCollections.observableArrayList();
    private ObservableList<Hardware> hardwareListe = FXCollections.observableArrayList();

    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        this.RaumListview.setItems(this.raumListe);
        this.HardwareListview.setItems(this.hardwareListe);

        BooleanBinding Hardwarefilled =
                txtRaumFkId.textProperty().isNotEmpty()
                        .and(txtHardwareArt.textProperty().isNotEmpty())
                        .and(txtHardwareTyp.textProperty().isNotEmpty())
                        .and(txtHardwareSeriennummer.textProperty().isNotEmpty())
                        .and(txtHardwareInventarnummer.textProperty().isNotEmpty())
                        .and(txtHardwareHersteller.textProperty().isNotEmpty())
                        .and(txtHardwareModell.textProperty().isNotEmpty())
                        .and(txtHardwareStatus.textProperty().isNotEmpty())
                        .and((txtDruckerBetriebsmittel.textProperty().isNotEmpty())
                                .or(txtRechnerImagepfad.textProperty().isNotEmpty()));
        btnHardwareHinzufuegen.disableProperty()
                .bind((HardwareListview.getSelectionModel()
                        .selectedItemProperty()
                        .isNotNull())
                        .or(Hardwarefilled.not()));

        btnHardwareAendern.disableProperty()
                .bind(HardwareListview.getSelectionModel()
                        .selectedItemProperty()
                        .isNull());

        BooleanBinding Raumfilled =
                txtRaumBezeichnung.textProperty().isNotEmpty()
                        .and(txtRaumTyp.textProperty().isNotEmpty())
                        .and(txtRaumAnzahlArbeitsplaetze.textProperty().isNotEmpty());
        btnRaumHinzufuegen.disableProperty()
                .bind((RaumListview.getSelectionModel()
                        .selectedItemProperty()
                        .isNotNull())
                        .or(Raumfilled.not()));

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

        btnRaumAendern.disableProperty()
                .bind(RaumListview.getSelectionModel()
                        .selectedItemProperty()
                        .isNull());
    }

    @FXML
    public void handleBtnResetAction(ActionEvent actionEvent) {
        RaumListview.getSelectionModel().select(-1);
    }

    @FXML
    public void handleBtnRaumHinzufuegenAction(ActionEvent actionEvent) {
        Raum r = new Raum();
        int anzahlArbeitsplaetze;

        try{

            if(txtRaumBezeichnung.getText().isEmpty()||
                    txtRaumTyp.getText().isEmpty()||
                    txtRaumAnzahlArbeitsplaetze.getText().isEmpty()) throw new Exception("Felder dürfen nicht leer sein!");

            anzahlArbeitsplaetze = Integer.parseInt(txtRaumAnzahlArbeitsplaetze.getText());

            r.setBezeichnung(txtRaumBezeichnung.getText());
            r.setTyp(txtRaumTyp.getText());
            r.setAnzahlArbeitsplaetze(anzahlArbeitsplaetze);

            HHBKVdHuRModel.addRoom(r);

            raumListe.add(r);

            initRaumDetails();
        }
        catch(NumberFormatException e){
            addMessageDialog("Die Anzahl der Arbeitsplätze muss eine ganze Zahl sein!");
        }
        catch(Exception e){
            addMessageDialog(e.getMessage());
        }
    }


    @FXML
    public void handleBtnRaumAendernAction(ActionEvent actionEvent) {
        try {

            if(txtRaumBezeichnung.getText().isEmpty()||
                    txtRaumTyp.getText().isEmpty()||
                    txtRaumAnzahlArbeitsplaetze.getText().isEmpty()) throw new Exception("Felder dürfen nicht leer sein!");

            Raum r = RaumListview.getSelectionModel().getSelectedItem();
            int listIndex = this.HardwareListview.getSelectionModel().getSelectedIndex();

            r.setAnzahlArbeitsplaetze(Integer.parseInt(txtRaumAnzahlArbeitsplaetze.getText()));
            r.setTyp(txtRaumTyp.getText());
            r.setBezeichnung(txtRaumBezeichnung.getText());

            HHBKVdHuRModel.updateRoom(r);

            raumListe.set(listIndex, r);
        }
        catch(NumberFormatException e){
            addMessageDialog("Die Anzahl der Arbeitsplätze muss eine ganze Zahl sein!");
        }
        catch(Exception e){
            addMessageDialog(e.getMessage());
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

    @FXML
    public void handleBtnHardwareHinzufuegenAction(ActionEvent actionEvent) {

        try {

            if (txtHardwareTyp.getText().isEmpty() ||
                    txtHardwareHersteller.getText().isEmpty() ||
                    txtHardwareTyp.getText().isEmpty() ||
                    txtHardwareInventarnummer.getText().isEmpty() ||
                    txtHardwareSeriennummer.getText().isEmpty() ||
                    txtHardwareStatus.getText().isEmpty() ||
                    txtHardwareModell.getText().isEmpty()) throw new Exception("Felder dürfen nicht leer sein!");
            if (!txtHardwareTyp.getText().equals("Computer") && !txtHardwareTyp.getText().equals("Computer"))
                throw new Exception("Hardwaretyp muss 'Computer' oder 'Drucker' sein!");


            int status = Integer.parseInt(txtHardwareSeriennummer.getText());

            Hardware h = new Hardware();

            h.setHersteller(txtHardwareHersteller.getText());
            h.setInventarnummer(txtHardwareInventarnummer.getText());
            h.setModell(txtHardwareModell.getText());
            h.setSeriennummer(txtHardwareSeriennummer.getText());
            h.setTyp(txtHardwareTyp.getText());
            h.setStatus(status);

            if (txtRaumFkId.getText().isEmpty()) {
                h.setRaumFkId(this.RaumListview.getSelectionModel().getSelectedItem().getRaumid());
            } else {
                h.setRaumFkId(Integer.parseInt(txtRaumFkId.getText()));
            }


            if (txtHardwareTyp.getText().indexOf("Computer") > 0) {
                Rechner r = (Rechner) h;

                r.setImagepfad(txtRechnerImagepfad.getText());
                hardwareListe.add(r);

            } else if (txtHardwareTyp.getText().indexOf("Drucker") > 0) {
                Drucker d = (Drucker) h;

                d.setBetriebsmittel(txtDruckerBetriebsmittel.getText());
                hardwareListe.add(d);
            } else {
                hardwareListe.add(h);
            }

        }catch(NumberFormatException e){
                addMessageDialog("Hardwarestatus muss eine ganze Zahl sein!");
            }
        catch(Exception e){
                addMessageDialog(e.getMessage());
            }
    }

    @FXML
    public void handleBtnHardwareAendernAction(ActionEvent actionEvent) {
        Hardware h = (Hardware) this.HardwareListview.getSelectionModel().getSelectedItem();
        int listIndex = this.HardwareListview.getSelectionModel().getSelectedIndex();

        try
        {
            if(txtHardwareTyp.getText().isEmpty()||
                    txtHardwareHersteller.getText().isEmpty()||
                    txtHardwareTyp.getText().isEmpty()||
                    txtHardwareInventarnummer.getText().isEmpty()||
                    txtHardwareSeriennummer.getText().isEmpty()||
                    txtHardwareStatus.getText().isEmpty()||
                    txtHardwareModell.getText().isEmpty()) throw new Exception("Felder dürfen nicht leer sein!");
            if(!txtHardwareTyp.getText().equals("Computer")&&!txtHardwareTyp.getText().equals("Computer"))
                throw new Exception("Hardwaretyp muss 'Computer' oder 'Drucker' sein!");

            int status = Integer.parseInt(txtHardwareSeriennummer.getText());

            h.setHersteller(txtHardwareHersteller.getText());
            h.setInventarnummer(txtHardwareInventarnummer.getText());
            h.setModell(txtHardwareModell.getText());
            h.setSeriennummer(txtHardwareSeriennummer.getText());
            h.setTyp(txtHardwareTyp.getText());
            h.setStatus(status);

            HHBKVdHuRModel.updateHardware(h);

            hardwareListe.set(listIndex, h);
        }
        catch(NumberFormatException e){
            addMessageDialog("Hardwarestatus muss eine ganze Zahl sein!");
        }
        catch(Exception e){
            addMessageDialog(e.getMessage());
        }
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
        if(hardwareDetails instanceof Rechner){
            Rechner rechnerDetails = (Rechner) hardwareDetails;
            this.txtRechnerImagepfad.setText(rechnerDetails.getImagepfad());
        }
        else if(hardwareDetails instanceof Drucker){
            Drucker druckerDetails = (Drucker) hardwareDetails;
            this.txtDruckerBetriebsmittel.setText(druckerDetails.getBetriebsmittel());
        }
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

    private void addMessageDialog(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setContentText(text);
        alert.showAndWait();
    }
}
