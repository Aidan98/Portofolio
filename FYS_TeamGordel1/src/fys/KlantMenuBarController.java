package fys;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller Doel: De functies van de menubalk laten werken.
 *
 * @author is102 - 3, GORDEL
 */
public class KlantMenuBarController implements Initializable {

    // Attributen
    FYS fys = new FYS();
    Language language = new Language();
    @FXML
    private Button menubarProfielBtn;
    @FXML
    private Button menubarUitloggenBtn;
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
            menubarProfielBtn.setText(language.getKlantMenuBarButtonProfile());
            menubarUitloggenBtn.setText(language.getKlantMenuBarButtonSignOut());
        }
    }

    /**
     * Doel: Gaan naar een nieuwe scene.
     *
     * @param event klik op de knop
     * @throws IOException vangt fouten op.
     */
    @FXML
    private void handleHomeBtnAction(ActionEvent event) throws IOException {
        fys.changeToAnotherFXML("Corendon Lost & Found - Home",
                "view/klant/KlantHome.fxml");
    }

    /**
     * Doel: Gaan naar een nieuwe scene.
     *
     * @param event klik op de knop
     * @throws IOException vangt fouten op.
     */
    @FXML
    private void handleProfielBtnAction(ActionEvent event) throws IOException {
        if (lang == 2) {
            fys.changeToAnotherFXML("Corendon Lost & Found - Profile",
                    "view/klant/KlantProfiel.fxml");
        } else {
            fys.changeToAnotherFXML("Corendon Lost & Found - Profiel",
                    "view/klant/KlantProfiel.fxml");
        }
    }

    /**
     * Doel: Gaan naar een nieuwe scene.
     *
     * @param event klik op de knop
     * @throws IOException vangt fouten op.
     */
    @FXML
    private void handleUitloggenBtnAction(ActionEvent event) throws IOException {
        if (lang == 2) {
            fys.changeToAnotherFXML("Corendon Lost & Found - Log out",
                    "view/Uitloggen.fxml");
        } else {
            fys.changeToAnotherFXML("Corendon Lost & Found - Uitloggen",
                    "view/Uitloggen.fxml");
        }
    }
}
