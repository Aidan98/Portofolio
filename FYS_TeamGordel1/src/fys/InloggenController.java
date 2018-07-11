package fys;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import java.sql.*;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;

/**
 * FXML Controller Doel: Deze controller is gekoppeld aan de inlog pagina. Deze
 * controller verwerkt de buttons en de textfields.
 *
 * @author is102 - 3, GORDEL
 */
public class InloggenController implements Initializable {

    // Attributen.
    FYS fys = new FYS();
    Language language = new Language();
    @FXML
    private TextField inloggenEmail;
    @FXML
    private PasswordField inloggenWachtwoord;
    @FXML
    private Button inloggenBtnLbl;
    @FXML
    private Hyperlink wachtwoordVergetenLbl;
    @FXML
    private Button applicatieSluitenBtn;
    @FXML
    private JFXButton nederlandsBtn;
    @FXML
    private JFXButton engelsBtn;
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
            engelsBtn.setDisable(true);
            nederlandsBtn.setDisable(false);
            inloggenWachtwoord.setPromptText(language.getInloggenPromptTextPassword());
            inloggenBtnLbl.setText(language.getInloggenButtonLogIn());
            wachtwoordVergetenLbl.setText(language.getInloggenButtonForgotPassword());
            applicatieSluitenBtn.setText(language.getInloggenCloseApp());
        } else {
            nederlandsBtn.setDisable(true);
            engelsBtn.setDisable(false);
        }
    }

    /**
     * Doel: Wanneer op de knop wordt gedrukt moet worden gecontroleerd of de
     * ingevulde data in de database staat, en zoja moet de gebruiker worden
     * ingelogd
     *
     * @param event klik op de Log In knop.
     * @throws IOException SQL errors ontwijken
     */
    @FXML
    private void handleLoginBtnAction(ActionEvent event) throws IOException {
        Connection conn = null;
        Statement stmt = null;
        try {
            int loggedIn = 0;
            String loginEmail = inloggenEmail.getText().toLowerCase();
            String loginPassword = inloggenWachtwoord.getText();
            // hash het wachtwoord
            int passwordHashInt = loginPassword.hashCode();
            String passwordHash = Integer.toString(passwordHashInt);

            conn = FYS.getDBConnection();
            stmt = conn.createStatement();
            // haal de informatie uit de database
            String sql = "SELECT emailadres,wachtwoord,klant_id FROM klant";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String email = rs.getString("emailadres");
                String wachtwoordString = rs.getString("wachtwoord");
                int id = rs.getInt("klant_id");
                if (email.equals(loginEmail)) {
                    if (passwordHash.equals(wachtwoordString)) {
                        loggedIn = 1;
                        if (lang == 2) {
                            fys.setGebruiker(email);
                            fys.setGebruikerID(id);
                            fys.changeToAnotherFXML("Corendon Lost & Found - "
                                    + "Welcome", "view/klant/KlantHome.fxml");
                        } else {
                            fys.setGebruiker(email);
                            fys.setGebruikerID(id);
                            fys.changeToAnotherFXML("Corendon Lost & Found - "
                                    + "Welkom", "view/klant/KlantHome.fxml");
                        }
                        // Wanneer alle checks door komen switch naar de klant omgeving
                        // en zet de attributen van de klasse FYS.
                    }
                }
            }

            // Als de inloggegevens niet overeenkomen met een klant, probeer hetzelfde
            // voor de medewerkers tabel.
            String sql2 = "SELECT emailadres,wachtwoord,manager,medewerker_id FROM medewerker";
            ResultSet rs2 = stmt.executeQuery(sql2);
            while (rs2.next()) {
                String emailadres = rs2.getString("emailadres");
                String wachtwoordString = rs2.getString("wachtwoord");
                int id = rs2.getInt("medewerker_id");
                int manager = rs2.getInt("manager");
                if (emailadres.equals(loginEmail)) {
                    if (passwordHash.equals(wachtwoordString)) {
                        // Als de gebruiker een manager is, verwijs naar de manager omgeving
                        // anders verwijs naar de medewerker omgeving en zet de attributen.
                        loggedIn = 1;
                        if (manager == 1) {
                            if (lang == 2) {
                                fys.changeToAnotherFXML("Corendon Lost & Found - "
                                        + "Welcome", "view/manager/ManagerHome.fxml");
                            } else {
                                fys.changeToAnotherFXML("Corendon Lost & Found - "
                                        + "Welkom", "view/manager/ManagerHome.fxml");
                            }
                        } else if (lang == 2) {
                            fys.changeToAnotherFXML("Corendon Lost & Found - "
                                    + "Welcome", "view/medewerker/MedewerkerHome.fxml");
                        } else {
                            fys.changeToAnotherFXML("Corendon Lost & Found - "
                                    + "Welkom", "view/medewerker/MedewerkerHome.fxml");
                        }
                        fys.setGebruiker(emailadres);
                        fys.setGebruikerID(id);
                    }
                }
            }
            if (loggedIn == 0) {
                if (lang == 2) {
                    fys.maakNotifaction("Warning", "Enter a valid emailadres and password!");
                } else {
                    fys.maakNotifaction("Warning", "Vul een geldig e-mailadres en wachtwoord in!");
                }
            }
            rs.close();
            rs2.close();
            conn.close();
        } catch (SQLException ex) {
            // vangt fouten op
        }
    }

    /**
     * Doel: Deze methode zorgt ervoor dat wanneer op de wachtwoord vergeten
     * link wordt geklikt de pagina veranderd naar de wachtwoord vergeten
     * pagina.
     *
     * @param event klik op wachtwoord vergeten.
     * @throws IOException vang fouten op.
     */
    @FXML
    private void handleWachtwoordVergetenAction(ActionEvent event)
            throws IOException {
        fys.changeToAnotherFXML("Corendon Lost & Found - Wachtwoord vergeten",
                "view/WachtwoordVergeten.fxml");
    }

    /**
     * Doel: deze knop sluit de applicatie.
     *
     * @param event klik op applicatie afsluiten.
     */
    @FXML
    private void handleCloseBtnAction(ActionEvent event
    ) {
        Platform.exit();
    }

    /**
     * Doel: Gaan naar een nieuwe scene.
     *
     * @param event klik op de knop
     * @throws IOException vangt fouten op.
     */
    @FXML
    private void handleNederlandsBtnAction(ActionEvent event) throws IOException {
        fys.setLanguage(1);
        fys.changeToAnotherFXML("Corendon Lost & Found - Inloggen", "view/Inloggen.fxml");
    }

    /**
     * Doel: Gaan naar een nieuwe scene.
     *
     * @param event klik op de knop
     * @throws IOException vangt fouten op.
     */
    @FXML
    private void handleEngelsBtnAction(ActionEvent event) throws IOException {
        fys.setLanguage(2);
        fys.changeToAnotherFXML("Corendon Lost & Found - Sign in", "view/Inloggen.fxml");
    }
}
