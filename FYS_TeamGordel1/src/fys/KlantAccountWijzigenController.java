package fys;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller Doel: Functies van de account verwijderen pagina maken
 *
 * @author is102 - 3, GORDEL
 */
public class KlantAccountWijzigenController implements Initializable {

    // Attributen
    FYS fys = new FYS();
    Language language = new Language();

    @FXML
    private PasswordField wachtwoordTextveld;
    @FXML
    private PasswordField herhaalWachtwoord;
    @FXML
    private TextField adres;
    @FXML
    private TextField woonplaats;
    @FXML
    private TextField postcode;
    @FXML
    private TextField land;
    @FXML
    private TextField telNummer;
    @FXML
    private TextField email;
    @FXML
    private Text profielBewerkenLbl;
    @FXML
    private Button opslaanBtn;

    private String loginPassword1;
    private String loginPassword2;
    private final int id = fys.getGebruikerID();
    private String adres1;
    private String woonplaats1;
    private String postcode1;
    private String land1;
    private String telnr;
    private String email1;
    int lang = fys.getLanguage();

    /**
     * Doel: Initialiseer de pagina. Voeg al de gegevens van de klant toe aan de
     * text velden zodat de klant dit zelf niet iedere keer in hoeft te vullen.
     *
     * @param url URL
     * @param rb ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (lang == 2) {
            profielBewerkenLbl.setText(language.getKlantAccountWijzigen());
            wachtwoordTextveld.setPromptText(language.getKlantAccountWijzigenPromptTextPassword());
            herhaalWachtwoord.setPromptText(language.getKlantAccountWijzigenPromptTextRePassowrd());
            adres.setPromptText(language.getKlantAccountWijzigenPromptTextAdres());
            woonplaats.setPromptText(language.getKlantAccountWijzigenPromptTextCity());
            postcode.setPromptText(language.getKlantAccountWijzigenPromptTextZipCode());
            land.setPromptText(language.getKlantAccountWijzigenPromptTextCountry());
            telNummer.setPromptText(language.getKlantAccountWijzigenPromptTextPhoneNbr());
            opslaanBtn.setText(language.getKlantAccountWijzigenButtonSave());
        }
        Connection conn;
        Statement stmt1;
        try {
            conn = FYS.getDBConnection();
            stmt1 = conn.createStatement();
            String insertTableSQL1 = "SELECT * FROM klant WHERE klant_id =" + id;
            ResultSet rs = stmt1.executeQuery(insertTableSQL1);

            // Als er een resultaat is haal de informatie op die in de database staat.
            if (rs.next()) {
                adres1 = rs.getString("adres");
                woonplaats1 = rs.getString("woonplaats");
                postcode1 = rs.getString("postcode");
                land1 = rs.getString("land");
                telnr = rs.getString("telefoonnummer");
                email1 = rs.getString("emailadres");
            }
            // vul de textvelden met de data.
            adres.setText(adres1);
            woonplaats.setText(woonplaats1);
            postcode.setText(postcode1);
            land.setText(land1);
            telNummer.setText(telnr);
            email.setText(email1);
            conn.close();
        } catch (SQLException ex) {
            // vang fouten op
        }
    }

    /**
     * Doel: Het opslaan van de nieuwe ingevoerde data, met een controle dat het
     * wachtwoord hetzelfde is.
     *
     * @param event klik op de opslaan knop
     * @throws IOException vang fouten op
     * @throws SQLException vang fouten op
     */
    @FXML
    private void opslaanProfielAction(ActionEvent event) throws IOException, SQLException {
        String emailTekst = email.getText();
        loginPassword1 = wachtwoordTextveld.getText();
        loginPassword2 = herhaalWachtwoord.getText();

        // Controleert of wachtwoordvelden overeenkomen en niet leeg zijn.
        if (loginPassword1.trim().equals("")) {
            fys.nietIngevuldeVelden(wachtwoordTextveld);
            if (lang == 2) {
                fys.maakNotifaction("Error", "Fill in both password fields!");
            } else {
                fys.maakNotifaction("Error", "Vul beide wachtwoord velden in!");
            }
        } else if (loginPassword2.trim().equals("")) {
            fys.nietIngevuldeVelden(herhaalWachtwoord);
            if (lang == 2) {
                fys.maakNotifaction("Error", "Fill in both password fields!");
            } else {
                fys.maakNotifaction("Error", "Vul beide wachtwoord velden in!");
            }
        } else if (!(loginPassword1.equals(loginPassword2))) {
            fys.nietIngevuldeVelden(wachtwoordTextveld);
            fys.nietIngevuldeVelden(herhaalWachtwoord);
            if (lang == 2) {
                fys.maakNotifaction("Error", "The passwords do not match!");
            } else {
                fys.maakNotifaction("Error", "Wachtwoorden komen niet overeen!");
            }
        } else if (emailTekst.trim().equals("")) {
            fys.nietIngevuldeVelden(email);
            if (lang == 2) {
                fys.maakNotifaction("Error", "Email address field cannot be left empty!");
            } else {
                fys.maakNotifaction("Error", "Emailadres veld mag niet leeg blijven!");
            }
        } else {
            // als de wachtwoorden goed zijn, sla de data op.
            slaWachtwoordOp(loginPassword1, id);

            String adres2 = adres.getText();
            String woonplaats2 = woonplaats.getText();
            String postcode2 = postcode.getText();
            String land2 = land.getText();
            String tel2 = telNummer.getText();
            String email2 = email.getText();
            String wachtwoord = String.valueOf(wachtwoordTextveld.getText().hashCode());
            Connection dbConnection = null;
            PreparedStatement preparedStatement = null;
            // query om de data te updaten
            String updateTableSQL2 = "UPDATE klant SET wachtwoord = ?, adres = "
                    + "?, woonplaats = ?, postcode = ?, land = ?, "
                    + "telefoonnummer = ?, emailadres = ? WHERE klant_id = ?";

            try {
                dbConnection = FYS.getDBConnection();
                preparedStatement = dbConnection.prepareStatement(updateTableSQL2);

                preparedStatement.setString(1, wachtwoord);
                preparedStatement.setString(2, adres2);
                preparedStatement.setString(3, woonplaats2);
                preparedStatement.setString(4, postcode2);
                preparedStatement.setString(5, land2);
                preparedStatement.setString(6, tel2);
                preparedStatement.setString(7, email2);
                preparedStatement.setInt(8, id);

                // execute update SQL statement
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                // vangt errors op
            } finally {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (dbConnection != null) {
                    dbConnection.close();
                }
                if (lang == 2) {
                    fys.changeToAnotherFXML("Corendon Lost & Found - Customer Profile ", "view/klant/KlantProfiel.fxml");
                } else {
                    fys.changeToAnotherFXML("Corendon Lost & Found - Klant profiel ", "view/klant/KlantProfiel.fxml");
                }
            }
        }
    }

    /**
     * Doel: Sla het nieuwe wachtwoord op in de database.
     *
     * @param password het wachtwoord wat moet worden opgeslagen
     * @param id het ID van de gebruiker.
     * @throws SQLException
     */
    private static void slaWachtwoordOp(String password, int id) throws SQLException {
        Connection dbConnection;

        Statement stmt;
        String insertTableSQL = "UPDATE medewerker SET wachtwoord='" + password
                + "' WHERE medewerker_id='" + id + "'";

        try {
            dbConnection = FYS.getDBConnection();
            stmt = dbConnection.createStatement();
            stmt.executeUpdate(insertTableSQL);

            dbConnection.close();
        } catch (SQLException e) {
            // vangt errors op
        }
    }
}