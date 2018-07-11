package fys;

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller Doel: Het maken van al de functies van de logboek pagina.
 *
 * @author is102 - 3, GORDEL
 */
public class ManagerLogboekController implements Initializable {

    // attributen
    FYS fys = new FYS();
    Language language = new Language();
    @FXML
    private TableView<LogboekRow> table;
    @FXML
    private TableColumn logboekCol;
    @FXML
    private TableColumn medewerkerCol;
    @FXML
    private TableColumn datumCol;
    @FXML
    private TableColumn actieCol;
    private final ObservableList<LogboekRow> data = FXCollections.observableArrayList();
    int lang = fys.getLanguage();

    /**
     * Doel: Het initialiseren van de pagina. alles wordt uit de tabel van het
     * logboek gehaald en in de table gezet.
     *
     * @param url URL
     * @param rb Resources
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (lang == 2) {
            logboekCol.setText(language.getManagerLogboekColLogId());
            medewerkerCol.setText(language.getManagerLogboekColEmployeeId());
            datumCol.setText(language.getManagerLogboekColDate());
            actieCol.setText(language.getManagerLogboekColActivity());
        }
        try {
            selectFromLogboek();
        } catch (SQLException ex) {
            Logger.getLogger(ManagerLogboekController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // zet de opgehaalde waardes in de cellen.
        logboekCol.setCellValueFactory(new PropertyValueFactory<>("logboekID"));
        medewerkerCol.setCellValueFactory(new PropertyValueFactory<>("medewerkerID"));
        actieCol.setCellValueFactory(new PropertyValueFactory<>("actie"));
        datumCol.setCellValueFactory(new PropertyValueFactory<>("datum"));

        table.setItems(data);
        table.getColumns().addAll(logboekCol, medewerkerCol, datumCol, actieCol);
    }

    /**
     * Doel: het maken van de rijen van het logboek.
     */
    public class LogboekRow {

        // attributen
        private final SimpleIntegerProperty logboekID;
        private final SimpleIntegerProperty medewerkerID;
        private final SimpleStringProperty datum;
        private final SimpleStringProperty actie;

        /**
         * Doel: constructor, het initialiseren van de variabelen
         *
         * @param logboekID het logboekID
         * @param medewerkerID het id van de medewerker die het heeft gedaan
         * @param datum de datum waarop de actie is gedaan
         * @param actie de beschrijving van de actie.
         */
        public LogboekRow(Integer logboekID, Integer medewerkerID, String datum, String actie) {
            this.logboekID = new SimpleIntegerProperty(logboekID);
            this.medewerkerID = new SimpleIntegerProperty(medewerkerID);
            this.datum = new SimpleStringProperty(datum);
            this.actie = new SimpleStringProperty(actie);
        }

        /**
         * Doel: de actie opvragen
         *
         * @return de actie
         */
        public String getActie() {
            return actie.get();
        }

        /**
         * Doel: de actie aanpassen
         *
         * @param actieCorendon de nieuwe actie
         */
        public void setActie(String actieCorendon) {
            actie.set(actieCorendon);
        }

        /**
         * Doel: de datum ophalen
         *
         * @return de datum
         */
        public String getDatum() {
            return datum.get();
        }

        /**
         * De datum vervangen
         *
         * @param datum de nieuwe datum
         */
        public void setDatum(String datum) {
            this.datum.set(datum);
        }

        /**
         * Doel: het logboek ID ophalen
         *
         * @return het logboekID
         */
        public Integer getLogboekID() {
            return logboekID.get();
        }

        /**
         * Doel: Het logboek id vervangen
         *
         * @param logboekID het nieuwe logboek id
         */
        public void setLogboekID(Integer logboekID) {
            this.logboekID.set(logboekID);
        }

        /**
         * Doel: Het medewerkerID ophalen
         *
         * @return het medewerker ID
         */
        public Integer getMedewerkerID() {
            return medewerkerID.get();
        }

        /**
         * Doel: Het ID van de medewerker vervangen
         *
         * @param medewerkerID het nieuwe medewerkerID
         */
        public void setMedewerkerID(Integer medewerkerID) {
            this.medewerkerID.set(medewerkerID);
        }

    }

    /**
     * Doel: Al de data uit het logboek ophalen, gesorteerd op datum.
     *
     * @throws SQLException vang fouten op.
     */
    private void selectFromLogboek() throws SQLException {

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String selectSQL = "SELECT * FROM logboek ORDER BY logboek_id DESC;";

        try {
            dbConnection = FYS.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);

            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();
            table.getColumns().clear();

            while (rs.next()) {

                Integer logboek = rs.getInt("logboek_id");
                Integer medewerker = rs.getInt("medewerker_id");
                String datum = rs.getString("datum");
                String actie = rs.getString("actie");
                // voeg de data toe.
                data.add(new LogboekRow(logboek, medewerker, datum, actie)); // zet data in ObservableList
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
}
