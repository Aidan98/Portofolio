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
 * FXML Controller Doel: De functies van de schadeclaim maken.
 *
 * @author is102 - 3, GORDEL
 */
public class KlantSchadeclaimsController implements Initializable {

    // Attributen
    FYS fys = new FYS();
    Language language = new Language();

    @FXML
    private Text schadeclaimIDLabel;
    @FXML
    private Label statusText;
    @FXML
    private JFXButton statusVerlorenBagageBtn;
    @FXML
    private JFXButton indiendenSchadeclaimBtn;

    int lang = fys.getLanguage();

    /**
     * Doel: Initialiseer de pagina. Controleer of de gebruiker een schadeclaim
     * heeft ingediend, en toon de status.
     *
     * @param url URL
     * @param rb ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (lang == 2) {
            statusVerlorenBagageBtn.setText(language.getKlantHomeButtonStatusLuggage());
            indiendenSchadeclaimBtn.setText(language.getKlantHomeButtonSubmitClaim());
            schadeclaimIDLabel.setText(language.getKlantSchadeclaimLabelClaimId());
        }
        int id = fys.getGebruikerID();
        Connection dbConnection = null;
        Statement stmt = null;
        String selectTableSQL = "SELECT * "
                + "FROM schadeclaim "
                + "INNER JOIN verlorenbagage "
                + "ON schadeclaim.verlorenbagage_id=verlorenbagage.verlorenbagage_id "
                + "WHERE verlorenbagage.klant_id=" + id + ";";
        try {
            dbConnection = FYS.getDBConnection();
            stmt = dbConnection.createStatement();

            ResultSet rs = stmt.executeQuery(selectTableSQL);

            if (rs.next()) {
                // als er een schadeclaim is, toon de status.
                if (lang == 2) {
                    int schadeclaimStatus = rs.getInt("status");
                    int schadeclaimID = rs.getInt("schadeclaim_id");
                    schadeclaimIDLabel.setText("Claim ID: " + Integer.toString(schadeclaimID));

                    String status = bepaalStatus(schadeclaimStatus);
                    statusText.setText(status);
                } else {
                    int schadeclaimStatus = rs.getInt("status");
                    int schadeclaimID = rs.getInt("schadeclaim_id");
                    schadeclaimIDLabel.setText("Schadeclaim ID: " + Integer.toString(schadeclaimID));

                    String status = bepaalStatus(schadeclaimStatus);
                    statusText.setText(status);

                }
            } else if (lang == 2) {
                schadeclaimIDLabel.setText("No ID");
                statusText.setText("There is no status to show.");
            } else {
                schadeclaimIDLabel.setText("Geen ID");
                statusText.setText("Er is geen status weer te geven.");
            }
        } catch (SQLException e) {
            // vangt de fouten op.
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KlantSchadeclaimsController.class.
                            getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KlantSchadeclaimsController.class.
                            getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Doel: Het statusnummer omzetten in een zin.
     *
     * @param schadeclaimStatus het nummer van de status.
     * @return een string met de status
     */
    public String bepaalStatus(int schadeclaimStatus) {
        String text = "";
        if (lang == 2) {
            switch (schadeclaimStatus) {
                case 1:
                    text = "Your claim is being reviewed.";
                    break;
                case 2:
                    text = "Your claim has been accepted .\nThe amount has been sent to your bank account.";
                    break;
                case 3:
                    text = "Your claim has been declined.\nReason: Corendon does not agree with claimed amount.\n To submit a complaint please contact Corendon help desk.";
                    break;
                case 4:
                    text = "Your claim has been declined.\nReason: Corendon considers this as an invalid claim.\nTo submit a complaint please contact Corendon help desk.";
                    break;
            }
        } else {
            switch (schadeclaimStatus) {
                case 1:
                    text = "Uw schadeclaim is in behandeling.";
                    break;
                case 2:
                    text = "Uw schadeclaim is goedgekeurd.\nHet bedrag is naar uw rekening over gemaakt.";
                    break;
                case 3:
                    text = "Uw schadeclaim is afgekeurd.\nDe reden van afkeuring is: Corendon is het niet eens met het gevraagde bedrag.\nOm bezwaar te maken neem contact op met de Corendon ServiceBalie.";
                    break;
                case 4:
                    text = "Uw schadeclaim is afgekeurd.\nDe reden van afkeuring is: Corendon acht dit geen geldige schadeclaim.\nOm bezwaar te maken neem contact op met de Corendon ServiceBalie.";
                    break;
            }
        }
        return text;
    }

    /**
     * Doel: Gaan naar een nieuwe scene.
     *
     * @param event klik op de knop
     * @throws IOException vangt fouten op.
     */
    @FXML
    private void handleInzienStatusVerlorenBtnAction(ActionEvent event) throws IOException {
        if (lang == 2) {
            fys.changeToAnotherFXML("Corendon Lost & Found - View status lost luggage",
                    "view/klant/KlantInzienStatus.fxml");
        } else {
            fys.changeToAnotherFXML("Corendon Lost & Found - Inzien status verloren bagage",
                    "view/klant/KlantInzienStatus.fxml");
        }
    }

    /**
     * Doel: Gaan naar een nieuwe scene.
     *
     * @param event klik op de knop
     * @throws IOException vangt fouten op.
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
}
