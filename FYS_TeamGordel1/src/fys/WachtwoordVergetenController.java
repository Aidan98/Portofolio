package fys;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller Doel: de functies voor de wachtwoord vergeten pagina maken.
 *
 * @author is102 - 3, GORDEL
 */
public class WachtwoordVergetenController implements Initializable {

    // Attributen
    FYS fys = new FYS();
    Language language = new Language();

    @FXML
    private TextField emailLbl;
    @FXML
    private Button verstuur;
    @FXML
    private Button terug;
    @FXML
    private Label wachtwoordVergeten;
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

            wachtwoordVergeten.setText(language.getWachwoordtvergetenLabel());
            emailLbl.setPromptText(language.getWactwoordvergetenPrompTextEmail());
            verstuur.setText(language.getWachtwoordVergetenButtonVerstuur());
            terug.setText(language.getwachtwoordVergetenButtonTerug());
        }

    }

    /**
     * Doel: Gaan naar een nieuwe scene.
     *
     * @param event klik op de knop
     * @throws IOException vangt fouten op.
     */
    @FXML
    private void handleTerugBtnAction(ActionEvent event) throws IOException {
        if (lang == 2) {
            fys.changeToAnotherFXML("Corendon Lost & Found - Sign in", "view/Inloggen.fxml");
        } else {
            fys.changeToAnotherFXML("Corendon Lost & Found - Inloggen", "view/Inloggen.fxml");

        }
    }

    /**
     * Doel: Wanneer op verstuur wordt gedrukt, verstuur een email met het
     * nieuwe wachtwoord.
     *
     * @param event klik op de knop
     * @throws IOException vangt fouten op.
     */
    @FXML
    private void handleVerstuurBtnAction(ActionEvent event) throws IOException, SQLException {
        String email = emailLbl.getText().toLowerCase();
        String newPassword = FYS.randomWachtwoord();

        String to = email; // list of recipient email addresses
        String subject;
        String body;
        if (lang == 2) {
            subject = "Did you forget your password?";
            body = "<img src=\"https://upload.wikimedia.org/wikipedia"
                    + "/en/thumb/3/36/Corendon_Airlines_2014_logo.svg/1280px-Corend"
                    + "on_Airlines_2014_logo.svg.png\" alt=\"Corendon\" style"
                    + "=\"width:200px;height:75px;\">\n"
                    + "<h1><font face=\"calibri\" color=\"black\">Best customer,</font></h1>\n"
                    + "<font face=\"calibri\" size=\"5\" color=\"black\">Your "
                    + "temporary password: <strong><u>" + newPassword
                    + "</u></strong>.<br> use this temporary key to sign in and create a new password.</br>\n"
                    + "<br><br>\n"
                    + "Team <a href=\"https://www.corendon.com\\\">Corendon</a></font>";
        } else {
            subject = "Bent u uw wachtwoord vergeten?";
            body = "<img src=\"https://upload.wikimedia.org/wikipedia"
                    + "/en/thumb/3/36/Corendon_Airlines_2014_logo.svg/1280px-Corend"
                    + "on_Airlines_2014_logo.svg.png\" alt=\"Corendon\" style"
                    + "=\"width:200px;height:75px;\">\n"
                    + "<h1><font face=\"calibri\" color=\"black\">Beste klant,</font></h1>\n"
                    + "<font face=\"calibri\" size=\"5\" color=\"black\">Uw "
                    + "tijdelijke wachtwoord is: <strong><u>" + newPassword
                    + "</u></strong>.<br> Gebruik dit tijdelijke wachtwoord om in te loggen.\n"
                    + "en een zelfgekozen wachtwoord aan te maken.</br>\n"
                    + "<br><br>\n"
                    + "Team <a href=\"https://www.corendon.com\\\">Corendon</a></font>";
        }
        if (!emailLbl.getText().trim().equals("")) {
            if (fys.isEmailValid(email)) {

                // update the password to the new password.
                String hashedPassword = String.valueOf(newPassword.hashCode());
                Connection dbConnection = null;
                PreparedStatement preparedStatement = null;
                String updateSQL = null;
                if (fys.isKlant(email)) {
                    if (FYS.sendFromGMail(to, subject, body)) {
                        updateSQL = "UPDATE klant SET wachtwoord=? WHERE emailadres = ?";
                    }
                } else if (fys.isMedewerker(email)) {
                    if (FYS.sendFromGMail(to, subject, body)) {
                        updateSQL = "UPDATE medewerker SET wachtwoord=? WHERE emailadres = ?";
                    }
                } else if (lang == 2) {
                    fys.maakNotifaction("Warning", "Your emailadres cannot be found!");
                } else {
                    fys.maakNotifaction("Warning", "Uw emailadress is niet bekend!");
                }

                try {
                    dbConnection = FYS.getDBConnection();
                    preparedStatement = dbConnection.prepareStatement(updateSQL);
                    preparedStatement.setString(1, hashedPassword);
                    preparedStatement.setString(2, email);

                    // execute delete SQL stetement
                    preparedStatement.executeUpdate();

                    emailLbl.clear();
                    if (lang == 2) {
                        fys.maakNotifaction("Confirm", "You will receive an e-mail with instructions.");
                    } else {
                        fys.maakNotifaction("Confirm", "U Krijgt een e-mail met instructies.");
                    }
                } catch (SQLException e) {
                    //vangt fouten op
                } finally {
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                    if (dbConnection != null) {
                        dbConnection.close();
                    }
                }
            } else if (lang == 2) {
                fys.maakNotifaction("Warning", "Enter a valid emailadres!");
            } else {
                fys.maakNotifaction("Warning", "Vul een email in!");
            }
        } else if (lang == 2) {
            fys.maakNotifaction("Warning", "Emailadres field has been left empty!");
        } else {
            fys.maakNotifaction("Warning", "Emailadres veld is niet ingevuld!");
        }
    }
}
