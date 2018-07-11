package fys;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

/**
 * FXML Controller Doel: Het beheren van de uitgelogd pagina.
 *
 * @author is102 - 3, GORDEL
 */
public class UitloggenController implements Initializable {

    // Attributen
    FYS fys = new FYS();
    Language language = new Language();

    @FXML
    private Button inloggen;
    @FXML
    private Text uBentUitgelogdTotZiens;
    @FXML
    private Button applicatieSluiten;
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
            inloggen.setText(language.getUitloggenButtonInloggen());
            applicatieSluiten.setText(language.getUitloggenButtonApplicatieSluiten());
            uBentUitgelogdTotZiens.setText(language.getUitloggenPromTextuBentUitgelogdTotZiens());

        }
    }

    /*
     * Doel: Gaan naar een nieuwe scene.
     *
     * @param event klik op de knop
     * @throws IOException vangt fouten op.
     */
    @FXML
    private void handleLoginBtnAction(ActionEvent event) throws IOException {
        if (lang == 2) {
            fys.changeToAnotherFXML("Corendon Lost & Found - Sign in", "view/Inloggen.fxml");
        } else {
            fys.changeToAnotherFXML("Corendon Lost & Found - Inloggen", "view/Inloggen.fxml");
        }
    }

    /**
     * Doel: Gaan naar een nieuwe scene.
     *
     * @param event klik op de knop
     * @throws IOException vangt fouten op.
     */
    @FXML
    private void handleCloseBtnAction(ActionEvent event) {
        Platform.exit();
    }
}
