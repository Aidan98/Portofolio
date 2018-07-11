package fys;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

/**
 * FXML Controller Doel:
 *
 * @author is102 - 3, GORDEL
 */
public class KlantProfileController implements Initializable {

    // Attributen
    FYS fys = new FYS();
    Language language = new Language();

    @FXML
    private Label naamLbl;
    @FXML
    private Label adresLbl;
    @FXML
    private Label woonplaatsLbl;
    @FXML
    private Label postcodeLbl;
    @FXML
    private Label landLbl;
    @FXML
    private Label telefoonnummerLbl;
    @FXML
    private Label emailadresLbl;
    @FXML
    private Text klantProfielLbl;
    @FXML
    private Text klantNaamLbl;
    @FXML
    private Text klantAdresLbl;
    @FXML
    private Text klantWoonplaatsLbl;
    @FXML
    private Text klantTeleNmrLbl;
    @FXML
    private JFXButton wijzigGegevensBtn;
    @FXML
    private Text klantPostcodeLbl;
    @FXML
    private Text klantLandLbl;
    @FXML
    private Button verwijderAcoountBtn;

    int lang = fys.getLanguage();

    /**
     * Doel: Initialiseer de pagina
     *
     * @param url URL
     * @param rb Resource Bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (lang == 2) {
            klantProfielLbl.setText(language.getKlantProfielLabelProfile());
            klantNaamLbl.setText(language.getKlantProfielLabelName());
            klantAdresLbl.setText(language.getKlantProfielLabelAddress());
            klantWoonplaatsLbl.setText(language.getKlantProfielLabelCity());
            klantPostcodeLbl.setText(language.getKlantProfielLabelZipCode());
            klantLandLbl.setText(language.getKlantProfielLabelCountry());
            klantTeleNmrLbl.setText(language.getKlantProfielLabelPhoneNmr());
            wijzigGegevensBtn.setText(language.getKlantProfielButtonEditDetails());
            verwijderAcoountBtn.setText(language.getKlantProfielButtonDeleteAccount());
        }
        String gebruiker = fys.getGebruiker();

        Connection conn;
        Statement stmt;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/fys?"
                    + "user=fys_is102&password=gordel");
            stmt = conn.createStatement();
            String volnaam, naam = "", tussenvoegsel = "", achternaam = "",
                    adres = "", woonplaats = "", postcode = "", land = "",
                    telefoonnummer = "", emailadres = "";
            String sql = "SELECT * FROM klant WHERE emailadres='" + gebruiker + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                naam = rs.getString("naam");
                tussenvoegsel = rs.getString("tussenvoegsel");
                achternaam = rs.getString("achternaam");
                adres = rs.getString("adres");
                woonplaats = rs.getString("woonplaats");
                postcode = rs.getString("postcode");
                land = rs.getString("land");
                telefoonnummer = rs.getString("telefoonnummer");
                emailadres = rs.getString("emailadres");
            }
            if (tussenvoegsel == null) {
                volnaam = naam + " " + achternaam;
            } else {
                volnaam = naam + " " + tussenvoegsel + " " + achternaam;
            }
            naamLbl.setText(volnaam);
            adresLbl.setText(adres);
            woonplaatsLbl.setText(woonplaats);
            postcodeLbl.setText(postcode);
            landLbl.setText(land);
            telefoonnummerLbl.setText(telefoonnummer);
            emailadresLbl.setText(emailadres);
            conn.close();
        } catch (SQLException ex) {
            // vang fouten op
        }
    }

    /**
     * Doel: Gaan naar een nieuwe scene
     *
     * @param event klik op de knop
     * @throws IOException vangt fouten op
     */
    @FXML
    private void handleWijzigGegevensBtnAction(ActionEvent event) throws IOException {
        if (lang == 2) {
            fys.changeToAnotherFXML("Corendon Lost & Found - Edit customer profile",
                    "view/klant/KlantAccountWijzigen.fxml");
        } else {
            fys.changeToAnotherFXML("Corendon Lost & Found - Klant profiel "
                    + "wijzigen", "view/klant/KlantAccountWijzigen.fxml");
        }
    }

    /**
     * Doel: Gaan naar een nieuwe scene
     *
     * @param event klik op de knop
     * @throws IOException vangt fouten op
     */
    @FXML
    private void handleAccountVerwijderenAction(ActionEvent event) throws IOException {
        if (lang == 2) {
            fys.changeToAnotherFXML("Corendon Lost & Found - Delete customer"
                    + "Profile", "view/klant/KlantAccountVerwijderen.fxml");
        } else {
            fys.changeToAnotherFXML("Corendon Lost & Found - Klant profiel "
                    + "verwijderen", "view/klant/KlantAccountVerwijderen.fxml");
        }
    }
}
