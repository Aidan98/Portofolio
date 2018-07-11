package fys;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller Doel: De functies van de homepagina van de manager maken
 *
 * @author is102 - 3, GORDEL
 */
public class ManagerHomeController implements Initializable {

    // attributen
    FYS fys = new FYS();
    Language language = new Language();
    @FXML
    private Button jaaranalysesBtn;
    @FXML
    private Button schadeclaimsBeherenBtn;
    @FXML
    private Button accountsBeherenBtn;
    @FXML
    private Button logboekActiviteitenBtn;
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
            jaaranalysesBtn.setText(language.getManagerHomeYearlyAnalysis());
            schadeclaimsBeherenBtn.setText(language.getManagerHomeMangeClaims());
            accountsBeherenBtn.setText(language.getManagerHomeManageAccounts());
            logboekActiviteitenBtn.setText(language.getManagerHomeActivityLog());
        }
    }

    /**
     * Doel: Gaan naar een nieuwe scene.
     *
     * @param event klik op de knop
     * @throws IOException vangt fouten op.
     */
    @FXML
    private void handleJaaranalyseBtnAction(ActionEvent event) throws IOException {
        if (lang == 2) {
            fys.changeToAnotherFXML("Corendon Lost & Found - Manager Yearly Analysis",
                    "view/manager/ManagerAnalyse.fxml");
        } else {
            fys.changeToAnotherFXML("Corendon Lost & Found - Manager Analyse",
                    "view/manager/ManagerAnalyse.fxml");
        }

    }

    /**
     * Doel: Gaan naar een nieuwe scene.
     *
     * @param event klik op de knop
     * @throws IOException vangt fouten op.
     */
    @FXML
    private void handleSchadeclaimsBtnAction(ActionEvent event) throws IOException {
        if (lang == 2) {
            fys.changeToAnotherFXML("Corendon Lost & Found - Manager Claims",
                    "view/manager/ManagerSchadeClaims.fxml");
        } else {
            fys.changeToAnotherFXML("Corendon Lost & Found - Manager Schadeclaims",
                    "view/manager/ManagerSchadeClaims.fxml");
        }

    }

    /**
     * Doel: Gaan naar een nieuwe scene.
     *
     * @param event klik op de knop
     * @throws IOException vangt fouten op.
     */
    @FXML
    private void handleAccountsBtnAction(ActionEvent event) throws IOException {
        if (lang == 2) {
            fys.changeToAnotherFXML("Corendon Lost & Found - Manager Account Management",
                    "view/manager/ManagerAccountsBeheren.fxml");
        } else {
            fys.changeToAnotherFXML("Corendon Lost & Found - Manager Accounts Beheren",
                    "view/manager/ManagerAccountsBeheren.fxml");
        }
    }

    /**
     * Doel: Gaan naar een nieuwe scene.
     *
     * @param event klik op de knop
     * @throws IOException vangt fouten op.
     */
    @FXML
    private void handleLogboekBtnAction(ActionEvent event) throws IOException {
        if (lang == 2) {
            fys.changeToAnotherFXML("Corendon Lost & Found - Manager Log",
                    "view/manager/ManagerLogboek.fxml");
        } else {
            fys.changeToAnotherFXML("Corendon Lost & Found - Manager Logboek",
                    "view/manager/ManagerLogboek.fxml");
        }
    }
}
