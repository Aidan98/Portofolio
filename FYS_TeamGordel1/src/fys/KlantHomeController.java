package fys;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller Doel: De homepagina voor de klant omgeving. De klant kan van
 * hier navigeren naar de statusen en schadeclaim indienen.
 *
 * @author is102 - 3, GORDEL
 */
public class KlantHomeController implements Initializable {

    // attributen
    FYS fys = new FYS();
    Language language = new Language();

    @FXML
    private JFXButton statusVerlorenBagageBtn;
    @FXML
    private JFXButton indiendenSchadeclaimBtn;
    @FXML
    private JFXButton statusSchadeclaimBtn;

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
            statusVerlorenBagageBtn.setText(language.getKlantHomeButtonStatusLuggage());
            indiendenSchadeclaimBtn.setText(language.getKlantHomeButtonSubmitClaim());
            statusSchadeclaimBtn.setText(language.getKlantHomeButtonStatusClaims());
        }
    }

    /**
     * Doel: Gaan naar een nieuwe scene.
     *
     * @param event klik op de knop
     * @throws IOException vangt fouten op.
     */
    @FXML
    private void handleInzienStatusVerlorenBtnAction(ActionEvent event) throws IOException {
        if (lang == 2) {
            fys.changeToAnotherFXML("Corendon Lost & Found - View status lost luggage",
                    "view/klant/KlantInzienStatus.fxml");
        } else {
            fys.changeToAnotherFXML("Corendon Lost & Found - Inzien status verloren bagage",
                    "view/klant/KlantInzienStatus.fxml");
        }
    }

    /**
     * Doel: Gaan naar een nieuwe scene.
     *
     * @param event klik op de knop
     * @throws IOException vangt fouten op.
     */
    @FXML
    private void handleIndienenSchadeclaimAction(ActionEvent event) throws IOException {
        if (lang == 2) {
            fys.changeToAnotherFXML("Corendon Lost & Found - Submit insurance claim",
                    "view/klant/KlantSchadeclaimIndienen.fxml");
        } else {
            fys.changeToAnotherFXML("Corendon Lost & Found - Indienen schadeclaim",
                    "view/klant/KlantSchadeclaimIndienen.fxml");
        }
    }

    /**
     * Doel: Gaan naar een nieuwe scene.
     *
     * @param event klik op de knop
     * @throws IOException vangt fouten op.
     */
    @FXML
    private void handleStatusSchadeclaimAction(ActionEvent event) throws IOException {
        if (lang == 2) {
            fys.changeToAnotherFXML("Corendon Lost & Found - View status insurance claim",
                    "view/klant/KlantSchadeclaimsStatus.fxml");
        } else {
            fys.changeToAnotherFXML("Corendon Lost & Found - Inzien status schadeclaim",
                    "view/klant/KlantSchadeclaimsStatus.fxml");
        }
    }
}
