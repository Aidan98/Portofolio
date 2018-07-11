package fys;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

/**
 * FXML Controller Doel: Functies van de inzien status bagage pagina maken
 *
 * @author is102 - 3, GORDEL
 */
public class KlantInzienStatusController implements Initializable {

    // Attributen
    Language language = new Language();
    FYS fys = new FYS();

    @FXML
    private Text bagageIDLabel;
    @FXML
    private Label bagageInformatieText;
    @FXML
    private JFXButton indiendenSchadeclaimBtn;
    @FXML
    private JFXButton statusSchadeclaimBtn;

    int lang = fys.getLanguage();

    /**
     * Doel: Initialiseer de pagina. Kijk in de database of er verloren bagage
     * is gekoppeld aan de klant, kijk of de verloren bagage ook in de
     * gematchede bagage tabel staat.
     *
     * @param url URL
     * @param rb ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (lang == 2) {
            indiendenSchadeclaimBtn.setText(language.getKlantHomeButtonSubmitClaim());
            statusSchadeclaimBtn.setText(language.getKlantHomeButtonStatusClaims());
            bagageIDLabel.setText(language.getKlantInzienStatusLabelLuggageId());
        }
        Connection dbConnection = null;
        Statement stmt = null;
        int id = fys.getGebruikerID(), verlorenBagageID;

        String verlorenBagageIDString, status;

        //de queries
        String selectTableSQL = "SELECT verlorenbagage_id "
                + "FROM verlorenbagage "
                + "WHERE klant_id=" + id + ";";
        String selectTableSQL2 = "SELECT * FROM verlorenbagage "
                + "INNER JOIN gematchedebagage "
                + "ON verlorenbagage.verlorenbagage_id="
                + "gematchedebagage.verlorenbagage_id "
                + "WHERE verlorenbagage.klant_id=" + id + ";";

        try {
            // set the ID label
            dbConnection = FYS.getDBConnection();
            stmt = dbConnection.createStatement();

            // execute select SQL statement
            ResultSet rs = stmt.executeQuery(selectTableSQL);
            if (rs.next()) {
                verlorenBagageID = rs.getInt("verlorenbagage_id");
                if (lang == 2) {
                    verlorenBagageIDString = "Lost luggage ID: "
                            + String.valueOf(verlorenBagageID);
                } else {
                    verlorenBagageIDString = "Verloren bagage ID: "
                            + String.valueOf(verlorenBagageID);
                }
            } else if (lang == 2) {
                verlorenBagageIDString = "Oops something went wrong, "
                        + "Splease contact the Corendon help desk.";
            } else {
                verlorenBagageIDString = "Er is iets fout gegaan, neem contact "
                        + "op met Corendon informatie.";
            }
            bagageIDLabel.setText(verlorenBagageIDString);

            // haal het bagageID uit de database.
            rs = stmt.executeQuery(selectTableSQL2);
            if (rs.next()) {
                int testID = rs.getInt("verlorenbagage_id");
                // geef de status weer.
                if (testID > 0) {
                    if (lang == 2) {
                        status = "Your luggage has been found, your luggage "
                                + "will be sent to you as soon as possible.";
                    } else {
                        status = "Uw koffer is gevonden, uw koffer wordt zo snel "
                                + "mogelijk naar u verstuurd.";
                    }
                } else if (lang == 2) {
                    status = "Your luggage hasn't been found yet.";
                } else {
                    status = "Uw koffer is nog niet gevonden.";
                }
            } else if (lang == 2) {
                status = "Your luggage hasn't been found yet.";
            } else {
                status = "Uw koffer is nog niet gevonden.";
            }
            bagageInformatieText.setText(status);

        } catch (SQLException e) {
            // vangt errors op
        } finally {

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KlantSchadeclaimsController.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
            }

            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KlantSchadeclaimsController.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Doel: Gaan naar een nieuwe scene.
     *
     * @param event klik op de knop
     * @throws IOException vangt fouten op
     */
    @FXML
    private void handleIndienenSchadeclaimAction(ActionEvent event) throws IOException {
        if (lang == 2) {
            fys.changeToAnotherFXML("Corendon Lost & Found - Submit insurance claim",
                    "view/klant/KlantSchadeclaimIndienen.fxml");
        } else {
            fys.changeToAnotherFXML("Corendon Lost & Found - Indienen schadeclaim",
                    "view/klant/KlantSchadeclaimIndienen.fxml");
        }
    }

    /**
     * Doel: Gaan naar een nieuwe scene.
     *
     * @param event klik op de knop
     * @throws IOException vangt fouten op.
     */
    @FXML
    private void handleStatusSchadeclaimAction(ActionEvent event) throws IOException {
        if (lang == 2) {
            fys.changeToAnotherFXML("Corendon Lost & Found - View status insurance claim",
                    "view/klant/KlantSchadeclaimsStatus.fxml");
        } else {
            fys.changeToAnotherFXML("Corendon Lost & Found - Inzien status schadeclaim",
                    "view/klant/KlantSchadeclaimsStatus.fxml");
        }
    }

    /**
     * Doel: Gaan naar een nieuwe scene.
     *
     * @param event klik op de knop
     * @throws IOException vangt fouten op.
     */
    private void bekijkInfoBtnAction(ActionEvent event) throws IOException {
        if (lang == 2) {
            fys.changeToAnotherFXML("Corendon Lost & Found - Lost luggage information ",
                    "view/klant/KlantVerlorenKofferInformatie.fxml");
        } else {
            fys.changeToAnotherFXML("Corendon Lost & Found - Verloren koffer informatie",
                    "view/klant/KlantVerlorenKofferInformatie.fxml");
        }
    }
}
