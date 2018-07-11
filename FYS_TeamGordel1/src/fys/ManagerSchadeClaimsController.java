package fys;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller Doel: Het maken van de functies van de schadeclaim beheer
 * pagina.
 *
 * @author is102 - 3, GORDEL
 */
public class ManagerSchadeClaimsController implements Initializable {

    // Attributen
    FYS fys = new FYS();
    Language language = new Language();
    @FXML
    private ComboBox<Integer> schadeclaimsCombobox;
    @FXML
    private Label schadeclaimidLbl;
    @FXML
    private Label labelnummerLbl;
    @FXML
    private Label vluchtnummerLbl;
    @FXML
    private Label bestemmingLbl;
    @FXML
    private Label bagageTypeLbl;
    @FXML
    private Label merkLbl;
    @FXML
    private Label kleurLbl;
    @FXML
    private Label bijzonderhedenLbl;
    @FXML
    private Label datumRegistratieLbl;
    @FXML
    private Label aantalDagenLbl;
    @FXML
    private Label uitBetalingLbl;
    @FXML
    private Label bedragLbl;
    @FXML
    private ComboBox<String> redenAfwijzingCombobox;
    @FXML
    private Button bevestigAfwijzing;
    @FXML
    private Button goedkeurenBtn;
    @FXML
    private Button afwijzenBtn;
    @FXML
    private Label schadeclaimSelectieLbl;
    @FXML
    private Label redenSelectieLbl;
    @FXML
    private Text goedgekeurdLbl;
    @FXML
    private AnchorPane dataPane;
    @FXML
    private Button managerBekijkBtn;
    @FXML
    private Label redenAfwijzenLbl;
    @FXML
    private Label labelnmrLbl;
    @FXML
    private Label vluchtnmrLbl;
    @FXML
    private Label bestemLbl;
    @FXML
    private Label typeLbl;
    @FXML
    private Label brandLbl;
    @FXML
    private Label colourLbl;
    @FXML
    private Label specialLbl;
    @FXML
    private Label dateRegLbl;
    @FXML
    private Label daysLbl;
    @FXML
    private Label payOutLbl;
    @FXML
    private Label amountLbl;
    private ObservableList<String> afwijzingenRedenList
            = FXCollections.observableArrayList("Wij zijn het niet eens met het "
                    + "bedrag.", "De claim is niet acceptabel.");
    private final ObservableList<Integer> schadeclaimIDList
            = FXCollections.observableArrayList();
    int lang = fys.getLanguage();

    /**
     * Doel: de pagina inladen.
     *
     * @param url URL
     * @param rb Resources
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (lang == 2) {
            schadeclaimidLbl.setText(language.getManagerSchadeclaimBeherenComboClaim());
            managerBekijkBtn.setText(language.getManagerSchadeclaimBeherenButtonView());
            goedkeurenBtn.setText(language.getManagerSchadeclaimBeherenButtonAccept());
            afwijzenBtn.setText(language.getManagerSchadeclaimBeherenButtonDecline());
            redenAfwijzenLbl.setText(language.getManagerSchadeclaimBeherenLabelReason());
            redenSelectieLbl.setText(language.getManagerSchadeclaimBeherenButtonReason());
            bevestigAfwijzing.setText(language.getManagerSchadeclaimBeherenButtonConfReason());
            afwijzingenRedenList = FXCollections.observableArrayList("We do not agree with the amount."
                    + "", "The claim is not acceptable");
        }
        //Zet info in items in combobox
        redenAfwijzingCombobox.setValue("");
        redenAfwijzingCombobox.setItems(afwijzingenRedenList);
        goedkeurenBtn.setDisable(true);
        afwijzenBtn.setDisable(true);
        schadeclaimSelectieLbl.setVisible(false);
        redenSelectieLbl.setVisible(false);
        goedgekeurdLbl.setVisible(false);
        schadeclaimIDFill();
        // laadt de schadeclaimIDList
    }

    /**
     * Doel: bekijk een schadeclaim
     *
     * @param event klik op de knop
     * @throws IOException vang de fouten op
     */
    @FXML
    private void handleBekijkBtnAction(ActionEvent event) throws IOException {
        int schadeclaimID = 0;
        try {
            schadeclaimID = schadeclaimsCombobox.getValue();
        } catch (Exception e) {
            if (lang == 2) {
                schadeclaimSelectieLbl.setVisible(true);
                schadeclaimSelectieLbl.setText("Select a claim.");
            } else {
                schadeclaimSelectieLbl.setVisible(true);
                schadeclaimSelectieLbl.setText("Selecteer een schadeclaim.");
            }
        }
        if (schadeclaimID > 0) {
            goedkeurenBtn.setDisable(false);
            afwijzenBtn.setDisable(false);
            schadeclaimSelectieLbl.setVisible(false);
            schadeclaimidLbl.setText(String.valueOf(schadeclaimID));
            String selectTableSQL = "SELECT *,datediff(now(),verlorenbagage.datum_verloren) "
                    + "AS dagensindsverloren "
                    + "FROM schadeclaim "
                    + "INNER JOIN verlorenbagage "
                    + "ON verlorenbagage.verlorenbagage_id="
                    + "schadeclaim.verlorenbagage_id "
                    + "INNER JOIN bagage "
                    + "ON verlorenbagage.bagage_id=bagage.bagage_id "
                    + "WHERE schadeclaim.schadeclaim_id=" + schadeclaimID + ";";
            Connection dbConnection = null;
            Statement stmt = null;

            try {
                dbConnection = FYS.getDBConnection();
                stmt = dbConnection.createStatement();

                // execute select SQL stetement
                ResultSet rs = stmt.executeQuery(selectTableSQL);
                // vul de labels 
                if (rs.next()) {
                    dataPane.setVisible(true);
                    labelnummerLbl.setText(rs.getString("bagage.labelnummer"));
                    vluchtnummerLbl.setText(rs.getString("bagage.vluchtnummer"));
                    bestemmingLbl.setText(rs.getString("bagage.bestemming"));
                    bagageTypeLbl.setText(rs.getString("bagage.type"));
                    merkLbl.setText(rs.getString("bagage.merk"));
                    kleurLbl.setText(rs.getString("bagage.kleur"));
                    bijzonderhedenLbl.setText(rs.getString("bagage.bijzondere_kenmerken"));
                    datumRegistratieLbl.setText(rs.getString("verlorenbagage.datum_verloren"));
                    aantalDagenLbl.setText(rs.getString("dagensindsverloren"));
                    uitBetalingLbl.setText("Ja");
                    bedragLbl.setText(rs.getString("schadeclaim.bedrag"));
                    if (lang == 2) {
                        dataPane.setVisible(true);
                        labelnmrLbl.setText(language.getManagerSchadeclaimBeherenLabelLableNbr());
                        vluchtnmrLbl.setText(language.getManagerSchadeclaimBeherenLabelFlightNbr());
                        bestemLbl.setText(language.getManagerSchadeclaimBeherenLabelDestination());
                        typeLbl.setText(language.getManagerSchadeclaimBeherenLabelType());
                        brandLbl.setText(language.getManagerSchadeclaimBeherenLabelBrand());
                        colourLbl.setText(language.getManagerSchadeclaimBeherenLabelColour());
                        specialLbl.setText(language.getManagerSchadeclaimBeherenLabelSpecial());
                        dateRegLbl.setText(language.getManagerSchadeclaimBeherenLabelDate());
                        daysLbl.setText(language.getManagerSchadeclaimBeherenLabelDays());
                        payOutLbl.setText(language.getManagerSchadeclaimBeherenLabelPayOut());
                        amountLbl.setText(language.getManagerSchadeclaimBeherenLabelAmount());
                    }
                }
            } catch (SQLException e) {
                // vang fouten op
            } finally {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(KlantSchadeclaimsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (dbConnection != null) {
                    try {
                        dbConnection.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(KlantSchadeclaimsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    /**
     * Doel: bevestig de afwijzing, haal de reden uit de combobox, en verstuur
     * een email naar de klant.
     *
     * @param event druk op de knop
     */
    @FXML
    private void handleBevestigAfwijzingAction(ActionEvent event) {
        redenAfwijzingCombobox.setDisable(true);
        bevestigAfwijzing.setDisable(true);
        String afwijzingString;
        int schadeclaimID;

        afwijzingString = redenAfwijzingCombobox.getValue();
        schadeclaimID = schadeclaimsCombobox.getValue();
        if (!redenAfwijzingCombobox.getValue().isEmpty()) {
            String email = getKlantEmail(schadeclaimID);
            if (schadeclaimUpdateEmail(email)) {
                //alert bij succes
                if (lang == 2) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success!");
                    alert.setHeaderText("The claim has been reviewed.");
                    alert.setContentText("Press on \"ok\" to continue.");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Gelukt!");
                    alert.setHeaderText("De claim is beoordeeld.");
                    alert.setContentText("Druk op \"ok\" om door te gaan.");
                    alert.showAndWait();
                }
                int status;
                if (afwijzingString.equals("Wij zijn het niet eens met het bedrag.") || afwijzingString.equals("We do not agree with the ammount.")) {
                    status = 3;
                } else {
                    status = 4;
                }
                String insertSQL = "UPDATE schadeclaim SET status=? WHERE schadeclaim_id=?;";

                Connection dbConnection = null;
                PreparedStatement stmt = null;

                try {

                    if (lang == 2) {
                        dbConnection = FYS.getDBConnection();
                        stmt = dbConnection.prepareStatement(insertSQL);
                        stmt.setInt(1, status);
                        stmt.setInt(2, schadeclaimID);

                        stmt.executeUpdate();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success!");
                        alert.setHeaderText("The claim has been reviewed.");
                        alert.setContentText("Press on \"ok\" to continue.");
                        alert.showAndWait();
                    } else {
                        dbConnection = FYS.getDBConnection();
                        stmt = dbConnection.prepareStatement(insertSQL);
                        stmt.setInt(1, status);
                        stmt.setInt(2, schadeclaimID);

                        stmt.executeUpdate();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Success!");
                        alert.setHeaderText("The claim has been reviewed.");
                        alert.setContentText("Press on \"ok\" to continue.");
                        alert.showAndWait();
                    }

                } catch (SQLException e) {
                    // vang fouten op
                } finally {
                    if (stmt != null) {
                        try {
                            stmt.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(KlantSchadeclaimsController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (dbConnection != null) {
                        try {
                            dbConnection.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(KlantSchadeclaimsController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        } else if (lang == 2) {
            redenSelectieLbl.setVisible(true);
            redenSelectieLbl.setText("Select a reason.");
        } else {
            redenSelectieLbl.setVisible(true);
            redenSelectieLbl.setText("Selecteer een reden.");
        }
    }

    /**
     * Doel: Keur de schadeclaim goed. De schadeclaim wordt in de database
     * geupdate.
     *
     * @param event klik op de knop
     */
    @FXML
    private void handleGoedkeurenBtnAction(ActionEvent event) {
        int schadeclaimID = schadeclaimsCombobox.getValue();
        String email = getKlantEmail(schadeclaimID);
        if (schadeclaimUpdateEmail(email)) {
            goedkeurenBtn.setDisable(true);
            afwijzenBtn.setDisable(true);
            String insertSQL = "UPDATE schadeclaim SET status=? WHERE schadeclaim_id=?;";
            Connection dbConnection = null;
            PreparedStatement stmt = null;
            try {
                dbConnection = FYS.getDBConnection();
                stmt = dbConnection.prepareStatement(insertSQL);
                stmt.setInt(1, 2);
                stmt.setInt(2, schadeclaimID);
                stmt.executeUpdate();

                //alert bij succes
                if (lang == 2) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success!");
                    alert.setHeaderText("The claim has been reviewed.");
                    alert.setContentText("Press on \"ok\" to continue.");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Gelukt!");
                    alert.setHeaderText("De claim is beoordeeld.");
                    alert.setContentText("Druk op \"ok\" om door te gaan.");
                    alert.showAndWait();
                }
            } catch (SQLException e) {
                // vang fouten op
            } finally {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(KlantSchadeclaimsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                if (dbConnection != null) {
                    try {
                        dbConnection.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(KlantSchadeclaimsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    /**
     * Doel: maak de knoppen klikbaar om de afwijzing te bevestigen.
     *
     * @param event klik op de knop
     */
    @FXML
    private void handleAfwijzenBtnAction(ActionEvent event) {
        redenAfwijzingCombobox.setDisable(false);
        bevestigAfwijzing.setDisable(false);
        goedkeurenBtn.setDisable(true);
        afwijzenBtn.setDisable(true);
    }

    /**
     * Doel: Haal al de schadeclaims uit de database waar de status nog 1 van is
     */
    public void schadeclaimIDFill() {
        Connection dbConnection = null;
        Statement stmt = null;
        String selectTableSQL = "SELECT schadeclaim_id FROM schadeclaim WHERE status=1";
        try {
            dbConnection = FYS.getDBConnection();
            stmt = dbConnection.createStatement();

            // execute select SQL stetement
            ResultSet rs = stmt.executeQuery(selectTableSQL);
            // voeg al de resultaten toe
            while (rs.next()) {
                int schadeClaimID = rs.getInt("schadeclaim_id");
                schadeclaimIDList.add(schadeClaimID);
            }
            // vul de combobox met de resultaten.
            schadeclaimsCombobox.setItems(schadeclaimIDList);

        } catch (SQLException e) {
            // vang fouten op
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KlantSchadeclaimsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KlantSchadeclaimsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Doel: een email versturen naar een klant over zijn of haar status.
     *
     * @param email het adres naar wie moet worden gemaild.
     * @return of de email is verstuurd
     */
    public boolean schadeclaimUpdateEmail(String email) {
        String to = email;
        if (lang == 2) {
            final String subject = "Your claim has been reviewed.";
            final String body = "img src=\"https://upload.wikimedia.org/wikipedia"
                    + "/en/thumb/3/36/Corendon_Airlines_2014_logo.svg/1280px-Corend"
                    + "on_Airlines_2014_logo.svg.png\" alt=\"Corendon\" style"
                    + "=\"width:200px;height:75px;\">\n"
                    + "<h1><font face=\"calibri\" color=\"black\">Dear customer,</font></h1>\n"
                    + "<font face=\"calibri\" size=\"5\" color=\"black\">"
                    + "your claim has been reviewed. </br>Please sign in on the Corendon "
                    + "Lost luggage application to view your status.</br>\n"
                    + "<br><br>"
                    + " Team <a href=\"https://www.corendon.com\">Corendon</a></font>";
            return FYS.sendFromGMail(to, subject, body);
        } else {
            final String subject = "Uw schadeclaim is beoordeeld.";
            final String body = "img src=\"https://upload.wikimedia.org/wikipedia"
                    + "/en/thumb/3/36/Corendon_Airlines_2014_logo.svg/1280px-Corend"
                    + "on_Airlines_2014_logo.svg.png\" alt=\"Corendon\" style"
                    + "=\"width:200px;height:75px;\">\n"
                    + "<h1><font face=\"calibri\" color=\"black\">Beste klant,</font></h1>\n"
                    + "<font face=\"calibri\" size=\"5\" color=\"black\">"
                    + "Uw schadeclaim is beoordeeld. </br>Log in op de Corendon "
                    + "Verloren bagage applicatie om de status in te zien.</br>\n"
                    + "<br><br>"
                    + " Team <a href=\"https://www.corendon.com\">Corendon</a></font>";
            return FYS.sendFromGMail(to, subject, body);
        }
    }

    /**
     * Doel: haal het klantid op wat is gekoppeld aan de schadeclaim. Dit doe je
     * door uit klant naar verloren bagage naar schadeclaim te navigeren.
     *
     * @param schadeclaimID het ID van de schadeclaim
     * @return het emailadres van de klant
     */
    public String getKlantEmail(int schadeclaimID) {
        String email = "";
        String selectTableSQL = "SELECT klant.emailadres "
                + "FROM schadeclaim "
                + "INNER JOIN verlorenbagage "
                + "ON verlorenbagage.verlorenbagage_id="
                + "schadeclaim.verlorenbagage_id "
                + "INNER JOIN klant "
                + "ON verlorenbagage.klant_id=klant.klant_id "
                + "WHERE schadeclaim.schadeclaim_id=" + schadeclaimID + ";";
        Connection dbConnection = null;
        Statement stmt = null;
        try {
            dbConnection = FYS.getDBConnection();
            stmt = dbConnection.createStatement();

            // execute select SQL stetement
            ResultSet rs = stmt.executeQuery(selectTableSQL);
            if (rs.next()) {
                email = rs.getString("klant.emailadres");
            }
        } catch (SQLException e) {
            // vang fouten op
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KlantSchadeclaimsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(KlantSchadeclaimsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return email;
    }
}
