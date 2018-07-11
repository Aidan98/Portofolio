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
 * FXML Controller Doel: De functies van het profiel bewerken maken.
 *
 * @author is102 - 3, GORDEL
 */
public class ManagerProfileBewerkenController implements Initializable {

    // Attributen
    FYS fys = new FYS();
    Language language = new Language();
    @FXML
    private PasswordField wachtwoordTxtField;
    @FXML
    private PasswordField wachtwoord2TxtField;
    @FXML
    private Text managerProfielBewLbl;
    @FXML
    private Button managerSaveBtn;

    private String loginPassword1;
    private String loginPassword2;
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
            managerProfielBewLbl.setText(language.getManagerProfielBewerkenLabelEditProfile());
            wachtwoordTxtField.setPromptText(language.getManagerProfielBewerkenPromptTextPassword());
            wachtwoord2TxtField.setPromptText(language.getManagerProfielBewerkenPromptTextRepeatPassword());
            managerSaveBtn.setText(language.getManagerProfielBewerkenButtonSave());
        }
    }

    /**
     * Doel: wanneer op opslaan wordt gedrukt controleer of de data correct is
     * en zo ja, sla deze op.
     *
     * @param event klik op de knop
     * @throws SQLException vang fouten op
     * @throws IOException vang fouten op
     */
    @FXML
    private void handleOpslaanBtnAction(ActionEvent event) throws SQLException, IOException {

        loginPassword1 = wachtwoordTxtField.getText();
        loginPassword2 = wachtwoord2TxtField.getText();

        // Controleert of wachtwoordvelden overeenkomen en niet leeg zijn.
        if (loginPassword1.trim().equals("")) {
            fys.nietIngevuldeVelden(wachtwoordTxtField);
            if (lang == 2) {
                fys.maakNotifaction("Error", "Fill in both password fields!");
            } else {
                fys.maakNotifaction("Error", "Vul beide wachtwoord velden in!");
            }
        } else if (loginPassword2.trim().equals("")) {
            fys.nietIngevuldeVelden(wachtwoord2TxtField);
            if (lang == 2) {
                fys.maakNotifaction("Error", "Fill in both password fields!");
            } else {
                fys.maakNotifaction("Error", "Vul beide wachtwoord velden in!");
            }
        } else if (!(loginPassword1.equals(loginPassword2))) {
            fys.nietIngevuldeVelden(wachtwoordTxtField);
            fys.nietIngevuldeVelden(wachtwoord2TxtField);
            if (lang == 2) {
                fys.maakNotifaction("Error", "The passwords do not match!");
            } else {
                fys.maakNotifaction("Error", "Wachtwoorden komen niet overeen!");
            }
        } else {
            int id = fys.getGebruikerID();
            slaWachtwoordOp(loginPassword1, id);
            if (lang == 2) {
                fys.changeToAnotherFXML("Corendon Lost & Found - Employee edit"
                        + " profile", "view/manager/ManagerProfiel.fxml");
            } else {
                fys.changeToAnotherFXML("Corendon Lost & Found - Medewerker Profiel"
                        + " Wijzigen", "view/manager/ManagerProfiel.fxml");
            }
        }
    }

    /**
     * Doel: Sla het nieuwe wachtwoord op in de database.
     *
     * @param password het nieuwe wachtwoord
     * @param id het ID van het account wat moet worden geupdate
     * @throws SQLException
     */
    private static void slaWachtwoordOp(String password, int id) throws SQLException {
        Connection dbConnection = null;

        Statement stmt = null;

        String hashedPassword = String.valueOf(password.hashCode());
        String insertTableSQL = "UPDATE medewerker SET wachtwoord='"
                + hashedPassword + "' WHERE medewerker_id='" + id + "'";
        try {
            dbConnection = FYS.getDBConnection();
            stmt = dbConnection.createStatement();
            stmt.executeUpdate(insertTableSQL);
            dbConnection.close();
        } catch (SQLException e) {
            // vang fouten op
        }
    }
}
