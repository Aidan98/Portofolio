package fys;

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
 * FXML Controller Doel: Functies van de medewerkerprofiel pagina maken.
 *
 * @author is102 - 3, GORDEL
 */
public class MedewerkerProfielController implements Initializable {

    // Attributen
    FYS fys = new FYS();
    Language language = new Language();
    @FXML
    private Label profielNaam;
    @FXML
    private Label profielCorendonID;
    @FXML
    private Text profiel;
    @FXML
    private Text emailAdres;
    @FXML
    private Button wijzigWachtwoord;
    @FXML
    private Button verwijderenAccount;
    int lang = fys.getLanguage();

    /**
     * Doel: Initialiseer de pagina, vult labels met informatie uit database.
     *
     * @param url URL
     * @param rb ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (lang == 2) {
            profiel.setText(language.getMedewerkerProfielPromptTextProfiel());
            emailAdres.setText(language.getMedewerkerProfielPromptTextEmailAdres());
            wijzigWachtwoord.setText(language.getMedewerkerProfielButtonWijzigWachtwoord());
            verwijderenAccount.setText(language.getMedewerkerProfielButtonVerwijderenAccount());
        }

        String gebruiker = fys.getGebruiker();
        // Vraagt informatie op uit de database.
        Connection conn;
        Statement stmt;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/fys?"
                    + "user=fys_is102&password=gordel");
            stmt = conn.createStatement();
            String medewerkerID = "";
            String sql = "SELECT medewerker_id FROM medewerker WHERE emailadres='" + gebruiker + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                medewerkerID = rs.getString("medewerker_id");
            }
            // Vult labels met informatie.
            profielNaam.setText(gebruiker);
            profielCorendonID.setText(medewerkerID);
            conn.close();
        } catch (SQLException ex) {
            // Vangt errors op.
        }
    }

    /**
     * Doel: Deze button linkt naar de medewerkerprofiel wijzigen pagina.
     *
     * @param event
     * @throws IOException employee modify Profile
     */
    @FXML
    private void handleWijzigGegevensAction(ActionEvent event) throws IOException {
        if (lang == 2) {
            fys.changeToAnotherFXML("Corendon Lost & Found - Edit employee profile",
                    "view/medewerker/MedewerkerProfielWijzigen.fxml");
        } else {
            fys.changeToAnotherFXML("Corendon Lost & Found - Medewerker Profiel bewerken",
                    "view/medewerker/MedewerkerProfielWijzigen.fxml");
        }
    }

    /**
     * Doel: Deze button linkt naar de medewerkeraccount verwijderen pagina.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleVerwijderAccountAction(ActionEvent event) throws IOException {
        if (lang == 2) {
            fys.changeToAnotherFXML("Corendon Lost & Found - Delete employee profile",
                    "view/medewerker/MedewerkerVerwijderenAccount.fxml");
        } else {
            fys.changeToAnotherFXML("Corendon Lost & Found - Medewerker Profiel vewijderen",
                    "view/medewerker/MedewerkerVerwijderenAccount.fxml");
        }
    }
}