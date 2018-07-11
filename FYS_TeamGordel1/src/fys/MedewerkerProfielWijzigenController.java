package fys;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;

/**
 * FXML Controller Doel: Functies van de account wijzigen pagina maken.
 *
 * @author is102 - 3, GORDEL
 */
public class MedewerkerProfielWijzigenController implements Initializable {

    // Attributen
    FYS fys = new FYS();
    Language language = new Language();

    @FXML
    private PasswordField wachtwoordWijzigenHerhalen;
    @FXML
    private PasswordField wachtwoordWijzigen;
    @FXML
    private Text profielBewerken;
    @FXML
    private Button opslaan;

    private String loginPassword1;
    private String loginPassword2;
    private final int id = FYS.gebruikerID;
    int lang = fys.getLanguage();

    /**
     * Doel: Initialiseer de pagina.
     *
     * @param url URL
     * @param rb ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (lang == 2) {
            wachtwoordWijzigen.setPromptText(language.getMedewerkerProfielWijzigenPromptTextWachtwoordWijzigen());
            wachtwoordWijzigenHerhalen.setPromptText(language.getMedewerkerProfielWijzigenPromptTextWachtwoordWijzigenHerhalen());
            profielBewerken.setText(language.getMedewerkerProfielWijzigePromptTextProfielBewerken());
            opslaan.setText(language.getMedewerkerProfielWijzigePromptTextOpslaan());

        }
    }

    /**
     * Doel: Nieuwe wachtwoord opslaan en controleren of velden correct ingevuld
     * zijn.
     *
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    private void handleWijzigingenProfielOpslaanAction(ActionEvent event) throws IOException, SQLException {
        loginPassword2 = wachtwoordWijzigenHerhalen.getText();
        loginPassword1 = wachtwoordWijzigen.getText();

        // Controleert of wachtwoordvelden overeenkomen en niet leeg zijn.
        if (loginPassword1.trim().equals("")) {
            fys.nietIngevuldeVelden(wachtwoordWijzigen);
            if (lang == 2) {
                fys.maakNotifaction("Error", "Fill in both password fields!");
            } else {
                fys.maakNotifaction("Error", "Vul beide wachtwoord velden in!");
            }
        } else if (loginPassword2.trim().equals("")) {
            fys.nietIngevuldeVelden(wachtwoordWijzigenHerhalen);
            if (lang == 2) {
                fys.maakNotifaction("Error", "Fill in both password fields!");
            } else {
                fys.maakNotifaction("Error", "Vul beide wachtwoord velden in!");
            }
        } else if (!(loginPassword1.equals(loginPassword2))) {
            fys.nietIngevuldeVelden(wachtwoordWijzigen);
            fys.nietIngevuldeVelden(wachtwoordWijzigenHerhalen);
            if (lang == 2) {
                fys.maakNotifaction("Error", "The passwords do not match!");
            } else {
                fys.maakNotifaction("Error", "Wachtwoorden komen niet overeen!");
            }
        } else {
            slaWachtwoordOp(loginPassword1, id);
            if (lang == 2) {
                fys.changeToAnotherFXML("Corendon Lost & Found - Employee Profile",
                        "view/medewerker/MedewerkerProfiel.fxml");
            } else {
                fys.changeToAnotherFXML("Corendon Lost & Found - Medewerker Profiel",
                        "view/medewerker/MedewerkerProfiel.fxml");
            }
        }
    }

    /**
     * Doel: Nieuwe ingevoerde wachtwoord opslaan in database bij juiste
     * account.
     *
     * @param password
     * @param id
     * @throws SQLException
     */
    private static void slaWachtwoordOp(String password, int id) throws SQLException {
        Connection dbConnection = null;

        Statement stmt = null;
        String hashedPassword = String.valueOf(password.hashCode());
        String insertTableSQL = "UPDATE medewerker SET wachtwoord='" + hashedPassword
                + "' WHERE medewerker_id='" + id + "'";

        try {
            dbConnection = FYS.getDBConnection();
            stmt = dbConnection.createStatement();
            stmt.executeUpdate(insertTableSQL);

            dbConnection.close();

        } catch (SQLException e) {
            // Vangt errors op
        }
    }
}
