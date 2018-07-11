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
public class ManagerVerwijderAccountController implements Initializable {

    // Attributen
    FYS fys = new FYS();
    Language language = new Language();
    @FXML
    private Text managerConfirmantionLbl;
    @FXML
    private Button managerAnnuleerBtn;
    @FXML
    private Button managerBevestigBtn;
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
            managerConfirmantionLbl.setText(language.getManagerVerwijderAccountLabelConfirmation());
            managerAnnuleerBtn.setText(language.getManagerVerwijderAccountButtonDecline());
            managerBevestigBtn.setText(language.getManagerVerwijderAccountButtonAccept());
        }
    }

    /**
     * Doel: Deze button linkt terug naar de manager profiel pagina, het account
     * wordt niet verwijderd.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleAnnulerenBtnAction(ActionEvent event) throws IOException {
        if (lang == 2) {
            fys.changeToAnotherFXML("Corendon Lost & Found - Manager Delete "
                    + "Account", "view/manager/ManagerProfiel.fxml");
        } else {
            fys.changeToAnotherFXML("Corendon Lost & Found - Manager Account "
                    + "Verwijderen", "view/manager/ManagerProfiel.fxml");
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
    private void handleBevestigenBtnAction(ActionEvent event) throws IOException, SQLException {
        // Linkt naar uitloggen pagina
        if (lang == 2) {
            fys.changeToAnotherFXML("Corendon Lost & Found - Employee Account "
                    + "Deletion", "view/Inloggen.fxml");
        } else {
            fys.changeToAnotherFXML("Corendon Lost & Found - Medewerker Account "
                    + "Verwijderen", "view/Inloggen.fxml");
        }

        String gebruiker = fys.getGebruiker();
        deleteManager(gebruiker);
    }

    /**
     * Doel: Deze methode verwijdert het manager account uit de database.
     *
     * @param gebruiker
     * @throws SQLException
     */
    private static void deleteManager(String gebruiker) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String updateSQL = "UPDATE medewerker SET emailadres= ?, wachtwoord=? WHERE emailadres = ?";

        try {
            dbConnection = FYS.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(updateSQL);
            preparedStatement.setString(1, "verwijderd");
            preparedStatement.setString(2, "verwijderd");
            preparedStatement.setString(3, gebruiker);

            // voert SQL statement uit.
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
