package fys;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller Doel: Functies van medewerker klantregistreren pagina maken.
 *
 * @author is102 - 3, GORDEL
 */
public class MedewerkerKlantRegController implements Initializable {

    // Attributen
    Language language = new Language();
    FYS fys = new FYS();
    @FXML
    private TextField voornaamTxt, adresTxt, woonplaatsTxt, postcodeTxt, landTxt, telefoonTxt,
            emailTxt, labelnummerTxt, vluchtnummerTxt, bestemmingTxt, typeTxt, kleurTxt, merkTxt,
            tussenvoegselTxt, achternaamTxt;
    @FXML
    private TextArea bijzondereKenmerkenTxt;
    @FXML
    private ComboBox<String> luchthavenCombobox;
    @FXML
    private Label vermisteBagageRegForm;
    @FXML
    private Text luchthavenText;
    @FXML
    private Label reizigerInformatie;
    @FXML
    private Label bagageLabelInformatie;
    @FXML
    private Label bagageInformatie;
    @FXML
    private JFXButton slaOpButton;

    private final ObservableList<String> vliegveldList = FXCollections.observableArrayList();
    private int luchthavenNummer = 0;
    private int dbBagageId = 0;
    private int dbKlantId = 0;
    private String voornaam;
    private String tussenvoegsel;
    private String achternaam;
    private String kleurKoffer;
    private String typeKoffer;
    private String merkKoffer;
    private String bestemming;
    private String email;
    private String land;
    private String woonplaats;
    private String adres;
    private String postcode;
    private String telefoonnummer;
    private String bijzondereKenmerken;
    private String vluchtnummer;
    private String labelnummer;
    int lang = fys.getLanguage();

    /**
     * Doel: Initialiseer de pagina, vult combobox informatie(luchthavens) uit
     * database.
     *
     * @param url URL
     * @param rb ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (lang == 2) {
            vermisteBagageRegForm.setText(language.getMedewerkerKlantRegLabelVermisteBagageRegForm());
            reizigerInformatie.setText(language.getMedewerkerKlantRegLabelReizigerInformatie());
            bagageLabelInformatie.setText(language.getMedewerkerKlantRegLabelBagageLabelInformatie());
            bagageInformatie.setText(language.getMedewerkerKlantRegLabelBagageInformatie());
            slaOpButton.setText(language.getMedewerkerKlantRegButtonSlaOpButtom());
            luchthavenText.setText(language.getMedewerkerKlantRegPromptTextLuchthavenText());
            voornaamTxt.setPromptText(language.getMedewerkerKlantRegPromptTextVoornaamTxt());
            adresTxt.setPromptText(language.getMedewerkerKlantRegPromptTextAdresTxt());
            woonplaatsTxt.setPromptText(language.getMedewerkerKlantRegPromptTextWoonplaatsTxt());
            postcodeTxt.setPromptText(language.getMedewerkerKlantRegPromptTextPostcodeTxt());
            landTxt.setPromptText(language.getMedewerkerKlantRegPromptTextLandTxt());
            telefoonTxt.setPromptText(language.getMedewerkerKlantRegPromptTextTelefoonTxt());
            emailTxt.setPromptText(language.getMedewerkerKlantRegPromptTextEmailTxt());
            labelnummerTxt.setPromptText(language.getMedewerkerKlantRegPromptTextLabelnummerTxt());
            vluchtnummerTxt.setPromptText(language.getMedewerkerKlantRegPromptTextVluchtnummerTxt());
            bestemmingTxt.setPromptText(language.getMedewerkerKlantRegPromptTextBestemmingTxt());
            typeTxt.setPromptText(language.getMedewerkerKlantRegPromptTextTypeTxt());
            kleurTxt.setPromptText(language.getMedewerkerKlantRegPromptTextKleurTxt());
            merkTxt.setPromptText(language.getMedewerkerKlantRegPromptTextMerkTxt());
            tussenvoegselTxt.setPromptText(language.getMedewerkerKlantRegPromptTextTussenvoegselTxt());
            achternaamTxt.setPromptText(language.getMedewerkerKlantRegPromptTextAchternaamTxt());
            bijzondereKenmerkenTxt.setPromptText(language.getMedewerkerklantRegPromptTextBijzondereKenmerkenTxt());
        }

        //voeg een leeg string aan observablelist
        vliegveldList.add("");

        //roep query op voor alle vliegvelden
        try {
            selectFromVliegveld("vliegveld");
        } catch (SQLException ex) {
            Logger.getLogger(MedewerkerKlantRegController.class.getName()).log(Level.SEVERE,
                    null, ex);
        }

        //zet de value van de combobox op leeg string
        luchthavenCombobox.setValue("");

        //Voeg alle values van de observablelist aan de combobox
        luchthavenCombobox.setItems(vliegveldList);
    }

    /**
     * Doel: Deze button slaat ingevoerde gegevens op in de database. Linkt naar
     * de homepagina en stuurt een email aan ingevoerde emailadres.
     *
     * @param event
     * @throws IOException
     * @throws SQLException
     */
    @FXML
    private void handleSlaOpBtnAction(ActionEvent event) throws IOException, SQLException {
        //haal value van luchthaven combobox op
        String vliegveldNaam = (String) luchthavenCombobox.getValue();

        //controleer dat combobox gevuld is
        if (!luchthavenCombobox.getValue().isEmpty()) {
            //roep methode aan om vliegveld id terug te geven
            selectFromVliegveld(vliegveldNaam);
        }

        // Haalt gegevens uit combobox en tekstvelden op.
        voornaam = voornaamTxt.getText();
        tussenvoegsel = tussenvoegselTxt.getText();
        achternaam = achternaamTxt.getText();
        kleurKoffer = kleurTxt.getText();
        typeKoffer = typeTxt.getText();
        merkKoffer = merkTxt.getText();
        bestemming = bestemmingTxt.getText();
        email = emailTxt.getText().toLowerCase();
        land = landTxt.getText();
        woonplaats = woonplaatsTxt.getText();
        adres = adresTxt.getText();
        postcode = postcodeTxt.getText();
        telefoonnummer = telefoonTxt.getText();
        bijzondereKenmerken = bijzondereKenmerkenTxt.getText();
        vluchtnummer = vluchtnummerTxt.getText();
        labelnummer = labelnummerTxt.getText();

        //roep methode op om bagageid terug te geven
        selectBagageId();
        int bagageId = dbBagageId;

        //haal medewerkerid op
        int medewerkerId = fys.getGebruikerID();

        //geneer een randomwachtwoord voor klant
        String newPassword = FYS.randomWachtwoord();

        //hash gegeneerde wachtwoord om in database te zetten
        String hashedPassword = String.valueOf(newPassword.hashCode());

        // Email aan ingevoerde emailadres met daarin een tijdelijk wachtwoord om in te loggen.
        String to = email; // lijst met ontvangers.
        String subject;
        String body;
        String onderwerp;
        String bagageEmail;

        //email met account informatie
        if (lang == 2) {
            subject = "Welcome to Corendon Lost & Found application";
            body = "<p>Beste&nbsp;" + voornaam + "&nbsp;" + tussenvoegsel
                    + "&nbsp;" + achternaam + ",</p>\n"
                    + "<p>We have created an account for you at Corendon Lost &amp;"
                    + " Found application.</p>\n"
                    + "<p> you can sign in here&nbsp;with the following information:</p>\n"
                    + "<p>Username:&nbsp;" + email + "<br />Password:&nbsp;<strong><u>"
                    + newPassword + "</u></strong></p>\n"
                    + "<p>use this temporary key to sign in and create a new password"
                    + "</p>\n"
                    + "<p>With kind regards,</p>\n"
                    + "<p>Team&nbsp;<a href=\"\\&quot;https://www.corendon.com\\&qu"
                    + "ot;\">Corendon</a></p>";
        } else {
            subject = "Welkom bij Corendon Lost & Found applicatie";
            body = "<p>Beste&nbsp;" + voornaam + "&nbsp;" + tussenvoegsel
                    + "&nbsp;" + achternaam + ",</p>\n"
                    + "<p>We hebben een account voor je aangemaakt op Corendon Lost &amp;"
                    + " Found applicatie.</p>\n"
                    + "<p>Je kunt hierop inloggen&nbsp;met het volgende gegevens:</p>\n"
                    + "<p>Gebruikersnaam:&nbsp;" + email + "<br />Wachtwoord:&nbsp;<strong><u>"
                    + newPassword + "</u></strong></p>\n"
                    + "<p>Gebruik dit tijdelijke wachtwoord om in te loggen en een zelfgekozen "
                    + "wachtwoord aan te maken.</p>\n"
                    + "<p>Met vriendelijke groet,</p>\n"
                    + "<p>Team&nbsp;<a href=\"\\&quot;https://www.corendon.com\\&qu"
                    + "ot;\">Corendon</a></p>";

        }
        //Email met bagage informatie
        if (lang == 2) {
            onderwerp = "your reported luggage";
            bagageEmail = "<p>Best&nbsp;" + voornaam + "&nbsp;"
                    + tussenvoegsel + "&nbsp;" + achternaam + ",</p>\n"
                    + "<p>We have saved the following information about your luggage:</p>\n"
                    + "<p>Destination: <strong>" + bestemming + "</strong></p>\n"
                    + "<p>Tag number: <strong>" + labelnummer + "</strong></p>\n"
                    + "<p>flight number: <strong>" + vluchtnummer + "</strong></p>\n"
                    + "<p>Type: <strong>" + typeKoffer + "</strong></p>\n"
                    + "<p>Brand: <strong>" + merkKoffer + "</strong></p>\n"
                    + "<p>Colour: <strong>" + kleurKoffer + "</strong></p>\n"
                    + "<p>Special characteristics: <strong>" + bijzondereKenmerken + "</strong></p>\n"
                    + "<p>With kind regards,</p>\n"
                    + "<p>Team&nbsp;<a href=\"\\&quot;https://www.corendon.com\\&quot;\">Corendon</a>"
                    + "</p>";
        } else {
            onderwerp = "Uw gemelde bagage";
            bagageEmail = "<p>Beste&nbsp;" + voornaam + "&nbsp;"
                    + tussenvoegsel + "&nbsp;" + achternaam + ",</p>\n"
                    + "<p>We hebben de volgende informatie over uw bagage opgeslagen:</p>\n"
                    + "<p>Bestemming: <strong>" + bestemming + "</strong></p>\n"
                    + "<p>Labelnummer: <strong>" + labelnummer + "</strong></p>\n"
                    + "<p>Vluchtnummer: <strong>" + vluchtnummer + "</strong></p>\n"
                    + "<p>Type: <strong>" + typeKoffer + "</strong></p>\n"
                    + "<p>Merk: <strong>" + merkKoffer + "</strong></p>\n"
                    + "<p>Kleur: <strong>" + kleurKoffer + "</strong></p>\n"
                    + "<p>Bijzondere kenmerken: <strong>" + bijzondereKenmerken + "</strong></p>\n"
                    + "<p>Met vriendelijke groet,</p>\n"
                    + "<p>Team&nbsp;<a href=\"\\&quot;https://www.corendon.com\\&quot;\">Corendon</a>"
                    + "</p>";
        }

        // Invoer controleren en juiste labels met informatie tonen wanneer dit niet klopt.
        if (!vliegveldNaam.equals("")) {
            if (!voornaam.isEmpty()) {
                if (!achternaam.isEmpty()) {
                    if (!adres.isEmpty()) {
                        if (!woonplaats.isEmpty()) {
                            if (!postcode.isEmpty()) {
                                if (!land.isEmpty()) {
                                    if (!telefoonnummer.isEmpty()) {
                                        if (!email.isEmpty()) {
                                            if (fys.isEmailValid(email)) {
                                                if (!fys.isKlant(email) && !fys.isMedewerker(email)) {
                                                    if (!bestemming.isEmpty()) {
                                                        if (!typeKoffer.isEmpty()) {
                                                            if (!merkKoffer.isEmpty()) {
                                                                if (!kleurKoffer.isEmpty()) {
                                                                    insertInto(medewerkerId,
                                                                            bagageId,
                                                                            hashedPassword,
                                                                            luchthavenNummer);

                                                                    // Stuurt emails
                                                                    if (FYS.sendFromGMail(to, subject, body)) {
                                                                        FYS.sendFromGMail(to, onderwerp, bagageEmail);
                                                                    }

                                                                    //alert bij gelukt!
                                                                    if (lang == 2) {
                                                                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                                        alert.setTitle("Succes!");
                                                                        alert.setHeaderText("The lost luggage haf been registered and an has been created for the client.");
                                                                        alert.setContentText("Press \"ok\" to continue.");
                                                                        alert.showAndWait();

                                                                        //Reload hetzelfde pagina
                                                                        fys.changeToAnotherFXML("Corendon Lost & Found - Employee customer lost luggage",
                                                                                "view/medewerker/MedewerkerKlantReg.fxml");
                                                                    } else {
                                                                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                                        alert.setTitle("Gelukt!");
                                                                        alert.setHeaderText("De verloren bagage is geregistreerd en een account is gemaakt voor de klant.");
                                                                        alert.setContentText("Druk op \"ok\" om door te gaan.");
                                                                        alert.showAndWait();

                                                                        //Reload hetzelfde pagina
                                                                        fys.changeToAnotherFXML("Corendon Lost & Found - Medewerker klant verloren bagage",
                                                                                "view/medewerker/MedewerkerKlantReg.fxml");
                                                                    }

                                                                } else {
                                                                    fys.nietIngevuldeVelden(kleurTxt);
                                                                    if (lang == 2) {
                                                                        fys.maakNotifaction("Error", "Fill in the colour of the lost luggage");
                                                                    } else {
                                                                        fys.maakNotifaction("Error", "Vul de kleur van de verloren koffer in!");
                                                                    }
                                                                }
                                                            } else {
                                                                fys.nietIngevuldeVelden(merkTxt);
                                                                if (lang == 2) {
                                                                    fys.maakNotifaction("Error", "Fill in the brand of the lost luggage");
                                                                } else {
                                                                    fys.maakNotifaction("Error", "Vul het merk van de verloren koffer in!");
                                                                }
                                                            }
                                                        } else {
                                                            fys.nietIngevuldeVelden(typeTxt);
                                                            if (lang == 2) {
                                                                fys.maakNotifaction("Error", "Fill in the type of luggage that has been lost");
                                                            } else {
                                                                fys.maakNotifaction("Error", "Vul het type van de verloren koffer in!");
                                                            }
                                                        }
                                                    } else {
                                                        fys.nietIngevuldeVelden(bestemmingTxt);
                                                        if (lang == 2) {
                                                            fys.maakNotifaction("Error", "Select the luggage destination");
                                                        } else {
                                                            fys.maakNotifaction("Error", "Vul de bestemming van de koffer in!");
                                                        }
                                                    }
                                                } else {
                                                    fys.nietIngevuldeVelden(emailTxt);
                                                    if (lang == 2) {
                                                        fys.maakNotifaction("Error", "Email already exists");
                                                    } else {
                                                        fys.maakNotifaction("Error", "Email bestaat al!");
                                                    }
                                                }
                                            } else {
                                                fys.nietIngevuldeVelden(emailTxt);
                                                if (lang == 2) {
                                                    fys.maakNotifaction("Error", "Enter a valid email");
                                                } else {
                                                    fys.maakNotifaction("Error", "Vul een geldige email in!");
                                                }
                                            }
                                        } else {
                                            fys.nietIngevuldeVelden(emailTxt);
                                            if (lang == 2) {
                                                fys.maakNotifaction("Error", "Enter your emailaddress");
                                            } else {
                                                fys.maakNotifaction("Error", "Vul een email in!");
                                            }
                                        }
                                    } else {
                                        fys.nietIngevuldeVelden(telefoonTxt);
                                        if (lang == 2) {
                                            fys.maakNotifaction("Error", "Enter your telephone number in!");
                                        } else {
                                            fys.maakNotifaction("Error", "Vul een telefoonnummer in!");
                                        }
                                    }
                                } else {
                                    fys.nietIngevuldeVelden(landTxt);
                                    if (lang == 2) {
                                        fys.maakNotifaction("Error", "Enter the country of the owner");
                                    } else {
                                        fys.maakNotifaction("Error", "Vul het land van de eigenaar in!");
                                    }
                                }
                            } else {
                                fys.nietIngevuldeVelden(postcodeTxt);
                                if (lang == 2) {
                                    fys.maakNotifaction("Error", "Enter your zip code");
                                } else {
                                    fys.maakNotifaction("Error", "Vul een postcode in!");
                                }
                            }
                        } else {
                            fys.nietIngevuldeVelden(woonplaatsTxt);
                            if (lang == 2) {
                                fys.maakNotifaction("Error", "Enter your residence");
                            } else {
                                fys.maakNotifaction("Error", "Vul een woonplaats in!");
                            }
                        }
                    } else {
                        fys.nietIngevuldeVelden(adresTxt);
                        if (lang == 2) {
                            fys.maakNotifaction("Error", "Enter your address");
                        } else {
                            fys.maakNotifaction("Error", "Vul een adres in!");
                        }
                    }
                } else {
                    fys.nietIngevuldeVelden(achternaamTxt);
                    if (lang == 2) {
                        fys.maakNotifaction("Error", "Enter your surname");
                    } else {
                        fys.maakNotifaction("Error", "Vul een achternaam in!");
                    }
                }
            } else {
                fys.nietIngevuldeVelden(voornaamTxt);
                if (lang == 2) {
                    fys.maakNotifaction("Error", "Enter your first name");
                } else {
                    fys.maakNotifaction("Error", "Vul een voornaam in!");
                }
            }
        } else {
            luchthavenCombobox.setStyle("-fx-background-color: rgba(192, 192, 192, 0.6)");
            if (lang == 2) {
                fys.maakNotifaction("Error", "Select an airport!");
            } else {
                fys.maakNotifaction("Error", "Selecteer een luchthaven!");
            }
        }

    }

    /**
     * Doel: Deze methode maakt een bagageID aan voor de verloren bagage.
     *
     * @throws SQLException
     */
    private void selectBagageId() throws SQLException {

        Connection dbConnection = null;
        Statement statement = null;
        // Sorteert bagage_id in database van hoog naar laag en pakt dan het hoogste nummer.
        String selectTableSQL = "SELECT bagage_id FROM bagage ORDER BY bagage_id DESC LIMIT 1";

        try {
            dbConnection = FYS.getDBConnection();
            statement = dbConnection.createStatement();

            // Voert SQL statement uit.
            ResultSet rs = statement.executeQuery(selectTableSQL);

            while (rs.next()) {
                int BagageId = rs.getInt("bagage_id");
                // Hoogste nummer bagage_id + 1 = bagage_id van nieuw geregistreerde bagage.
                dbBagageId = BagageId + 1;
            }

        } catch (SQLException e) {
            // Vangt errors op.
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    /**
     * Doel: Deze methode kiest het laatste klantID aan voor de eigenaar van de
     * verloren bagage.
     *
     * @throws SQLException
     */
    private void selectKlantId() throws SQLException {

        Connection dbConnection = null;
        Statement statement = null;
        // Sorteert klant_id in database van hoog naar laag en pakt dan het hoogste nummer.
        String selectTableSQL = "SELECT * FROM klant ORDER BY klant_id DESC LIMIT 1";

        try {
            dbConnection = FYS.getDBConnection();
            statement = dbConnection.createStatement();

            // Voert SQL statement uit.
            ResultSet rs = statement.executeQuery(selectTableSQL);

            while (rs.next()) {
                int klantId = rs.getInt("klant_id");
                dbKlantId = klantId;
            }

        } catch (SQLException e) {

            // Vangt errors op.
        } finally {

            if (statement != null) {
                statement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    /**
     * Doel: Deze methode slaat de ingevoerde klant gegevens op in de database
     * en registreerd de activiteit in het logboek.
     *
     * @param medewerkerId
     * @param bagageId
     * @param wachtwoord
     * @param vliegveldId
     * @throws SQLException
     */
    private void insertInto(int medewerkerId, int bagageId, String wachtwoord, int vliegveldId)
            throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement2 = null;

        String sql = "INSERT INTO klant (naam, tussenvoegsel, achternaam, adres, woonplaats, "
                + "postcode, land, telefoonnummer, emailadres, wachtwoord) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String sql2 = "INSERT INTO bagage (bagage_id, labelnummer, vluchtnummer, bestemming, "
                + "type, merk, kleur, bijzondere_kenmerken, vliegveld_id)"
                + "VALUES (?,?,?,?,?,?,?,?, ?)";

        try {
            dbConnection = FYS.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement2 = dbConnection.prepareStatement(sql2);

            preparedStatement.setString(1, voornaam);
            preparedStatement.setString(2, tussenvoegsel);
            preparedStatement.setString(3, achternaam);
            preparedStatement.setString(4, adres);
            preparedStatement.setString(5, woonplaats);
            preparedStatement.setString(6, postcode);
            preparedStatement.setString(7, land);
            preparedStatement.setString(8, telefoonnummer);
            preparedStatement.setString(9, email);
            preparedStatement.setString(10, wachtwoord);

            preparedStatement2.setInt(1, bagageId);
            preparedStatement2.setString(2, labelnummer);
            preparedStatement2.setString(3, vluchtnummer);
            preparedStatement2.setString(4, bestemming);
            preparedStatement2.setString(5, typeKoffer);
            preparedStatement2.setString(6, merkKoffer);
            preparedStatement2.setString(7, kleurKoffer);
            preparedStatement2.setString(8, bijzondereKenmerken);
            preparedStatement2.setInt(9, vliegveldId);

            // Voert SQL statements uit.
            preparedStatement.executeUpdate();
            preparedStatement2.executeUpdate();
            insertIntoVerlorenBagageAndLogboek(vliegveldId, bagageId, medewerkerId);

        } catch (SQLException e) {
            // Vangt errors op.
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (preparedStatement2 != null) {
                preparedStatement2.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    /**
     * Doel: Deze methode slaat de bagage gegevens op in de database en
     * registreert de actie in het logboek.
     *
     * @param vliegvledId
     * @param bagageId
     * @param medewerkerId
     * @throws SQLException
     */
    private void insertIntoVerlorenBagageAndLogboek(int vliegvledId, int bagageId, int medewerkerId)
            throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement3 = null;
        PreparedStatement preparedStatement4 = null;

        String sql3 = "INSERT INTO verlorenbagage (datum_verloren, vliegveld_id,"
                + " bagage_id, klant_id) VALUES (?, ?, ?, ?)";
        String sql4 = "INSERT INTO logboek (medewerker_id, actie, datum) VALUES (?, ?, ?)";

        try {
            dbConnection = FYS.getDBConnection();
            preparedStatement3 = dbConnection.prepareStatement(sql3);
            preparedStatement4 = dbConnection.prepareStatement(sql4);

            selectKlantId();
            int klantId = dbKlantId;

            preparedStatement3.setString(1, FYS.getDate());
            preparedStatement3.setInt(2, vliegvledId);
            preparedStatement3.setInt(3, bagageId);
            preparedStatement3.setInt(4, klantId);

            preparedStatement4.setInt(1, medewerkerId);
            preparedStatement4.setString(2, "Klant bagage geregisteerd met klant id "
                    + klantId + " en bagage id " + bagageId);
            preparedStatement4.setString(3, FYS.getDate());
            // Voert SQL statements uit.

            preparedStatement3.executeUpdate();
            preparedStatement4.executeUpdate();

        } catch (SQLException e) {
            // Vangt errors op.
        } finally {
            if (preparedStatement3 != null) {
                preparedStatement3.close();
            }
            if (preparedStatement4 != null) {
                preparedStatement4.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    /**
     * Doel: Deze methode vult de combobox of geeft de naam van de luchthaven.
     *
     * @param luchthavenNaam
     * @throws SQLException
     */
    private void selectFromVliegveld(String luchthavenNaam) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String selectTableSQL;
        if ("vliegveld".equals(luchthavenNaam)) {
            selectTableSQL = "SELECT * FROM vliegveld";
        } else {
            selectTableSQL = "SELECT * FROM vliegveld WHERE naam_vliegveld = ?";
        }

        try {
            dbConnection = FYS.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectTableSQL);
            if (!"vliegveld".equals(luchthavenNaam)) {
                preparedStatement.setString(1, luchthavenNaam);
            }

            // Voert SQL statement uit
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String vliegveldNaam = rs.getString("naam_vliegveld");
                luchthavenNummer = rs.getInt("vliegveld_id");

                if ("vliegveld".equals(luchthavenNaam)) {
                    vliegveldList.add(vliegveldNaam);
                }
            }

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
