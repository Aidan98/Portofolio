package fys;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller Doel: De knoppen van de menubalk laten werken
 *
 * @author is102 - 3, GORDEL
 */
public class ManagerMenuBarController implements Initializable {

    // Attributen
    FYS fys = new FYS();
    Language language = new Language();
    @FXML
    private JFXButton menubarProfielBtn;
    @FXML
    private JFXButton menubarUitloggenBtn;
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
            menubarUitloggenBtn.setText(language.getManagerMenuBarSignOut());
            menubarProfielBtn.setText(language.getManagerMenuBarProfile());
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
        fys.changeToAnotherFXML("Corendon Lost & Found - Home", "view/manager/ManagerHome.fxml");
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
                    "view/manager/ManagerProfiel.fxml");
        } else {
            fys.changeToAnotherFXML("Corendon Lost & Found - Profiel",
                    "view/manager/ManagerProfiel.fxml");
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
            fys.changeToAnotherFXML("Corendon Lost & Found - Sign out",
                    "view/Uitloggen.fxml");
        } else {
            fys.changeToAnotherFXML("Corendon Lost & Found - "
                    + "Uitloggen", "view/Uitloggen.fxml");
        }
    }
}
