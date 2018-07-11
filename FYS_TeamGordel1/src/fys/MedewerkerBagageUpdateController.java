package fys;

import com.jfoenix.controls.JFXButton;
import com.mysql.jdbc.Connection;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller Doel: Het updaten van bagage
 *
 * @author is102 - 3, GORDEL
 */
public class MedewerkerBagageUpdateController implements Initializable {

    // Attributen
    Language language = new Language();
    FYS fys = new FYS();
    @FXML
    private TextField labelnummerTxt, vluchtnummerTxt, bestemmingTxt, typeTxt, merkTxt, kleurTxt;
    @FXML
    private TextArea bijzondereKenmerkenTxt;
    @FXML
    private ComboBox<String> bagageUpdateCombobox, statusCombobox;
    @FXML
    private Label bagageIdLbl;
    @FXML
    private Button slaOpBtn;
    @FXML
    private Label updateBagage;
    @FXML
    private JFXButton zoekenBtn;
    @FXML
    private Text bagageId;
    @FXML
    private Label bagageLabelInformatie;
    @FXML
    private Label bagageInformatie;

    private final ObservableList<String> statusList = FXCollections.observableArrayList("Gevonden bagage", "Verloren bagage");
    private final ObservableList<String> bagageList = FXCollections.observableArrayList();
    private String bestemming;
    private String bijzondereKenmerken;
    private String merkKoffer;
    private String kleurKoffer;
    private String typeKoffer;
    private String vluchtnummer;
    private String labelnummer;
    private String status;
    private int kofferID;
    int lang = fys.getLanguage();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (lang == 2) {
            updateBagage.setText(language.getMedewerkerBagageUpdateLabeltUpdateBagage());
            bagageLabelInformatie.setText(language.getMedewerkerBagageUpdateLabelBagageLabelInformatie());
            bagageInformatie.setText(language.getMedewerkerBagageUpdateLabelBagageInformatie());
            zoekenBtn.setText(language.getMedewerkerBagageUpdateButtonZoekenBtn());
            bagageId.setText(language.getMedewerkerBagageUpdatePromptTextBagageId());
            labelnummerTxt.setPromptText(language.getMedewerkerBagageUpdatePromptTextLabelnummerTxt());
            vluchtnummerTxt.setPromptText(language.getMedewerkerBagageUpdatePromptTextVluchtnummerTxt());
            bestemmingTxt.setPromptText(language.getMedewerkerBagageUpdatePromptTextBestemmingTxt());
            typeTxt.setPromptText(language.getMedewerkerBagageUpdatePromptTextTypeTxt());
            merkTxt.setPromptText(language.getMedewerkerBagageUpdatePromptTextMerkTxt());
            kleurTxt.setPromptText(language.getMedewerkerBagageUpdatePromptTextKleurTxt());
            bijzondereKenmerkenTxt.setPromptText(language.getMedewerkerBagageUpdatePromptTextBijzondereKenmerkenTxt());
            statusCombobox.setPromptText(language.getMedewerkerBagageUpdatePromptTextStatusCombox());
            slaOpBtn.setText(language.getMedewerkerBagageUpdateButtonSlaOpBtn());
        }
        statusCombobox.setItems(statusList);
    }

    /**
     *
     * @param event
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    private void handleSlaOpBtnAction(ActionEvent event) throws SQLException, IOException {
        String labelnr = labelnummerTxt.getText();
        String vluchtnr = vluchtnummerTxt.getText();
        String destination = bestemmingTxt.getText();
        String type = typeTxt.getText();
        String merk = merkTxt.getText();
        String kleur = kleurTxt.getText();
        String bijzonderKenmerekn = bijzondereKenmerkenTxt.getText();

        updateBagage(labelnr, vluchtnr, destination, type, merk, kleur, bijzonderKenmerekn, kofferID);
        if (lang == 2) {
            fys.changeToAnotherFXML("Corendon Lost & Found - Employee luggage edit",
                    "view/medewerker/MedewerkerBagageUpdate.fxml");
        } else {
            fys.changeToAnotherFXML("Corendon Lost & Found - Medewerker bagage bewerken",
                    "view/medewerker/MedewerkerBagageUpdate.fxml");
        }
    }

    /**
     *
     * @param event
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    private void handleZoekenBagageAction(ActionEvent event) throws SQLException, IOException {
        if (!bagageUpdateCombobox.getValue().isEmpty()) {
            labelnummerTxt.setDisable(false);
            vluchtnummerTxt.setDisable(false);
            bestemmingTxt.setDisable(false);
            typeTxt.setDisable(false);
            merkTxt.setDisable(false);
            kleurTxt.setDisable(false);
            bijzondereKenmerkenTxt.setDisable(false);

            String comboWaarde = (String) bagageUpdateCombobox.getValue();
            kofferID = Integer.parseInt(comboWaarde);

            if (status.equals("Gevonden bagage")) {
                selectFromBagage(kofferID, "Zoeken " + status);
            } else {
                selectFromBagage(kofferID, "Zoeken " + status);
            }

            bagageIdLbl.setText(comboWaarde);
            labelnummerTxt.setText(labelnummer);
            vluchtnummerTxt.setText(vluchtnummer);
            bestemmingTxt.setText(bestemming);
            typeTxt.setText(typeKoffer);
            merkTxt.setText(merkKoffer);
            kleurTxt.setText(kleurKoffer);
            bijzondereKenmerkenTxt.setText(bijzondereKenmerken);

            slaOpBtn.setDisable(false);
        }
    }

    /**
     *
     * @param event
     * @throws SQLException
     * @throws IOException
     */
    @FXML
    private void handleStatusAction(ActionEvent event) throws SQLException, IOException {
        slaOpBtn.setDisable(true);

        bagageIdLbl.setText("");
        labelnummerTxt.setText("");
        vluchtnummerTxt.setText("");
        bestemmingTxt.setText("");
        typeTxt.setText("");
        merkTxt.setText("");
        kleurTxt.setText("");
        bijzondereKenmerkenTxt.setText("");

        labelnummerTxt.setDisable(true);
        vluchtnummerTxt.setDisable(true);
        bestemmingTxt.setDisable(true);
        typeTxt.setDisable(true);
        merkTxt.setDisable(true);
        kleurTxt.setDisable(true);
        bijzondereKenmerkenTxt.setDisable(true);

        status = statusCombobox.getValue();
        bagageUpdateCombobox.getItems().clear();
        if (status.equals("Gevonden bagage")) {
            selectFromBagage(0, status);
            bagageUpdateCombobox.setValue("");
            bagageUpdateCombobox.setItems(bagageList);
        } else {
            selectFromBagage(0, status);
            bagageUpdateCombobox.setValue("");
            bagageUpdateCombobox.setItems(bagageList);
        }
    }

    /**
     *
     * @param bagageID
     * @param omschrijving
     * @throws SQLException
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void selectFromBagage(int bagageID, String omschrijving) throws SQLException, FileNotFoundException, IOException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String selectSQL = null;
        switch (omschrijving) {
            case "Gevonden bagage":
                selectSQL = "SELECT * FROM fys.bagage inner join gevondenbagage on bagage.bagage_id = gevondenbagage.bagage_id WHERE gevondenbagage_id NOT IN (select gevondenbagage_id from gematchedebagage) AND ((DATEDIFF(now(), datum_gevonden)) < 365)";
                break;
            case "Verloren bagage":
                selectSQL = "SELECT * FROM fys.bagage inner join verlorenbagage on bagage.bagage_id = verlorenbagage.bagage_id WHERE verlorenbagage_id NOT IN (select verlorenbagage_id from gematchedebagage) AND verlorenbagage_id NOT IN (select verlorenbagage_id from schadeclaim)";
                break;
            case "Zoeken Gevonden bagage":
                selectSQL = "SELECT * FROM fys.bagage inner join gevondenbagage on bagage.bagage_id = gevondenbagage.bagage_id WHERE gevondenbagage_id NOT IN (select gevondenbagage_id from gematchedebagage) AND ((DATEDIFF(now(), datum_gevonden)) < 365) AND bagage.bagage_id = ?";
                break;
            case "Zoeken Verloren bagage":
                selectSQL = "SELECT * FROM fys.bagage inner join verlorenbagage on bagage.bagage_id = verlorenbagage.bagage_id WHERE verlorenbagage_id NOT IN (select verlorenbagage_id from gematchedebagage) AND verlorenbagage_id NOT IN (select verlorenbagage_id from schadeclaim) AND bagage.bagage_id = ?";
                break;
            default:
                break;
        }

        try {
            dbConnection = (Connection) FYS.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);

            if ("Zoeken Gevonden bagage".equals(omschrijving) || "Zoeken Verloren bagage".equals(omschrijving)) {
                preparedStatement.setInt(1, bagageID);
            }

            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                bagageID = rs.getInt("bagage_id");
                bestemming = rs.getString("bestemming");
                bijzondereKenmerken = rs.getString("bijzondere_kenmerken");
                merkKoffer = rs.getString("merk");
                kleurKoffer = rs.getString("kleur");
                typeKoffer = rs.getString("type");
                vluchtnummer = rs.getString("vluchtnummer");
                labelnummer = rs.getString("labelnummer");

                if ("Gevonden bagage".equals(omschrijving) || "Verloren bagage".equals(omschrijving)) {
                    String bagage = String.valueOf(bagageID);
                    bagageList.add(bagage);
                }
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
     *
     * @param labelnr
     * @param vluchtnr
     * @param bestemming
     * @param type
     * @param merk
     * @param kleur
     * @param bijzondereKenmerken
     * @param bagageID
     * @throws SQLException
     */
    private void updateBagage(String labelnr, String vluchtnr, String bestemming, String type, String merk, String kleur, String bijzondereKenmerken, int bagageID) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String updateTableSQL = "UPDATE bagage SET labelnummer = ?, vluchtnummer = ?, "
                + "bestemming = ?, type = ?, merk = ?, kleur = ?, bijzondere_kenmerken = ? WHERE bagage_id = ?;";

        try {
            dbConnection = (Connection) FYS.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(updateTableSQL);

            preparedStatement.setString(1, labelnr);
            preparedStatement.setString(2, vluchtnr);
            preparedStatement.setString(3, bestemming);
            preparedStatement.setString(4, type);
            preparedStatement.setString(5, merk);
            preparedStatement.setString(6, kleur);
            preparedStatement.setString(7, bijzondereKenmerken);
            preparedStatement.setInt(8, bagageID);

            // execute update SQL stetement
            preparedStatement.executeUpdate();

            updateLogboek(bagageID);

            //alert bij succes
            if (lang == 2) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Success!");
                alert.setHeaderText("The luggage has been updated.");
                alert.setContentText("Press on \"ok\" to continue.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Gelukt!");
                alert.setHeaderText("De bagage is geupdate.");
                alert.setContentText("Druk op \"ok\" om door te gaan.");
                alert.showAndWait();
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
     *
     * @param bagageID
     * @throws SQLException
     */
    private void updateLogboek(int bagageID) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String insertTableSQL = "INSERT INTO logboek (medewerker_id, actie, datum) VALUES (?, ?, ?);";

        try {
            dbConnection = (Connection) FYS.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);

            preparedStatement.setInt(1, fys.getGebruikerID());
            preparedStatement.setString(2, "Bagage met id " + bagageID + " geüpdate door medewerker met id " + fys.getGebruikerID());
            preparedStatement.setString(3, FYS.getDate());

            preparedStatement.executeUpdate();

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
}
