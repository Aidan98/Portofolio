package fys;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.apache.commons.validator.routines.EmailValidator;
import org.controlsfx.control.Notifications;

/**
 * Doel: Dit is de hoofdklasse van de applicatie. Hier komt code die het scherm
 * van de applicatie initialiseert en de code die wordt gebruikt door meerdere
 * controllers.
 *
 * @author is102 - 3, GORDEL
 */
public class FYS extends Application {

    // Attributen
    private static final String DB_CONNECTION = "jdbc:mysql://localhost/fys";
    private static final String DB_USER = "fys_is102";
    private static final String DB_PASSWORD = "gordel";
    private static final String FROM = "teamgordel@gmail.com";
    private static final String PASS = "Fastyourseatbelt";

    public static String gebruiker;
    public static int gebruikerID;
    public static int language;

    public static Stage parentWindow;

    /**
     * Doel: het starten van de applicatie.
     *
     * @param stage het scherm.
     * @throws Exception vang fouten op.
     */
    @Override
    public void start(Stage stage) throws Exception {

        parentWindow = stage;
        Parent root = FXMLLoader.load(getClass().getResource("view/Inloggen.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setHeight(768);
        stage.setResizable(false);
        stage.setWidth(1366);
        stage.getIcons().add(new Image("fys/view/images/logo.png") {
        });
        stage.setTitle("Corendon Lost & Found - Inloggen");
        stage.show();
    }

    /**
     * Doel: Verander naar het volgende scherm.
     *
     * @param title de titel van de nieuwe scene.
     * @param changeToWindow locatie van de nieuwe scene.
     * @throws IOException vang de fouten op.
     */
    public void changeToAnotherFXML(String title, String changeToWindow) throws IOException {
        Parent window1;
        window1 = FXMLLoader.load(getClass().getResource(changeToWindow));
        Stage mainStage;
        mainStage = FYS.parentWindow;
        mainStage.setTitle(title);
        mainStage.setHeight(768);
        mainStage.setWidth(1366);
        mainStage.setResizable(false);
        mainStage.getScene().setRoot(window1);
    }

    /**
     * Doel: Maak connectie met de database.
     *
     * @return de connectie.
     */
    public static Connection getDBConnection() {
        Connection dbConnection = null;

        // maak de connectie met de database
        try {
            dbConnection = DriverManager.getConnection(
                    DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            // vang errors op
        }
        return dbConnection;
    }

    /**
     * Doel: controleren of een string een geldig emailadres is.
     *
     * @param email een String die uit een emailadres
     * @return
     */
    public boolean isEmailValid(String email) {
        if (email == null || "".equals(email)) {
            return false;
        }
        email = email.trim();

        EmailValidator emailValidator = EmailValidator.getInstance();
        return emailValidator.isValid(email);
    }

    /**
     * Doel: controleren of een emailadres in de tabel Klanten voor komt.
     *
     * @param email het te testen emailadres.
     * @return true wanneer de klant in de database staat, anders false.
     * @throws SQLException vang de fouten op.
     */
    public boolean isKlant(String email) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String selectSQL = "SELECT emailadres FROM klant";

        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);

            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();
            int columnCount = rs.getMetaData().getColumnCount();
            // kijk of het emailadres in de tabel staat.
            while (rs.next()) {
                String[] row = new String[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    row[i] = rs.getString("emailadres");
                    if (row[i].toLowerCase().equals(email)) {
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            // vangt errors op
        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        // Wanneer het emailadres niet in de lijst staat return false.
        return false;
    }

    /**
     * Doel: controleren of een emailadres in de tabel medewerker voor komt.
     *
     * @param email het te testen emailadres.
     * @return true wanneer de medewerker in de database staat, anders false.
     * @throws SQLException vang de fouten op.
     */
    public boolean isMedewerker(String email) throws SQLException {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        String selectSQL = "SELECT emailadres FROM medewerker";

        try {
            dbConnection = getDBConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);

            // execute select SQL statement
            ResultSet rs = preparedStatement.executeQuery();
            int columnCount = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                String[] row = new String[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    row[i] = rs.getString("emailadres");
                    if (row[i].toLowerCase().equals(email)) {
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            // vangt errors op
        } finally {

            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (dbConnection != null) {
                dbConnection.close();
            }

        }
        // wanneer het emailadres niet in de medewerker tabel staat return false
        return false;
    }

    /**
     * Doel: Het attribuut initialiseren
     *
     * @param gebruiker het emailadres van de gebruiker.
     * @return de gebruiker
     */
    public String setGebruiker(String gebruiker) {
        this.gebruiker = gebruiker;
        return gebruiker;
    }

    /**
     * Doel: Het opvragen van de waarde van het attribuut
     *
     * @return de waarde van het attribuut
     */
    public String getGebruiker() {
        return gebruiker;
    }

    /**
     * Doel: Het initialiseren het attribuut
     *
     * @param gebruikerid het ID van de gebruiker.
     * @return de waarde van het attribuut
     */
    public int setGebruikerID(int gebruikerid) {
        this.gebruikerID = gebruikerid;
        return gebruikerID;
    }

    /**
     * Doel: Het opvragen van de waarde van het attribuut.
     *
     * @return de waarde van het attribuut
     */
    public int getGebruikerID() {
        return gebruikerID;
    }

    /**
     * Doel: Vraag het systeem de huidige datum.
     *
     * @return een string met de huidige datum.
     */
    public static String getDate() {
        // haal de datum op.
        Date utilDate = new Date();
        java.sql.Date date = new java.sql.Date(utilDate.getTime());

        // haal de tijd op.
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String time = sdf.format(cal.getTime());

        // zet de datum en tijd om in een string met hetzelfde formaat als datetime uit de database
        String dateString = date + " " + time;
        return dateString;
    }

    /**
     * Doel: Het opvragen van de waarde van het attribuut.
     *
     * @return de taal
     */
    public int getLanguage() {

        return language;
    }

    /**
     * Doel: Het initialiseren het attribuut
     *
     * @param language de nieuwe taal
     * @return
     */
    public int setLanguage(int language) {
        this.language = language;

        return language;
    }

    /**
     * Doel: genereer een string van 8 tekens met minstens één nummer en één
     * symbool als wachtwoord. Dit doen we door een random nummer te kiezen aan
     * de hand van de lengte van de opties, de character op die positie wordt
     * dan toegevoegd aan de wachtwoord String.
     *
     * @return het random wachtwoord.
     */
    public static String randomWachtwoord() {

        final String LETTERS = "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPA"
                + "SDFGHJKLZXCVBNM!@#$%^&*()_+{}:|<>?,./;][=-";
        final String SYMBOLEN = "!@#$%^&*()_+{}:|<>?,./;][=-";
        final String NUMMERS = "0123456789";
        String wachtwoord = "";
        int keuzeLengte = LETTERS.length();
        int symbolenLengte = SYMBOLEN.length();
        int nummersLengte = NUMMERS.length();

        // voeg zes symbolen, letters en nummers toe.
        for (int i = 0; i < 6; i++) {
            int randomGetal = (int) (Math.random() * keuzeLengte);
            char randomLetter = LETTERS.charAt(randomGetal);
            wachtwoord = wachtwoord + randomLetter;
        }
        // voeg een nummer toe, zodat er minimaal één nummer in het wachtwoord staat.
        int randomGetal = (int) (Math.random() * nummersLengte);
        char randomNummer = NUMMERS.charAt(randomGetal);
        wachtwoord = wachtwoord + randomNummer;
        // voeg een symbool toe, zodat er minimaal één symbool in het wachtwoord staat.
        randomGetal = (int) (Math.random() * symbolenLengte);
        char randomSymbol = SYMBOLEN.charAt(randomGetal);
        wachtwoord = wachtwoord + randomSymbol;

        return wachtwoord;
    }

    /**
     * Doel: Verstuur een email met gegeven inhoud naar een gebruiker.
     *
     * @param to Het emailadres van de ontvanger.
     * @param subject het onderwerp van de email.
     * @param body de content van de email.
     * @return of de email is verstuurd.
     */
    public static boolean sendFromGMail(String to, String subject, String body) {
        Properties props = System.getProperties();
        // voeg de email host toe.
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", FROM);
        props.put("mail.smtp.password", PASS);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        // maak de email aan als een MimeMessage, Mimemessage is een email methode uit java.
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(FROM));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            message.setSubject(subject);
            message.setContent(body, "text/html");

            // maak de connectie met de host om de mail te versturen.
            Transport transport = session.getTransport("smtp");
            transport.connect(host, FROM, PASS);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            // Send message
            return true;
        } catch (MessagingException mex) {
            // Waanneer het versturen van de mail niet lukt, return false.
            return false;
        }
    }

    public static boolean sendEmailMetBestand(String to, String subject, String bericht, String attachment) {
        Properties props = System.getProperties();
        // voeg de email host toe.
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", FROM);
        props.put("mail.smtp.password", PASS);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        // maak de email aan als een MimeMessage, Mimemessage is een email methode uit java.
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(FROM));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            message.setSubject(subject);

            BodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setText(bericht);

            MimeBodyPart messageBodyPart2 = new MimeBodyPart();
            String filename = attachment;
            DataSource source = new FileDataSource(filename);
            messageBodyPart2.setDataHandler(new DataHandler(source));
            messageBodyPart2.setFileName(filename);

            Multipart body = new MimeMultipart();
            body.addBodyPart(messageBodyPart1);
            body.addBodyPart(messageBodyPart2);

            message.setContent(body);

            // maak de connectie met de host om de mail te versturen.
            Transport transport = session.getTransport("smtp");
            transport.connect(host, FROM, PASS);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            // Send message
            return true;
        } catch (MessagingException mex) {
            // als het versturen van de mail niet lukt, return false
            return false;
        }
    }

    /**
     *
     * @param typeNotifaction
     * @param text
     */
    public void maakNotifaction(String typeNotifaction, String text) {
        Image img;
        switch (typeNotifaction) {
            case "Error":
                img = new Image("fys/view/images/notifications_icon/Error.png");
                break;
            case "Confirm":
                img = new Image("fys/view/images/notifications_icon/Confirm.png");
                break;
            case "Information":
                img = new Image("fys/view/images/notifications_icon/Information.png");
                break;
            case "Warning":
                img = new Image("fys/view/images/notifications_icon/Warning.png");
                break;
            default:
                img = new Image("fys/view/images/notifications_icon/Picture.png");
                break;
        }

        Notifications notifications = Notifications.create()
                .title("Corendon Lost & Found")
                .text(text)
                .graphic(new ImageView(img))
                .hideAfter(Duration.seconds(5))
                .position(Pos.TOP_LEFT);
        notifications.darkStyle();
        notifications.owner(FYS.parentWindow);
        notifications.show();
    }

    /**
     *
     * @param textFieldString
     */
    public void nietIngevuldeVelden(TextField textFieldString) {
        textFieldString.setStyle("-fx-background-color: rgba(192, 192, 192, 0.6);"
                + "-fx-text-fill: #d81e05;"
                + "-fx-prompt-text-fill: black;");
    }

    /**
     * Doel: Het lanceren van de applicatie
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
