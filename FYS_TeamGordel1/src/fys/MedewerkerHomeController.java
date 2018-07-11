package fys;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller Doel: Functies van medewerker home pagina maken.
 *
 * @author is102 - 3, GORDEL
 */
public class MedewerkerHomeController implements Initializable {

    // Attributen
    FYS fys = new FYS();
    Language language = new Language();
    @FXML
    private JFXButton zoekenBagage;
    @FXML
    private JFXButton klantRegVerBagage;
    @FXML
    private JFXButton registratieGevBagage;
    @FXML
    private JFXButton bagageUpdate;
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
            zoekenBagage.setText(language.getMedewerkerHomeButtonzoekenBagage());
            klantRegVerBagage.setText(language.getMedewerkerHomeButtonKlantRegVerBagage());
            registratieGevBagage.setText(language.getMedewerkerHomeButtonRegistratieGevBagage());
            bagageUpdate.setText(language.getMedewerkerHomeButtonBagageUpdate());
        }
    }

    /**
     * Doel: Deze button linkt naar de bagage zoeken bagage pagina.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleZoekenBagageAction(ActionEvent event) throws IOException {
        if (lang == 2) {
            fys.changeToAnotherFXML("Corendon Lost & Found - Employee search luggage",
                    "view/medewerker/MedewerkerBagageZoeken.fxml");
        } else {
            fys.changeToAnotherFXML("Corendon Lost & Found - Medewerker zoeken bagage",
                    "view/medewerker/MedewerkerBagageZoeken.fxml");
        }
    }

    /**
     * Doel: Deze button linkt naar de klant met verloren bagage registratie
     * pagina.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleKlantVerlorenBagageAction(ActionEvent event) throws IOException {
        if (lang == 2) {
            fys.changeToAnotherFXML("Corendon Lost & Found - Employee customer lost luggage",
                    "view/medewerker/MedewerkerKlantReg.fxml");
        } else {
            fys.changeToAnotherFXML("Corendon Lost & Found - Medewerker klant verloren bagage",
                    "view/medewerker/MedewerkerKlantReg.fxml");
        }
    }

    /**
     * Doel: Deze button linkt naar de gevonden bagage registratie pagina.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleGevondenBagageAction(ActionEvent event) throws IOException {
        if (lang == 2) {
            fys.changeToAnotherFXML("Corendon Lost & Found - Employee found luggage",
                    "view/medewerker/MedewerkerGevondenBagage.fxml");
        } else {
            fys.changeToAnotherFXML("Corendon Lost & Found - Medewerker gevonden bagage",
                    "view/medewerker/MedewerkerGevondenBagage.fxml");
        }
    }

    @FXML
    private void handleUpdateBagageAction(ActionEvent event) throws IOException {
        if (lang == 2) {
            fys.changeToAnotherFXML("Corendon Lost & Found - Employee update luggage",
                    "view/medewerker/MedewerkerBagageUpdate.fxml");
        } else {
            fys.changeToAnotherFXML("Corendon Lost & Found - Medewerker bagage bewerken",
                    "view/medewerker/MedewerkerBagageUpdate.fxml");
        }
    }
}
