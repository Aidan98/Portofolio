package fys;

import com.jfoenix.controls.JFXButton;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 * FXML Controller Doel: Functies van medewerker bagage zoeken pagina maken.
 *
 * @author is102 - 3, GORDEL
 */
public class MedewerkerBagageZoekenController implements Initializable {

    // Attributen
    FYS fys = new FYS();
    Language language = new Language();

    @FXML
    private TableView<Bagage> tableview;
    @FXML
    private TableColumn<Bagage, String> luchthaven;
    @FXML
    private TableColumn<Bagage, String> vluchtnummer, labelnummer, type, merk, kleur, bijzondereKenmerken, bestemming;
    @FXML
    private TableColumn<Bagage, Integer> identificatienummer;
    @FXML
    private TextField zoekterm;
    @FXML
    private ComboBox kenmerkenComboBox, klantCombobox;
    @FXML
    private Button matchBtn;
    @FXML
    private Button klantZoeken;
    @FXML
    private Label klantVluchtnrLbl;
    @FXML
    private Label klantLabelnrLbl;
    @FXML
    private Label klantTypeLbl, klantMerkLbl, klantKleurLbl,
            klantBijzondereKenmerkenLbl, typeLbl, merkLbl, kleurLbl,
            bijzondereKenmerkenLbl, vluchtnummerLbl, labelnrLbl;
    @FXML
    private Label klantLbl;
    @FXML
    private Label alleKoffers;
    @FXML
    private Label zoekenBagage;
    @FXML
    private JFXButton zoekenBtn;
    @FXML
    private JFXButton laadAlleBagageBtn;
    @FXML
    private ImageView kofferImageView;

    private ObservableList<Bagage> data = FXCollections.observableArrayList();
    private final ObservableList<String> kenmerkenList = FXCollections.observableArrayList("Labelnummer", "Vluchtnummer", "Kleur", "Type", "Merk", "Bestemming");
    private final ObservableList<Integer> klantData = FXCollections.observableArrayList();
    private Image image;
    private int klantID;
    private String klantEmail;
    private String klantNaam;
    private String klantVluchtnr;
    private String klantBagageLabelnr;
    private String klantBagageBijzonderekenmerken;
    private String klantBagageType;
    private String klantBagageMerk;
    private String klantBagageKleur;
    private String klantAdres;
    private String klantWoonplaats;
    private String klantPostcode;
    private String klantLand;
    private String klantTelefoonnummer;
    private String gevondBagageLocatie;
    int lang = fys.getLanguage();

    /**
     * Doel: Initialiseer de pagina en vult tabelkolommen en combobox met
     * informatie uit de database.
     *
     * @param url URL
     * @param rb ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (lang == 2) {
            alleKoffers.setText(language.getMedewerkerBagageZoekenLabelAlleKoffers());
            zoekenBagage.setText(language.getMedewerkerBagageZoekenLabelZoekenBagage());
            zoekenBtn.setText(language.getMedewerkerBagageZoekenPromptTextZoekterm());
            laadAlleBagageBtn.setText(language.getMedewerkerBagageZoekenButtonLaadAlleBagageBtn());
            luchthaven.setText(language.getMedewerkerBagageZoekenPromptTextLuchthaven());
            vluchtnummer.setText(language.getMedewerkerBagageZoekenPromptTextVluchtnummer());
            labelnummer.setText(language.getMedewerkerBagageZoekenPromptTextLabelnummer());
            type.setText(language.getMedewerkerBagageZoekenPromptTextType());
            merk.setText(language.getMedewerkerBagageZoekenPromptTextMerk());
            kleur.setText(language.getMedewerkerBagageZoekenPromptTextKleur());
            bijzondereKenmerken.setText(language.getMedewerkerBagageZoekenPromptTextBijzondereKenmerken());
            bestemming.setText(language.getMedewerkerBagageZoekenPromptTextBestemming());
            identificatienummer.setText(language.getMedewerkerBagageZoekenPromptTextIdentificatienummer());
            zoekterm.setPromptText(language.getMedewerkerBagageZoekenPromptTextZoekterm());
            kenmerkenComboBox.setPromptText(language.getMedewerkerBagageZoekenPromptTextKenmerkenComboBox());
            matchBtn.setText(language.getMedewerkerBagageZoekenButtonMatchBtn());
            typeLbl.setText(language.getMedewerkerBagageZoekenLabelTypeLbl());
            klantLbl.setText(language.getMedewerkerBagageZoekenLabelKlantLbl());
            vluchtnummerLbl.setText(language.getMedewerkerBagageZoekenLabelvluchtnummerLbl());
            merkLbl.setText(language.getMedewerkerBagageZoekenLabelMerkLbl());
            kleurLbl.setText(language.getMedewerkerBagageZoekenLabelKleurLbl());
            bijzondereKenmerkenLbl.setText(language.getMedewerkerBagageZoekenLabelBijzondereKenmerkenLbl());
            labelnrLbl.setText(language.getMedewerkerBagageZoekenLabelLabelnrLbl());
        }
        matchBtn.setVisible(false);
        //haal data uit database
        try {
            selectFromVliegveldenAndBagage();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerLogboekController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Vult de kolommen
        identificatienummer.setCellValueFactory(new PropertyValueFactory<>("verlorendID"));
        luchthaven.setCellValueFactory(new PropertyValueFactory<>("dbluchthaven"));
        vluchtnummer.setCellValueFactory(new PropertyValueFactory<>("dbvluchtnummer"));
        labelnummer.setCellValueFactory(new PropertyValueFactory<>("dblabelnummer"));
        type.setCellValueFactory(new PropertyValueFactory<>("dbtype"));
        merk.setCellValueFactory(new PropertyValueFactory<>("dbmerk"));
        kleur.setCellValueFactory(new PropertyValueFactory<>("dbkleur"));
        bijzondereKenmerken.setCellValueFactory(new PropertyValueFactory<>("dbbijzonderekenmerken"));
        bestemming.setCellValueFactory(new PropertyValueFactory<>("dbbestemming"));

        tableview.setItems(data);
        tableview.getColumns().addAll(identificatienummer, luchthaven, vluchtnummer, labelnummer, type, merk, kleur, bijzondereKenmerken, bestemming);
        tableview.getSelectionModel().selectedIndexProperty().addListener(new geselecteerdeRij());

        //Zet info in items in combobox
        if (lang == 2) {
            kenmerkenComboBox.setItems(language.getKenmerkenList());
        } else {
            kenmerkenComboBox.setItems(kenmerkenList);
        }
    }

    /**
     * Doel: Deze button zoekt in de database naar overeenkomende kenmerken met
     * de zoekterm en filtert deze en geeft enkel deze weer in de tabel.
     *
     * @param event klik op de knop
     * @throws SQLException vang fouten op
     */
    @FXML
    private void handleZoekenOpAction(ActionEvent event) throws SQLException {
        data = FXCollections.observableArrayList();

        String zoekOp = (String) kenmerkenComboBox.getValue();
        String zoekTerm = zoekterm.getText().toLowerCase();
        if (null != zoekOp && !zoekTerm.isEmpty()) {
            selectOpZoekActie(zoekOp, zoekTerm);
            tableview.setItems(data);
            tableview.getColumns().addAll(identificatienummer, luchthaven, vluchtnummer, labelnummer, type, merk, kleur, bijzondereKenmerken, bestemming);
        }
    }

    /**
     * Doel: Deze button laadt de tabel zonder zoekopdrachten of filters.
     *
     * @param event klik op de knop
     * @throws SQLException vang fouten op
     * @throws IOException vang fouten op
     */
    @FXML
    private void handleLaadTabelBtnAction(ActionEvent event) throws SQLException, IOException {
        if (lang == 2) {
            fys.changeToAnotherFXML("Corendon Lost & Found - Employee search luggage",
                    "view/medewerker/MedewerkerBagageZoeken.fxml");
        } else {
            fys.changeToAnotherFXML("Corendon Lost & Found - Medewerker zoeken bagage",
                    "view/medewerker/MedewerkerBagageZoeken.fxml");
        }
    }

    /**
     * Doel: Bij het maken van een match sla de informatie op.
     *
     * @param event klik op de knop
     * @throws SQLException vang fouten op
     */
    @FXML
    private void handleMatchAction(ActionEvent event) throws SQLException, IOException {
        int gevondenBagageID = tableview.getSelectionModel()
                .selectedItemProperty().getValue().verlorendID.intValue();
        int verlorenBagageId = (int) klantCombobox.getValue();
        int medewerkerId = fys.getGebruikerID();

        maakPDF();
        insertGematchedeBagageEnLogboek(verlorenBagageId, gevondenBagageID, medewerkerId);

        String to = "dummy@DHL.com"; // list of recipient email addresses
        final String subject = "Ophalen bagage";
        final String body = " Geachte, \n"
                + "\n"
                + "Wij hebben een pakket dat zo snel mogelijk opgehaald en bezorgd moet worden! Bijgevoegd is een PDF-document met de benodigde informatie.\n"
                + "\n"
                + "Met vriendelijke groeten,\n"
                + "\n"
                + "Lost & Found balie\n"
                + "\n"
                + "Corendon";
        String bestand = "repatriatiebericht_" + klantID + ".pdf";
        FYS.sendEmailMetBestand(to, subject, body, bestand);

        if (lang == 2) {
            fys.changeToAnotherFXML("Corendon Lost & Found - Employee search luggage",
                    "view/medewerker/MedewerkerBagageZoeken.fxml");
        } else {
            fys.changeToAnotherFXML("Corendon Lost & Found - Medewerker zoeken bagage",
                    "view/medewerker/MedewerkerBagageZoeken.fxml");
        }
    }

    /**
     * Doel: Zoekt klanten die mogelijke match met de geselecteerde verloren
     * bagage kunnen zijn.
     *
     * @param event klik op de knop
     * @throws SQLException vang fouten op
     */
    @FXML
    private void handleKlantZoekenAction(ActionEvent event) throws SQLException {
        if (!klantCombobox.getValue().equals("")) {
            labelnrLbl.setVisible(false);
            vluchtnummerLbl.setVisible(false);
            typeLbl.setVisible(false);
            merkLbl.setVisible(false);
            kleurLbl.setVisible(false);
            bijzondereKenmerkenLbl.setVisible(false);

            klantLabelnrLbl.setText("");
            klantVluchtnrLbl.setText("");
            klantTypeLbl.setText("");
            klantMerkLbl.setText("");
            klantKleurLbl.setText("");
            klantBijzondereKenmerkenLbl.setText("");
            int verlorenBagageID = (int) klantCombobox.getValue();
            selectKlantZoekActie(verlorenBagageID);

            if (!klantVluchtnr.isEmpty()) {
                vluchtnummerLbl.setVisible(true);
                klantVluchtnrLbl.setText(klantVluchtnr);
            }
            if (!klantBagageLabelnr.isEmpty()) {
                labelnrLbl.setVisible(true);
                klantLabelnrLbl.setText(klantBagageLabelnr);
            }
            if (!klantBagageType.isEmpty()) {
                typeLbl.setVisible(true);
                klantTypeLbl.setText(klantBagageType);
            }
            if (!klantBagageMerk.isEmpty()) {
                merkLbl.setVisible(true);
                klantMerkLbl.setText(klantBagageMerk);
            }
            if (!klantBagageKleur.isEmpty()) {
                kleurLbl.setVisible(true);
                klantKleurLbl.setText(klantBagageKleur);
            }
            if (!klantBagageBijzonderekenmerken.isEmpty()) {
                bijzondereKenmerkenLbl.setVisible(true);
                klantBijzondereKenmerkenLbl.setText(klantBagageBijzonderekenmerken);
            }
            matchBtn.setVisible(true);
        }
    }

    /**
     * Doel: De bagage objecten aanmaken.
     */
    public static class Bagage {

        private final SimpleIntegerProperty verlorendID;
        private final SimpleStringProperty dbluchthaven;
        private final SimpleStringProperty dbvluchtnummer;
        private final SimpleStringProperty dblabelnummer;
        private final SimpleStringProperty dbtype;
        private final SimpleStringProperty dbmerk;
        private final SimpleStringProperty dbkleur;
        private final SimpleStringProperty dbbijzonderekenmerken;
        private final SimpleStringProperty dbbestemming;

        private Bagage(Integer verlorendID, String dbluchthaven, String dbvluchtnummer, String dblabelnummer, String dbtype, String dbmerk, String dbkleur, String dbbijzonderekenmerken, String dbbestemming) {
            this.verlorendID = new SimpleIntegerProperty(verlorendID);
            this.dbluchthaven = new SimpleStringProperty(dbluchthaven);
            this.dbvluchtnummer = new SimpleStringProperty(dbvluchtnummer);
            this.dblabelnummer = new SimpleStringProperty(dblabelnummer);
            this.dbtype = new SimpleStringProperty(dbtype);
            this.dbmerk = new SimpleStringProperty(dbmerk);
            this.dbkleur = new SimpleStringProperty(dbkleur);
            this.dbbijzonderekenmerken = new SimpleStringProperty(dbbijzonderekenmerken);
            this.dbbestemming = new SimpleStringProperty(dbbestemming);
        }

        /**
         * Doel: Geeft waarde uit de database terug.
         *
         * @return de dbluchthaven
         */
        public String getDbluchthaven() {
            return dbluchthaven.get();
        }

        /**
         * Doel: Geeft nieuwe waarde aan attribuut.
         *
         * @param dbluchthaven de dbluchthaven
         */
        public void setDbluchthaven(String dbluchthaven) {
            this.dbluchthaven.set(dbluchthaven);
        }

        /**
         * Doel: Geeft waarde uit de database terug.
         *
         * @return het dbvluchtnummer
         */
        public String getDbvluchtnummer() {
            return dbvluchtnummer.get();
        }

        /**
         * Doel: Geeft nieuwe waarde aan attribuut.
         *
         * @param dbvluchtnummer het nieuwe dbvluchtnummer
         */
        public void setDbvluchtnummer(String dbvluchtnummer) {
            this.dbvluchtnummer.set(dbvluchtnummer);
        }

        /**
         * Doel: Geeft waarde uit de database terug.
         *
         * @return het dblabelnummer
         */
        public String getDblabelnummer() {
            return dblabelnummer.get();
        }

        /**
         * Doel: Geeft nieuwe waarde aan attribuut.
         *
         * @param dblabelnummer het nieuwe dblabelnummer
         */
        public void setDblabelnummer(String dblabelnummer) {
            this.dblabelnummer.set(dblabelnummer);
        }

        /**
         * Doel: Geeft waarde uit de database terug.
         *
         * @return het dbtype
         */
        public String getDbtype() {
            return dbtype.get();
        }

        /**
         * Doel: Geeft nieuwe waarde aan attribuut.
         *
         * @param dbtype het nieuwe dbtype
         */
        public void setDbtype(String dbtype) {
            this.dbtype.set(dbtype);
        }

        /**
         * Doel: Geeft waarde uit de database terug.
         *
         * @return het dbmerk
         */
        public String getDbmerk() {
            return dbmerk.get();
        }

        /**
         * Doel: Geeft nieuwe waarde aan attribuut.
         *
         * @param dbmerk het nieuwe dbmerk
         */
        public void setDbmerk(String dbmerk) {
            this.dbmerk.set(dbmerk);
        }

        /**
         * Doel: Geeft waarde uit de database terug.
         *
         * @return de dbkleur
         */
        public String getDbkleur() {
            return dbkleur.get();
        }

        /**
         * Doel: Geeft nieuwe waarde aan attribuut.
         *
         * @param dbkleur de nieuwe dbkleur
         */
        public void setDbkleur(String dbkleur) {
            this.dbkleur.set(dbkleur);
        }

        /**
         * Doel: Geeft waarde uit de database terug.
         *
         * @return de dbbijzonderekenmerken
         */
        public String getDbbijzonderekenmerken() {
            return dbbijzonderekenmerken.get();
        }

        /**
         * Doel: Geeft nieuwe waarde aan attribuut.
         *
         * @param dbbijzonderekenmerken de nieuwe dbbijzonderekenmerken
         */
        public void setDbbijzonderekenmerken(String dbbijzonderekenmerken) {
            this.dbbijzonderekenmerken.set(dbbijzonderekenmerken);
        }

        /**
         * Doel: Geeft waarde uit de database terug.
         *
         * @return de dbbestemming
         */
        public String getDbbestemming() {
            return dbbestemming.get();
        }

        /**
         * Doel: Geeft nieuwe waarde aan attribuut.
         *
         * @param dbbestemming de dbbestemmming
         */
        public void setDbbestemming(String dbbestemming) {
            this.dbluchthaven.set(dbbestemming);
        }

        /**
         * Doel: Geeft waarde uit de database terug.
         *
         * @return het verlorendID
         */
        public Integer getVerlorendID() {
            return verlorendID.get();
        }

        /**
         * Doel: Geeft nieuwe waarde aan attribuut.
         *
         * @param verlorendID het nieuwe verlorenID
         */
        public void setVerlorendID(Integer verlorendID) {
            this.verlorendID.set(verlorendID);
        }

    }

    /**
     * Doel: al de vliegvelden selecteren.
     *
     * @throws SQLException vang fouten op
     */
    private void selectFromVliegveldenAndBagage() throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        // Vraagt informatie op uit de database.
        String selectSQL = "SELECT * FROM fys.gevondenbagage INNER JOIN "
                + "fys.bagage ON gevondenbagage.bagage_id = bagage.bagage_id "
                + "INNER JOIN fys.vliegveld "
                + "ON gevondenbagage.vliegveld_id = vliegveld.vliegveld_id "
                + "WHERE gevondenbagage.gevondenbagage_id "
                + "NOT IN(SELECT gevondenbagage_id FROM gematchedebagage)";

        try {
            dbConnection = FYS.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);

            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();
            tableview.getColumns().clear();

            while (rs.next()) {
                Integer gevondenBagageID = rs.getInt("gevondenbagage_id");
                String dbluchthaven = rs.getString("naam_vliegveld");
                String dbvluchtnummer = rs.getString("vluchtnummer");
                String dblabelnummer = rs.getString("labelnummer");
                String dbtype = rs.getString("type");
                String dbmerk = rs.getString("merk");
                String dbkleur = rs.getString("kleur");
                String dbbijzonderekenmerken = rs.getString("bijzondere_kenmerken");
                String dbbestemming = rs.getString("bestemming");

                data.add(new Bagage(gevondenBagageID, dbluchthaven,
                        dbvluchtnummer, dblabelnummer, dbtype, dbmerk, dbkleur,
                        dbbijzonderekenmerken, dbbestemming));
            }

        } catch (SQLException e) {

            // vang fouten op
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    /**
     * Doel: selecteren nadat er op specifieke kenmerken gezocht wordt.
     *
     * @param zoekOp de zoekterm
     * @param zoekTerm de zoekterm
     * @throws SQLException vang fouten op
     */
    private void selectOpZoekActie(String zoekOp, String zoekTerm) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String selectSQL = null;

        //Switch kijk welke sql het gebruikt moet worden
        switch (zoekOp) {
            case "Bestemming":
                selectSQL = "SELECT * FROM fys.gevondenbagage INNER JOIN "
                        + "fys.bagage ON gevondenbagage.bagage_id = bagage.bagage_id "
                        + "INNER JOIN fys.vliegveld "
                        + "ON gevondenbagage.vliegveld_id = vliegveld.vliegveld_id "
                        + "WHERE gevondenbagage.gevondenbagage_id "
                        + "NOT IN(SELECT gevondenbagage_id FROM gematchedebagage) "
                        + "AND bestemming LIKE ?";
                break;
            case "Labelnummer":
                selectSQL = "SELECT * FROM fys.gevondenbagage INNER JOIN "
                        + "fys.bagage ON gevondenbagage.bagage_id = bagage.bagage_id"
                        + " INNER JOIN fys.vliegveld "
                        + "ON gevondenbagage.vliegveld_id = vliegveld.vliegveld_id"
                        + " WHERE gevondenbagage.gevondenbagage_id "
                        + "NOT IN(SELECT gevondenbagage_id FROM gematchedebagage) "
                        + "AND labelnummer LIKE ?";
                break;
            case "Vluchtnummer":
                selectSQL = "SELECT * FROM fys.gevondenbagage INNER JOIN "
                        + "fys.bagage ON gevondenbagage.bagage_id = bagage.bagage_id "
                        + "INNER JOIN fys.vliegveld ON gevondenbagage.vliegveld_id = "
                        + "vliegveld.vliegveld_id WHERE gevondenbagage.gevondenbagage_id "
                        + "NOT IN(SELECT gevondenbagage_id FROM gematchedebagage) "
                        + "AND vluchtnummer LIKE ?";
                break;
            case "Kleur":
                selectSQL = "SELECT * FROM fys.gevondenbagage INNER JOIN fys.bagage"
                        + " ON gevondenbagage.bagage_id = bagage.bagage_id INNER JOIN"
                        + " fys.vliegveld ON gevondenbagage.vliegveld_id = "
                        + "vliegveld.vliegveld_id WHERE gevondenbagage.gevondenbagage_id"
                        + " NOT IN(SELECT gevondenbagage_id FROM gematchedebagage)"
                        + " AND kleur LIKE ?";
                break;
            case "Type":
                selectSQL = "SELECT * FROM fys.gevondenbagage INNER JOIN fys.bagage "
                        + "ON gevondenbagage.bagage_id = bagage.bagage_id INNER JOIN"
                        + " fys.vliegveld ON gevondenbagage.vliegveld_id = "
                        + "vliegveld.vliegveld_id WHERE gevondenbagage.gevondenbagage_id "
                        + "NOT IN(SELECT gevondenbagage_id FROM gematchedebagage)"
                        + " AND type LIKE ?";
                break;
            case "Merk":
                selectSQL = "SELECT * FROM fys.gevondenbagage INNER JOIN fys.bagage "
                        + "ON gevondenbagage.bagage_id = bagage.bagage_id INNER "
                        + "JOIN fys.vliegveld ON gevondenbagage.vliegveld_id = "
                        + "vliegveld.vliegveld_id WHERE gevondenbagage.gevondenbagage_id"
                        + " NOT IN(SELECT gevondenbagage_id FROM gematchedebagage) "
                        + "AND merk LIKE ?";
                break;
            default:
                break;
        }
        try {
            dbConnection = FYS.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement.setString(1, "%" + zoekTerm + "%");

            // Voert SQL statement uit.
            ResultSet rs = preparedStatement.executeQuery();
            tableview.getColumns().clear();

            while (rs.next()) {
                Integer gevondenBagageID = rs.getInt("gevondenbagage_id");
                String dbluchthaven = rs.getString("naam_vliegveld");
                String dbvluchtnummer = rs.getString("vluchtnummer");
                String dblabelnummer = rs.getString("labelnummer");
                String dbtype = rs.getString("type");
                String dbmerk = rs.getString("merk");
                String dbkleur = rs.getString("kleur");
                String dbbijzonderekenmerken = rs.getString("bijzondere_kenmerken");
                String dbbestemming = rs.getString("bestemming");
                data.add(new Bagage(gevondenBagageID, dbluchthaven, dbvluchtnummer,
                        dblabelnummer, dbtype, dbmerk, dbkleur,
                        dbbijzonderekenmerken, dbbestemming));
            }
        } catch (SQLException e) {
            // Vangt errors op.
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    /**
     * Doel: de geselecteerde rij gebruiken
     */
    private class geselecteerdeRij implements ChangeListener<Number> {

        /**
         * Doel: vervang wanneer geldig de data
         *
         * @param ov de waarde
         * @param oldVal de oude waarde
         * @param newVal de nieuwe waarde
         */
        @Override
        public void changed(ObservableValue<? extends Number> ov,
                Number oldVal, Number newVal) {

            int ix = newVal.intValue();

            if ((ix < 0) || (ix >= data.size())) {

                return; // ongeldigde data
            }

            int gevondenBagageID = tableview.getSelectionModel()
                    .selectedItemProperty().getValue().verlorendID.intValue();
            gevondBagageLocatie = tableview.getSelectionModel()
                    .selectedItemProperty().getValue().getDbluchthaven();

            String merk = tableview.getSelectionModel()
                    .selectedItemProperty().getValue().getDbmerk();
            String labelnummer = tableview.getSelectionModel()
                    .selectedItemProperty().getValue().getDblabelnummer();

            labelnrLbl.setVisible(false);
            vluchtnummerLbl.setVisible(false);
            typeLbl.setVisible(false);
            merkLbl.setVisible(false);
            kleurLbl.setVisible(false);
            bijzondereKenmerkenLbl.setVisible(false);

            klantLabelnrLbl.setText("");
            klantVluchtnrLbl.setText("");
            klantTypeLbl.setText("");
            klantMerkLbl.setText("");
            klantKleurLbl.setText("");
            klantBijzondereKenmerkenLbl.setText("");
            klantLbl.setVisible(true);

            klantCombobox.getSelectionModel().clearSelection();
            klantCombobox.getItems().clear();
            klantCombobox.setVisible(true);
            klantZoeken.setVisible(true);
            try {
                selectFoto(gevondenBagageID);
                selectFromKlant(merk, labelnummer);
            } catch (SQLException | IOException ex) {
                Logger.getLogger(MedewerkerBagageZoekenController.class.getName()).log(Level.SEVERE, null, ex);
            }
            klantCombobox.setValue("");
            klantCombobox.setItems(klantData);
            matchBtn.setVisible(false);
        }
    }

    /**
     * Doel: Al de klanten selecteren
     *
     * @param type het type van de bagage van de klant
     * @param merk het merk van de bagage van de klant
     * @param labelnr de kleur van de bagage van de klant
     * @throws SQLException vang fouten op
     */
    private void selectFromKlant(String merk, String labelnr) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        // Vraagt informatie op uit de database.
        String SQL = "SELECT * FROM fys.klant INNER JOIN verlorenbagage "
                + "ON klant.klant_id = verlorenbagage.klant_id INNER JOIN bagage "
                + "ON verlorenbagage.bagage_id = bagage.bagage_id "
                + "WHERE (merk = ? OR labelnummer = ?) "
                + "AND verlorenbagage_id NOT IN(SELECT verlorenbagage_id "
                + "FROM gematchedebagage)";

        try {
            dbConnection = FYS.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(SQL);
            preparedStatement.setString(1, merk);
            preparedStatement.setString(2, labelnr);

            // Voert SQL statement uit.
            ResultSet rs = preparedStatement.executeQuery();

            klantCombobox.setValue(null);
            while (rs.next()) {
                int verlorenBagageID = rs.getInt("verlorenbagage_id");
                klantData.add(verlorenBagageID);
            }

        } catch (SQLException e) {

            // Vangt errors op.
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    /**
     * Selecteer de foto.
     *
     * @param verlorenBagageID het ID van de verloren bagage
     * @throws SQLException vang fouten op
     * @throws FileNotFoundException vang fouten op
     * @throws IOException vang fouten op
     */
    private void selectFoto(int verlorenBagageID) throws SQLException,
            FileNotFoundException, IOException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String selectSQL = "SELECT bagage.foto FROM fys.gevondenbagage "
                + "INNER JOIN fys.bagage ON gevondenbagage.bagage_id ="
                + " bagage.bagage_id WHERE gevondenbagage.gevondenbagage_id = ?";
        try {
            dbConnection = FYS.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, verlorenBagageID);

            // Voert SQL statement uit.
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                InputStream is = (InputStream) rs.getBinaryStream("foto");
                OutputStream os = new FileOutputStream(new File("logo.png"));
                byte[] foto = new byte[1024];
                int size = 0;
                while ((size = is.read(foto)) != -1) {
                    os.write(foto, 0, size);
                }
                os.close();
                is.close();

                image = new Image("file:logo.png");
                kofferImageView.setImage(image);

            }
        } catch (SQLException e) {
            // Vangt errors op.
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    /**
     * Selecteer de klant informatie.
     *
     * @param klantId het id van de klant van wie informatie wordt gezocht.
     * @throws SQLException vang fouten op
     */
    private void selectKlantZoekActie(int klantId) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String selectSQL = "SELECT * FROM fys.klant INNER JOIN fys.verlorenbagage "
                + "ON klant.klant_id = verlorenbagage.klant_id INNER JOIN "
                + "fys.bagage ON verlorenbagage.bagage_id = bagage.bagage_id "
                + "WHERE verlorenbagage.verlorenbagage_id = ?";

        try {
            dbConnection = FYS.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, klantId);

            // Voert SQL statement uit.
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                klantID = rs.getInt("klant_id");
                klantVluchtnr = rs.getString("vluchtnummer");
                klantBagageLabelnr = rs.getString("labelnummer");
                klantBagageType = rs.getString("type");
                klantBagageMerk = rs.getString("merk");
                klantBagageKleur = rs.getString("kleur");
                klantBagageBijzonderekenmerken = rs.getString("bijzondere_kenmerken");
                klantEmail = rs.getString("emailadres");
                String voornaam = rs.getString("naam");
                String tussenvoegsel = rs.getString("tussenvoegsel");
                String achternaam = rs.getString("achternaam");

                //controleer of er een tussen volgens er bij zit
                if (tussenvoegsel.length() > 0) {
                    klantNaam = voornaam + " " + tussenvoegsel + " " + achternaam;
                } else {
                    klantNaam = voornaam + " " + achternaam;
                }

                klantAdres = rs.getString("adres");
                klantWoonplaats = rs.getString("woonplaats");
                klantPostcode = rs.getString("postcode");
                klantLand = rs.getString("land");
                klantTelefoonnummer = rs.getString("telefoonnummer");
            }

        } catch (SQLException e) {
            // Vangt errors op.
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    /**
     * Gematchede bagage invoer in database en logboek actie toevoegen
     *
     * @param verlorenBagageId
     * @param gevondenBagageId
     * @param medewerkerId
     * @throws SQLException
     */
    private void insertGematchedeBagageEnLogboek(int verlorenBagageId,
            int gevondenBagageId, int medewerkerId) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;

        String sql = "INSERT INTO fys.gematchedebagage "
                + "(verlorenbagage_id, gevondenbagage_id) VALUES (?, ?)";
        String sql1 = "INSERT INTO logboek "
                + "(medewerker_id, actie, datum) VALUES (?, ?, ?)";

        try {
            dbConnection = FYS.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement1 = dbConnection.prepareStatement(sql1);

            preparedStatement.setInt(1, verlorenBagageId);
            preparedStatement.setInt(2, gevondenBagageId);

            preparedStatement1.setInt(1, medewerkerId);
            preparedStatement1.setString(2, "Medewerker heeft een match gemaakt "
                    + "tussen verlorenbagage met id " + verlorenBagageId
                    + " en gevondenbagage met id  " + gevondenBagageId);
            preparedStatement1.setString(3, FYS.getDate());

            // Voert SQL statement uit.
            preparedStatement.executeUpdate();
            preparedStatement1.executeUpdate();

            String to = klantEmail; // list of recipient email addresses
            final String subject = "U bagage is gevonden";
            final String body = "<img src=\"https://upload.wikimedia.org/wikipedia"
                    + "/en/thumb/3/36/Corendon_Airlines_2014_logo.svg/1280px-Corend"
                    + "on_Airlines_2014_logo.svg.png\" alt=\"Corendon\" style"
                    + "=\"width:200px;height:75px;\">\n"
                    + "<h1><font face=\"calibri\" color=\"black\">Beste klant,</font></h1><br>"
                    + "Wij hebben uw verloren bagage gevonden! We sturen het bagage stuk op naar het door u opgegeven adres. Excuses voor het ongemak, en we hopen uw terug te zien bij corendon. <br><br>"
                    + "Voor meer informatie kunt u inloggen met uw klant account of bellen met de klantenservice. <br><br>"
                    + "Met vriendelijke groeten,<br><br><br>"
                    + "Team <a href=\"https://www.corendon.com\">Corendon</a></font>";

            FYS.sendFromGMail(to, subject, body);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succes een match is gemaakt");
            alert.setHeaderText("Een match op verlorenbagage met id "
                    + verlorenBagageId + " en gevondenbagage met id  "
                    + gevondenBagageId + " is gemaakt");
            alert.setContentText("druk op \"ok\" voor een andere match");
            alert.showAndWait();

        } catch (SQLException e) {
            // Vangt errors op.
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (preparedStatement1 != null) {
                preparedStatement1.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    public void maakPDF() throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDFont font = PDType1Font.HELVETICA_BOLD;
        PDPageContentStream content = new PDPageContentStream(document, page);
        // vul de pdf met informatie
        content.beginText();
        content.setFont(font, 16);
        content.newLineAtOffset(20, 700);
        content.showText("Repatriatiebericht");
        content.endText();

        //klant informatie
        content.beginText();
        content.setFont(font, 12);
        content.setLeading(14.5f);
        content.newLineAtOffset(20, 675);
        content.showText("Klant informatie: ");
        content.newLine();
        content.showText("Naam: " + klantNaam);
        content.newLine();
        content.showText("Adres: " + klantAdres);
        content.newLine();
        content.showText("Postcode: " + klantPostcode);
        content.newLine();
        content.showText("Woonplaats: " + klantWoonplaats);
        content.newLine();
        content.showText("Land: " + klantLand);
        content.newLine();
        content.showText("Telefoon: " + klantTelefoonnummer);
        content.endText();

        //bagage informatie
        content.beginText();
        content.setFont(font, 12);
        content.setLeading(14.5f);
        content.newLineAtOffset(250, 675);
        content.showText("Bagage informatie: ");
        content.newLine();
        content.showText("Locatie bagage: " + gevondBagageLocatie);
        content.newLine();
        content.showText("Labelnummer: " + klantBagageLabelnr);
        content.newLine();
        content.showText("Merk: " + klantBagageMerk);
        content.newLine();
        content.showText("Type: " + klantBagageType);
        content.newLine();
        content.showText("Kleur: " + klantBagageKleur);
        content.endText();

        content.close();
        document.save("src/fys/view/PDF/RepatriatieBagage_" + klantID + ".pdf");
        document.close();

        Desktop.getDesktop().open(new File("src/fys/view/PDF/RepatriatieBagage_" + klantID + ".pdf"));
    }
}
