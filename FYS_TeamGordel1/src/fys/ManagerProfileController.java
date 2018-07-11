package fys;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
 * FXML Controller Doel: De functies van de profielpagina van de manager maken.
 *
 * @author is102 - 3, GORDEL
 */
public class ManagerProfileController implements Initializable {

    // Attributen
    FYS fys = new FYS();
    Language language = new Language();
    @FXML
    private Label wachtwoordLbl;
    @FXML
    private Label corendonIDLbl;
    @FXML
    private Text managerProfielLbl;
    @FXML
    private Button managerWachtwoordBtn;
    @FXML
    private Button managerVerwijderAccBtn;
    int lang = fys.getLanguage();

    /**
     * Doel: Initialiseer de pagina. Laadt de labels met de informatie van de
     * medewerker.
     *
     * @param url URL
     * @param rb ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (lang == 2) {
            managerProfielLbl.setText(language.getManagerProfielLabelProfile());
            managerWachtwoordBtn.setText(language.getManagerProfielButtonPassword());
            managerVerwijderAccBtn.setText(language.getManagerProfielButttonDeleteAcc());
        }
        String gebruiker = fys.getGebruiker();

        Connection conn;
        Statement stmt;
        try {
            conn = FYS.getDBConnection();
            stmt = conn.createStatement();
            String medewerkerID = "";
            String sql = "SELECT medewerker_id FROM medewerker WHERE emailadres='" + gebruiker + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                medewerkerID = rs.getString("medewerker_id");
            }
            wachtwoordLbl.setText(gebruiker);
            corendonIDLbl.setText(medewerkerID);
            conn.close();
        } catch (SQLException ex) {
            // vang fouten op
        }
    }

    /**
     * Doel: Gaan naar een nieuwe scene.
     *
     * @param event klik op de knop
     * @throws IOException vangt fouten op.
     */
    @FXML
    private void handleWijzigGegevensBtnAction(ActionEvent event) throws IOException {
        if (lang == 2) {
            fys.changeToAnotherFXML("Corendon Lost & Found - Manager edit profile", "view/manager/ManagerProfielBewerken.fxml");
        } else {
            fys.changeToAnotherFXML("Corendon Lost & Found - Manager Account Verwijderen", "view/manager/ManagerProfielBewerken.fxml");
        }
    }

    /**
     * Doel: Gaan naar een nieuwe scene.
     *
     * @param event klik op de knop
     * @throws IOException vangt fouten op.
     */
    @FXML
    private void handleVerwijderAccountBtnAction(ActionEvent event) throws IOException {
        if (lang == 2) {
            fys.changeToAnotherFXML("Corendon Lost & Found - Manager delete profile", "view/manager/ManagerVerwijderAccount.fxml");
        } else {
            fys.changeToAnotherFXML("Corendon Lost & Found - Manager Account Verwijderen", "view/manager/ManagerVerwijderAccount.fxml");
        }
    }
}
