package fys;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

/**
 * FXML Controller Doel: Functies van de schadeclaim indienen pagina maken.
 *
 * @author is102 - 3, GORDEL
 */
public class KlantSchadeclaimIndienenController implements Initializable {

    // Attributen
    FYS fys = new FYS();
    Language language = new Language();

    @FXML
    private Label kofferIDLabel;
    @FXML
    private Text schadeclaimIndienenLbl;
    @FXML
    private Text idKofferLbl;
    @FXML
    private Text ibanLbl;
    @FXML
    private TextField ibanPrmptTxt, bedragPrmptTxt;
    @FXML
    private Text bedragLbl;
    @FXML
    private Text disclaimerLbl;
    @FXML
    private Button verstuurSchadeclaimBtn;
    @FXML
    private JFXButton statusVerlorenBagageBtn;
    @FXML
    private JFXButton statusSchadeclaimBtn;

    int lang = fys.getLanguage();
    private int verlorenBagageID;

    /**
     * Initialiseer de pagina, check of de klant al een schadeclaim heeft
     * ingediend, zo ja, laat de klant geen nieuwe schadeclaim indienen. Zo nee,
     * laadt de verlorenBagageID.
     *
     * @param url URL
     * @param rb Resources
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (lang == 2) {
            schadeclaimIndienenLbl.setText(language.getKlantSchadeclaimIndienenLabelSubmitClaim());
            idKofferLbl.setText(language.getKlantSchadeclaimIndienenLabelIdLuggage());
            ibanLbl.setText(language.getKlantSchadeclaimIndienenLabelIban());
            ibanPrmptTxt.setPromptText(language.getKlantSchadeclaimIndienenPromptTextIban());
            verstuurSchadeclaimBtn.setText(language.getKlantSchadeclaimIndienenButtonSubmitClaim());
            bedragPrmptTxt.setPromptText(language.getKlantSchadeclaimIndienenPromptTextAmount());
            bedragLbl.setText(language.getKlantSchadeclaimIndienLabelAmount());
            disclaimerLbl.setText(language.getKlantSchadeclaimIndienenLabelDisclaimer());
            statusVerlorenBagageBtn.setText(language.getKlantSchadeclaimIndienenStatusLuggageBtn());
            statusSchadeclaimBtn.setText(language.getKlantSchadeclaimIndienenStatusClaimsBtn());
        }
        if (lockCheck()) {
            verstuurSchadeclaimBtn.setDisable(true);
            verstuurSchadeclaimBtn.setVisible(false);
            bedragLbl.setVisible(false);
            bedragPrmptTxt.setVisible(false);
            ibanLbl.setVisible(false);
            ibanPrmptTxt.setVisible(false);
            kofferIDLabel.setVisible(false);
            idKofferLbl.setVisible(false);
            disclaimerLbl.setVisible(false);
            if (lang == 2) {
                schadeclaimIndienenLbl.setText("See Status Insurance Claim.");
            } else {
                schadeclaimIndienenLbl.setText("Zie Status Schadeclaim.");
            }

        } else {
            Connection dbConnection = null;
            Statement stmt = null;
            String datum, verlorenDatum;
            boolean jaarGeleden = false;
            int id = fys.getGebruikerID();
            String selectTableSQL = "SELECT * "
                    + "FROM verlorenbagage "
                    + "WHERE klant_id=" + id + ";";
            try {
                dbConnection = FYS.getDBConnection();
                stmt = dbConnection.createStatement();

                ResultSet rs = stmt.executeQuery(selectTableSQL);

                if (rs.next()) {
                    verlorenBagageID = rs.getInt("verlorenbagage_id");
                    verlorenDatum = rs.getString("datum_verloren");
                    datum = FYS.getDate();
                    jaarGeleden = jaarGeleden(verlorenDatum, datum);
                }

                // als de koffer meer dan een jaar geleden verloren is, laat de 
                // klant een schadeclaim indienen, anders niet.
                if (jaarGeleden) {
                    verstuurSchadeclaimBtn.setVisible(true);
                    verstuurSchadeclaimBtn.setDisable(false);
                    ibanPrmptTxt.setVisible(true);
                    bedragPrmptTxt.setVisible(true);
                    ibanLbl.setVisible(true);
                    schadeclaimIndienenLbl.setVisible(true);
                    kofferIDLabel.setText(String.valueOf(verlorenBagageID));
                } else {
                    verstuurSchadeclaimBtn.setVisible(false);
                    ibanPrmptTxt.setVisible(false);
                    bedragPrmptTxt.setVisible(false);
                    ibanLbl.setVisible(false);
                    bedragLbl.setVisible(false);
                    if (lang == 2) {
                        kofferIDLabel.setText("No luggage ID.");
                    } else {
                        kofferIDLabel.setText("Geen koffer ID.");
                    }

                }

            } catch (SQLException e) {
                // vangt de fouten op
            } finally {

                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(KlantSchadeclaimsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                if (dbConnection != null) {
                    try {
                        dbConnection.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(KlantSchadeclaimsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    /**
     * Doel: Wanneer op de knop wordt gedrukt, sla de ingevulde gegevens op in
     * de schadeclaims tabel.
     *
     * @param event klik op de knop
     * @throws SQLException vangt fouten op.
     */
    @FXML
    private void handleVerstuurSchadeclaimAction(ActionEvent event) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String ibanWaarde = ibanPrmptTxt.getText().trim();
        String bedragString = bedragPrmptTxt.getText().trim();
        if (!ibanWaarde.trim().isEmpty() || !bedragString.trim().isEmpty()) {
            int bedragWaarde = Integer.parseInt(bedragString.replaceAll("[\\D]", ""));

            String date = FYS.getDate();

            String insertTableSQL = "INSERT INTO schadeclaim (datum, verlorenbagage_id, status, IBAN, bedrag) VALUES (?, ?, ?, ?, ?)";
            try {
                dbConnection = FYS.getDBConnection();
                preparedStatement = dbConnection.prepareStatement(insertTableSQL);

                preparedStatement.setString(1, date);
                preparedStatement.setInt(2, verlorenBagageID);
                preparedStatement.setInt(3, 1);
                preparedStatement.setString(4, ibanWaarde);
                preparedStatement.setInt(5, bedragWaarde);
                preparedStatement.executeUpdate();

                if (lang == 2) {
                    fys.maakNotifaction("Confirm", "Your insurance claim is being processed, you can exit this page now.");
                } else {
                    fys.maakNotifaction("Confirm", "Uw schadeclaim is ingediend, u kunt de pagina verlaten.");
                }
                verstuurSchadeclaimBtn.setDisable(true);

            } catch (SQLException e) {
                // geeft een waarschuwing
                if (lang == 2) {
                    fys.maakNotifaction("Error", "Something went wrong, please try again.");
                } else {
                    fys.maakNotifaction("Error", "Er is iets mis gegaan, probeer het opnieuw.");
                }
            } finally {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (dbConnection != null) {
                    dbConnection.close();
                }
            }
        } else // geeft een waarschuwing
         if (lang == 2) {
                fys.maakNotifaction("Error", "Fill in both textfields.");
            } else {
                fys.maakNotifaction("Error", "Voer beide velden in.");
            }
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
     * Doel: vergelijken van twee data.
     *
     * @param datumVerloren de datum waarom de bagage is verloren
     * @param datumNu de huidige datum
     * @return of de bagage meer dan een jaar geleden verloren is.
     */
    public boolean jaarGeleden(String datumVerloren, String datumNu) {
        String verlorenIntString = "" + datumVerloren.charAt(0)
                + datumVerloren.charAt(1) + datumVerloren.charAt(2)
                + datumVerloren.charAt(3) + datumVerloren.charAt(5)
                + datumVerloren.charAt(6) + datumVerloren.charAt(8)
                + datumVerloren.charAt(9);
        int verlorenInt = Integer.parseInt(verlorenIntString);
        String nuIntString = "" + datumNu.charAt(0)
                + datumNu.charAt(1) + datumNu.charAt(2)
                + datumNu.charAt(3) + datumNu.charAt(5)
                + datumNu.charAt(6) + datumNu.charAt(8)
                + datumNu.charAt(9);
        int nuInt = Integer.parseInt(nuIntString);
        int datumVerschil = nuInt - verlorenInt;
        if (datumVerschil > 10000) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Doel: Controleer of de gebruiker al een schadclaim heeft ingediend.
     *
     * @return of de gebruiker al een schadeclaim heeft ingediend.
     */
    public boolean lockCheck() {
        Connection dbConnection = null;
        Statement stmt = null;
        int id = fys.getGebruikerID();
        String selectTableSQL = "SELECT * "
                + "FROM verlorenbagage INNER "
                + "JOIN schadeclaim "
                + "ON verlorenbagage.verlorenbagage_id=schadeclaim.verlorenbagage_id "
                + "WHERE klant_id=" + id + ";";
        try {
            dbConnection = FYS.getDBConnection();
            stmt = dbConnection.createStatement();

            ResultSet rs = stmt.executeQuery(selectTableSQL);
            // als er een schadeclaim is return true
            if (rs.next()) {
                return rs.getInt("schadeclaim_id") > 0;
            }
        } catch (SQLException e) {
            return false;
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
        return false;
    }
}
