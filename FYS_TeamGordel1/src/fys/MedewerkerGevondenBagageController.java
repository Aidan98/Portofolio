package fys;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

/**
 * FXML Controller Doel:Functies van gevondenbagage pagina maken.
 *
 * @author is102 - 3, GORDEL
 */
public class MedewerkerGevondenBagageController implements Initializable {

    // Attributen
    FYS fys = new FYS();
    Language language = new Language();

    @FXML
    private TextField labelnummerTxt, vluchtnummerTxt, bestemmingTxt, typeTxt, kleurTxt, merkTxt;
    @FXML
    private TextArea bijzondereKenmerkenTxt;
    @FXML
    private Button slaOpBtn;
    @FXML
    private ComboBox<String> luchthavenCombobox;
    @FXML
    private Label infoFotolbl;
    @FXML
    private ImageView kofferImageView;
    @FXML
    private Label gevondenBagRegFrom;
    @FXML
    private Text luchthavenText;
    @FXML
    private Label bagageLblInformatieText;
    @FXML
    private Label bagageInformatieText;
    @FXML
    private JFXButton fotoUploadenBtn;

    private final ObservableList<String> vliegveldList = FXCollections.observableArrayList();
    private int luchthavenNummer;
    private int dbBagageId;
    private String bestemming;
    private String bijzondereKenmerken;
    private String merkKoffer;
    private String kleurKoffer;
    private String typeKoffer;
    private String vluchtnummer;
    private String labelnummer;
    private File file;
    private Image image;
    int lang = fys.getLanguage();

    /**
     * Doel: Initialiseer de pagina, vult luchthaven combobox.
     *
     * @param url URL
     * @param rb ResourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (lang == 2) {
            gevondenBagRegFrom.setText(language.getMedewerkerGevondenBagageLabelGevondenBagRegFrom());
            bagageInformatieText.setText(language.getmedewerkerGevondenBagageLabelBagageInformatieText());
            bagageLblInformatieText.setText(language.getMedewerkerGevondenBagageLabelBagageLblInformatieText());
            fotoUploadenBtn.setText(language.getMedewerkerGevondenBagageLabelFotoUploadenBtn());
            luchthavenText.setText(language.getMedewerkerGevondenBagagePromptTextLuchthavenText());
            slaOpBtn.setText(language.getMedewerkerGevondenBagageButtonSlaOpBtn());
            infoFotolbl.setText(language.getMedewerkerGevondenBagageLabelInfoFotolbl());
            // setPromptText
            labelnummerTxt.setPromptText(language.getMedewerkerGevondenBagagePromptTextLabelnummerTxt());
            vluchtnummerTxt.setPromptText(language.getMedewerkerGevondenBagagePromptTextVluchtnummerTxt());
            bestemmingTxt.setPromptText(language.getMedewerkerGevondenBagagePromptTextBestemmingTxt());
            typeTxt.setPromptText(language.getMedewerkerGevondenBagagePromptTextTypeTxt());
            kleurTxt.setPromptText(language.getMedewerkerGevondenBagagePromptTextKleurTxt());
            merkTxt.setPromptText(language.getMedewerkerGevondenBagagePromptTextMerkTxt());
            bijzondereKenmerkenTxt.setPromptText(language.getMedewerkerGevondenBagagePromptTextBestemmingTxt());
            //voeg een leeg string aan observablelist
            vliegveldList.add("");
        }
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
     * Doel: Deze button zorgt ervoor dat je een fotobestand kan kiezen,
     * uploaden en zien en controleert de invoer. Laat zo nodig een label met
     * informatie zien.
     *
     * @param event
     */
    @FXML
    private void handleFotoUploadenAction(ActionEvent event) {
        // Laat de gebruiker een foto kiezen
        FileChooser fileChooser = new FileChooser();
        //filteren onder de volgende extensies
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files",
                "*.png", "*.jpg", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);
        file = fileChooser.showOpenDialog(null);

        //check of een foto is upgeload
        if (file.length() > 0) {
            slaOpBtn.setDisable(false);
            infoFotolbl.setVisible(false);
        }

        //Path van foto terug geven in image
        image = new Image(file.toURI().toString());//path, PrefWidth
        // laat foto zien.
        kofferImageView.setImage(image);
    }

    /**
     * Doel: Deze button slaat alle ingevoerde bagage gegevens (behalve de foto)
     * op en controleert de invoer en laat zo nodig een label met informatie
     * zien.
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

        // haalt gegevens op uit de tekstvelden
        bestemming = bestemmingTxt.getText();
        bijzondereKenmerken = bijzondereKenmerkenTxt.getText();
        merkKoffer = merkTxt.getText();
        kleurKoffer = kleurTxt.getText();
        typeKoffer = typeTxt.getText();
        vluchtnummer = vluchtnummerTxt.getText();
        labelnummer = labelnummerTxt.getText();

        //roep methode op om bagageid terug te geven
        selectBagageId();
        int bagageId = dbBagageId;

        //haal medewerkerid op
        int medewerkerId = fys.getGebruikerID();

        //Controleert invoer en laat zo nodig labels zien.
        if (!vliegveldNaam.equals("")) {
            if (!typeTxt.getText().isEmpty()) {
                if (!merkTxt.getText().isEmpty()) {
                    if (!kleurTxt.getText().isEmpty()) {

                        //methode voor insert aan roep
                        insertIntoDatabase(luchthavenNummer, medewerkerId, bagageId, file);

                        //alert bij succes
                        if (lang == 2) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Succes");
                            alert.setHeaderText("The luggage that has been foud is registered.");
                            alert.setContentText("Press \"ok\" to continue.");
                            alert.showAndWait();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Gelukt!");
                            alert.setHeaderText("De gevonden bagage is geregistreerd.");
                            alert.setContentText("Druk op \"ok\" om door te gaan.");
                            alert.showAndWait();
                        }
                        //Reload hetzelfde pagina                        
                        if (lang == 2) {
                            fys.changeToAnotherFXML("Corendon Lost & Found - "
                                    + "Employee found luggage",
                                    "view/medewerker/MedewerkerGevondenBagage.fxml");
                        } else {
                            fys.changeToAnotherFXML("Corendon Lost & Found - "
                                    + "Medewerker gevonden bagage",
                                    "view/medewerker/MedewerkerGevondenBagage.fxml");
                        }

                    } else {

                        fys.nietIngevuldeVelden(kleurTxt);
                        if (lang == 2) {
                            fys.maakNotifaction("Error", "Fill in a colour");
                        } else {
                            fys.maakNotifaction("Error", "Vul een Kleur in!");
                        }
                    }
                } else {
                    fys.nietIngevuldeVelden(merkTxt);
                    if (lang == 2) {
                        fys.maakNotifaction("Error", "Fill in a brand");
                    } else {
                        fys.maakNotifaction("Error", "Vul een merk in!");
                    }
                }
            } else {
                fys.nietIngevuldeVelden(typeTxt);
                if (lang == 2) {
                    fys.maakNotifaction("Error", "Fill in the Type");
                } else {
                    fys.maakNotifaction("Error", "Vul een type in!");
                }
            }
        } else if (lang == 2) {
            fys.maakNotifaction("Error", "Select an airport");
        } else {
            fys.maakNotifaction("Error", "Selecteer een luchthaven");
        }
    }

    /**
     * Doel: Deze methode maakt een bagageID aan voor de gevonden bagage.
     *
     * @throws SQLException
     */
    private void selectBagageId() throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;

        //query
        String sql = "SELECT bagage_id FROM bagage ORDER BY bagage_id DESC LIMIT 1";

        try {
            //roep database connection op
            dbConnection = FYS.getDBConnection();

            statement = dbConnection.createStatement();

            // Voert SQL statement uit.
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                //haal bagageid op 
                int BagageId = rs.getInt("bagage_id");
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
     * Doel: Deze methode slaat de ingevoerde bagage gegevens op in de database.
     *
     * @param vliegvledId
     * @param medewerkerId
     * @param bagageId
     * @param file
     * @throws SQLException
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void insertIntoDatabase(int vliegvledId, int medewerkerId, int bagageId, File file)
            throws SQLException, FileNotFoundException, IOException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;

        //maakt een fileinputstream aan
        FileInputStream fis = null;

        //controleer of file niet leeg is
        if (!(file.length() == 0)) {
            //geeft value van file aan fis
            fis = new FileInputStream(file);
        }

        //querys
        String sql = "INSERT INTO bagage (bagage_id, labelnummer, vluchtnummer, bestemming, type, merk, kleur, bijzondere_kenmerken, foto, vliegveld_id)"
                + "VALUES (?,?,?,?,?,?,?,?,?,?)";
        String sql1 = "INSERT INTO gevondenbagage (datum_gevonden, vliegveld_id, bagage_id)"
                + " VALUES (?, ?, ?)";
        String sql2 = "INSERT INTO logboek (medewerker_id, actie, datum) VALUES (?, ?, ?)";

        try {
            dbConnection = FYS.getDBConnection();

            preparedStatement = dbConnection.prepareStatement(sql);

            preparedStatement.setInt(1, bagageId);
            preparedStatement.setString(2, labelnummer);
            preparedStatement.setString(3, vluchtnummer);
            preparedStatement.setString(4, bestemming);
            preparedStatement.setString(5, typeKoffer);
            preparedStatement.setString(6, merkKoffer);
            preparedStatement.setString(7, kleurKoffer);
            preparedStatement.setString(8, bijzondereKenmerken);
            preparedStatement.setBlob(9, fis, file.length());
            preparedStatement.setInt(10, vliegvledId);

            // Voert SQL statement uit.
            preparedStatement.executeUpdate();

            preparedStatement1 = dbConnection.prepareStatement(sql1);

            preparedStatement1.setString(1, FYS.getDate());
            preparedStatement1.setInt(2, vliegvledId);
            preparedStatement1.setInt(3, bagageId);
            // Voert SQL statement 1 uit.
            preparedStatement1.executeUpdate();

            preparedStatement2 = dbConnection.prepareStatement(sql2);

            preparedStatement2.setInt(1, medewerkerId);
            preparedStatement2.setString(2, "Gevonden bagage geregisteerd met bagageID "
                    + bagageId);
            preparedStatement2.setString(3, FYS.getDate());

            // Voert SQL statement 2 uit.
            preparedStatement2.executeUpdate();

            fis.close();

        } catch (SQLException e) {
            // Vangt errors op.
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (preparedStatement1 != null) {
                preparedStatement1.close();
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
     * Doel: Deze methode vult de combobox of geeft de naam van de luchthaven.
     *
     * @param luchthavenNaam
     * @throws SQLException
     */
    private void selectFromVliegveld(String luchthavenNaam) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String sql;
        //controleer welke query uitgevoerd moet worden
        if ("vliegveld".equals(luchthavenNaam)) {
            sql = "SELECT * FROM vliegveld";
        } else {
            sql = "SELECT * FROM vliegveld WHERE naam_vliegveld = ?";
        }

        try {
            dbConnection = FYS.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(sql);
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
