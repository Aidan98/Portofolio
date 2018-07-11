package fys;

import com.jfoenix.controls.JFXButton;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javax.print.attribute.standard.SheetCollate;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 * FXML Controller Doel: De analyse pagina laten werken.
 *
 * @author is102 - 3, GORDEL
 */
public class ManagerAnalyseController implements Initializable {

    // Attributen
    FYS fys = new FYS();
    int lang = fys.getLanguage();

    Language language = new Language();
    @FXML
    private ComboBox<String> jaarComboBox;
    @FXML
    private ComboBox<String> maandComboBox;
    @FXML
    private Label jaarLbl;
    @FXML
    private Label maandLbl;
    @FXML
    private Label geregistreerdVerlorenLbl;
    @FXML
    private Label terugGevondLbl;
    @FXML
    private Label gemiddeldeTijdTerugvindLbl;
    @FXML
    private Label percentageTerugGevondenLbl;
    @FXML
    private Label ingediendeSchadeclaimsLbl;
    @FXML
    private Label uitkeringLbl;
    @FXML
    private Label bagageZonderClaimLbl;
    @FXML
    private Label vernietigdeLbl;
    @FXML
    private CategoryAxis x;
    @FXML
    private NumberAxis y;
    @FXML
    private LineChart grafiekSchadeVergoeding;
    @FXML
    private ComboBox<String> graphComboBox;
    @FXML
    private Label yearLbl;
    @FXML
    private Label monthLbl;
    @FXML
    private Label luggageRegisteredLostLbl;
    @FXML
    private Label foundLbl;
    @FXML
    private Label averageTimeFoundLbl;
    @FXML
    private Label percentageFoundLbl;
    @FXML
    private Label submittedClaimLbl;
    @FXML
    private Label cashedOutClaimLbl;
    @FXML
    private Label foundWithOutClaimLbl;
    @FXML
    private Label destroyedluggageLbl;
    @FXML
    private Button exporteerNaarPdfBtn;

    private String beginDatum = "";
    private String eindDatum = "";
    private String voorDatum = "";
    private String voorDatumEind = "";

    // variabelen voor de export
    private int vernietigdeBagageAantal;
    private int gevondenBagageZonderClaim;
    private int uitgekeerdeSchadeclaims;
    private int ingediendeSchadeclaims;
    private int bagageGeregistreerdAlsVerloren;
    private int terugGevondenBagage;
    private String percentageString;
    private String gemiddeldeTijdTerugvinden;

    // de lijsten voor comboboxen
    private final ObservableList<String> jarenList
            = FXCollections.observableArrayList("2017", "2016", "2015", "2014");

    private final ObservableList<String> maandenList
            = FXCollections.observableArrayList("", "januari", "februari", "maart",
                    "april", "mei", "juni", "juli", "augustus", "september",
                    "oktober", "november", "december");
    private final ObservableList<String> graphList
            = FXCollections.observableArrayList("Bagage Geregistreerd als verloren",
                    "Teruggevonden Bagage", "Ingediende schadeclaims",
                    "Uitkering aan schadeclaims", "Gevonden bagage zonder claim",
                    "Vernietigde bagage");
    @FXML
    private JFXButton exporteerNaarCsvBtn;

    /**
     * Initialiseer de pagina met de items uit de lijsten.
     *
     * @param url URL
     * @param rb Resources
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        jaarComboBox.setValue("");
        maandComboBox.setValue("");
        graphComboBox.setValue("");
        jaarComboBox.setItems(jarenList);
        grafiekSchadeVergoeding.setLegendVisible(false);
        if (lang == 2) {

            grafiekSchadeVergoeding.setTitle("Graph for the year: ");
            exporteerNaarPdfBtn.setText(language.getExportToPDFBtn());
            exporteerNaarCsvBtn.setText(language.getExportToCSFBtn());
            maandComboBox.setItems(language.getMonthList());
            graphComboBox.setItems(language.getGraphListEN());
            yearLbl.setText(language.getYearLbl());
            monthLbl.setText(language.getMonthLbl());
            destroyedluggageLbl.setText(language.getDestroyedLuggageLbl());
            cashedOutClaimLbl.setText(language.getAmountPaidLbl());
            foundWithOutClaimLbl.setText(language.getUnclaimedLuggageLbl());
            submittedClaimLbl.setText(language.getAmountOfClaimsLbl());
            percentageFoundLbl.setText(language.getPercentageOfFoundLuggageLbl());
            averageTimeFoundLbl.setText(language.getAvgTimeBeforeFoundLbl());
            foundLbl.setText(language.getAmountOfLuggageFoundLbl());
            luggageRegisteredLostLbl.setText(language.getLuggageRegisteredAsLostLbl());

        } else {
            grafiekSchadeVergoeding.setTitle("Grafiek van het jaar: ");
            maandComboBox.setItems(maandenList);
            graphComboBox.setItems(graphList);
        }
        graphComboBox.setVisible(false);
        exporteerNaarPdfBtn.setDisable(true);
        exporteerNaarCsvBtn.setDisable(true);

        // Initialiseer de grafiek zodat de x en y as goed komen te staan
        resetGraph();
    }

    /**
     * Doel: wanneer op de knop wordt gedruk exporteer de data naar een pdf.
     *
     * @param event druk op de knop
     * @throws IOException vang fouten op
     */
    @FXML
    private void handleExpPdfBtnAction(ActionEvent event) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        PDFont font = PDType1Font.HELVETICA_BOLD;
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        // vul de pdf met informatie
        contentStream.beginText();
        contentStream.setFont(font, 16);
        contentStream.newLineAtOffset(100, 700);
        if (lang == 2) {
            contentStream.showText("Analysis " + beginDatum);
        } else {
            contentStream.showText("Analyse " + beginDatum);
        }
        contentStream.endText();

        contentStream.beginText();
        contentStream.setFont(font, 12);
        contentStream.setLeading(14.5f);
        contentStream.newLineAtOffset(100, 675);
        contentStream.newLine();

        if (lang == 2) {
            contentStream.showText("Amount of luggage registered as lost: ");
            contentStream.showText(Integer.toString(bagageGeregistreerdAlsVerloren));
            contentStream.newLine();
            contentStream.showText("Amount of luggage found: ");
            contentStream.showText(Integer.toString(terugGevondenBagage));
            contentStream.newLine();
            contentStream.showText("Average time before luggage is found: ");
            contentStream.showText(gemiddeldeTijdTerugvinden);
            contentStream.newLine();
            contentStream.showText("Percentage of found luggage: ");
            contentStream.showText(percentageString);
            contentStream.newLine();
            contentStream.showText("Amount of submitted claims: ");
            contentStream.showText(Integer.toString(ingediendeSchadeclaims));
            contentStream.newLine();
            contentStream.showText("Amount of money paid out in euros: ");
            contentStream.showText(Integer.toString(uitgekeerdeSchadeclaims));
            contentStream.newLine();
            contentStream.showText("unclaimed luggage: ");
            contentStream.showText(Integer.toString(gevondenBagageZonderClaim));
            contentStream.newLine();
            contentStream.showText("Destroyed luggage: ");
            contentStream.showText(Integer.toString(vernietigdeBagageAantal));
            contentStream.endText();
            contentStream.close();
            document.save("Analysis_" + beginDatum + ".pdf");
            document.close();
        } else {
            contentStream.showText("Aantal bagage geregistreerd als verloren: ");
            contentStream.showText(Integer.toString(bagageGeregistreerdAlsVerloren));
            contentStream.newLine();
            contentStream.showText("Aantal teruggevonden bagage: ");
            contentStream.showText(Integer.toString(terugGevondenBagage));
            contentStream.newLine();
            contentStream.showText("Gemiddelde tijd terugvinden bagage: ");
            contentStream.showText(gemiddeldeTijdTerugvinden);
            contentStream.newLine();
            contentStream.showText("Percentage teruggevonden bagage: ");
            contentStream.showText(percentageString);
            contentStream.newLine();
            contentStream.showText("Aantal ingediende schadeclaims: ");
            contentStream.showText(Integer.toString(ingediendeSchadeclaims));
            contentStream.newLine();
            contentStream.showText("Bedrag wat is uitgekeerd aan schadeclaims in euro's: ");
            contentStream.showText(Integer.toString(uitgekeerdeSchadeclaims));
            contentStream.newLine();
            contentStream.showText("Gevonden bagage zonder claim: ");
            contentStream.showText(Integer.toString(gevondenBagageZonderClaim));
            contentStream.newLine();
            contentStream.showText("Vernietigde bagage: ");
            contentStream.showText(Integer.toString(vernietigdeBagageAantal));

            contentStream.endText();
            contentStream.close();
            document.save("src/fys/view/PDF/Analyse_" + beginDatum + ".pdf");
            document.close();
        }

        Desktop.getDesktop().open(new File("src/fys/view/PDF/Analyse_" + beginDatum + ".pdf"));
    }

    @FXML
    private void handleCsfBtnAction(ActionEvent event) throws IOException {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new File("src/fys/view/EXCEL/Jaaranalyse.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (lang == 2) {
            StringBuilder builder = new StringBuilder();

            String ColumnNamesList = "information, amount";

            builder.append(ColumnNamesList).append("\n");
            builder.append("Amount of luggage registered as lost ").append(bagageGeregistreerdAlsVerloren);
            builder.append("\n");
            builder.append("Amount of luggage found ").append(terugGevondenBagage);
            builder.append("\n");
            builder.append("Average time before luggage is found ").append(gemiddeldeTijdTerugvinden);
            builder.append("\n");
            builder.append("Percentage of found luggage ").append(percentageString);
            builder.append("\n");
            builder.append("Amount of submitted claims ").append(ingediendeSchadeclaims);
            builder.append("\n");
            builder.append("Amount of claims that have been paid out in euros ").append(uitgekeerdeSchadeclaims);
            builder.append("\n");
            builder.append("unclaimed luggage ").append(gevondenBagageZonderClaim);
            builder.append("\n");
            builder.append("Destroyed luggage ").append(vernietigdeBagageAantal);
            pw.write(builder.toString());
            pw.close();
        } else {
            StringBuilder builder = new StringBuilder();
            String ColumnNamesList = "information, amount";

            builder.append(ColumnNamesList).append("\n");
            builder.append("Aantal bagage geregistreerd als verloren ").append(bagageGeregistreerdAlsVerloren);
            
            builder.append("Aantal teruggevonden bagage ").append(terugGevondenBagage);
            builder.append("\n");
            builder.append("Gemiddelde tijd terugvinden bagage ").append(gemiddeldeTijdTerugvinden);
            builder.append("\n");
            builder.append("Percentage teruggevonden bagage ").append(percentageString);
            builder.append("\n");
            builder.append("Aantal ingediende schadeclaims ").append(ingediendeSchadeclaims);
            builder.append("\n");
            builder.append("Bedrag wat is uitgekeerd aan schadeclaims in euro's ").append(uitgekeerdeSchadeclaims);
            builder.append("\n");
            builder.append("Gevonden bagage zonder claim ").append(gevondenBagageZonderClaim);
            builder.append("\n");
            builder.append("Vernietigde bagage ").append(vernietigdeBagageAantal);
            pw.write(builder.toString());
            pw.close();
        }

        Desktop.getDesktop().open(new File("src/fys/view/EXCEL/Jaaranalyse.csv"));
    }

    /**
     * Doel: Selecteer de datum van de combobox, en de datum die ná de
     * geselecteerde datum komt. Dus wanneer 2016-06 de datum is, selecteer je
     * ook 2016-07.
     */
    public void selecteerDeDatum() {
        beginDatum = "";
        voorDatum = "";
        eindDatum = "";
        voorDatumEind = "";
        String analyseJaartal = jaarComboBox.getValue();
        jaarLbl.setText(analyseJaartal);
        String analyseMaand = analyseMaandToNumbers();

        if (analyseMaand.isEmpty()) {
            beginDatum += analyseJaartal;
            eindDatum += Integer.parseInt(analyseJaartal) + 1;
            voorDatum += Integer.parseInt(analyseJaartal) - 1;
            voorDatumEind += analyseJaartal;
            maandLbl.setText("-");
        } else {
            beginDatum += analyseJaartal + "-" + analyseMaand;
            switch (analyseMaand) {
                case "12":
                    eindDatum += Integer.parseInt(analyseJaartal) + 1 + "-01";
                    voorDatum += Integer.parseInt(analyseJaartal) - 1 + "-12";
                    voorDatumEind += Integer.parseInt(analyseJaartal) + "-01";
                    break;
                case "09":
                case "10":
                case "11":
                    eindDatum += analyseJaartal + "-" + (Integer.parseInt(analyseMaand) + 1);
                    voorDatum += (Integer.parseInt(analyseJaartal) - 1) + "-" + analyseMaand;
                    voorDatumEind += (Integer.parseInt(analyseJaartal) - 1) + "-"
                            + (Integer.parseInt(analyseMaand) + 1);
                    break;
                default:
                    eindDatum += analyseJaartal + "-0" + (Integer.parseInt(analyseMaand) + 1);
                    voorDatum += (Integer.parseInt(analyseJaartal) - 1) + "-" + analyseMaand;
                    voorDatumEind += (Integer.parseInt(analyseJaartal) - 1) + "-0"
                            + (Integer.parseInt(analyseMaand) + 1);
                    break;
            }
        }
    }

    /**
     * Doel: Zet de waarde uit de combobox om in een bruikbare String.
     *
     * @return de maand bruikbaar voor een sql query
     */
    public String analyseMaandToNumbers() {
        int maandInt;
        String maandString = maandComboBox.getValue();
        if (lang == 2) {
            maandInt = language.getMonthList().indexOf(maandString);
        } else {
            maandInt = maandenList.indexOf(maandString);

        }
        if (maandInt > 9) {
            maandLbl.setText(maandString);
            return "" + maandInt;
        } else if (maandInt == 0) {
            return "";
        } else {
            maandLbl.setText(maandString);
            return "0" + maandInt;
        }
    }

    /**
     * Doel: Het aantal verloren bagage in de periode tellen.
     *
     * @param tableOrGraph
     * @return verloren bagage
     * @throws SQLException vang fouten op
     */
    public int bagageGeregistreerdAlsVerloren(int tableOrGraph) throws SQLException {
        bagageGeregistreerdAlsVerloren = 0;

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String sql = "SELECT * FROM verlorenbagage WHERE datum_verloren >= ? AND datum_verloren < ?;";
        try {
            dbConnection = FYS.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setString(1, beginDatum);
            preparedStatement.setString(2, eindDatum);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                bagageGeregistreerdAlsVerloren++;
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
        if (tableOrGraph == 1) {
            geregistreerdVerlorenLbl.setText("" + bagageGeregistreerdAlsVerloren);
        }
        return bagageGeregistreerdAlsVerloren;
    }

    /**
     * Doel: bereken het aantal teruggevonden bagage
     *
     * @param tableOrGraph
     * @return het aantal teruggevonden bagage
     * @throws SQLException vang fouten op.
     */
    public int terugGevondenBagage(int tableOrGraph) throws SQLException {
        terugGevondenBagage = 0;

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String sql = "SELECT * FROM verlorenbagage INNER JOIN gematchedebagage "
                + "ON verlorenbagage.verlorenbagage_id=gematchedebagage.verlorenbagage_id "
                + "WHERE datum_verloren >= ? AND datum_verloren < ?;";
        try {
            dbConnection = FYS.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setString(1, beginDatum);
            preparedStatement.setString(2, eindDatum);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                terugGevondenBagage++;
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

        if (tableOrGraph == 1) {
            terugGevondLbl.setText("" + terugGevondenBagage);
        }
        return terugGevondenBagage;
    }

    /**
     * Doel: bereken het gemiddelde aantal dagen tussen verlies en vinden.
     *
     * @param tableOrGraph
     * @return het aantal dagen
     * @throws SQLException vang fouten op.
     */
    public String gemiddeldeTijdTerugvinden(int tableOrGraph) throws SQLException {
        int totaalGevonden = 0;
        int datumVerschilTotaal = 0;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String sql = "SELECT datediff(gevondenbagage.datum_gevonden, verlorenbagage.datum_verloren) AS datumverschil FROM verlorenbagage INNER JOIN gematchedebagage "
                + "ON verlorenbagage.verlorenbagage_id=gematchedebagage.verlorenbagage_id "
                + "INNER JOIN gevondenbagage "
                + "ON gevondenbagage.gevondenbagage_id=gematchedebagage.gevondenbagage_id "
                + "WHERE datum_verloren >= ? AND datum_verloren < ?;";
        try {
            dbConnection = FYS.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setString(1, beginDatum);
            preparedStatement.setString(2, eindDatum);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String datumVerschilString = rs.getString("datumverschil");
                datumVerschilTotaal += Integer.parseInt(datumVerschilString);
                totaalGevonden++;
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
        int gemiddelde;
        if (totaalGevonden > 0) {
            gemiddelde = datumVerschilTotaal / totaalGevonden;
        } else {
            gemiddelde = 0;
        }
        if (lang == 2) {
            if (tableOrGraph == 1) {
                gemiddeldeTijdTerugvindLbl.setText(gemiddelde + " days.");

                return gemiddelde + " days.";
            }
        } else if (tableOrGraph == 1) {
            gemiddeldeTijdTerugvindLbl.setText(gemiddelde + " dagen.");
        }
        return gemiddelde + " dagen.";
    }

    /**
     * Doel: bereken het percentage teruggevonden bagage.
     *
     * @param tableOrGraph
     * @return het percentage
     */
    public String percentageTeruggevondenBagage(int tableOrGraph) {
        double gevondenDouble = terugGevondenBagage;
        double verlorenDouble = bagageGeregistreerdAlsVerloren;
        double calc = (100 / verlorenDouble) * gevondenDouble;
        percentageString = String.format("%.2f", calc) + "%";
        if (percentageString.equals("NaN%")) {
            if (tableOrGraph == 1) {
                percentageTerugGevondenLbl.setText("0%");
            }
            return "0%";
        } else {
            if (tableOrGraph == 1) {
                percentageTerugGevondenLbl.setText(percentageString);
            }
            return percentageString;
        }
    }

    /**
     * Doel: Bereken het aantal ingediende schadeclaims.
     *
     * @param tableOrGraph
     * @return ingediende schadeclaims
     * @throws SQLException
     */
    public int ingediendeSchadeclaims(int tableOrGraph) throws SQLException {
        ingediendeSchadeclaims = 0;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String sql = "SELECT * FROM schadeclaim WHERE datum >= ? AND datum < ?;";
        try {
            dbConnection = FYS.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setString(1, beginDatum);
            preparedStatement.setString(2, eindDatum);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                ingediendeSchadeclaims++;
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

        if (tableOrGraph == 1) {
            ingediendeSchadeclaimsLbl.setText("" + ingediendeSchadeclaims);
        }
        return ingediendeSchadeclaims;
    }

    /**
     * Doel: Bereken het bedrag uitgekeerd aan schadeclaims.
     *
     * @param tableOrGraph
     * @return het bedrag uitgekeerd voor schadeclaims.
     * @throws SQLException vang fouten op
     */
    public int uitgekeerdeSchadeclaims(int tableOrGraph) throws SQLException {
        uitgekeerdeSchadeclaims = 0;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String sql = "SELECT bedrag FROM schadeclaim WHERE datum >= ? AND datum < ? AND status = 2;";
        try {
            dbConnection = FYS.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setString(1, beginDatum);
            preparedStatement.setString(2, eindDatum);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                uitgekeerdeSchadeclaims += rs.getInt("bedrag");
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

        if (tableOrGraph == 1) {
            uitkeringLbl.setText("" + uitgekeerdeSchadeclaims);
        }

        return uitgekeerdeSchadeclaims;
    }

    /**
     * Doel: Bereken het aantal gevonden bagage wat niet is gematched aan een
     * klant.
     *
     * @param tableOrGraph
     * @return bagage zonder claim
     * @throws SQLException vang fouten op
     */
    public int gevondenBagageZonderClaim(int tableOrGraph) throws SQLException {
        int totaalGevondenBagage = 0;
        int gevondenBagageMetClaim = 0;
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String sql = "SELECT * FROM gevondenbagage "
                + "INNER JOIN gematchedebagage "
                + "ON gematchedebagage.gevondenbagage_id=gevondenbagage.gevondenbagage_id "
                + "WHERE datum_gevonden >= ? AND datum_gevonden < ?;";

        try {
            dbConnection = FYS.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setString(1, beginDatum);
            preparedStatement.setString(2, eindDatum);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                gevondenBagageMetClaim++;
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

        String sql2 = "SELECT * FROM gevondenbagage WHERE datum_gevonden >= ? "
                + "AND datum_gevonden < ?;";
        try {
            dbConnection = FYS.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(sql2);
            preparedStatement.setString(1, beginDatum);
            preparedStatement.setString(2, eindDatum);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                totaalGevondenBagage++;
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

        gevondenBagageZonderClaim = totaalGevondenBagage - gevondenBagageMetClaim;

        if (tableOrGraph == 1) {
            bagageZonderClaimLbl.setText("" + gevondenBagageZonderClaim);
        }
        return gevondenBagageZonderClaim;
    }

    /**
     * Doel: Bereken het aantal vernietigde bagage.
     *
     * @param tableOrGraph
     * @return het aantal vernietigde bagage.
     * @throws SQLException vang fouten op
     */
    public int vernietigdeBagage(int tableOrGraph) throws SQLException {
        vernietigdeBagageAantal = 0;

        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String sql = "SELECT * FROM gevondenbagage "
                + "WHERE datum_gevonden >= ? AND datum_gevonden < ?;";
        try {
            dbConnection = FYS.getDBConnection();
            preparedStatement = dbConnection.prepareStatement(sql);
            preparedStatement.setString(1, voorDatum);
            preparedStatement.setString(2, voorDatumEind);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                vernietigdeBagageAantal++;
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

        if (tableOrGraph == 1) {
            vernietigdeLbl.setText("" + vernietigdeBagageAantal);
        }
        return vernietigdeBagageAantal;
    }

    /**
     * Doel: Vul de tabel met gegevens uit de combobox
     *
     * @param series
     * @param welkeWaarde
     * @return
     * @throws SQLException
     */
    public XYChart.Series fillChart(XYChart.Series series, String welkeWaarde) throws SQLException {
        if (lang == 2) {
            switch (welkeWaarde) {
                case "Luggage registered as lost":
                    y.setLabel("Amount of luggage");
                    x.setLabel("Month");
                    series = fillChartBGAV(series);
                    break;
                case "Returned luggage":
                    y.setLabel("Amount of luggage");
                    x.setLabel("Month");
                    series = fillChartTB(series);
                    break;
                case "Submitted claims":
                    y.setLabel("Amount of submitted claims");
                    x.setLabel("Month");
                    series = fillChartIS(series);
                    break;
                case "Amount of money paid for claims":
                    y.setLabel("Amount in euros");
                    x.setLabel("Month");
                    series = fillChartUAS(series);
                    break;
                case "Found luggage without claim":
                    y.setLabel("Amount of luggage");
                    x.setLabel("Month");
                    series = fillChartGBZC(series);
                    break;
                case "Destroyed luggage":
                    y.setLabel("Amount of luggage");
                    x.setLabel("Month");
                    series = fillChartVB(series);
                    break;
                default:
                    break;

            }
        } else {
            switch (welkeWaarde) {
                case "Bagage Geregistreerd als verloren":
                    y.setLabel("Aantal Bagage");
                    series = fillChartBGAV(series);
                    break;
                case "Teruggevonden Bagage":
                    y.setLabel("Aantal Bagage");
                    series = fillChartTB(series);
                    break;
                case "Ingediende schadeclaims":
                    y.setLabel("Aantal ingediende schadeclaims");
                    series = fillChartIS(series);
                    break;
                case "Uitkering aan schadeclaims":
                    y.setLabel("Bedrag in euros");
                    series = fillChartUAS(series);
                    break;
                case "Gevonden bagage zonder claim":
                    y.setLabel("Aantal Bagage");
                    series = fillChartGBZC(series);
                    break;
                case "Vernietigde bagage":
                    y.setLabel("Aantal Bagage");
                    series = fillChartVB(series);
                    break;
                default:
                    break;
            }
        }
        return series;
    }

    /**
     * Doel: Vul de grafiek met de data
     *
     * @param series de te vullen serie
     * @return de gevulde serie
     * @throws SQLException
     */
    public XYChart.Series fillChartBGAV(XYChart.Series series) throws SQLException {
        int count;
        if (lang == 2) {
            setJan();
            count = bagageGeregistreerdAlsVerloren(2);
            series.getData().add(new XYChart.Data("Jan", count));
            setFeb();
            count += bagageGeregistreerdAlsVerloren(2);
            series.getData().add(new XYChart.Data("Feb", count));
            setMar();
            count += bagageGeregistreerdAlsVerloren(2);
            series.getData().add(new XYChart.Data("Mar", count));
            setApr();
            count += bagageGeregistreerdAlsVerloren(2);
            series.getData().add(new XYChart.Data("Apr", count));
            setMay();
            count += bagageGeregistreerdAlsVerloren(2);
            series.getData().add(new XYChart.Data("May", count));
            setJun();
            count += bagageGeregistreerdAlsVerloren(2);
            series.getData().add(new XYChart.Data("Jun", count));
            setJul();
            count += bagageGeregistreerdAlsVerloren(2);
            series.getData().add(new XYChart.Data("Jul", count));
            setAug();
            count += bagageGeregistreerdAlsVerloren(2);
            series.getData().add(new XYChart.Data("Aug", count));
            setSep();
            count += bagageGeregistreerdAlsVerloren(2);
            series.getData().add(new XYChart.Data("Sep", count));
            setOct();
            count += bagageGeregistreerdAlsVerloren(2);
            series.getData().add(new XYChart.Data("Oct", count));
            setNov();
            count += bagageGeregistreerdAlsVerloren(2);
            series.getData().add(new XYChart.Data("Nov", count));
            setDec();
            count += bagageGeregistreerdAlsVerloren(2);
            series.getData().add(new XYChart.Data("Dec", count));
        } else {
            setJan();
            count = bagageGeregistreerdAlsVerloren(2);
            series.getData().add(new XYChart.Data("Jan", count));
            setFeb();
            count += bagageGeregistreerdAlsVerloren(2);
            series.getData().add(new XYChart.Data("Feb", count));
            setMar();
            count += bagageGeregistreerdAlsVerloren(2);
            series.getData().add(new XYChart.Data("Mar", count));
            setApr();
            count += bagageGeregistreerdAlsVerloren(2);
            series.getData().add(new XYChart.Data("Apr", count));
            setMay();
            count += bagageGeregistreerdAlsVerloren(2);
            series.getData().add(new XYChart.Data("Mei", count));
            setJun();
            count += bagageGeregistreerdAlsVerloren(2);
            series.getData().add(new XYChart.Data("Jun", count));
            setJul();
            count += bagageGeregistreerdAlsVerloren(2);
            series.getData().add(new XYChart.Data("Jul", count));
            setAug();
            count += bagageGeregistreerdAlsVerloren(2);
            series.getData().add(new XYChart.Data("Aug", count));
            setSep();
            count += bagageGeregistreerdAlsVerloren(2);
            series.getData().add(new XYChart.Data("Sep", count));
            setOct();
            count += bagageGeregistreerdAlsVerloren(2);
            series.getData().add(new XYChart.Data("Okt", count));
            setNov();
            count += bagageGeregistreerdAlsVerloren(2);
            series.getData().add(new XYChart.Data("Nov", count));
            setDec();
            count += bagageGeregistreerdAlsVerloren(2);
            series.getData().add(new XYChart.Data("Dec", count));
        }
        return series;
    }

    /**
     * Doel: Vul de grafiek met de data
     *
     * @param series de te vullen serie
     * @return de gevulde serie
     * @throws SQLException
     */
    public XYChart.Series fillChartTB(XYChart.Series series) throws SQLException {
        int count;
        if (lang == 2) {
            setJan();
            count = terugGevondenBagage(2);
            series.getData().add(new XYChart.Data("Jan", count));
            setFeb();
            count += terugGevondenBagage(2);
            series.getData().add(new XYChart.Data("Feb", count));
            setMar();
            count += terugGevondenBagage(2);
            series.getData().add(new XYChart.Data("Mar", count));
            setApr();
            count += terugGevondenBagage(2);
            series.getData().add(new XYChart.Data("Apr", count));
            setMay();
            count += terugGevondenBagage(2);
            series.getData().add(new XYChart.Data("May", count));
            setJun();
            count += terugGevondenBagage(2);
            series.getData().add(new XYChart.Data("Jun", count));
            setJul();
            count += terugGevondenBagage(2);
            series.getData().add(new XYChart.Data("Jul", count));
            setAug();
            count += terugGevondenBagage(2);
            series.getData().add(new XYChart.Data("Aug", count));
            setSep();
            count += terugGevondenBagage(2);
            series.getData().add(new XYChart.Data("Sep", count));
            setOct();
            count += terugGevondenBagage(2);
            series.getData().add(new XYChart.Data("Oct", count));
            setNov();
            count += terugGevondenBagage(2);
            series.getData().add(new XYChart.Data("Nov", count));
            setDec();
            count += terugGevondenBagage(2);
            series.getData().add(new XYChart.Data("Dec", count));
        } else {
            setJan();
            count = terugGevondenBagage(2);
            series.getData().add(new XYChart.Data("Jan", count));
            setFeb();
            count += terugGevondenBagage(2);
            series.getData().add(new XYChart.Data("Feb", count));
            setMar();
            count += terugGevondenBagage(2);
            series.getData().add(new XYChart.Data("Mar", count));
            setApr();
            count += terugGevondenBagage(2);
            series.getData().add(new XYChart.Data("Apr", count));
            setMay();
            count += terugGevondenBagage(2);
            series.getData().add(new XYChart.Data("Mei", count));
            setJun();
            count += terugGevondenBagage(2);
            series.getData().add(new XYChart.Data("Jun", count));
            setJul();
            count += terugGevondenBagage(2);
            series.getData().add(new XYChart.Data("Jul", count));
            setAug();
            count += terugGevondenBagage(2);
            series.getData().add(new XYChart.Data("Aug", count));
            setSep();
            count += terugGevondenBagage(2);
            series.getData().add(new XYChart.Data("Sep", count));
            setOct();
            count += terugGevondenBagage(2);
            series.getData().add(new XYChart.Data("Okt", count));
            setNov();
            count += terugGevondenBagage(2);
            series.getData().add(new XYChart.Data("Nov", count));
            setDec();
            count += terugGevondenBagage(2);
            series.getData().add(new XYChart.Data("Dec", count));
        }

        return series;
    }

    /**
     * Doel: Vul de grafiek met de data
     *
     * @param series de te vullen serie
     * @return de gevulde serie
     * @throws SQLException
     */
    public XYChart.Series fillChartIS(XYChart.Series series) throws SQLException {
        int count;
        if (lang == 2) {
            setJan();
            count = ingediendeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Jan", count));
            setFeb();
            count += ingediendeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Feb", count));
            setMar();
            count += ingediendeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Mar", count));
            setApr();
            count += ingediendeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Apr", count));
            setMay();
            count += ingediendeSchadeclaims(2);
            series.getData().add(new XYChart.Data("May", count));
            setJun();
            count += ingediendeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Jun", count));
            setJul();
            count += ingediendeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Jul", count));
            setAug();
            count += ingediendeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Aug", count));
            setSep();
            count += ingediendeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Sep", count));
            setOct();
            count += ingediendeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Oct", count));
            setNov();
            count += ingediendeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Nov", count));
            setDec();
            count += ingediendeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Dec", count));
        } else {
            setJan();
            count = ingediendeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Jan", count));
            setFeb();
            count += ingediendeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Feb", count));
            setMar();
            count += ingediendeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Mar", count));
            setApr();
            count += ingediendeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Apr", count));
            setMay();
            count += ingediendeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Mei", count));
            setJun();
            count += ingediendeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Jun", count));
            setJul();
            count += ingediendeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Jul", count));
            setAug();
            count += ingediendeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Aug", count));
            setSep();
            count += ingediendeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Sep", count));
            setOct();
            count += ingediendeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Okt", count));
            setNov();
            count += ingediendeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Nov", count));
            setDec();
            count += ingediendeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Dec", count));
        }

        return series;
    }

    /**
     * Doel: Vul de grafiek met de data
     *
     * @param series de te vullen serie
     * @return de gevulde serie
     * @throws SQLException
     */
    public XYChart.Series fillChartUAS(XYChart.Series series) throws SQLException {
        int count;
        if (lang == 2) {
            setJan();
            count = uitgekeerdeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Jan", count));
            setFeb();
            count += uitgekeerdeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Feb", count));
            setMar();
            count += uitgekeerdeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Mar", count));
            setApr();
            count += uitgekeerdeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Apr", count));
            setMay();
            count += uitgekeerdeSchadeclaims(2);
            series.getData().add(new XYChart.Data("May", count));
            setJun();
            count += uitgekeerdeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Jun", count));
            setJul();
            count += uitgekeerdeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Jul", count));
            setAug();
            count += uitgekeerdeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Aug", count));
            setSep();
            count += uitgekeerdeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Sep", count));
            setOct();
            count += uitgekeerdeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Oct", count));
            setNov();
            count += uitgekeerdeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Nov", count));
            setDec();
            count += uitgekeerdeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Dec", count));
        } else {
            setJan();
            count = uitgekeerdeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Jan", count));
            setFeb();
            count += uitgekeerdeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Feb", count));
            setMar();
            count += uitgekeerdeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Mar", count));
            setApr();
            count += uitgekeerdeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Apr", count));
            setMay();
            count += uitgekeerdeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Mei", count));
            setJun();
            count += uitgekeerdeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Jun", count));
            setJul();
            count += uitgekeerdeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Jul", count));
            setAug();
            count += uitgekeerdeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Aug", count));
            setSep();
            count += uitgekeerdeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Sep", count));
            setOct();
            count += uitgekeerdeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Okt", count));
            setNov();
            count += uitgekeerdeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Nov", count));
            setDec();
            count += uitgekeerdeSchadeclaims(2);
            series.getData().add(new XYChart.Data("Dec", count));
        }

        return series;
    }

    /**
     * Doel: Vul de grafiek met de data
     *
     * @param series de te vullen serie
     * @return de gevulde serie
     * @throws SQLException
     */
    public XYChart.Series fillChartGBZC(XYChart.Series series) throws SQLException {
        int count;
        if (lang == 2) {
            setJan();
            count = gevondenBagageZonderClaim(2);
            series.getData().add(new XYChart.Data("Jan", count));
            setFeb();
            count += gevondenBagageZonderClaim(2);
            series.getData().add(new XYChart.Data("Feb", count));
            setMar();
            count += gevondenBagageZonderClaim(2);
            series.getData().add(new XYChart.Data("Mar", count));
            setApr();
            count += gevondenBagageZonderClaim(2);
            series.getData().add(new XYChart.Data("Apr", count));
            setMay();
            count += gevondenBagageZonderClaim(2);
            series.getData().add(new XYChart.Data("May", count));
            setJun();
            count += gevondenBagageZonderClaim(2);
            series.getData().add(new XYChart.Data("Jun", count));
            setJul();
            count += gevondenBagageZonderClaim(2);
            series.getData().add(new XYChart.Data("Jul", count));
            setAug();
            count += gevondenBagageZonderClaim(2);
            series.getData().add(new XYChart.Data("Aug", count));
            setSep();
            count += gevondenBagageZonderClaim(2);
            series.getData().add(new XYChart.Data("Sep", count));
            setOct();
            count += gevondenBagageZonderClaim(2);
            series.getData().add(new XYChart.Data("Oct", count));
            setNov();
            count += gevondenBagageZonderClaim(2);
            series.getData().add(new XYChart.Data("Nov", count));
            setDec();
            count += gevondenBagageZonderClaim(2);
            series.getData().add(new XYChart.Data("Dec", count));
        } else {
            setJan();
            count = gevondenBagageZonderClaim(2);
            series.getData().add(new XYChart.Data("Jan", count));
            setFeb();
            count += gevondenBagageZonderClaim(2);
            series.getData().add(new XYChart.Data("Feb", count));
            setMar();
            count += gevondenBagageZonderClaim(2);
            series.getData().add(new XYChart.Data("Mar", count));
            setApr();
            count += gevondenBagageZonderClaim(2);
            series.getData().add(new XYChart.Data("Apr", count));
            setMay();
            count += gevondenBagageZonderClaim(2);
            series.getData().add(new XYChart.Data("Mei", count));
            setJun();
            count += gevondenBagageZonderClaim(2);
            series.getData().add(new XYChart.Data("Jun", count));
            setJul();
            count += gevondenBagageZonderClaim(2);
            series.getData().add(new XYChart.Data("Jul", count));
            setAug();
            count += gevondenBagageZonderClaim(2);
            series.getData().add(new XYChart.Data("Aug", count));
            setSep();
            count += gevondenBagageZonderClaim(2);
            series.getData().add(new XYChart.Data("Sep", count));
            setOct();
            count += gevondenBagageZonderClaim(2);
            series.getData().add(new XYChart.Data("Okt", count));
            setNov();
            count += gevondenBagageZonderClaim(2);
            series.getData().add(new XYChart.Data("Nov", count));
            setDec();
            count += gevondenBagageZonderClaim(2);
            series.getData().add(new XYChart.Data("Dec", count));
        }

        return series;
    }

    /**
     * Doel: Vul de grafiek met de data
     *
     * @param series de te vullen serie
     * @return de gevulde serie
     * @throws SQLException
     */
    public XYChart.Series fillChartVB(XYChart.Series series) throws SQLException {
        int count;
        if (lang == 2) {
            setJan();
            count = vernietigdeBagage(2);
            series.getData().add(new XYChart.Data("Jan", count));
            setFeb();
            count += vernietigdeBagage(2);
            series.getData().add(new XYChart.Data("Feb", count));
            setMar();
            count += vernietigdeBagage(2);
            series.getData().add(new XYChart.Data("Mar", count));
            setApr();
            count += vernietigdeBagage(2);
            series.getData().add(new XYChart.Data("Apr", count));
            setMay();
            count += vernietigdeBagage(2);
            series.getData().add(new XYChart.Data("May", count));
            setJun();
            count += vernietigdeBagage(2);
            series.getData().add(new XYChart.Data("Jun", count));
            setJul();
            count += vernietigdeBagage(2);
            series.getData().add(new XYChart.Data("Jul", count));
            setAug();
            count += vernietigdeBagage(2);
            series.getData().add(new XYChart.Data("Aug", count));
            setSep();
            count += vernietigdeBagage(2);
            series.getData().add(new XYChart.Data("Sep", count));
            setOct();
            count += vernietigdeBagage(2);
            series.getData().add(new XYChart.Data("Oct", count));
            setNov();
            count += vernietigdeBagage(2);
            series.getData().add(new XYChart.Data("Nov", count));
            setDec();
            count += vernietigdeBagage(2);
            series.getData().add(new XYChart.Data("Dec", count));
        } else {
            setJan();
            count = vernietigdeBagage(2);
            series.getData().add(new XYChart.Data("Jan", count));
            setFeb();
            count += vernietigdeBagage(2);
            series.getData().add(new XYChart.Data("Feb", count));
            setMar();
            count += vernietigdeBagage(2);
            series.getData().add(new XYChart.Data("Mar", count));
            setApr();
            count += vernietigdeBagage(2);
            series.getData().add(new XYChart.Data("Apr", count));
            setMay();
            count += vernietigdeBagage(2);
            series.getData().add(new XYChart.Data("Mei", count));
            setJun();
            count += vernietigdeBagage(2);
            series.getData().add(new XYChart.Data("Jun", count));
            setJul();
            count += vernietigdeBagage(2);
            series.getData().add(new XYChart.Data("Jul", count));
            setAug();
            count += vernietigdeBagage(2);
            series.getData().add(new XYChart.Data("Aug", count));
            setSep();
            count += vernietigdeBagage(2);
            series.getData().add(new XYChart.Data("Sep", count));
            setOct();
            count += vernietigdeBagage(2);
            series.getData().add(new XYChart.Data("Okt", count));
            setNov();
            count += vernietigdeBagage(2);
            series.getData().add(new XYChart.Data("Nov", count));
            setDec();
            count += vernietigdeBagage(2);
            series.getData().add(new XYChart.Data("Dec", count));
        }
        return series;
    }

    /**
     * Doel: Zet de datum naar de benodigde maand voor de graph.
     */
    public void setJan() {
        int jaar = Integer.valueOf(jaarComboBox.getValue());
        beginDatum = jaar + "-01";
        voorDatum = (jaar - 1) + "-01";
        eindDatum = jaar + "-02";
        voorDatumEind = (jaar - 1) + "-02";
    }

    /**
     * Doel: Zet de datum naar de benodigde maand voor de graph.
     */
    public void setFeb() {

        int jaar = Integer.valueOf(jaarComboBox.getValue());
        beginDatum = jaar + "-02";
        voorDatum = (jaar - 1) + "-02";
        eindDatum = jaar + "-03";
        voorDatumEind = (jaar - 1) + "-03";
    }

    /**
     * Doel: Zet de datum naar de benodigde maand voor de graph.
     */
    public void setMar() {

        int jaar = Integer.valueOf(jaarComboBox.getValue());
        beginDatum = jaar + "-03";
        voorDatum = (jaar - 1) + "-03";
        eindDatum = jaar + "-04";
        voorDatumEind = (jaar - 1) + "-04";
    }

    /**
     * Doel: Zet de datum naar de benodigde maand voor de graph.
     */
    public void setApr() {

        int jaar = Integer.valueOf(jaarComboBox.getValue());
        beginDatum = jaar + "-04";
        voorDatum = (jaar - 1) + "-04";
        eindDatum = jaar + "-05";
        voorDatumEind = (jaar - 1) + "-05";
    }

    /**
     * Doel: Zet de datum naar de benodigde maand voor de graph.
     */
    public void setMay() {

        int jaar = Integer.valueOf(jaarComboBox.getValue());
        beginDatum = jaar + "-05";
        voorDatum = (jaar - 1) + "-05";
        eindDatum = jaar + "-06";
        voorDatumEind = (jaar - 1) + "-06";
    }

    /**
     * Doel: Zet de datum naar de benodigde maand voor de graph.
     */
    public void setJun() {

        int jaar = Integer.valueOf(jaarComboBox.getValue());
        beginDatum = jaar + "-06";
        voorDatum = (jaar - 1) + "-06";
        eindDatum = jaar + "-07";
        voorDatumEind = (jaar - 1) + "-07";
    }

    /**
     * Doel: Zet de datum naar de benodigde maand voor de graph.
     */
    public void setJul() {

        int jaar = Integer.valueOf(jaarComboBox.getValue());
        beginDatum = jaar + "-07";
        voorDatum = (jaar - 1) + "-07";
        eindDatum = jaar + "-08";
        voorDatumEind = (jaar - 1) + "-08";
    }

    /**
     * Doel: Zet de datum naar de benodigde maand voor de graph.
     */
    public void setAug() {

        int jaar = Integer.valueOf(jaarComboBox.getValue());
        beginDatum = jaar + "-08";
        voorDatum = (jaar - 1) + "-08";
        eindDatum = jaar + "-09";
        voorDatumEind = (jaar - 1) + "-09";
    }

    /**
     * Doel: Zet de datum naar de benodigde maand voor de graph.
     */
    public void setSep() {

        int jaar = Integer.valueOf(jaarComboBox.getValue());
        beginDatum = jaar + "-09";
        voorDatum = (jaar - 1) + "-09";
        eindDatum = jaar + "-10";
        voorDatumEind = (jaar - 1) + "-10";
    }

    /**
     * Doel: Zet de datum naar de benodigde maand voor de graph.
     */
    public void setOct() {

        int jaar = Integer.valueOf(jaarComboBox.getValue());
        beginDatum = jaar + "-10";
        voorDatum = (jaar - 1) + "-10";
        eindDatum = jaar + "-11";
        voorDatumEind = (jaar - 1) + "-11";
    }

    /**
     * Doel: Zet de datum naar de benodigde maand voor de graph.
     */
    public void setNov() {

        int jaar = Integer.valueOf(jaarComboBox.getValue());
        beginDatum = jaar + "-11";
        voorDatum = (jaar - 1) + "-11";
        eindDatum = jaar + "-12";
        voorDatumEind = (jaar - 1) + "-12";
    }

    /**
     * Doel: Zet de datum naar de benodigde maand voor de graph.
     */
    public void setDec() {

        int jaar = Integer.valueOf(jaarComboBox.getValue());
        beginDatum = jaar + "-12";
        voorDatum = (jaar - 1) + "-12";
        eindDatum = (jaar + 1) + "-01";
        voorDatumEind = jaar + "-01";
    }

    /**
     * Doel: reageer op een selectie uit de combobox
     *
     * @param event selectie uit combobox
     * @throws SQLException vangt fouten op
     */
    @FXML
    private void handleGraphComboBoxAction(ActionEvent event) throws SQLException {
        grafiekSchadeVergoeding.getData().clear();
        x.setLabel("Maand");
        y.setLabel("Bedrag");

        grafiekSchadeVergoeding.setLegendVisible(true);
        XYChart.Series series = new XYChart.Series();
        series.setName(jaarComboBox.getValue());
        String welkeWaarde = graphComboBox.getValue();
        series = fillChart(series, welkeWaarde);
        grafiekSchadeVergoeding.getData().add(series);
    }

    /**
     * Doel: reageer op een selectie uit de combobox
     *
     * @param event selectie uit combobox
     * @throws SQLException vangt fouten op
     */
    @FXML
    private void handleJaarComboBoxAction(ActionEvent event) throws SQLException {
        handleAction();
        maandComboBox.setDisable(false);
        exporteerNaarPdfBtn.setDisable(false);
        exporteerNaarCsvBtn.setDisable(false);
    }

    /**
     * Doel: reageer op een selectie uit de combobox
     *
     * @param event selectie uit combobox
     * @throws SQLException vangt fouten op
     */
    @FXML
    private void handleMaandComboBoxAction(ActionEvent event) throws SQLException {
        handleAction();
    }

    /**
     * Doel: reageer op actie.
     *
     * @throws SQLException vangt fouten op
     */
    public void handleAction() throws SQLException {

        grafiekSchadeVergoeding.getData().clear();
        if (lang == 2) {
            grafiekSchadeVergoeding.setTitle("Graph of the year: "
                    + jaarComboBox.getValue());
        } else {
            grafiekSchadeVergoeding.setTitle("Grafiek bij het jaar: "
                    + jaarComboBox.getValue());
        }

        selecteerDeDatum();
        resetGraph();
        grafiekSchadeVergoeding.setVisible(true);
        // vul de labels met de informatie.
        bagageGeregistreerdAlsVerloren = bagageGeregistreerdAlsVerloren(1);
        terugGevondenBagage = terugGevondenBagage(1);
        gemiddeldeTijdTerugvinden = gemiddeldeTijdTerugvinden(1);
        percentageString = percentageTeruggevondenBagage(1);
        ingediendeSchadeclaims = ingediendeSchadeclaims(1);
        uitgekeerdeSchadeclaims = uitgekeerdeSchadeclaims(1);
        gevondenBagageZonderClaim = gevondenBagageZonderClaim(1);
        vernietigdeBagageAantal = vernietigdeBagage(1);
        graphComboBox.setVisible(true);
    }

    /**
     * Doel: ververs de grafiek
     */
    public void resetGraph() {
        XYChart.Series series = new XYChart.Series();
        series.setName(jaarComboBox.getValue());
        if (lang == 2) {
            series.getData().add(new XYChart.Data("Jan", 0));
            series.getData().add(new XYChart.Data("Feb", 0));
            series.getData().add(new XYChart.Data("Mar", 0));
            series.getData().add(new XYChart.Data("Apr", 0));
            series.getData().add(new XYChart.Data("May", 0));
            series.getData().add(new XYChart.Data("Jun", 0));
            series.getData().add(new XYChart.Data("Jul", 0));
            series.getData().add(new XYChart.Data("Aug", 0));
            series.getData().add(new XYChart.Data("Sep", 0));
            series.getData().add(new XYChart.Data("Oct", 0));
            series.getData().add(new XYChart.Data("Nov", 0));
            series.getData().add(new XYChart.Data("Dec", 0));
        } else {
            series.getData().add(new XYChart.Data("Jan", 0));
            series.getData().add(new XYChart.Data("Feb", 0));
            series.getData().add(new XYChart.Data("Mar", 0));
            series.getData().add(new XYChart.Data("Apr", 0));
            series.getData().add(new XYChart.Data("Mei", 0));
            series.getData().add(new XYChart.Data("Jun", 0));
            series.getData().add(new XYChart.Data("Jul", 0));
            series.getData().add(new XYChart.Data("Aug", 0));
            series.getData().add(new XYChart.Data("Sep", 0));
            series.getData().add(new XYChart.Data("Okt", 0));
            series.getData().add(new XYChart.Data("Nov", 0));
            series.getData().add(new XYChart.Data("Dec", 0));
        }

        grafiekSchadeVergoeding.getData().add(series);
    }

}