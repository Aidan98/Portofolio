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
import javafx.scene.text.Text;

/**
 * FXML Controller Doel: Functies van de account verwijderen pagina maken.
 *
 * @author is102 - 3, GORDEL
 */
public class MedewerkerVerwijderenAccountController implements Initializable {

    // Attributen
    FYS fys = new FYS();
    Language language = new Language();

    @FXML
    private Text accountVerwijderen;
    @FXML
    private Button bevestigen;
    @FXML
    private Button annuleren;
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
            accountVerwijderen.setText(language.getMedewerkerVerwijdereAccountPromtextAccountVerwijderen());
            bevestigen.setText(language.getMedewerkerVerwijdereAccountButtonBevestigen());
            annuleren.setText(language.getMedewerkerVerwijdereAccountButtonAnnuleren());
        }
    }

    /**
     * Doel: Deze button linkt terug naar de medewerker profiel pagina, het
     * account wordt niet verwijderd.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleAccountNietVerwijderenAction(ActionEvent event) throws IOException {
        if (lang == 2) {
            fys.changeToAnotherFXML("Corendon Lost & Found - Employee Profile",
                    "view/medewerker/MedewerkerProfiel.fxml");
        } else {
            fys.changeToAnotherFXML("Corendon Lost & Found - Medewerker profiel",
                    "view/medewerker/MedewerkerProfiel.fxml");
        }
    }

    /**
     * Doel: Deze button linkt naar de uitloggen pagina, het account wordt
     * verwijderd uit de database.
     *
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    private void handleAccountVerwijderenAction(ActionEvent event) throws IOException, SQLException {
        // Linkt naar uitloggen pagina
        if (lang == 2) {
            fys.changeToAnotherFXML("Corendon Lost & Found - Sign out",
                    "view/Uitloggen.fxml");
        } else {
            fys.changeToAnotherFXML("Corendon Lost & Found - Uitloggen",
                    "view/Uitloggen.fxml");
        }

        String gebruiker = fys.getGebruiker();
        deleteMedewerker(gebruiker);
    }

    /**
     * Doel: Deze methode verwijdert het medewerker account uit de database.
     *
     * @param gebruiker
     * @throws SQLException
     */
    private static void deleteMedewerker(String gebruiker) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String updateSQL = "UPDATE medewerker SET emailadres= ?,wachtwoord=? WHERE emailadres = ?";

        try {
            dbConnection = FYS.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(updateSQL);
            preparedStatement.setString(1, "verwijderd");
            preparedStatement.setString(2, "verwijderd");
            preparedStatement.setString(3, gebruiker);

            // Voert SQL statement uit.
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // Vangt errors op.
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }
}
