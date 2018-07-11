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
public class KlantAccountVerwijderenController implements Initializable {

    // Attributen
    FYS fys = new FYS();
    Language language = new Language();

    @FXML
    private Text accountVerwijderenLbl;
    @FXML
    private Button annulerenBtn;
    @FXML
    private Button bevestigenBtn;

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
            annulerenBtn.setText(language.getKlantAccountVerwijderenButtonCancel());
            bevestigenBtn.setText(language.getKlantAccountVerwijderenButtonContinue());
            accountVerwijderenLbl.setText(language.getKlantAccountVerwijderenLabelQuestion());
        }
    }

    /**
     * Doel: Wanneer op de knop wordt gedrukt verwijder het account en verwijs
     * naar de uitgelogd pagina.
     *
     * @param event druk op de verwijderen knop.
     * @throws IOException vang fouten op.
     * @throws SQLException vang fouten op .
     */
    @FXML
    private void handleAccountVerwijderenAction(ActionEvent event) throws IOException, SQLException {
        if (lang == 2) {
            fys.changeToAnotherFXML("Corendon Lost & Found - Log out", "view/Uitloggen.fxml");
        } else {
            fys.changeToAnotherFXML("Corendon Lost & Found - Uitloggen", "view/Uitloggen.fxml");
        }
        String gebruiker = fys.getGebruiker();
        deleteKlant(gebruiker);
    }

    /**
     * Doel: Wanneer op de knop wordt gedrukt verwijs terug naar het klant
     * profiel.
     *
     * @param event druk op de niet verwijderen knop.
     * @throws IOException vang fouten op.
     * @throws SQLException vang fouten op.
     */
    @FXML
    private void handleAccountNietVerwijderenAction(ActionEvent event) throws IOException, SQLException {
        if (lang == 2) {
            fys.changeToAnotherFXML("Corendon Lost & Found - Customer profile", "view/klant/KlantProfiel.fxml");
        } else {
            fys.changeToAnotherFXML("Corendon Lost & Found - Klant profiel", "view/klant/KlantProfiel.fxml");
        }
    }

    /**
     * Doel: Verwijder de gebruiker uit de database door de gegevens te
     * vervangen met verwijderd - verwijderd. Niemand kan hier meer op inloggen
     * omdat het wachtwoord wordt gehashd voor de vergelijking.
     *
     * @param gebruiker de gebruiker die moet worden verwijderd.
     * @throws SQLException vang fouten op.
     */
    private static void deleteKlant(String gebruiker) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String updateSQL = "UPDATE klant SET emailadres= ?,wachtwoord=? WHERE emailadres = ?";

        try {
            // voer de query uit.
            dbConnection = FYS.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(updateSQL);
            preparedStatement.setString(1, "verwijderd");
            preparedStatement.setString(2, "verwijderd");
            preparedStatement.setString(3, gebruiker);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // vangt errors op.
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
