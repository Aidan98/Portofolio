package fys;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

/**
 * FXML Controller Doel: Het kiezen welke accounts je wil beheren.
 *
 * @author is102 - 3, GORDEL
 */
public class ManagerAccountsBeherenController implements Initializable {

    // attributen
    FYS fys = new FYS();
    Language language = new Language();
    @FXML
    private TableView<MedewerkerRow> table;
    @FXML
    private TableColumn<MedewerkerRow, Integer> accountIDCol;
    @FXML
    private TableColumn<MedewerkerRow, String> emailCol;
    @FXML
    private TableColumn<MedewerkerRow, String> managerCol;
    @FXML
    private JFXButton verwijderSelecAccountBtn;
    @FXML
    private Label zoekOpLbl;
    @FXML
    private JFXRadioButton accountIDRadioBtn;
    @FXML
    private JFXRadioButton emailRadioBtn;
    @FXML
    private JFXButton zoekAccountBtn;
    @FXML
    private JFXButton laadTabelBtn;
    @FXML
    private JFXTextField zoekveldTxtField;
    @FXML
    private ToggleGroup zoekOp;
    @FXML
    private JFXRadioButton managerRadioBtn;
    @FXML
    private ToggleGroup typeAccountToggleGroup;
    @FXML
    private JFXRadioButton medewerkerRadioBtn;
    @FXML
    private JFXTextField emailTxtField;
    @FXML
    private JFXPasswordField wachtwoordTxtField;
    @FXML
    private JFXButton maakAccountBtn;

    private ObservableList<MedewerkerRow> data = FXCollections.observableArrayList();
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    int lang = fys.getLanguage();

    /**
     * Initialiseer de pagina, laadt de accounts uit de database en toon ze in
     * een tabel.
     *
     * @param url URL
     * @param rb Resources
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        if (lang == 2) {
            zoekOpLbl.setText(language.getManagerAccountVerwijderenLabelSearch());
            emailRadioBtn.setText(language.getManagerAccountVerwijderenLabelEmail());
            zoekveldTxtField.setPromptText(language.getManagerAccountVerwijderenPromptTextSearchBar());
            zoekAccountBtn.setText(language.getManagerAccountVerwijderenButtonSearchAccount());
            laadTabelBtn.setText(language.getManagerAccountVerwijderenButtonLoadTable());
            verwijderSelecAccountBtn.setText(language.getManagerAccountVerwijderenButtonDeleteSelecAcc());
            managerRadioBtn.setText(language.getManagerMedewerkerAccountManager());
            medewerkerRadioBtn.setText(language.getManagerMedewerkerAccountEmployee());
            wachtwoordTxtField.setPromptText(language.getManagerMedewerkerAccountPromptTextPassword());
            emailTxtField.setPromptText(language.getManagerMedewerkerAccountPromptTextEmail());
            maakAccountBtn.setText(language.getManagerMedewerkerAccountButtonMakeAccount());
        }
        //Data uit database halen
        try {
            selectAlles();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerAccountsBeherenController.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Maak de tabel aan en maak de kolommen.
        table.setEditable(true);
        accountIDCol.setCellValueFactory(new PropertyValueFactory<>("accountID"));

        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setOnEditCommit((TableColumn.CellEditEvent<MedewerkerRow, String> t) -> {
            ((MedewerkerRow) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())).setEmail(t.getNewValue().toLowerCase());

            //variable die het accountId haal uit het tabel
            int accountID = table.getSelectionModel().selectedItemProperty().
                    getValue().accountID.intValue();

            //string voor ingevoerde email in tabel
            String newEmail = t.getNewValue().toLowerCase();
            // vervangen van het emailadres

            if (fys.isEmailValid(newEmail)) {
                try {
                    if (!fys.isMedewerker(newEmail)) {

                        updateEmail(accountID, newEmail);
                        refreshTabel();

                    } else if (lang == 2) {
                        alert.setTitle("Corendon Lost & found");
                        alert.setHeaderText("Failed to update email.");
                        alert.setContentText("email is already in use");
                        alert.showAndWait();
                        refreshTabel();
                    } else {
                        alert.setTitle("Corendon Lost & found");
                        alert.setHeaderText("Updaten van email is niet gelukt!");
                        alert.setContentText("email bestaat al");
                        alert.showAndWait();
                        refreshTabel();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ManagerAccountsBeherenController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (lang == 2) {
                alert.setTitle("Corendon Lost & found");
                alert.setHeaderText("Failed to update email.");
                alert.setContentText("email does not exist");
                alert.showAndWait();
                try {
                    refreshTabel();
                } catch (SQLException ex) {
                    Logger.getLogger(ManagerAccountsBeherenController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {

                alert.setTitle("Corendon Lost & found");
                alert.setHeaderText("Updaten van email is niet gelukt!");
                alert.setContentText("email is niet geldig");
                alert.showAndWait();
                try {
                    refreshTabel();
                } catch (SQLException ex) {
                    Logger.getLogger(ManagerAccountsBeherenController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });

        // vul de kolom manager.
        managerCol.setCellValueFactory(new PropertyValueFactory<>("manager"));
        managerCol.setCellFactory(TextFieldTableCell.forTableColumn());
        managerCol.setOnEditCommit((TableColumn.CellEditEvent<MedewerkerRow, String> t) -> {
            ((MedewerkerRow) t.getTableView().getItems().get(
                    t.getTablePosition().getRow())).setManager(t.getNewValue().toLowerCase());

            //variable die het accountId haal uit het tabel
            int accountID = table.getSelectionModel().selectedItemProperty().
                    getValue().accountID.intValue();

            //invoer van manager of medewerker
            String medewerker = t.getNewValue().toLowerCase();

            if ("medewerker".equals(medewerker) || "employee".equals(medewerker) || "manager".equals(medewerker)) {

                int manager = 0;

                if ("manager".equals(medewerker)) {
                    manager = 1;
                }

                //update manager
                try {
                    updateManager(accountID, manager);
                    refreshTabel();
                } catch (SQLException ex) {
                    Logger.getLogger(ManagerAccountsBeherenController.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (lang == 2) {
                    alert.setTitle("Corendon Lost & found");
                    alert.setHeaderText("Updating was successful.");
                    alert.showAndWait();
                } else {
                    alert.setTitle("Corendon Lost & found");
                    alert.setHeaderText("Updaten is gelukt!");
                    alert.showAndWait();
                }

            } else {
                if (lang == 2) {
                    alert.setTitle("Corendon Lost & found");
                    alert.setHeaderText("There was an error updating to manager!");
                    alert.setContentText("Enter \"Employee\" or \"Manager\".");
                    alert.showAndWait();
                } else {
                    alert.setTitle("Corendon Lost & found");
                    alert.setHeaderText("Updaten van manager is niet gelukt!");
                    alert.setContentText("Vul \"Medewerker\" of \"Manager\" in.");
                    alert.showAndWait();
                }

                try {
                    refreshTabel();
                } catch (SQLException ex) {
                    Logger.getLogger(ManagerAccountsBeherenController.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });

        table.setItems(data);
        table.getColumns().addAll(accountIDCol, emailCol, managerCol);
        table.getSelectionModel().selectedIndexProperty().addListener(new geselecteerdeRij());

    }

    /**
     * Doel: Wanneer op de knop gedrukt wordt zoek de accounts op basis van de
     * gegeven parameters.
     *
     * @param event druk op de knop.
     * @throws IOException vang de fouten op.
     * @throws SQLException vang de fouten op.
     */
    @FXML
    private void handleZoekAccountBtnAction(ActionEvent event) throws IOException, SQLException {
        data = FXCollections.observableArrayList();

        accountIDRadioBtn.setUserData(1);
        emailRadioBtn.setUserData(2);

        int medewerkerID;
        int typeZoekActie = (int) zoekOp.getSelectedToggle().getUserData();
        String zoekRow = zoekveldTxtField.getText();

        if (!zoekRow.isEmpty()) {

            // zoek op de geselecteerde box, als geen box, zoek op alles
            switch (typeZoekActie) {
                case 1:
                    medewerkerID = Integer.parseInt(zoekRow);
                    selectFromMedewerker(medewerkerID);
                    table.setItems(data);
                    table.getColumns().addAll(accountIDCol, emailCol, managerCol);
                    break;
                case 2:
                    selectFromEmail(zoekRow);
                    table.setItems(data);
                    table.getColumns().addAll(accountIDCol, emailCol, managerCol);
                    break;
                default:
                    break;
            }
        } else {
            selectAlles();
        }

    }

    /**
     * Doel: Verwijder het geselecteerde account uit de database.
     *
     * @param event klik op de verwijderen knop
     * @throws SQLException vang de fouten op.
     */
    @FXML
    private void handleVerwijderenBtnAction(ActionEvent event) throws SQLException {
        // Get selected MedewerkerRow and delete
        int selectIndex = table.getSelectionModel().getSelectedIndex();
        int accountID = table.getSelectionModel().selectedItemProperty().
                getValue().accountID.intValue();
        data.remove(selectIndex);

        // selecteer de gebruiker
        if (table.getItems().isEmpty()) {
            return;
        }

        if (selectIndex != 0) {
            selectIndex = selectIndex - 1;
        }
        // verwijder het account.
        deleteFromMedewerkers(accountID);

        table.requestFocus();
        table.getSelectionModel().select(selectIndex);
        table.getFocusModel().focus(selectIndex);
        // ververs de tabel
        refreshTabel();
    }

    /**
     * Doel: refresh de tabel, haal de nieuwe data op uit de database.
     *
     * @param event klik op de herladen knop
     * @throws SQLException vang de fouten op
     */
    @FXML
    private void handleLaadTabelBtnAction(ActionEvent event) throws SQLException {
        refreshTabel();
    }

    /**
     * Doel: wanneer op de knop gedrukt wordt haal de data uit de waardes uit de
     * velden, en maak een account met die data.
     *
     * @param event klik op de knop
     * @throws SQLException vang fouten op.
     */
    @FXML
    private void handleMaakAccountBtnAction(ActionEvent event) throws SQLException, IOException {
        managerRadioBtn.setUserData(1);
        medewerkerRadioBtn.setUserData(0);
        int typeAcc = (int) typeAccountToggleGroup.getSelectedToggle().getUserData();
        String email = emailTxtField.getText().toLowerCase();
        String wachtwoord = String.valueOf(wachtwoordTxtField.getText().hashCode());

        // doe checks
        if (!email.isEmpty()) {
            if (fys.isEmailValid(email)) {
                if (!fys.isMedewerker(email)) {
                    if (!wachtwoordTxtField.getText().trim().equals("")) {
                        try {
                            if (lang == 2) {
                                // voeg de medewerker toe.
                                insertInto(email, wachtwoord, typeAcc);
                                alert.setTitle("Corendon Lost & found");
                                alert.setHeaderText("New account");
                                alert.setContentText("You have succesfully created an account");
                                alert.showAndWait();
                                emailTxtField.setText("");
                                wachtwoordTxtField.setText("");
                                fys.changeToAnotherFXML("Corendon Lost & Found - Manager Account Management",
                                        "view/manager/ManagerAccountsBeheren.fxml");

                            } else {
                                // voeg de medewerker toe.
                                insertInto(email, wachtwoord, typeAcc);
                                alert.setTitle("Corendon Lost & found");
                                alert.setHeaderText("Nieuwe account");
                                alert.setContentText("Account is succesvol aangemaakt");
                                alert.showAndWait();
                                emailTxtField.setText("");
                                wachtwoordTxtField.setText("");
                                fys.changeToAnotherFXML("Corendon Lost & Found - Manager Accounts Beheren",
                                        "view/manager/ManagerAccountsBeheren.fxml");
                            }
                        } catch (SQLException e) {

                            if (lang == 2) {
                                fys.maakNotifaction("Error", "Something has gone wrong, please try again");
                            } else {
                                fys.maakNotifaction("Error", "Er is een fout probeer opnieuw");
                            }
                        }
                    } else if (lang == 2) {
                        fys.nietIngevuldeVelden(wachtwoordTxtField);
                        fys.maakNotifaction("Error", "Please fill in your password");
                    } else {
                        fys.nietIngevuldeVelden(wachtwoordTxtField);
                        fys.maakNotifaction("Error", "Vul een gewenste wachtwoord in!");
                    }
                } else if (lang == 2) {
                    fys.nietIngevuldeVelden(emailTxtField);
                    fys.maakNotifaction("Error", "This email address is already in use");
                } else {
                    fys.nietIngevuldeVelden(emailTxtField);
                    fys.maakNotifaction("Error", "Emailadres bestaat al");
                }
            } else if (lang == 2) {
                fys.nietIngevuldeVelden(emailTxtField);
                fys.maakNotifaction("Error", "Email address is not valid");
            } else {
                fys.nietIngevuldeVelden(emailTxtField);
                fys.maakNotifaction("Error", "Emailadres niet geldig");
            }
        } else if (lang == 2) {
            fys.nietIngevuldeVelden(emailTxtField);
            fys.maakNotifaction("Error", "Please fill in an email address");
        } else {
            fys.nietIngevuldeVelden(emailTxtField);
            fys.maakNotifaction("Error", "Vul een emailadres in!");
        }
    }

    /**
     * Doel: Haal alles uit de database en voeg het toe aan de data rijen.
     *
     * @throws SQLException vang de fouten op.
     */
    private void selectAlles() throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String sql = "SELECT * FROM medewerker WHERE emailadres != \"verwijderd\" AND medewerker_id != ?";
        try {
            dbConnection = FYS.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setInt(1, fys.getGebruikerID());

            ResultSet rs = preparedStatement.executeQuery();

            table.getColumns().clear();
            while (rs.next()) {
                int medewerkerID = rs.getInt("medewerker_id");
                String email = rs.getString("emailadres");
                byte manager = rs.getByte("manager");
                data.add(new MedewerkerRow(medewerkerID, email, manager));
            }

        } catch (SQLException e) {
            // Vang de fouten op
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    /**
     * Doel: Selecteer al de medewerkers waar het emailadres niet verwijderd is
     * en de zoekterm klopt.
     *
     * @param zoekTerm de term waarop wordt gezocht voor het email adres
     * @throws SQLException vang de fouten op
     */
    private void selectFromEmail(String zoekTerm) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String sql = "SELECT * FROM medewerker WHERE emailadres LIKE ? AND (emailadres != \"verwijderd\" AND medewerker_id != ?)";

        try {
            dbConnection = FYS.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + zoekTerm + "%");
            preparedStatement.setInt(2, fys.getGebruikerID());

            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();

            table.getColumns().clear();
            while (rs.next()) {
                int medewerkerID = rs.getInt("medewerker_id");
                String email = rs.getString("emailadres");
                byte manager = rs.getByte("manager");
                data.add(new MedewerkerRow(medewerkerID, email, manager));
            }

        } catch (SQLException e) {
            // vang fouten op
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    /**
     * Doel: Selecteer al de medewerkers waar het emailadres niet verwijderd is
     * en de zoekterm op het ID klopt.
     *
     * @param zoekTerm de term waarop wordt gezocht voor het id adres
     * @throws SQLException vang de fouten op
     */
    private void selectFromMedewerker(int medewerkerID) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String sql = "SELECT * FROM medewerker WHERE (emailadres != \"verwijderd\" AND medewerker_id != ?) AND medewerker_id = ?";
        try {
            dbConnection = FYS.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setInt(1, fys.getGebruikerID());
            preparedStatement.setInt(2, medewerkerID);

            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();
            table.getColumns().clear();
            while (rs.next()) {
                int accountID = rs.getInt("medewerker_id");
                String email = rs.getString("emailadres");
                byte manager = rs.getByte("manager");
                data.add(new MedewerkerRow(accountID, email, manager));
            }
        } catch (SQLException e) {
            // vang fouten op
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    /**
     * Doel: update de medewerker email in de database
     *
     * @param medewerkerID het ID van de medewerker
     * @param email het nieuwe emailadres
     * @throws SQLException vang de fouten op
     */
    private static void updateEmail(int medewerkerID, String email) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE medewerker SET emailadres = ? "
                + "WHERE medewerker_id = ?";
        try {
            dbConnection = FYS.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(sql);

            preparedStatement.setString(1, email);
            preparedStatement.setInt(2, medewerkerID);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // vang fouten op
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    /**
     * Doel: update de manager email in de database
     *
     * @param medewerkerID het ID van de manager
     * @param email het nieuwe emailadres
     * @throws SQLException vang de fouten op
     */
    private static void updateManager(int medewerkerID, int manager) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE medewerker SET manager = ? "
                + "WHERE medewerker_id = ?";

        try {
            dbConnection = FYS.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(sql);

            preparedStatement.setInt(1, manager);
            preparedStatement.setInt(2, medewerkerID);

            // execute update SQL stetement
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // vang fouten op
        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    /**
     * Doel: verwijder de medewerker.
     *
     * @param accountID het ID van de medewerker
     * @throws SQLException vang de fouten op
     */
    private static void deleteFromMedewerkers(int accountID) throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE medewerker SET emailadres=?,wachtwoord=? WHERE medewerker_id= ?";

        try {
            dbConnection = FYS.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setString(1, "verwijderd");
            preparedStatement.setString(2, "verwijderd");
            preparedStatement.setInt(3, accountID);

            // execute delete SQL stetement
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // Vang fouten op
        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    /**
     * Doel: de functies van de geselecteerde rij maken.
     */
    private class geselecteerdeRij implements ChangeListener<Number> {

        /**
         * Doel: voeg de nieuwe data toe.
         *
         * @param ov de observable value
         * @param oldVal de oude waarde
         * @param newVal de nieuwe waarde
         */
        @Override
        public void changed(ObservableValue<? extends Number> ov,
                Number oldVal, Number newVal) {

            int ix = newVal.intValue();

            if ((ix < 0) || (ix >= data.size())) {

                return; // invalid data
            }
            MedewerkerRow row = data.get(ix);
        }
    }

    /**
     * Doel Ververs de tabel.
     *
     * @throws SQLException vang fouten op.
     */
    private void refreshTabel() throws SQLException {
        data = FXCollections.observableArrayList();
        table.getColumns().clear();
        selectAlles();
        table.setItems(data);
        table.getColumns().addAll(accountIDCol, emailCol, managerCol);
    }

    /**
     * Doel: De informatie van de medewerker definieëren.
     */
    public class MedewerkerRow {

        // attributen
        private final SimpleIntegerProperty accountID;
        private final SimpleStringProperty email;
        private final SimpleStringProperty manager;
        private String MANAGER;
        private String MEDEWERKER;

        /**
         * Doel: De medewerker aanmaken
         *
         * @param accountID de medewerker's ID
         * @param email de medewerker's email
         * @param manager de medewerker's manager waarde
         */
        private MedewerkerRow(int accountID, String email, int manager) {
            setTaal();
            this.accountID = new SimpleIntegerProperty(accountID);
            this.email = new SimpleStringProperty(email);
            if (manager == 1) {
                this.manager = new SimpleStringProperty(MANAGER);
            } else {
                this.manager = new SimpleStringProperty(MEDEWERKER);
            }
        }

        /**
         * Zet taal
         */
        public final void setTaal() {
            if (lang == 2) {
                MANAGER = "Manager";
                MEDEWERKER = "Employee";
            } else {
                MANAGER = "Manager";
                MEDEWERKER = "Medewerker";
            }
        }

        /**
         * Doel: het ophalen van het account id
         *
         * @return het account id
         */
        public Integer getAccountID() {
            return accountID.get();
        }

        /**
         * Doel: het aanpassen van het account ID
         *
         * @param accountID het nieuwe accountID
         */
        public void setAccountID(Integer accountID) {
            this.accountID.set(accountID);
        }

        /**
         * Doel: het ophalen van het emailadres van de medewerker
         *
         * @return het email adres
         */
        public String getEmail() {
            return email.get();
        }

        /**
         * Doel: het aanpassen van de email
         *
         * @param corendonEmail het nieuwe emailadres
         */
        public void setEmail(String corendonEmail) {
            email.set(corendonEmail);
        }

        /**
         * Doel: het ophalen van de manager status van de medewerker
         *
         * @return de manager status
         */
        public String getManager() {
            return manager.get();
        }

        /**
         * Doel: het aanpassen van de manager status
         *
         * @param manager de manager status
         */
        public void setManager(String manager) {
            this.manager.set(manager);
        }
    }

    /**
     * Doel: Het bewerken van de cellen van de tabel
     */
    class EditingCell extends TableCell<MedewerkerRow, String> {

        // attributen
        private TextField textField;

        /**
         * Doel: het initialiseren van de cell
         */
        public EditingCell() {
        }

        /**
         * Doel: bewerken van de cell beginnen.
         */
        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createTextField();
                setText(null);
                setGraphic(textField);
                textField.selectAll();
            }
        }

        /**
         * Doel: bewerken van de cell annuleren
         */
        @Override
        public void cancelEdit() {
            super.cancelEdit();

            setText((String) getItem());
            setGraphic(null);
        }

        /**
         * Doel: een item bewerken
         *
         * @param item het te bewerken item
         * @param empty de boolean of iets niet leeg is.
         */
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else if (isEditing()) {
                if (textField != null) {
                    textField.setText(getString());
                }
                setText(null);
                setGraphic(textField);
            } else {
                setText(getString());
                setGraphic(null);
            }
        }

        /**
         * Doel: een textveld maken om te kunnen bewerken
         */
        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
            textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> arg0,
                        Boolean arg1, Boolean arg2) {
                    if (!arg2) {
                        commitEdit(textField.getText());
                    }
                }
            });
        }

        /**
         * Doel: maak een String van de waarde in de cell.
         *
         * @return geef de string van de waarde in de cell terug
         */
        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }

    /**
     * Doel: Het toevoegen van de medewerker met de gegevens
     *
     * @param email het nieuwe emailadres
     * @param wachtwoord het gewenste wachtwoord
     * @param typeaccount het type van het nieuwe account
     * @throws SQLException vang fouten op
     */
    private static void insertInto(String email, String wachtwoord, int typeaccount) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String insertTableSQL = "INSERT INTO medewerker"
                + "(emailadres, wachtwoord, manager) VALUES"
                + "(?,?,?)";

        try {
            dbConnection = FYS.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(insertTableSQL);

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, wachtwoord);
            preparedStatement.setInt(3, typeaccount);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // vang fouten op
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
