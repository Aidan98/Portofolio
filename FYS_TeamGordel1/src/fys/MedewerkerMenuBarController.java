package fys;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller Doel: Functies van de medewerker menubar maken.
 *
 * @author is102 - 3, GORDEL
 */
public class MedewerkerMenuBarController implements Initializable {

    // Attributen
    Language language = new Language();
    FYS fys = new FYS();
    @FXML
    private JFXButton profiel;
    @FXML
    private JFXButton uitloggen;
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
            profiel.setText(language.getMedewerkerMenuBarButtonProfiel());
            uitloggen.setText(language.getMedewerkerMenuBarButtonUitloggen());
        }
    }

    /**
     * Doel: Deze button linkt naar de medewerker Home pagina.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleHomeBtnAction(ActionEvent event) throws IOException {
        fys.changeToAnotherFXML("Corendon Lost & Found - Home",
                "view/medewerker/MedewerkerHome.fxml");
    }

    /**
     * Doel: Deze button linkt naar de medewerker profiel pagina.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleProfielBtnAction(ActionEvent event) throws IOException {
        if (lang == 2) {
            fys.changeToAnotherFXML("Corendon Lost & Found - Profile",
                    "view/medewerker/MedewerkerProfiel.fxml");
        } else {
            fys.changeToAnotherFXML("Corendon Lost & Found - Profiel",
                    "view/medewerker/MedewerkerProfiel.fxml");
        }
    }

    /**
     * Doel: Deze button linkt naar de uitloggenpagina.
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void handleUitloggenBtnAction(ActionEvent event) throws IOException {
        if (lang == 2) {
            fys.changeToAnotherFXML("Corendon Lost & Found - Sign out",
                    "view/Uitloggen.fxml");
        } else {
            fys.changeToAnotherFXML("Corendon Lost & Found - Uitloggen",
                    "view/Uitloggen.fxml");
        }
    }
}
