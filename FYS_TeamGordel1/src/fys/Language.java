package fys;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Doel: alle vertalingen van het applicatie voor het engelse versie
 *
 * @author is102 - 3, GORDEL
 */
public class Language {

    // Attributen inloggen
    private String inloggenLabelError = "Email or password is wrong, "
            + "please try again";
    private String inloggenPromptTextPassword = "Password";
    private String inloggenButtonLogIn = "Sign in";
    private String inloggenButtonForgotPassword = "Forgot password?";
    private String inloggenCloseApp = "Close application";
    //Attributen klant account verwijderen
    private String klantAccountVerwijderenButtonCancel = "Cancel";
    private String klantAccountVerwijderenButtonContinue = "Continue";
    private String klantAccountVerwijderenLabelQuestion = "Are you sure you want to delete your account?";
    //Attributen klant account wijzigen
    private String klantAccountWijzigen = "Edit Profile";
    private String klantAccountWijzigenPromptTextPassword = "Password";
    private String klantAccountWijzigenPromptTextRePassowrd = "Repeat password";
    private String klantAccountWijzigenPromptTextAdres = "Address";
    private String klantAccountWijzigenPromptTextCity = "City";
    private String klantAccountWijzigenPromptTextZipCode = "ZIP code";
    private String klantAccountWijzigenPromptTextCountry = "Country";
    private String klantAccountWijzigenPromptTextPhoneNbr = "Phone number";
    private String klantAccountWijzigenButtonSave = "Save";
    //Attributen klant home
    private String klantHomeButtonStatusLuggage = "View status of luggage";
    private String klantHomeButtonSubmitClaim = "Submit a claim";
    private String klantHomeButtonStatusClaims = "Status of claims";
    //Attributen klant inzien status
    private String klantInzienStatusLabelLuggageId = "Lost luggage ID";
    //Attributen klant menu bar
    private String klantMenuBarButtonProfile = "Profile";
    private String klantMenuBarButtonSignOut = "Sign out";
    //Attributen klant profiel
    private String klantProfielLabelProfile = klantMenuBarButtonProfile;
    private String klantProfielLabelName = "Name";
    private String klantProfielLabelAddress = klantAccountWijzigenPromptTextAdres;
    private String klantProfielLabelCity = klantAccountWijzigenPromptTextCity;
    private String klantProfielLabelZipCode = klantAccountWijzigenPromptTextZipCode;
    private String klantProfielLabelCountry = klantAccountWijzigenPromptTextCountry;
    private String klantProfielLabelPhoneNmr = klantAccountWijzigenPromptTextPhoneNbr;
    private String klantProfielButtonEditDetails = "Edit details";
    private String klantProfielButtonDeleteAccount = "Delete account";
    //Attributen klant schadeclaim indienen
    private String klantSchadeclaimIndienenLabelSubmitClaim = "Submit claim";
    private String klantSchadeclaimIndienenLabelIdLuggage = "The ID of your luggage is: ";
    private String klantSchadeclaimIndienenLabelDisclaimer = "* You will only see a luggage ID when your luggage has been lost for more than 365 days.";
    private String klantSchadeclaimIndienLabelAmount = "Enter your desired amount (in euros)";
    private String klantSchadeclaimIndienenPromptTextAmount = "Amount: ";
    private String klantSchadeclaimIndienenLabelIban = "IBAN number";
    private String klantSchadeclaimIndienenPromptTextIban = "IBAN";
    private String klantSchadeclaimIndienenButtonSubmitClaim = "Submit claim";
    private String klantSchadeclaimIndienenStatusLuggageBtn = "View status of luggage";
    private String klantSchadeclaimIndienenStatusClaimsBtn = "Status of claims";
    //Atributen klant status schadeclaim
    private String klantSchadeclaimLabelClaimId = "Claim ID: ";
    //Attributen manager account verwijderen
    private String managerAccountVerwijderenLabelSearch = "Search: ";
    private String managerAccountVerwijderenLabelEmail = "Email address";
    private String managerAccountVerwijderenPromptTextSearchBar = "Search bar";
    private String managerAccountVerwijderenButtonSearchAccount = "Search Account";
    private String managerAccountVerwijderenButtonLoadTable = "Load Table";
    private String managerAccountVerwijderenButtonDeleteSelecAcc = "Delete selected account";
    //Attributen manager accounts beheren
    private String managerAccountsBeherenBtnAddEmployee = "Add employee account";
    private String managerAccountsBeherenbtnDeleteAccount = klantProfielButtonDeleteAccount;
    private String managerAccountsBeherenBtnBack = "Back";
    //Attributen manager home
    private String managerHomeYearlyAnalysis = "Yearly analysis";
    private String managerHomeManageAccounts = "Manage accounts";
    private String managerHomeActivityLog = "Activity log";
    private String managerHomeMangeClaims = "Manage claims";
    //Attributen manager logboek
    private String managerLogboekColLogId = "Log ID";
    private String managerLogboekColEmployeeId = "Employee ID";
    private String managerLogboekColDate = "Date";
    private String managerLogboekColActivity = "Activity";
    //Attributen manager medewerker account maken
    private String managerMedewerkerAccountType = "Account type";
    private String managerMedewerkerAccountManager = "Manager";
    private String managerMedewerkerAccountEmployee = "Employee";
    private String managerMedewerkerAccountPromptTextEmail = "Email";
    private String managerMedewerkerAccountPromptTextPassword = "Password";
    private String managerMedewerkerAccountButtonMakeAccount = "Make account";
    //Attributen manager menu bar
    private String managerMenuBarProfile = klantMenuBarButtonProfile;
    private String managerMenuBarSignOut = klantMenuBarButtonSignOut;
    //Atrributen manager profiel
    private String managerProfielLabelProfile = klantMenuBarButtonProfile;
    private String managerProfielButtonPassword = "Edit Password";
    private String managerProfielButttonDeleteAcc = "Delete account";
    //Attributen manager profiel bewerken
    private String managerProfielBewerkenLabelEditProfile = "Edit Profile";
    private String managerProfielBewerkenPromptTextPassword = managerMedewerkerAccountPromptTextPassword;
    private String managerProfielBewerkenPromptTextRepeatPassword = klantAccountWijzigenPromptTextRePassowrd;
    private String managerProfielBewerkenButtonSave = "Save";
    //Attributen manager schadeclaim beheren
    private String managerSchadeclaimBeherenComboClaim = "Claim ";
    private String managerSchadeclaimBeherenButtonView = "View";
    private String managerSchadeclaimBeherenButtonAccept = "Accept and pay out";
    private String managerSchadeclaimBeherenButtonDecline = "Decline";
    private String managerSchadeclaimBeherenLabelReason = "Select your reason to decline";
    private String managerSchadeclaimBeherenButtonReason = "Reason for decline";
    private String managerSchadeclaimBeherenButtonConfReason = "Confirm reasoning";
    private String managerSchadeclaimBeherenLabelLableNbr = "Label number: ";
    private String managerSchadeclaimBeherenLabelFlightNbr = "Flight number: ";
    private String managerSchadeclaimBeherenLabelDestination = "Destination: ";
    private String managerSchadeclaimBeherenLabelType = "Luggage type: ";
    private String managerSchadeclaimBeherenLabelBrand = "Brand: ";
    private String managerSchadeclaimBeherenLabelColour = "Colour: ";
    private String managerSchadeclaimBeherenLabelSpecial = "Special features: ";
    private String managerSchadeclaimBeherenLabelDate = "Date of registry: ";
    private String managerSchadeclaimBeherenLabelDays = "Amount of days lost: ";
    private String managerSchadeclaimBeherenLabelPayOut = "Eligible for payout: ";
    private String managerSchadeclaimBeherenLabelAmount = "Amount: ";
    //Attributen manager verwijder account
    private String managerVerwijderAccountLabelConfirmation = "Are you certain you want to delete this account?";
    private String managerVerwijderAccountButtonAccept = "Confirm";
    private String managerVerwijderAccountButtonDecline = "Reject";
    //Attributen manager analyse
    private String exportToPDFBtn = "Export to PDF";
    private String exportToCSFBtn = "Export to Excel";
    private ObservableList<String> monthList
            = FXCollections.observableArrayList("", "January", "February", "March",
                    "April", "May", "June", "July", "August", "September",
                    "October", "November", "December");
    private ObservableList<String> graphListEN
            = FXCollections.observableArrayList("Luggage registered as lost",
                    "Returned luggage", "Submitted claims",
                    "Amount of money paid for claims", "Found luggage without claim",
                    "Destroyed luggage");
    private String luggageRegisteredAsLostLbl = "Amount of luggage registered as lost: ";
    private String amountOfLuggageFoundLbl = "Amount of luggage found: ";
    private String avgTimeBeforeFoundLbl = "Average time before luggage is found: ";
    private String percentageOfFoundLuggageLbl = "Percentage of found luggage: ";
    private String amountOfClaimsLbl = "Amount of submitted claims: ";
    private String amountPaidLbl = "Amount of money paid out in euro's: ";
    private String unclaimedLuggageLbl = "Unclaimed luggage: ";
    private String destroyedLuggageLbl = "Destroyed luggage: ";
    private String yearLbl = "Year: ";
    private String monthLbl = "Month: ";
    //Attributen menu bar home
    private String menuBarHomeButtonNed = "Dutch";
    private String menuBarHomeButtonEng = "English";
    // Attributen wachtwoordvegeten
    private String wachtwoordVergetenLabel = "Please enter your email address "
            + "You will recieve a recovery link on your email address";
    private String wachtwoordVergetenPromptTextEmail = "Email";
    private String wachtwoordVergetenButtonVerstuur = "Send";
    private String wachtwoordVergetenButtonTerug = "Back";
    // Attributen uitloggen
    private String uitloggenButtonInloggen = "Sign in";
    private String uitloggenButtonApplicatieSluiten = "Close application";
    private String uitloggenPromTextuBentUitgelogdTotZiens = "You have signed out, good bye!";
    // Attributen medewerkerAccountverwijderen
    private String MedewerkerVerwijdereAccountPromtextAccountVerwijderen = "Are you sure you want to delete your account?";
    private String medewerkerVerwijdereAccountButtonBevestigen = "Confirm";
    private String medewerkerVerwijderAccountButtonAnnuleren = "Cancel";
    // Attributen medewerkerProfielWijzigen
    private String medewerkerProfielWijzigenPromptTextWachtwoordWijzigen = "Change password";
    private String medewerkerProfielWijzigenPromptTextWachtwoordWijzigenHerhalen = "Repeat password";
    private String medewerkerProfielWijzigePromptTextProfielBewerken = "Edit profile";
    private String medewerkerProfielWijzigePromptTextOpslaan = "Save";
    // Attributten medewerkerProfiel
    private String medewerkerProfielPromptTextProfiel = "Profile";
    private String medewerkerProfielPromptTextEmailAdres = "Email address";
    private String medewerkerProfielButtonWijzigWachtwoord = "Change password";
    private String medewerkerProfielButtonVerwijderenAccount = "Delete account";
    // Attributten medewerkerMenuBar
    private String medewerkerMenuBarButtonProfiel = "Profile";
    private String medewerkerMenuBarButtonUitloggen = "Sign out";
    // Attributten medewerkerKlantReg
    private String medewerkerKlantRegLabelVermisteBagageRegForm = "Missing luggage registration from";
    private String medewerkerKlantRegLabelReizigerInformatie = "Traveler information";
    private String medewerkerKlantRegLabelBagageLabelInformatie = "Luggage label information";
    private String medewerkerKlantRegLabelBagageInformatie = "Luggage information";
    private String medewerkerKlantRegButtonSlaOpButtom = "Save";
    private String medewerkerKlantRegPromptTextLuchthavenText = "Airport";
    private String medewerkerKlantRegPromptTextVoornaamText = "First name";
    private String medewerkerKlantRegPromptTextTussenvoegselText = "Insertion";
    private String medewerkerKlantRegPromptTextAchternaamText = "Surname";
    private String medewerkerKlantRegPromptTextAdresText = "Address";
    private String medewerkerKlantRegPromptTextwoonplaatsText = "Residence";
    private String medewerkerKlantRegPromptTextPostcodeText = "Postal code";
    private String medewerkerKlantRegPromptTextlandText = "Country";
    private String medewerkerKlantRegPromptTexttelefoonText = "Telephone number";
    private String medewerkerKlantRegPromptTextEmailText = "E-mail";
    private String medewerkerKlantRegPromptTextLabelnummerText = "Tag number";
    private String medewerkerKlantRegPrompttextVluchtnummerText = "Flight number";
    private String medewerkerKlantRegPromptTextBestemmingText = "Destination";
    private String medewerkerKlantRegPromptTextTypeText = "Type";
    private String medewerkerKlantRegPromptTextMerkText = "Brand";
    private String medewerkerKlantRegPromptTextKleurText = "Colour";
    private String medewerkerKlantRegPromptTextBijzondereKenmerkenText = "Special characteristics";
    private String medewerkerKlantRegPromptTextVoornaamTxt = "First name";
    private String medewerkerKlantRegPromptTextAdresTxt = "Address";
    private String medewerkerKlantRegPromptTextWoonplaatsTxt = "Residence";
    private String medewerkerKlantRegPromptTextPostcodeTxt = "Postal code";
    private String medewerkerKlantRegPromptTextLandTxt = "Country";
    private String medewerkerKlantRegPromptTextTelefoonTxt = "Tel number";
    private String medewerkerKlantRegPromptTextEmailTxt = "E-mail";
    private String medewerkerKlantRegPromptTextLabelnummerTxt = "Tag number";
    private String medewerkerKlantRegPromptTextVluchtnummerTxt = "Flight number";
    private String medewerkerKlantRegPromptTextBestemmingTxt = "Destination";
    private String medewerkerKlantRegPromptTextTypeTxt = "Type";
    private String medewerkerKlantRegPromptTextKleurTxt = "Colour";
    private String medewerkerKlantRegPromptTextMerkTxt = "Brand";
    private String medewerkerKlantRegPromptTextTussenvoegselTxt = "Insertion";
    private String medewerkerKlantRegPromptTextAchternaamTxt = "Surname";
    private String medewerkerklantRegPromptTextBijzondereKenmerkenTxt = "Special characteristics";
    private String medewerkerKlantRegLabelInfoLbl = "Select an airport";
    // Attributten medewerkerHome
    private String medewerkerHomeButtonzoekenBagage = "Search luggage";
    private String medewerkerHomeButtonKlantRegVerBagage = "Client record lost luggage";
    private String medewerkerHomeButtonRegistratieGevBagage = "Registration found luggage ";
    private String medewerkerHomeButtonBagageBewerker = "Edit luggage";
    // Attributten medewerkerGevondenBagage
    private String medewerkerGevondenBagageLabelGevondenBagRegFrom = "Found luggage registration form";
    private String medewerkerGevondenBagageLabelBagageLblInformatieText = "Luggage tag information";
    private String medewerkerGevondenBagageLabelBagageInformatieText = "Luggage information";
    private String medewerkerGevondenBagageLabelFotoUploadenBtn = "Upload photo";
    private String medewerkerGevondenBagagePromptTextLuchthavenText = "Airport";
    private String medewerkerGevondenBagagePromptTextLabelnummerText = "Tag number";
    private String medewerkerGevondenBagagePromptTextVluchtnummerText = "Flight number";
    private String medewerkerGevondenBagagePromptTextBestemmingText = "Destination";
    private String medewerkerGevondenBagagePromptTextTypeText = "Type";
    private String medewerkerGevondenBagagePromptTextMerkText = "Brand";
    private String medewerkerGevondenBagagePromptTextKleurText = "Colour";
    private String medewerkerGevondenBagagePromptTextbijzondereKenmerkenText = "Special characteristics ";
    private String medewerkerGevondenBagageButtonSlaOpBtn = "Save";
    private String medewerkerGevondenBagageLabelInfoFotolbl = "You must upload a photo before you can save it.";
    //setPromptText
    private String medewerkerGevondenBagagePromptTextLabelnummerTxt = "Tag number";
    private String medewerkerGevondenBagagePromptTextVluchtnummerTxt = "Flight number";
    private String medewerkerGevondenBagagePromptTextBestemmingTxt = "Destination";
    private String medewerkerGevondenBagagePromptTextTypeTxt = "Type";
    private String medewerkerGevondenBagagePromptTextKleurTxt = "Colour";
    private String medewerkerGevondenBagagePromptTextMerkTxt = "Brand";
    private String medewerkerGevondenBagagePromptTextBijzondereKenmerkenTxt = "Special characteristics";
    // Attributen medewerkerBagageZoeken
    private String medewerkerBagageZoekenLabelAlleKoffers = "All suitcases";
    private String medewerkerBagageZoekenLabelZoekenBagage = "Search luggage";
    private String medewerkerBagageZoekenButtonZoekenBtn = "Search";
    private String medewerkerBagageZoekenButtonLaadAlleBagageBtn = "Reload";
    private String medewerkerBagageZoekenPromptTextLuchthaven = "Airport";
    private String medewerkerBagageZoekenPromptTextVluchtnummer = "Flight number";
    private String medewerkerBagageZoekenPromptTextLabelnummer = "Tag number";
    private String medewerkerBagageZoekenPromptTextType = "Type";
    private String medewerkerBagageZoekenPromptTextMerk = "Brand";
    private String medewerkerBagageZoekenPromptTextKleur = "Colour";
    private String medewerkerBagageZoekenPromptTextBijzondereKenmerken = "Special characteristics";
    private String medewerkerBagageZoekenPromptTextBestemming = "Destination";
    private String medewerkerBagageZoekenPromptTextIdentificatienummer = "Identification number";
    private String medewerkerBagageZoekenPromptTextZoekterm = "Search";
    private String medewerkerBagageZoekenPromptTextKenmerkenComboBox = "Characterize";
    private String medewerkerBagageZoekenButtonMatchBtn = "Make match";
    private String medewerkerBagageZoekenLabelKlantLbl = "Lost-ID";
    private String medewerkerBagageZoekenLabelTypeLbl = "Type";
    private String medewerkerBagageZoekenLabelMerkLbl = "Brand";
    private String medewerkerBagageZoekenLabelKleurLbl = "Colour";
    private String medewerkerBagageZoekenLabelVluchtnummerLbl = "Flight number";
    private String medewerkerBagageZoekenLabelLabelnrLbl = "Tag number";
    private String medewerkerbagageZoekenLabelBijzondereKenmerkenLbl = "Special characteristics";
    // Attributen  medewerkerBagageUpdate
    private String medewerkerBagageUpdateLabeltUpdateBagage = "Update luggage";
    private String medewerkerBagageUpdateLabelBagageLabelInformatie = "Luggage tag information";
    private String medewerkerBagageUpdateLabelBagageInformatie = "Luggage information";
    private String medewerkerBagageUpdateButtonZoekenBtn = "Search";
    private String medewerkerBagageUpdatePromptTextType = "Type";
    private String medewerkerBagageUpdatePromptTextMerk = "Brand";
    private String medewerkerBagageUpdatePromptTextKleur = "Colour";
    private String medewerkerBagageUpdatePromptTextBagageId = "Luggage ID";
    private String medewerkerBagageUpdatePromptTextLabelnummerTxt = "Tag number";
    private String medewerkerBagageUpdatePromptTextVluchtnummerTxt = "Flight number";
    private String medewerkerBagageUpdatePromptTextBestemmingTxt = "Destination";
    private String medewerkerBagageUpdatePromptTextTypeTxt = "Type";
    private String medewerkerBagageUpdatePromptTextMerkTxt = "Brand";
    private String medewerkerBagageUpdatePromptTextKleurTxt = "Colour";
    private String medewerkerBagageUpdatePromptTextBijzondereKenmerkenTxt = "Special characteristics";
    private String medewerkerBagageUpdatePromptTextStatusCombox = "Status";
    private String medewerkerBagageUpdateButtonSlaOpBtn = "Save";
    private ObservableList<String> kenmerkenList = FXCollections.observableArrayList("Tag number", "Flight number", "Colour", "Type", "Brand", "Destination");

    /**
     * getters en setters van alle hierbovengenoemde attributen
     *
     * @return
     */
    public ObservableList<String> getKenmerkenList() {
        return kenmerkenList;
    }

    public void setKenmerkerlist(ObservableList<String> kenmerkenList) {
        this.kenmerkenList = kenmerkenList;
    }

    public String getManagerAccountsBeherenBtnBack() {
        return managerAccountsBeherenBtnBack;
    }

    public void setManagerAccountsBeherenBtnBack(String managerAccountsBeherenBtnBack) {
        this.managerAccountsBeherenBtnBack = managerAccountsBeherenBtnBack;
    }

    public String getManagerSchadeclaimBeherenLabelAmount() {
        return managerSchadeclaimBeherenLabelAmount;
    }

    public void setManagerSchadeclaimBeherenLabelAmount(String managerSchadeclaimBeherenLabelAmount) {
        this.managerSchadeclaimBeherenLabelAmount = managerSchadeclaimBeherenLabelAmount;
    }

    public String getManagerSchadeclaimBeherenLabelPayOut() {
        return managerSchadeclaimBeherenLabelPayOut;
    }

    public void setManagerSchadeclaimBeherenLabelPayOut(String managerSchadeclaimBeherenLabelPayOut) {
        this.managerSchadeclaimBeherenLabelPayOut = managerSchadeclaimBeherenLabelPayOut;
    }

    public String getManagerSchadeclaimBeherenLabelDays() {
        return managerSchadeclaimBeherenLabelDays;
    }

    public void setManagerSchadeclaimBeherenLabelDays(String managerSchadeclaimBeherenLabelDays) {
        this.managerSchadeclaimBeherenLabelDays = managerSchadeclaimBeherenLabelDays;
    }

    public String getManagerSchadeclaimBeherenLabelDate() {
        return managerSchadeclaimBeherenLabelDate;
    }

    public void setManagerSchadeclaimBeherenLabelDate(String managerSchadeclaimBeherenLabelDate) {
        this.managerSchadeclaimBeherenLabelDate = managerSchadeclaimBeherenLabelDate;
    }

    public String getManagerSchadeclaimBeherenLabelSpecial() {
        return managerSchadeclaimBeherenLabelSpecial;
    }

    public void setManagerSchadeclaimBeherenLabelSpecial(String managerSchadeclaimBeherenLabelSpecial) {
        this.managerSchadeclaimBeherenLabelSpecial = managerSchadeclaimBeherenLabelSpecial;
    }

    public String getManagerSchadeclaimBeherenLabelColour() {
        return managerSchadeclaimBeherenLabelColour;
    }

    public void setManagerSchadeclaimBeherenLabelColour(String managerSchadeclaimBeherenLabelColour) {
        this.managerSchadeclaimBeherenLabelColour = managerSchadeclaimBeherenLabelColour;
    }

    public String getManagerSchadeclaimBeherenLabelBrand() {
        return managerSchadeclaimBeherenLabelBrand;
    }

    public void setManagerSchadeclaimBeherenLabelBrand(String managerSchadeclaimBeherenLabelBrand) {
        this.managerSchadeclaimBeherenLabelBrand = managerSchadeclaimBeherenLabelBrand;
    }

    public String getManagerSchadeclaimBeherenLabelType() {
        return managerSchadeclaimBeherenLabelType;
    }

    public void setManagerSchadeclaimBeherenLabelType(String managerSchadeclaimBeherenLabelType) {
        this.managerSchadeclaimBeherenLabelType = managerSchadeclaimBeherenLabelType;
    }

    public String getManagerSchadeclaimBeherenLabelDestination() {
        return managerSchadeclaimBeherenLabelDestination;
    }

    public void setManagerSchadeclaimBeherenLabelDestination(String managerSchadeclaimBeherenLabelDestination) {
        this.managerSchadeclaimBeherenLabelDestination = managerSchadeclaimBeherenLabelDestination;
    }

    public String getManagerSchadeclaimBeherenLabelFlightNbr() {
        return managerSchadeclaimBeherenLabelFlightNbr;
    }

    public void setManagerSchadeclaimBeherenLabelFlightNbr(String managerSchadeclaimBeherenLabelFlightNbr) {
        this.managerSchadeclaimBeherenLabelFlightNbr = managerSchadeclaimBeherenLabelFlightNbr;
    }

    public String getManagerSchadeclaimBeherenLabelLableNbr() {
        return managerSchadeclaimBeherenLabelLableNbr;
    }

    public void setManagerSchadeclaimBeherenLabelLableNbr(String managerSchadeclaimBeherenLabelLableNbr) {
        this.managerSchadeclaimBeherenLabelLableNbr = managerSchadeclaimBeherenLabelLableNbr;
    }

    public String getWachtwoordVergetenPromptTextEmail() {
        return wachtwoordVergetenPromptTextEmail;
    }

    public void setWachtwoordVergetenPromptTextEmail(String wachtwoordVergetenPromptTextEmail) {
        this.wachtwoordVergetenPromptTextEmail = wachtwoordVergetenPromptTextEmail;
    }

    public String getYearLbl() {
        return yearLbl;
    }

    public void setYearLbl(String yearLbl) {
        this.yearLbl = yearLbl;
    }

    public String getMonthLbl() {
        return monthLbl;
    }

    public void setMonthLbl(String monthLbl) {
        this.monthLbl = monthLbl;
    }

    public String getLuggageRegisteredAsLostLbl() {
        return luggageRegisteredAsLostLbl;
    }

    public String getAmountOfLuggageFoundLbl() {
        return amountOfLuggageFoundLbl;
    }

    public void setAmountOfLuggageFoundLbl(String amountOfLuggageFoundLbl) {
        this.amountOfLuggageFoundLbl = amountOfLuggageFoundLbl;
    }

    public String getAvgTimeBeforeFoundLbl() {
        return avgTimeBeforeFoundLbl;
    }

    public void setAvgTimeBeforeFoundLbl(String avgTimeBeforeFoundLbl) {
        this.avgTimeBeforeFoundLbl = avgTimeBeforeFoundLbl;
    }

    public String getPercentageOfFoundLuggageLbl() {
        return percentageOfFoundLuggageLbl;
    }

    public void setPercentageOfFoundLuggageLbl(String percentageOfFoundLuggageLbl) {
        this.percentageOfFoundLuggageLbl = percentageOfFoundLuggageLbl;
    }

    public String getAmountOfClaimsLbl() {
        return amountOfClaimsLbl;
    }

    public void setAmountOfClaimsLbl(String amountOfClaimsLbl) {
        this.amountOfClaimsLbl = amountOfClaimsLbl;
    }

    public String getAmountPaidLbl() {
        return amountPaidLbl;
    }

    public void setAmountPaidLbl(String amountPaidLbl) {
        this.amountPaidLbl = amountPaidLbl;
    }

    public String getUnclaimedLuggageLbl() {
        return unclaimedLuggageLbl;
    }

    public void setUnclaimedLuggageLbl(String unclaimedLuggageLbl) {
        this.unclaimedLuggageLbl = unclaimedLuggageLbl;
    }

    public String getDestroyedLuggageLbl() {
        return destroyedLuggageLbl;
    }

    public void setDestroyedLuggageLbl(String destroyedLuggageLbl) {
        this.destroyedLuggageLbl = destroyedLuggageLbl;
    }

    public void setLuggageRegisteredAsLostLbl(String luggageRegisteredAsLostLbl) {
        this.luggageRegisteredAsLostLbl = luggageRegisteredAsLostLbl;
    }

    public ObservableList<String> getGraphListEN() {
        return graphListEN;
    }

    public void setGraphListEN(ObservableList<String> graphListEN) {
        this.graphListEN = graphListEN;
    }

    public ObservableList<String> getMonthList() {
        return monthList;
    }

    public void setMonthList(ObservableList<String> monthList) {
        this.monthList = monthList;
    }

    public String getWachwoordtvergetenLabel() {
        return wachtwoordVergetenLabel;
    }

    public void setWachtwoordVergetenLabel(String WachtwoordVergetenLabel) {
        this.wachtwoordVergetenLabel = WachtwoordVergetenLabel;
    }

    public String getWactwoordvergetenPrompTextEmail() {
        return wachtwoordVergetenPromptTextEmail;
    }

    public void setWactwoordvergetenPrompTextEmail(String WactwoordvergetenPrompTextEmail) {
        this.wachtwoordVergetenPromptTextEmail = WactwoordvergetenPrompTextEmail;
    }

    public String getWachtwoordVergetenButtonVerstuur() {
        return wachtwoordVergetenButtonVerstuur;
    }

    public void setWachtwoordVergetenButtonVertuur(String wachtwoordVergetenButtonVerstuur) {
        this.wachtwoordVergetenButtonVerstuur = wachtwoordVergetenButtonVerstuur;
    }

    public String getwachtwoordVergetenButtonTerug() {
        return wachtwoordVergetenButtonTerug;
    }

    public void setWachtwoordVergetenButtonTerug(String wachtwoordVergetenButtonTerug) {
        this.wachtwoordVergetenButtonTerug = wachtwoordVergetenButtonTerug;
    }

    public String getMenuBarHomeButtonEng() {
        return menuBarHomeButtonEng;
    }

    public void setMenuBarHomeButtonEng(String menuBarHomeButtonEng) {
        this.menuBarHomeButtonEng = menuBarHomeButtonEng;
    }

    public String getMenuBarHomeButtonNed() {
        return menuBarHomeButtonNed;
    }

    public void setMenuBarHomeButtonNed(String menuBarHomeButtonNed) {
        this.menuBarHomeButtonNed = menuBarHomeButtonNed;
    }

    public String getManagerVerwijderAccountButtonDecline() {
        return managerVerwijderAccountButtonDecline;
    }

    public void setManagerVerwijderAccountButtonDecline(String managerVerwijderAccountButtonDecline) {
        this.managerVerwijderAccountButtonDecline = managerVerwijderAccountButtonDecline;
    }

    public String getManagerVerwijderAccountButtonAccept() {
        return managerVerwijderAccountButtonAccept;
    }

    public void setManagerVerwijderAccountButtonAccept(String managerVerwijderAccountButtonAccept) {
        this.managerVerwijderAccountButtonAccept = managerVerwijderAccountButtonAccept;
    }

    public String getManagerVerwijderAccountLabelConfirmation() {
        return managerVerwijderAccountLabelConfirmation;
    }

    public void setManagerVerwijderAccountLabelConfirmation(String managerVerwijderAccountLabelConfirmation) {
        this.managerVerwijderAccountLabelConfirmation = managerVerwijderAccountLabelConfirmation;
    }

    public String getManagerSchadeclaimBeherenButtonConfReason() {
        return managerSchadeclaimBeherenButtonConfReason;
    }

    public void setManagerSchadeclaimBeherenButtonConfReason(String managerSchadeclaimBeherenButtonConfReason) {
        this.managerSchadeclaimBeherenButtonConfReason = managerSchadeclaimBeherenButtonConfReason;
    }

    public String getManagerSchadeclaimBeherenButtonReason() {
        return managerSchadeclaimBeherenButtonReason;
    }

    public void setManagerSchadeclaimBeherenButtonReason(String managerSchadeclaimBeherenButtonReason) {
        this.managerSchadeclaimBeherenButtonReason = managerSchadeclaimBeherenButtonReason;
    }

    public String getManagerSchadeclaimBeherenLabelReason() {
        return managerSchadeclaimBeherenLabelReason;
    }

    public void setManagerSchadeclaimBeherenLabelReason(String managerSchadeclaimBeherenLabelReason) {
        this.managerSchadeclaimBeherenLabelReason = managerSchadeclaimBeherenLabelReason;
    }

    public String getManagerSchadeclaimBeherenButtonDecline() {
        return managerSchadeclaimBeherenButtonDecline;
    }

    public void setManagerSchadeclaimBeherenButtonDecline(String managerSchadeclaimBeherenButtonDecline) {
        this.managerSchadeclaimBeherenButtonDecline = managerSchadeclaimBeherenButtonDecline;
    }

    public String getManagerSchadeclaimBeherenButtonAccept() {
        return managerSchadeclaimBeherenButtonAccept;
    }

    public void setManagerSchadeclaimBeherenButtonAccept(String managerSchadeclaimBeherenButtonAccept) {
        this.managerSchadeclaimBeherenButtonAccept = managerSchadeclaimBeherenButtonAccept;
    }

    public String getManagerSchadeclaimBeherenButtonView() {
        return managerSchadeclaimBeherenButtonView;
    }

    public void setManagerSchadeclaimBeherenButtonView(String managerSchadeclaimBeherenButtonView) {
        this.managerSchadeclaimBeherenButtonView = managerSchadeclaimBeherenButtonView;
    }

    public String getManagerSchadeclaimBeherenComboClaim() {
        return managerSchadeclaimBeherenComboClaim;
    }

    public void setManagerSchadeclaimBeherenComboClaim(String managerSchadeclaimBeherenComboClaim) {
        this.managerSchadeclaimBeherenComboClaim = managerSchadeclaimBeherenComboClaim;
    }

    public String getManagerProfielBewerkenButtonSave() {
        return managerProfielBewerkenButtonSave;
    }

    public void setManagerProfielBewerkenButtonSave(String managerProfielBewerkenButtonSave) {
        this.managerProfielBewerkenButtonSave = managerProfielBewerkenButtonSave;
    }

    public String getManagerProfielBewerkenPromptTextRepeatPassword() {
        return managerProfielBewerkenPromptTextRepeatPassword;
    }

    public void setManagerProfielBewerkenPromptTextRepeatPassword(String managerProfielBewerkenPromptTextRepeatPassword) {
        this.managerProfielBewerkenPromptTextRepeatPassword = managerProfielBewerkenPromptTextRepeatPassword;
    }

    public String getManagerProfielBewerkenPromptTextPassword() {
        return managerProfielBewerkenPromptTextPassword;
    }

    public void setManagerProfielBewerkenPromptTextPassword(String managerProfielBewerkenPromptTextPassword) {
        this.managerProfielBewerkenPromptTextPassword = managerProfielBewerkenPromptTextPassword;
    }

    public String getManagerProfielBewerkenLabelEditProfile() {
        return managerProfielBewerkenLabelEditProfile;
    }

    public void setManagerProfielBewerkenLabelEditProfile(String managerProfielBewerkenLabelEditProfile) {
        this.managerProfielBewerkenLabelEditProfile = managerProfielBewerkenLabelEditProfile;
    }

    public String getManagerProfielButttonDeleteAcc() {
        return managerProfielButttonDeleteAcc;
    }

    public void setManagerProfielButttonDeleteAcc(String managerProfielButttonDeleteAcc) {
        this.managerProfielButttonDeleteAcc = managerProfielButttonDeleteAcc;
    }

    public String getManagerProfielButtonPassword() {
        return managerProfielButtonPassword;
    }

    public void setManagerProfielButtonPassword(String managerProfielButtonPassword) {
        this.managerProfielButtonPassword = managerProfielButtonPassword;
    }

    public String getManagerProfielLabelProfile() {
        return managerProfielLabelProfile;
    }

    public void setManagerProfielLabelProfile(String managerProfielLabelProfile) {
        this.managerProfielLabelProfile = managerProfielLabelProfile;
    }

    public String getManagerMenuBarSignOut() {
        return managerMenuBarSignOut;
    }

    public void setManagerMenuBarSignOut(String managerMenuBarSignOut) {
        this.managerMenuBarSignOut = managerMenuBarSignOut;
    }

    public String getManagerMenuBarProfile() {
        return managerMenuBarProfile;
    }

    public void setManagerMenuBarProfile(String managerMenuBarProfile) {
        this.managerMenuBarProfile = managerMenuBarProfile;
    }

    public String getManagerMedewerkerAccountButtonMakeAccount() {
        return managerMedewerkerAccountButtonMakeAccount;
    }

    public void setManagerMedewerkerAccountButtonMakeAccount(String managerMedewerkerAccountButtonMakeAccount) {
        this.managerMedewerkerAccountButtonMakeAccount = managerMedewerkerAccountButtonMakeAccount;
    }

    public String getManagerMedewerkerAccountPromptTextPassword() {
        return managerMedewerkerAccountPromptTextPassword;
    }

    public void setManagerMedewerkerAccountPromptTextPassword(String managerMedewerkerAccountPromptTextPassword) {
        this.managerMedewerkerAccountPromptTextPassword = managerMedewerkerAccountPromptTextPassword;
    }

    public String getManagerMedewerkerAccountPromptTextEmail() {
        return managerMedewerkerAccountPromptTextEmail;
    }

    public void setManagerMedewerkerAccountPromptTextEmail(String managerMedewerkerAccountPromptTextEmail) {
        this.managerMedewerkerAccountPromptTextEmail = managerMedewerkerAccountPromptTextEmail;
    }

    public String getManagerMedewerkerAccountEmployee() {
        return managerMedewerkerAccountEmployee;
    }

    public void setManagerMedewerkerAccountEmployee(String managerMedewerkerAccountEmployee) {
        this.managerMedewerkerAccountEmployee = managerMedewerkerAccountEmployee;
    }

    public String getManagerMedewerkerAccountManager() {
        return managerMedewerkerAccountManager;
    }

    public void setManagerMedewerkerAccountManager(String managerMedewerkerAccountManager) {
        this.managerMedewerkerAccountManager = managerMedewerkerAccountManager;
    }

    public String getManagerMedewerkerAccountType() {
        return managerMedewerkerAccountType;
    }

    public void setManagerMedewerkerAccountType(String managerMedewerkerAccountType) {
        this.managerMedewerkerAccountType = managerMedewerkerAccountType;
    }

    public String getManagerLogboekColActivity() {
        return managerLogboekColActivity;
    }

    public void setManagerLogboekColActivity(String managerLogboekColActivity) {
        this.managerLogboekColActivity = managerLogboekColActivity;
    }

    public String getManagerLogboekColDate() {
        return managerLogboekColDate;
    }

    public void setManagerLogboekColDate(String managerLogboekColDate) {
        this.managerLogboekColDate = managerLogboekColDate;
    }

    public String getManagerLogboekColEmployeeId() {
        return managerLogboekColEmployeeId;
    }

    public void setManagerLogboekColEmployeeId(String managerLogboekColEmployeeId) {
        this.managerLogboekColEmployeeId = managerLogboekColEmployeeId;
    }

    public String getManagerLogboekColLogId() {
        return managerLogboekColLogId;
    }

    public void setManagerLogboekColLogId(String managerLogboekColLogId) {
        this.managerLogboekColLogId = managerLogboekColLogId;
    }

    public String getManagerHomeMangeClaims() {
        return managerHomeMangeClaims;
    }

    public void setManagerHomeMangeClaims(String managerHomeMangeClaims) {
        this.managerHomeMangeClaims = managerHomeMangeClaims;
    }

    public String getManagerHomeActivityLog() {
        return managerHomeActivityLog;
    }

    public void setManagerHomeActivityLog(String managerHomeActivityLog) {
        this.managerHomeActivityLog = managerHomeActivityLog;
    }

    public String getManagerHomeManageAccounts() {
        return managerHomeManageAccounts;
    }

    public void setManagerHomeManageAccounts(String managerHomeManageAccounts) {
        this.managerHomeManageAccounts = managerHomeManageAccounts;
    }

    public String getManagerHomeYearlyAnalysis() {
        return managerHomeYearlyAnalysis;
    }

    public void setManagerHomeYearlyAnalysis(String managerHomeYearlyAnalysis) {
        this.managerHomeYearlyAnalysis = managerHomeYearlyAnalysis;
    }

    public String getManagerAccountsBeherenBtnDeleteAccount() {
        return managerAccountsBeherenbtnDeleteAccount;
    }

    public void setManagerAccountsBeherenbtnDeleteAccount(String managerAccountsBeherenbtnDeleteAccount) {
        this.managerAccountsBeherenbtnDeleteAccount = managerAccountsBeherenbtnDeleteAccount;
    }

    public String getManagerAccountsBeherenBtnAddEmployee() {
        return managerAccountsBeherenBtnAddEmployee;
    }

    public void setManagerAccountsBeherenBtnAddEmployee(String managerAccountsBeherenBtnAddEmployee) {
        this.managerAccountsBeherenBtnAddEmployee = managerAccountsBeherenBtnAddEmployee;
    }

    public String getManagerAccountVerwijderenButtonDeleteSelecAcc() {
        return managerAccountVerwijderenButtonDeleteSelecAcc;
    }

    public void setManagerAccountVerwijderenButtonDeleteSelecAcc(String managerAccountVerwijderenButtonDeleteSelecAcc) {
        this.managerAccountVerwijderenButtonDeleteSelecAcc = managerAccountVerwijderenButtonDeleteSelecAcc;
    }

    public String getKlantSchadeclaimIndienenLabelIdLuggage() {
        return klantSchadeclaimIndienenLabelIdLuggage;
    }

    public void setKlantSchadeclaimIndienenLabelIdLuggage(String klantSchadeclaimIndienenLabelIdLuggage) {
        this.klantSchadeclaimIndienenLabelIdLuggage = klantSchadeclaimIndienenLabelIdLuggage;
    }

    public String getKlantSchadeclaimIndienenLabelSubmitClaim() {
        return klantSchadeclaimIndienenLabelSubmitClaim;
    }

    public void setKlantSchadeclaimIndienenLabelSubmitClaim(String klantSchadeclaimIndienenLabelSubmitClaim) {
        this.klantSchadeclaimIndienenLabelSubmitClaim = klantSchadeclaimIndienenLabelSubmitClaim;
    }

    public String getManagerAccountVerwijderenButtonLoadTable() {
        return managerAccountVerwijderenButtonLoadTable;
    }

    public void setManagerAccountVerwijderenButtonLoadTable(String managerAccountVerwijderenButtonLoadTable) {
        this.managerAccountVerwijderenButtonLoadTable = managerAccountVerwijderenButtonLoadTable;
    }

    public String getManagerAccountVerwijderenButtonSearchAccount() {
        return managerAccountVerwijderenButtonSearchAccount;
    }

    public void setManagerAccountVerwijderenButtonSearchAccount(String managerAccountVerwijderenButtonSearchAccount) {
        this.managerAccountVerwijderenButtonSearchAccount = managerAccountVerwijderenButtonSearchAccount;
    }

    public String getManagerAccountVerwijderenPromptTextSearchBar() {
        return managerAccountVerwijderenPromptTextSearchBar;
    }

    public void setManagerAccountVerwijderenPromptTextSearchBar(String managerAccountVerwijderenPromptTextSearchBar) {
        this.managerAccountVerwijderenPromptTextSearchBar = managerAccountVerwijderenPromptTextSearchBar;
    }

    public String getManagerAccountVerwijderenLabelEmail() {
        return managerAccountVerwijderenLabelEmail;
    }

    public void setManagerAccountVerwijderenLabelEmail(String managerAccountVerwijderenLabelEmail) {
        this.managerAccountVerwijderenLabelEmail = managerAccountVerwijderenLabelEmail;
    }

    public String getManagerAccountVerwijderenLabelSearch() {
        return managerAccountVerwijderenLabelSearch;
    }

    public void setManagerAccountVerwijderenLabelSearch(String managerAccountVerwijderenLabelSearch) {
        this.managerAccountVerwijderenLabelSearch = managerAccountVerwijderenLabelSearch;
    }

    public String getKlantSchadeclaimLabelClaimId() {
        return klantSchadeclaimLabelClaimId;
    }

    public void setKlantSchadeclaimLabelClaimId(String klantSchadeclaimLabelClaimId) {
        this.klantSchadeclaimLabelClaimId = klantSchadeclaimLabelClaimId;
    }

    public String getKlantProfielButtonDeleteAccount() {
        return klantProfielButtonDeleteAccount;
    }

    public void setKlantProfielButtonDeleteAccount(String klantProfielButtonDeleteAccount) {
        this.klantProfielButtonDeleteAccount = klantProfielButtonDeleteAccount;
    }

    public String getKlantProfielButtonEditDetails() {
        return klantProfielButtonEditDetails;
    }

    public void setKlantProfielButtonEditDetails(String klantProfielButtonEditDetails) {
        this.klantProfielButtonEditDetails = klantProfielButtonEditDetails;
    }

    public String getKlantProfielLabelPhoneNmr() {
        return klantProfielLabelPhoneNmr;
    }

    public void setKlantProfielLabelPhoneNmr(String klantProfielLabelPhoneNmr) {
        this.klantProfielLabelPhoneNmr = klantProfielLabelPhoneNmr;
    }

    public String getKlantProfielLabelCountry() {
        return klantProfielLabelCountry;
    }

    public void setKlantProfielLabelCountry(String klantProfielLabelCountry) {
        this.klantProfielLabelCountry = klantProfielLabelCountry;
    }

    public String getKlantProfielLabelZipCode() {
        return klantProfielLabelZipCode;
    }

    public void setKlantProfielLabelZipCode(String klantProfielLabelZipCode) {
        this.klantProfielLabelZipCode = klantProfielLabelZipCode;
    }

    public String getKlantProfielLabelCity() {
        return klantProfielLabelCity;
    }

    public void setKlantProfielLabelCity(String klantProfielLabelCity) {
        this.klantProfielLabelCity = klantProfielLabelCity;
    }

    public String getKlantProfielLabelAddress() {
        return klantProfielLabelAddress;
    }

    public void setKlantProfielLabelAddress(String klantProfielLabelAddress) {
        this.klantProfielLabelAddress = klantProfielLabelAddress;
    }

    public String getKlantProfielLabelName() {
        return klantProfielLabelName;
    }

    public void setKlantProfielLabelName(String klantProfielLabelName) {
        this.klantProfielLabelName = klantProfielLabelName;
    }

    public String getKlantProfielLabelProfile() {
        return klantProfielLabelProfile;
    }

    public void setKlantProfielLabelProfile(String klantProfielLabelProfile) {
        this.klantProfielLabelProfile = klantProfielLabelProfile;
    }

    public String getKlantMenuBarButtonSignOut() {
        return klantMenuBarButtonSignOut;
    }

    public void setKlantMenuBarButtonSignOut(String klantMenuBarButtonSignOut) {
        this.klantMenuBarButtonSignOut = klantMenuBarButtonSignOut;
    }

    public String getKlantMenuBarButtonProfile() {
        return klantMenuBarButtonProfile;
    }

    public void setKlantMenuBarButtonProfile(String klantMenuBarButtonProfile) {
        this.klantMenuBarButtonProfile = klantMenuBarButtonProfile;
    }

    public String getKlantInzienStatusLabelLuggageId() {
        return klantInzienStatusLabelLuggageId;
    }

    public void setKlantInzienStatusLabelLuggageId(String klantInzienStatusLabelLuggageId) {
        this.klantInzienStatusLabelLuggageId = klantInzienStatusLabelLuggageId;
    }

    public String getKlantHomeButtonStatusClaims() {
        return klantHomeButtonStatusClaims;
    }

    public void setKlantHomeButtonStatusClaims(String klantHomeButtonStatusClaims) {
        this.klantHomeButtonStatusClaims = klantHomeButtonStatusClaims;
    }

    public String getKlantHomeButtonSubmitClaim() {
        return klantHomeButtonSubmitClaim;
    }

    public void setKlantHomeButtonSubmitClaim(String klantHomeButtonSubmitClaim) {
        this.klantHomeButtonSubmitClaim = klantHomeButtonSubmitClaim;
    }

    public String getKlantHomeButtonStatusLuggage() {
        return klantHomeButtonStatusLuggage;
    }

    public void setKlantHomeButtonStatusLuggage(String klantHomeButtonStatusLuggage) {
        this.klantHomeButtonStatusLuggage = klantHomeButtonStatusLuggage;
    }

    public String getKlantAccountWijzigenButtonSave() {
        return klantAccountWijzigenButtonSave;
    }

    public void setKlantAccountWijzigenButtonSave(String klantAccountWijzigenButtonSave) {
        this.klantAccountWijzigenButtonSave = klantAccountWijzigenButtonSave;
    }

    public String getKlantAccountWijzigenPromptTextPhoneNbr() {
        return klantAccountWijzigenPromptTextPhoneNbr;
    }

    public void setKlantAccountWijzigenPromptTextPhoneNbr(String klantAccountWijzigenPromptTextPhoneNbr) {
        this.klantAccountWijzigenPromptTextPhoneNbr = klantAccountWijzigenPromptTextPhoneNbr;
    }

    public String getKlantAccountWijzigenPromptTextCountry() {
        return klantAccountWijzigenPromptTextCountry;
    }

    public void setKlantAccountWijzigenPromptTextCountry(String klantAccountWijzigenPromptTextCountry) {
        this.klantAccountWijzigenPromptTextCountry = klantAccountWijzigenPromptTextCountry;
    }

    public String getKlantAccountWijzigenPromptTextZipCode() {
        return klantAccountWijzigenPromptTextZipCode;
    }

    public void setKlantAccountWijzigenPromptTextZipCode(String klantAccountWijzigenPromptTextZipCode) {
        this.klantAccountWijzigenPromptTextZipCode = klantAccountWijzigenPromptTextZipCode;
    }

    public String getKlantAccountWijzigenPromptTextCity() {
        return klantAccountWijzigenPromptTextCity;
    }

    public void setKlantAccountWijzigenPromptTextCity(String klantAccountWijzigenPromptTextCity) {
        this.klantAccountWijzigenPromptTextCity = klantAccountWijzigenPromptTextCity;
    }

    public String getKlantAccountWijzigenPromptTextAdres() {
        return klantAccountWijzigenPromptTextAdres;
    }

    public void setKlantAccountWijzigenPromptTextAdres(String klantAccountWijzigenPromptTextAdres) {
        this.klantAccountWijzigenPromptTextAdres = klantAccountWijzigenPromptTextAdres;
    }

    public String getKlantAccountWijzigenPromptTextRePassowrd() {
        return klantAccountWijzigenPromptTextRePassowrd;
    }

    public void setKlantAccountWijzigenPromptTextRePassowrd(String klantAccountWijzigenPromptTextRePassowrd) {
        this.klantAccountWijzigenPromptTextRePassowrd = klantAccountWijzigenPromptTextRePassowrd;
    }

    public String getKlantAccountWijzigenPromptTextPassword() {
        return klantAccountWijzigenPromptTextPassword;
    }

    public void setKlantAccountWijzigenPromptTextPassword(String klantAccountWijzigenPromptTextPassword) {
        this.klantAccountWijzigenPromptTextPassword = klantAccountWijzigenPromptTextPassword;
    }

    public String getKlantAccountWijzigen() {
        return klantAccountWijzigen;
    }

    public void setKlantAccountWijzigen(String klantAccountWijzigen) {
        this.klantAccountWijzigen = klantAccountWijzigen;
    }

    public String getKlantAccountVerwijderenLabelQuestion() {
        return klantAccountVerwijderenLabelQuestion;
    }

    public void setKlantAccountVerwijderenLabelQuestion(String klantAccountVerwijderenLabelQuestion) {
        this.klantAccountVerwijderenLabelQuestion = klantAccountVerwijderenLabelQuestion;
    }

    public String getKlantAccountVerwijderenButtonContinue() {
        return klantAccountVerwijderenButtonContinue;
    }

    public void setKlantAccountVerwijderenButtonContinue(String klantAccountVerwijderenButtonContinue) {
        this.klantAccountVerwijderenButtonContinue = klantAccountVerwijderenButtonContinue;
    }

    public String getKlantAccountVerwijderenButtonCancel() {
        return klantAccountVerwijderenButtonCancel;
    }

    public void setKlantAccountVerwijderenButtonCancel(String klantAccountVerwijderenButtonCancel) {
        this.klantAccountVerwijderenButtonCancel = klantAccountVerwijderenButtonCancel;
    }

    public String getKlantSchadeclaimIndienenPromptTextIban() {
        return klantSchadeclaimIndienenPromptTextIban;
    }

    public void setKlantSchadeclaimIndienenPromptTextIban(String klantSchadeclaimIndienenPromptTextIban) {
        this.klantSchadeclaimIndienenPromptTextIban = klantSchadeclaimIndienenPromptTextIban;
    }

    public String getKlantSchadeclaimIndienenLabelIban() {
        return klantSchadeclaimIndienenLabelIban;
    }

    public void setKlantSchadeclaimIndienenLabelIban(String klantSchadeclaimIndienenLabelIban) {
        this.klantSchadeclaimIndienenLabelIban = klantSchadeclaimIndienenLabelIban;
    }

    public String getKlantSchadeclaimIndienenPromptTextAmount() {
        return klantSchadeclaimIndienenPromptTextAmount;
    }

    public void setKlantSchadeclaimIndienenPromptTextAmount(String klantSchadeclaimIndienenPromptTextAmount) {
        this.klantSchadeclaimIndienenPromptTextAmount = klantSchadeclaimIndienenPromptTextAmount;
    }

    public String getKlantSchadeclaimIndienLabelAmount() {
        return klantSchadeclaimIndienLabelAmount;
    }

    public void setKlantSchadeclaimIndienLabelAmount(String klantSchadeclaimIndienLabelAmount) {
        this.klantSchadeclaimIndienLabelAmount = klantSchadeclaimIndienLabelAmount;
    }

    public String getKlantSchadeclaimIndienenLabelDisclaimer() {
        return klantSchadeclaimIndienenLabelDisclaimer;
    }

    public void setKlantSchadeclaimIndienenLabelDisclaimer(String klantSchadeclaimIndienenLabelDisclaimer) {
        this.klantSchadeclaimIndienenLabelDisclaimer = klantSchadeclaimIndienenLabelDisclaimer;
    }

    public String getKlantSchadeclaimIndienenButtonSubmitClaim() {
        return klantSchadeclaimIndienenButtonSubmitClaim;
    }

    public void setKlantSchadeclaimIndienenButtonSubmitClaim(String klantSchadeclaimIndienenButtonSubmitClaim) {
        this.klantSchadeclaimIndienenButtonSubmitClaim = klantSchadeclaimIndienenButtonSubmitClaim;
    }

    public String getKlantSchadeclaimIndienenStatusClaimsBtn() {
        return klantSchadeclaimIndienenStatusClaimsBtn;
    }

    public void setKlantSchadeclaimIndienenStatusClaimsBtn(String klantSchadeclaimIndienenStatusClaimsBtn) {
        this.klantSchadeclaimIndienenStatusClaimsBtn = klantSchadeclaimIndienenStatusClaimsBtn;
    }

    public String getKlantSchadeclaimIndienenStatusLuggageBtn() {
        return klantSchadeclaimIndienenStatusLuggageBtn;
    }

    public void setKlantSchadeclaimIndienenStatusLuggageBtn(String klantSchadeclaimIndienenStatusLuggageBtn) {
        this.klantSchadeclaimIndienenStatusLuggageBtn = klantSchadeclaimIndienenStatusLuggageBtn;
    }

    public String getInloggenCloseApp() {
        return inloggenCloseApp;
    }

    public void setInloggenCloseApp(String inloggenCloseApp) {
        this.inloggenCloseApp = inloggenCloseApp;
    }

    public String getInloggenButtonForgotPassword() {
        return inloggenButtonForgotPassword;
    }

    public void setInloggenButtonForgotPassword(String inloggenButtonForgotPassword) {
        this.inloggenButtonForgotPassword = inloggenButtonForgotPassword;
    }

    public String getInloggenButtonLogIn() {
        return inloggenButtonLogIn;
    }

    public void setInloggenButtonLogIn(String inloggenButtonLogIn) {
        this.inloggenButtonLogIn = inloggenButtonLogIn;
    }

    public String getInloggenPromptTextPassword() {
        return inloggenPromptTextPassword;
    }

    public void setInloggenPromptTextPassword(String inloggenPromptTextPassword) {
        this.inloggenPromptTextPassword = inloggenPromptTextPassword;
    }

    public String getInloggenLabelError() {
        return inloggenLabelError;
    }

    public void setInloggenLabelError(String inloggenLabelError) {
        this.inloggenLabelError = inloggenLabelError;
    }

    public String getUitloggenButtonInloggen() {
        return uitloggenButtonInloggen;
    }

    public void setUitloggenButtonInloggen(String uitloggenButtonInloggen) {
        this.uitloggenButtonInloggen = uitloggenButtonInloggen;
    }

    public String getUitloggenButtonApplicatieSluiten() {
        return uitloggenButtonApplicatieSluiten;
    }

    public void setUitloggenButtonApplicatieSluiten(String uitloggenButtonApplicatieSluiten) {
        this.uitloggenButtonApplicatieSluiten = uitloggenButtonApplicatieSluiten;
    }

    public String getUitloggenPromTextuBentUitgelogdTotZiens() {
        return uitloggenPromTextuBentUitgelogdTotZiens;
    }

    public void setUitloggenPromTextuBentUitgelogdTotZiens(String uitloggenPromTextuBentUitgelogdTotZiens) {
        this.uitloggenPromTextuBentUitgelogdTotZiens = uitloggenPromTextuBentUitgelogdTotZiens;
    }

    public String getMedewerkerVerwijdereAccountPromtextAccountVerwijderen() {
        return MedewerkerVerwijdereAccountPromtextAccountVerwijderen;
    }

    public void setMedewerkerVerwijdereAccountPromtextAccountVerwijderen(String medewerkerVerwijdereAccountPromtextAccountVerwijderen) {
        this.MedewerkerVerwijdereAccountPromtextAccountVerwijderen = medewerkerVerwijdereAccountPromtextAccountVerwijderen;
    }

    public String getMedewerkerVerwijdereAccountButtonBevestigen() {
        return medewerkerVerwijdereAccountButtonBevestigen;
    }

    public void setMedewerkerVerwijdereAccountButtonBevestigen(String MedewerkerVerwijdereAccountButtonBevestigen) {
        this.medewerkerVerwijdereAccountButtonBevestigen = MedewerkerVerwijdereAccountButtonBevestigen;
    }

    public String getMedewerkerVerwijdereAccountButtonAnnuleren() {
        return medewerkerVerwijderAccountButtonAnnuleren;
    }

    public void setMedewerkerVerwijdereAccountButtonAnnuleren(String medewerkerVerwijderAccountButtonAnnuleren) {
        this.medewerkerVerwijderAccountButtonAnnuleren = medewerkerVerwijderAccountButtonAnnuleren;
    }

    public String getMedewerkerProfielWijzigenPromptTextWachtwoordWijzigen() {
        return medewerkerProfielWijzigenPromptTextWachtwoordWijzigen;
    }

    public void setMedewerkerProfielWijzigenPromptTextWachtwoordWijzigen(String medewerkerProfielWijzigenPromptTextWachtwoordWijzigen) {
        this.medewerkerProfielWijzigenPromptTextWachtwoordWijzigen = medewerkerProfielWijzigenPromptTextWachtwoordWijzigen;
    }

    public String getMedewerkerProfielWijzigenPromptTextWachtwoordWijzigenHerhalen() {
        return medewerkerProfielWijzigenPromptTextWachtwoordWijzigenHerhalen;
    }

    public void setMedewerkerProfielWijzigenPromptTextWachtwoordWijzigenHerhalen(String medewerkerProfielWijzigenPrompttextWachtwoordWijzigenHerhalen) {
        this.medewerkerProfielWijzigenPromptTextWachtwoordWijzigenHerhalen = medewerkerProfielWijzigenPrompttextWachtwoordWijzigenHerhalen;
    }

    public String getMedewerkerProfielWijzigePromptTextProfielBewerken() {
        return medewerkerProfielWijzigePromptTextProfielBewerken;
    }

    public void steMedewerkerProfielWijzigePromptTextProfielBewerken(String medewerkerProfielWijzigePromptTextProfielBewerken) {
        this.medewerkerProfielWijzigePromptTextProfielBewerken = medewerkerProfielWijzigePromptTextProfielBewerken;
    }

    public String getMedewerkerProfielWijzigePromptTextOpslaan() {
        return medewerkerProfielWijzigePromptTextOpslaan;
    }

    public void setMedewerkerProfielWijzigePromptTextOpslaan(String medewerkerProfielWijzigePromptTextOpslaan) {
        this.medewerkerProfielWijzigePromptTextOpslaan = medewerkerProfielWijzigePromptTextOpslaan;
    }

    public String getMedewerkerProfielPromptTextProfiel() {
        return medewerkerProfielPromptTextProfiel;
    }

    public void setMedewerkerProfielPromptTextProfiel(String medewerkerProfielPromptTextProfiel) {
        this.medewerkerProfielPromptTextProfiel = medewerkerProfielPromptTextProfiel;
    }

    public String getMedewerkerProfielPromptTextEmailAdres() {
        return medewerkerProfielPromptTextEmailAdres;
    }

    public void setMedewerkerProfielPromptTextEmailAdres(String medewerkerProfielPromptTextEmailAdres) {
        this.medewerkerProfielPromptTextEmailAdres = medewerkerProfielPromptTextEmailAdres;
    }

    public String getMedewerkerProfielButtonWijzigWachtwoord() {
        return medewerkerProfielButtonWijzigWachtwoord;
    }

    public void setMedewerkerProfielButtonWijzigWachtwoord(String medewerkerProfielButtonWijzigWachtwoord) {
        this.medewerkerProfielButtonWijzigWachtwoord = medewerkerProfielButtonWijzigWachtwoord;
    }

    public String getMedewerkerProfielButtonVerwijderenAccount() {
        return medewerkerProfielButtonVerwijderenAccount;
    }

    public void setMedewerkerProfielButtonVerwijderenAccount(String medewerkerProfielButtonVerwijderenAccount) {
        this.medewerkerProfielButtonVerwijderenAccount = medewerkerProfielButtonVerwijderenAccount;
    }

    public String getMedewerkerMenuBarButtonProfiel() {
        return medewerkerMenuBarButtonProfiel;
    }

    public void setMedewerkerMenuBarButtonProfiel(String medewerkerMenuBarButtonProfiel) {
        this.medewerkerMenuBarButtonProfiel = medewerkerMenuBarButtonProfiel;
    }

    public String getMedewerkerMenuBarButtonUitloggen() {
        return medewerkerMenuBarButtonUitloggen;
    }

    public void setMedewerkerMenuBarButtonUitloggen(String medewerkerMenuBarButtonUitloggen) {
        this.medewerkerMenuBarButtonUitloggen = medewerkerMenuBarButtonUitloggen;
    }

    public String getMedewerkerKlantRegLabelVermisteBagageRegForm() {
        return medewerkerKlantRegLabelVermisteBagageRegForm;
    }

    public void setMedewerkerKlantRegLabelVermisteBagageRegForm(String medewerkerKlantRegLabelVermisteBagageRegForm) {
        this.medewerkerKlantRegLabelVermisteBagageRegForm = medewerkerKlantRegLabelVermisteBagageRegForm;
    }

    public String getMedewerkerKlantRegLabelReizigerInformatie() {
        return medewerkerKlantRegLabelReizigerInformatie;
    }

    public void setMedewerkerKlantRegLabelReizigerInformatie(String medewerkerKlantRegLabelReizigerInformatie) {
        this.medewerkerKlantRegLabelReizigerInformatie = medewerkerKlantRegLabelReizigerInformatie;
    }

    public String getMedewerkerKlantRegLabelBagageLabelInformatie() {
        return medewerkerKlantRegLabelBagageLabelInformatie;
    }

    public void setMedewerkerKlantRegLabelBagageLabelInformatie(String medewerkerKlantRegLabelBagageLabelInformatie) {
        this.medewerkerKlantRegLabelBagageLabelInformatie = medewerkerKlantRegLabelBagageLabelInformatie;
    }

    public String getMedewerkerKlantRegLabelBagageInformatie() {
        return medewerkerKlantRegLabelBagageInformatie;
    }

    public void setMedewerkerKlantRegLabelBagageInformatie(String medewerkerKlantRegLabelBagageInformatie) {
        this.medewerkerKlantRegLabelBagageInformatie = medewerkerKlantRegLabelBagageInformatie;
    }

    public String getMedewerkerKlantRegButtonSlaOpButtom() {
        return medewerkerKlantRegButtonSlaOpButtom;
    }

    public void setMedewerkerKlantRegButtonSlaOpButtom(String medewerkerKlantRegButtonSlaOpButtom) {
        this.medewerkerKlantRegButtonSlaOpButtom = medewerkerKlantRegButtonSlaOpButtom;
    }

    public String getMedewerkerKlantRegPromptTextLuchthavenText() {
        return medewerkerKlantRegPromptTextLuchthavenText;
    }

    public void setMedewerkerKlantRegPromptTextLuchthavenText(String medewerkerKlantRegPromptTextLuchthavenText) {
        this.medewerkerKlantRegPromptTextLuchthavenText = medewerkerKlantRegPromptTextLuchthavenText;
    }

    public String getMedewerkerKlantRegPromptTextVoornaamText() {
        return medewerkerKlantRegPromptTextVoornaamText;
    }

    public void setMedewerkerKlantRegPromptTextVoornaamText(String medewerkerKlantRegPromptTextVoornaamText) {
        this.medewerkerKlantRegPromptTextVoornaamText = medewerkerKlantRegPromptTextVoornaamText;
    }

    public String getMedewerkerKlantRegPromptTextTussenvoegselText() {
        return medewerkerKlantRegPromptTextTussenvoegselText;
    }

    public void setMedewerkerKlantRegPromptTextTussenvoegselText(String medewerkerKlantRegPromptTextTussenvoegselText) {
        this.medewerkerKlantRegPromptTextTussenvoegselText = medewerkerKlantRegPromptTextTussenvoegselText;
    }

    public String getMedewerkerKlantRegPromptTextAchternaamText() {
        return medewerkerKlantRegPromptTextAchternaamText;
    }

    public void setMedewerkerKlantRegPromptTextAchternaamText(String medewerkerKlantRegPromptTextAchternaamText) {
        this.medewerkerKlantRegPromptTextAchternaamText = medewerkerKlantRegPromptTextAchternaamText;
    }

    public String getMedewerkerKlantRegPromptTextAdresText() {
        return medewerkerKlantRegPromptTextAdresText;
    }

    public void setMedewerkerKlantRegPromptTextAdresText(String medewerkerKlantRegPromptTextAdresText) {
        this.medewerkerKlantRegPromptTextAdresText = medewerkerKlantRegPromptTextAdresText;
    }

    public String getMedewerkerKlantRegPromptTextwoonplaatsText() {
        return medewerkerKlantRegPromptTextwoonplaatsText;
    }

    public void setMedewerkerKlantRegPromptTextwoonplaatsText(String medewerkerKlantRegPromptTextwoonplaatsText) {
        this.medewerkerKlantRegPromptTextwoonplaatsText = medewerkerKlantRegPromptTextwoonplaatsText;
    }

    public String getMedewerkerKlantRegPromptTextPostcodeText() {
        return medewerkerKlantRegPromptTextPostcodeText;
    }

    public void setMedewerkerKlantRegPromptTextPostcodeText(String medewerkerKlantRegPromptTextPostcodeText) {
        this.medewerkerKlantRegPromptTextPostcodeText = medewerkerKlantRegPromptTextPostcodeText;
    }

    public String getMedewerkerKlantRegPromptTextlandText() {
        return medewerkerKlantRegPromptTextlandText;
    }

    public void setMedewerkerKlantRegPromptTextlandText(String medewerkerKlantRegPromptTextlandText) {
        this.medewerkerKlantRegPromptTextlandText = medewerkerKlantRegPromptTextlandText;
    }

    public String getMedewerkerKlantRegPromptTexttelefoonText() {
        return medewerkerKlantRegPromptTexttelefoonText;
    }

    public void setMedewerkerKlantRegPromptTexttelefoonText(String medewerkerKlantRegPromptTexttelefoonText) {
        this.medewerkerKlantRegPromptTexttelefoonText = medewerkerKlantRegPromptTexttelefoonText;
    }

    public String getMedewerkerKlantRegPromptTextEmailText() {
        return medewerkerKlantRegPromptTextEmailText;
    }

    public void setMedewerkerKlantRegPromptTextEmailText(String medewerkerKlantRegPromptTextEmailText) {
        this.medewerkerKlantRegPromptTextEmailText = medewerkerKlantRegPromptTextEmailText;
    }

    public String getMedewerkerKlantRegPromptTextLabelnummerText() {
        return medewerkerKlantRegPromptTextLabelnummerText;
    }

    public void setMedewerkerKlantRegPromptTextLabelnummerText(String medewerkerKlantRegPromptTextLabelnummerText) {
        this.medewerkerKlantRegPromptTextLabelnummerText = medewerkerKlantRegPromptTextLabelnummerText;
    }

    public String getMedewerkerKlantRegPrompttextVluchtnummerText() {
        return medewerkerKlantRegPrompttextVluchtnummerText;
    }

    public void setMedewerkerKlantRegPrompttextVluchtnummerText(String medewerkerKlantRegPrompttextVluchtnummerText) {
        this.medewerkerKlantRegPrompttextVluchtnummerText = medewerkerKlantRegPrompttextVluchtnummerText;
    }

    public String getMedewerkerKlantRegPromptTextBestemmingText() {
        return medewerkerKlantRegPromptTextBestemmingText;
    }

    public void setMedewerkerKlantRegPromptTextBestemmingText(String medewerkerKlantRegPromptTextBestemmingText) {
        this.medewerkerKlantRegPromptTextBestemmingText = medewerkerKlantRegPromptTextBestemmingText;
    }

    public String getMedewerkerKlantRegPromptTextTypeText() {
        return medewerkerKlantRegPromptTextTypeText;
    }

    public void setMedewerkerKlantRegPromptTextTypeText(String medewerkerKlantRegPromptTextTypeText) {
        this.medewerkerKlantRegPromptTextTypeText = medewerkerKlantRegPromptTextTypeText;
    }

    public String getMedewerkerKlantRegPromptTextMerkText() {
        return medewerkerKlantRegPromptTextMerkText;
    }

    public void setMedewerkerKlantRegPromptTextMerkText(String medewerkerKlantRegPromptTextMerkText) {
        this.medewerkerKlantRegPromptTextMerkText = medewerkerKlantRegPromptTextMerkText;
    }

    public String getMedewerkerKlantRegPromptTextKleurText() {
        return medewerkerKlantRegPromptTextKleurText;
    }

    public void setMedewerkerKlantRegPromptTextKleurText(String medewerkerKlantRegPromptTextKleurText) {
        this.medewerkerKlantRegPromptTextKleurText = medewerkerKlantRegPromptTextKleurText;
    }

    public String getMedewerkerKlantRegPromptTextBijzondereKenmerkenText() {
        return medewerkerKlantRegPromptTextBijzondereKenmerkenText;
    }

    public void setMedewerkerKlantRegPromptTextBijzondereKenmerkenText(String medewerkerKlantRegPromptTextBijzondereKenmerkenText) {
        this.medewerkerKlantRegPromptTextBijzondereKenmerkenText = medewerkerKlantRegPromptTextBijzondereKenmerkenText;
    }

    public String getMedewerkerKlantRegPromptTextVoornaamTxt() {
        return medewerkerKlantRegPromptTextVoornaamTxt;
    }

    public void setMedewerkerKlantRegPromptTextVoornaamTxt(String medewerkerKlantRegPromptTextVoornaamTxt) {
        this.medewerkerKlantRegPromptTextVoornaamTxt = medewerkerKlantRegPromptTextVoornaamTxt;
    }

    public String getMedewerkerKlantRegPromptTextAdresTxt() {
        return medewerkerKlantRegPromptTextAdresTxt;
    }

    public void setMedewerkerKlantRegPromptTextAdresTxt(String medewerkerKlantRegPromptTextAdresTxt) {
        this.medewerkerKlantRegPromptTextAdresTxt = medewerkerKlantRegPromptTextAdresTxt;
    }

    public String getMedewerkerKlantRegPromptTextWoonplaatsTxt() {
        return medewerkerKlantRegPromptTextWoonplaatsTxt;
    }

    public void setMedewerkerKlantRegPromptTextWoonplaatsTxt(String medewerkerKlantRegPromptTextWoonplaatsTxt) {
        this.medewerkerKlantRegPromptTextWoonplaatsTxt = medewerkerKlantRegPromptTextWoonplaatsTxt;
    }

    public String getMedewerkerKlantRegPromptTextPostcodeTxt() {
        return medewerkerKlantRegPromptTextPostcodeTxt;
    }

    public void setMedewerkerKlantRegPromptTextPostcodeTxt(String medewerkerKlantRegPromptTextPostcodeTxt) {
        this.medewerkerKlantRegPromptTextPostcodeTxt = medewerkerKlantRegPromptTextPostcodeTxt;
    }

    public String getMedewerkerKlantRegPromptTextLandTxt() {
        return medewerkerKlantRegPromptTextLandTxt;
    }

    public void setMedewerkerKlantRegPromptTextLandTxt(String medewerkerKlantRegPromptTextLandTxt) {
        this.medewerkerKlantRegPromptTextLandTxt = medewerkerKlantRegPromptTextLandTxt;
    }

    public String getMedewerkerKlantRegPromptTextTelefoonTxt() {
        return medewerkerKlantRegPromptTextTelefoonTxt;
    }

    public void setMedewerkerKlantRegPromptTextTelefoonTxt(String medewerkerKlantRegPromptTextTelefoonTxt) {
        this.medewerkerKlantRegPromptTextTelefoonTxt = medewerkerKlantRegPromptTextTelefoonTxt;
    }

    public String getMedewerkerKlantRegPromptTextEmailTxt() {
        return medewerkerKlantRegPromptTextEmailTxt;
    }

    public void setMedewerkerKlantRegPromptTextEmailTxt(String medewerkerKlantRegPromptTextEmailTxt) {
        this.medewerkerKlantRegPromptTextEmailTxt = medewerkerKlantRegPromptTextEmailTxt;
    }

    public String getMedewerkerKlantRegPromptTextLabelnummerTxt() {
        return medewerkerKlantRegPromptTextLabelnummerTxt;
    }

    public void setMedewerkerKlantRegPromptTextLabelnummerTxt(String medewerkerKlantRegPromptTextLabelnummerTxt) {
        this.medewerkerKlantRegPromptTextLabelnummerTxt = medewerkerKlantRegPromptTextLabelnummerTxt;
    }

    public String getMedewerkerKlantRegPromptTextVluchtnummerTxt() {
        return medewerkerKlantRegPromptTextVluchtnummerTxt;
    }

    public void setMedewerkerKlantRegPromptTextVluchtnummerTxt(String medewerkerKlantRegPromptTextVluchtnummerTxt) {
        this.medewerkerKlantRegPromptTextVluchtnummerTxt = medewerkerKlantRegPromptTextVluchtnummerTxt;
    }

    public String getMedewerkerKlantRegPromptTextBestemmingTxt() {
        return medewerkerKlantRegPromptTextBestemmingTxt;
    }

    public void setMedewerkerKlantRegPromptTextBestemmingTxt(String medewerkerKlantRegPromptTextBestemmingTxt) {
        this.medewerkerKlantRegPromptTextBestemmingTxt = medewerkerKlantRegPromptTextBestemmingTxt;
    }

    public String getMedewerkerKlantRegPromptTextTypeTxt() {
        return medewerkerKlantRegPromptTextTypeTxt;
    }

    public void setMedewerkerKlantRegPromptTextTypeTxt(String medewerkerKlantRegPromptTextTypeTxt) {
        this.medewerkerKlantRegPromptTextTypeTxt = medewerkerKlantRegPromptTextTypeTxt;
    }

    public String getMedewerkerKlantRegPromptTextKleurTxt() {
        return medewerkerKlantRegPromptTextKleurTxt;
    }

    public void setMedewerkerKlantRegPromptTextKleurTxt(String medewerkerKlantRegPromptTextKleurTxt) {
        this.medewerkerKlantRegPromptTextKleurTxt = medewerkerKlantRegPromptTextKleurTxt;
    }

    public String getMedewerkerKlantRegPromptTextMerkTxt() {
        return medewerkerKlantRegPromptTextMerkTxt;
    }

    public void setMedewerkerKlantRegPromptTextMerkTxt(String medewerkerKlantRegPromptTextMerkTxt) {
        this.medewerkerKlantRegPromptTextMerkTxt = medewerkerKlantRegPromptTextMerkTxt;
    }

    public String getMedewerkerKlantRegPromptTextTussenvoegselTxt() {
        return medewerkerKlantRegPromptTextTussenvoegselTxt;
    }

    public void setMedewerkerKlantRegPromptTextTussenvoegselTxt(String medewerkerKlantRegPromptTextTussenvoegselTxt) {
        this.medewerkerKlantRegPromptTextTussenvoegselTxt = medewerkerKlantRegPromptTextTussenvoegselTxt;
    }

    public String getMedewerkerKlantRegPromptTextAchternaamTxt() {
        return medewerkerKlantRegPromptTextAchternaamTxt;
    }

    public void setMedewerkerKlantRegPromptTextAchternaamTxt(String medewerkerKlantRegPromptTextAchternaamTxt) {
        this.medewerkerKlantRegPromptTextAchternaamTxt = medewerkerKlantRegPromptTextAchternaamTxt;
    }

    public String getMedewerkerklantRegPromptTextBijzondereKenmerkenTxt() {
        return medewerkerklantRegPromptTextBijzondereKenmerkenTxt;
    }

    public void setMedewerkerklantRegPromptTextBijzondereKenmerkenTxt(String medewerkerklantRegPromptTextBijzondereKenmerkenTxt) {
        this.medewerkerklantRegPromptTextBijzondereKenmerkenTxt = medewerkerklantRegPromptTextBijzondereKenmerkenTxt;
    }

    public String getMedewerkerKlantRegLabelInfoLbl() {
        return medewerkerKlantRegLabelInfoLbl;
    }

    public void setMedewerkerKlantRegLabelInfoLbl(String medewerkerKlantRegLabelInfoLbl) {
        this.medewerkerKlantRegLabelInfoLbl = medewerkerKlantRegLabelInfoLbl;
    }

    public String getMedewerkerHomeButtonzoekenBagage() {
        return medewerkerHomeButtonzoekenBagage;
    }

    public void setMedewerkerHomeButtonzoekenBagage(String medewerkerHomeButtonzoekenBagage) {
        this.medewerkerHomeButtonzoekenBagage = medewerkerHomeButtonzoekenBagage;
    }

    public String getMedewerkerHomeButtonKlantRegVerBagage() {
        return medewerkerHomeButtonKlantRegVerBagage;
    }

    public void setMedewerkerHomeButtonKlantRegVerBagage(String medewerkerHomeButtonKlantRegVerBagage) {
        this.medewerkerHomeButtonKlantRegVerBagage = medewerkerHomeButtonKlantRegVerBagage;
    }

    public String getMedewerkerHomeButtonRegistratieGevBagage() {
        return medewerkerHomeButtonRegistratieGevBagage;
    }

    public void setMedewerkerHomeButtonRegistratieGevBagage(String medewerkerHomeButtonRegistratieGevBagage) {
        this.medewerkerHomeButtonRegistratieGevBagage = medewerkerHomeButtonRegistratieGevBagage;
    }

    public String getMedewerkerHomeButtonBagageUpdate() {
        return medewerkerHomeButtonBagageBewerker;
    }

    public void setMedewerkerHomeButtonBagageBewerker(String medewerkerHomeButtonBagageBewerker) {
        this.medewerkerHomeButtonBagageBewerker = medewerkerHomeButtonBagageBewerker;
    }

    public String getMedewerkerGevondenBagageLabelGevondenBagRegFrom() {
        return medewerkerGevondenBagageLabelGevondenBagRegFrom;
    }

    public void setMedewerkerGevondenBagageLabelGevondenBagRegFrom(String medewerkerGevondenBagageLabelGevondenBagRegFrom) {
        this.medewerkerGevondenBagageLabelGevondenBagRegFrom = medewerkerGevondenBagageLabelGevondenBagRegFrom;
    }

    public String getMedewerkerGevondenBagageLabelBagageLblInformatieText() {
        return medewerkerGevondenBagageLabelBagageLblInformatieText;
    }

    public void setMedewerkerGevondenBagageLabelBagageLblInformatieText(String medewerkerGevondenBagageLabelBagageLblInformatieText) {
        this.medewerkerGevondenBagageLabelBagageLblInformatieText = medewerkerGevondenBagageLabelBagageLblInformatieText;
    }

    public String getmedewerkerGevondenBagageLabelBagageInformatieText() {
        return medewerkerGevondenBagageLabelBagageInformatieText;
    }

    public void setMedewerkerGevondenBagageLabelBagageInformatieText(String medewerkerGevondenBagageLabelBagageInformatieText) {
        this.medewerkerGevondenBagageLabelBagageInformatieText = medewerkerGevondenBagageLabelBagageInformatieText;
    }

    public String getMedewerkerGevondenBagageLabelFotoUploadenBtn() {
        return medewerkerGevondenBagageLabelFotoUploadenBtn;
    }

    public void setMedewerkerGevondenBagageLabelFotoUploadenBtn(String medewerkerGevondenBagageLabelFotoUploadenBtn) {
        this.medewerkerGevondenBagageLabelFotoUploadenBtn = medewerkerGevondenBagageLabelFotoUploadenBtn;
    }

    public String getMedewerkerGevondenBagagePromptTextLuchthavenText() {
        return medewerkerGevondenBagagePromptTextLuchthavenText;
    }

    public void setMedewerkerGevondenBagagePromptTextLuchthavenText(String medewerkerGevondenBagagePromptTextLuchthavenText) {
        this.medewerkerGevondenBagagePromptTextLuchthavenText = medewerkerGevondenBagagePromptTextLuchthavenText;
    }

    public String getMedewerkerGevondenBagagePromptTextLabelnummerText() {
        return medewerkerGevondenBagagePromptTextLabelnummerText;
    }

    public void setmedewerkerGevondenBagagePromptTextLabelnummerText(String medewerkerGevondenBagagePromptTextLabelnummerText) {
        this.medewerkerGevondenBagagePromptTextLabelnummerText = medewerkerGevondenBagagePromptTextLabelnummerText;
    }

    public String getMedewerkerGevondenBagagePromptTextVluchtnummerText() {
        return medewerkerGevondenBagagePromptTextVluchtnummerText;
    }

    public void setMedewerkerGevondenBagagePromptTextVluchtnummerText(String medewerkerGevondenBagagePromptTextVluchtnummerText) {
        this.medewerkerGevondenBagagePromptTextVluchtnummerText = medewerkerGevondenBagagePromptTextVluchtnummerText;
    }

    public String getMedewerkerGevondenBagagePromptTextBestemmingText() {
        return medewerkerGevondenBagagePromptTextBestemmingText;
    }

    public void setMedewerkerGevondenBagagePromptTextBestemmingText(String medewerkerGevondenBagagePromptTextBestemmingText) {
        this.medewerkerGevondenBagagePromptTextBestemmingText = medewerkerGevondenBagagePromptTextBestemmingText;
    }

    public String getMedewerkerGevondenBagagePromptTextTypeText() {
        return medewerkerGevondenBagagePromptTextTypeText;
    }

    public void setMedewerkerGevondenBagagePromptTextTypeText(String medewerkerGevondenBagagePromptTextTypeText) {
        this.medewerkerGevondenBagagePromptTextTypeText = medewerkerGevondenBagagePromptTextTypeText;
    }

    public String getMedewerkerGevondenBagagePromptTextMerkText() {
        return medewerkerGevondenBagagePromptTextMerkText;
    }

    public void setMedewerkerGevondenBagagePromptTextMerkText(String medewerkerGevondenBagagePromptTextMerkText) {
        this.medewerkerGevondenBagagePromptTextMerkText = medewerkerGevondenBagagePromptTextMerkText;
    }

    public String getMedewerkerGevondenBagagePromptTextKleurText() {
        return medewerkerGevondenBagagePromptTextKleurText;
    }

    public void setMedewerkerGevondenBagagePromptTextKleurText(String medewerkerGevondenBagagePromptTextKleurText) {
        this.medewerkerGevondenBagagePromptTextKleurText = medewerkerGevondenBagagePromptTextKleurText;
    }

    public String getMedewerkerGevondenBagagePromptTextbijzondereKenmerkenText() {
        return medewerkerGevondenBagagePromptTextbijzondereKenmerkenText;
    }

    public void setMedewerkerGevondenBagagePromptTextbijzondereKenmerkenText(String medewerkerGevondenBagagePromptTextbijzondereKenmerkenText) {
        this.medewerkerGevondenBagagePromptTextbijzondereKenmerkenText = medewerkerGevondenBagagePromptTextbijzondereKenmerkenText;
    }

    public String getMedewerkerGevondenBagageButtonSlaOpBtn() {
        return medewerkerGevondenBagageButtonSlaOpBtn;
    }

    public void setMedewerkerGevondenBagageButtonSlaOpBtn(String medewerkerGevondenBagageButtonSlaOpBtn) {
        this.medewerkerGevondenBagageButtonSlaOpBtn = medewerkerGevondenBagageButtonSlaOpBtn;
    }

    public String getMedewerkerGevondenBagageLabelInfoFotolbl() {
        return medewerkerGevondenBagageLabelInfoFotolbl;
    }

    public void setMedewerkerGevondenBagageLabelInfoFotolbl(String medewerkerGevondenBagageLabelInfoFotolbl) {
        this.medewerkerGevondenBagageLabelInfoFotolbl = medewerkerGevondenBagageLabelInfoFotolbl;
    }

    public String getMedewerkerGevondenBagagePromptTextLabelnummerTxt() {
        return medewerkerGevondenBagagePromptTextLabelnummerTxt;
    }

    public void setMedewerkerGevondenBagagePromptTextLabelnummerTxt(String medewerkerGevondenBagagePromptTextLabelnummerTxt) {
        this.medewerkerGevondenBagagePromptTextLabelnummerTxt = medewerkerGevondenBagagePromptTextLabelnummerTxt;
    }

    public String getMedewerkerGevondenBagagePromptTextVluchtnummerTxt() {
        return medewerkerGevondenBagagePromptTextVluchtnummerTxt;
    }

    public void setMedewerkerGevondenBagagePromptTextVluchtnummerTxt(String medewerkerGevondenBagagePromptTextVluchtnummerTxt) {
        this.medewerkerGevondenBagagePromptTextVluchtnummerTxt = medewerkerGevondenBagagePromptTextVluchtnummerTxt;
    }

    public String getMedewerkerGevondenBagagePromptTextBestemmingTxt() {
        return medewerkerGevondenBagagePromptTextBestemmingTxt;
    }

    public void setMedewerkerGevondenBagagePromptTextBestemmingTxt(String medewerkerGevondenBagagePromptTextBestemmingTxt) {
        this.medewerkerGevondenBagagePromptTextBestemmingTxt = medewerkerGevondenBagagePromptTextBestemmingTxt;
    }

    public String getMedewerkerGevondenBagagePromptTextTypeTxt() {
        return medewerkerGevondenBagagePromptTextTypeTxt;
    }

    public void setMedewerkerGevondenBagagePromptTextTypeTxt(String medewerkerGevondenBagagePromptTextTypeTxt) {
        this.medewerkerGevondenBagagePromptTextTypeTxt = medewerkerGevondenBagagePromptTextTypeTxt;
    }

    public String getMedewerkerGevondenBagagePromptTextKleurTxt() {
        return medewerkerGevondenBagagePromptTextKleurTxt;
    }

    public void setMedewerkerGevondenBagagePromptTextKleurTxt(String medewerkerGevondenBagagePromptTextKleurTxt) {
        this.medewerkerGevondenBagagePromptTextKleurTxt = medewerkerGevondenBagagePromptTextKleurTxt;
    }

    public String getMedewerkerGevondenBagagePromptTextMerkTxt() {
        return medewerkerGevondenBagagePromptTextMerkTxt;
    }

    public void setMedewerkerGevondenBagagePromptTextMerkTxt(String medewerkerGevondenBagagePromptTextMerkTxt) {
        this.medewerkerGevondenBagagePromptTextMerkTxt = medewerkerGevondenBagagePromptTextMerkTxt;
    }

    public String getMedewerkerGevondenBagagePromptTextBijzondereKenmerkenTxt() {
        return medewerkerGevondenBagagePromptTextBijzondereKenmerkenTxt;
    }

    public void setMedewerkerGevondenBagagePromptTextBijzondereKenmerkenTxt(String medewerkerGevondenBagagePromptTextBijzondereKenmerkenTxt) {
        this.medewerkerGevondenBagagePromptTextBijzondereKenmerkenTxt = medewerkerGevondenBagagePromptTextBijzondereKenmerkenTxt;
    }

    public String getMedewerkerBagageZoekenLabelAlleKoffers() {
        return medewerkerBagageZoekenLabelAlleKoffers;
    }

    public void setMedewerkerBagageZoekenLabelAlleKoffers(String medewerkerBagageZoekenLabelAlleKoffers) {
        this.medewerkerBagageZoekenLabelAlleKoffers = medewerkerBagageZoekenLabelAlleKoffers;
    }

    public String getMedewerkerBagageZoekenLabelZoekenBagage() {
        return medewerkerBagageZoekenLabelZoekenBagage;
    }

    public void setmedewerkerBagageZoekenLabelZoekenBagage(String medewerkerBagageZoekenLabelZoekenBagage) {
        this.medewerkerBagageZoekenLabelZoekenBagage = medewerkerBagageZoekenLabelZoekenBagage;
    }

    public String getMedewerkerBagageZoekenButtonZoekenBtn() {
        return medewerkerBagageZoekenButtonZoekenBtn;
    }

    public void setMedewerkerBagageZoekenButtonZoekenBtn(String medewerkerBagageZoekenButtonZoekenBtn) {
        this.medewerkerBagageZoekenButtonZoekenBtn = medewerkerBagageZoekenButtonZoekenBtn;
    }

    public String getMedewerkerBagageZoekenButtonLaadAlleBagageBtn() {
        return medewerkerBagageZoekenButtonLaadAlleBagageBtn;
    }

    public void setMedewerkerBagageZoekenButtonLaadAlleBagageBtn(String medewerkerBagageZoekenButtonLaadAlleBagageBtn) {
        this.medewerkerBagageZoekenButtonLaadAlleBagageBtn = medewerkerBagageZoekenButtonLaadAlleBagageBtn;
    }

    public String getMedewerkerBagageZoekenPromptTextLuchthaven() {
        return medewerkerBagageZoekenPromptTextLuchthaven;
    }

    public void setMedewerkerBagageZoekenPromptTextLuchthaven(String medewerkerBagageZoekenPromptTextLuchthaven) {
        this.medewerkerBagageZoekenPromptTextLuchthaven = medewerkerBagageZoekenPromptTextLuchthaven;
    }

    public String getMedewerkerBagageZoekenPromptTextVluchtnummer() {
        return medewerkerBagageZoekenPromptTextVluchtnummer;
    }

    public void setMedewerkerBagageZoekenPromptTextVluchtnummer(String medewerkerBagageZoekenPromptTextVluchtnummer) {
        this.medewerkerBagageZoekenPromptTextVluchtnummer = medewerkerBagageZoekenPromptTextVluchtnummer;
    }

    public String getMedewerkerBagageZoekenPromptTextLabelnummer() {
        return medewerkerBagageZoekenPromptTextLabelnummer;
    }

    public void setMedewerkerBagageZoekenPromptTextLabelnummer(String medewerkerBagageZoekenPromptTextLabelnummer) {
        this.medewerkerBagageZoekenPromptTextLabelnummer = medewerkerBagageZoekenPromptTextLabelnummer;
    }

    public String getMedewerkerBagageZoekenPromptTextType() {
        return medewerkerBagageZoekenPromptTextType;
    }

    public void setMedewerkerBagageZoekenPromptTextType(String medewerkerBagageZoekenPromptTextType) {
        this.medewerkerBagageZoekenPromptTextType = medewerkerBagageZoekenPromptTextType;
    }

    public String getMedewerkerBagageZoekenPromptTextMerk() {
        return medewerkerBagageZoekenPromptTextMerk;
    }

    public void setMedewerkerBagageZoekenPromptTextMerk(String medewerkerBagageZoekenPromptTextMerk) {
        this.medewerkerBagageZoekenPromptTextMerk = medewerkerBagageZoekenPromptTextMerk;
    }

    public String getMedewerkerBagageZoekenPromptTextKleur() {
        return medewerkerBagageZoekenPromptTextKleur;
    }

    public void setMedewerkerBagageZoekenPromptTextKleur(String medewerkerBagageZoekenPromptTextKleur) {
        this.medewerkerBagageZoekenPromptTextKleur = medewerkerBagageZoekenPromptTextKleur;
    }

    public String getMedewerkerBagageZoekenPromptTextBijzondereKenmerken() {
        return medewerkerBagageZoekenPromptTextBijzondereKenmerken;
    }

    public void setMedewerkerBagageZoekenPromptTextBijzondereKenmerken(String medewerkerBagageZoekenPromptTextBijzondereKenmerken) {
        this.medewerkerBagageZoekenPromptTextBijzondereKenmerken = medewerkerBagageZoekenPromptTextBijzondereKenmerken;
    }

    public String getMedewerkerBagageZoekenPromptTextBestemming() {
        return medewerkerBagageZoekenPromptTextBestemming;
    }

    public void setMedewerkerBagageZoekenPromptTextBestemming(String medewerkerBagageZoekenPromptTextBestemming) {
        this.medewerkerBagageZoekenPromptTextBestemming = medewerkerBagageZoekenPromptTextBestemming;
    }

    public String getMedewerkerBagageZoekenPromptTextIdentificatienummer() {
        return medewerkerBagageZoekenPromptTextIdentificatienummer;
    }

    public void setMedewerkerBagageZoekenPromptTextIdentificatienummer(String medewerkerBagageZoekenPromptTextIdentificatienummer) {
        this.medewerkerBagageZoekenPromptTextIdentificatienummer = medewerkerBagageZoekenPromptTextIdentificatienummer;
    }

    public String getMedewerkerBagageZoekenPromptTextZoekterm() {
        return medewerkerBagageZoekenPromptTextZoekterm;
    }

    public void setMedewerkerBagageZoekenPromptTextZoekterm(String medewerkerBagageZoekenPromptTextZoekterm) {
        this.medewerkerBagageZoekenPromptTextZoekterm = medewerkerBagageZoekenPromptTextZoekterm;
    }

    public String getMedewerkerBagageZoekenPromptTextKenmerkenComboBox() {
        return medewerkerBagageZoekenPromptTextKenmerkenComboBox;
    }

    public void setMedewerkerBagageZoekenPromptTextKenmerkenComboBox(String medewerkerBagageZoekenPromptTextKenmerkenComboBox) {
        this.medewerkerBagageZoekenPromptTextKenmerkenComboBox = medewerkerBagageZoekenPromptTextKenmerkenComboBox;
    }

    public String getMedewerkerBagageZoekenButtonMatchBtn() {
        return medewerkerBagageZoekenButtonMatchBtn;
    }

    public void setMedewerkerBagageZoekenButtonMatchBtn(String medewerkerBagageZoekenButtonMatchBtn) {
        this.medewerkerBagageZoekenButtonMatchBtn = medewerkerBagageZoekenButtonMatchBtn;
    }

    public String getMedewerkerBagageZoekenLabelKlantLbl() {
        return medewerkerBagageZoekenLabelKlantLbl;
    }

    public void setMedewerkerBagageZoekenLabelKlantLbl(String medewerkerBagageZoekenLabelKlantLbl) {
        this.medewerkerBagageZoekenLabelKlantLbl = medewerkerBagageZoekenLabelKlantLbl;
    }

    public String getMedewerkerBagageZoekenLabelTypeLbl() {
        return medewerkerBagageZoekenLabelTypeLbl;
    }

    public void setMedewerkerBagageZoekenLabelTypeLbl(String medewerkerBagageZoekenLabelTypeLbl) {
        this.medewerkerBagageZoekenLabelTypeLbl = medewerkerBagageZoekenLabelTypeLbl;
    }

    public String getMedewerkerBagageZoekenLabelMerkLbl() {
        return medewerkerBagageZoekenLabelMerkLbl;
    }

    public void setMedewerkerBagageZoekenLabelMerkLbl(String medewerkerBagageZoekenLabelMerkLbl) {
        this.medewerkerBagageZoekenLabelMerkLbl = medewerkerBagageZoekenLabelMerkLbl;
    }

    public String getMedewerkerBagageZoekenLabelKleurLbl() {
        return medewerkerBagageZoekenLabelKleurLbl;
    }

    public void setMedewerkerBagageZoekenLabelKleurLbl(String medewerkerBagageZoekenLabelKleurLbl) {
        this.medewerkerBagageZoekenLabelKleurLbl = medewerkerBagageZoekenLabelKleurLbl;
    }

    public String getMedewerkerBagageZoekenLabelvluchtnummerLbl() {
        return medewerkerBagageZoekenLabelVluchtnummerLbl;
    }

    public void setMedewerkerBagageZoekenLabelvluchtnummerLbl(String medewerkerBagageZoekenLabelVluchtnummerLbl) {
        this.medewerkerBagageZoekenLabelVluchtnummerLbl = medewerkerBagageZoekenLabelVluchtnummerLbl;
    }

    public String getMedewerkerBagageZoekenLabelLabelnrLbl() {
        return medewerkerBagageZoekenLabelLabelnrLbl;
    }

    public void SetMedewerkerBagageZoekenLabelLabelnrLbl(String medewerkerBagageZoekenLabelLabelnrLbl) {
        this.medewerkerBagageZoekenLabelLabelnrLbl = medewerkerBagageZoekenLabelLabelnrLbl;
    }

    public String getMedewerkerBagageZoekenLabelBijzondereKenmerkenLbl() {
        return medewerkerbagageZoekenLabelBijzondereKenmerkenLbl;
    }

    public void setMedewerkerbagageZoekenLabelBijzondereKenmerkenLbl(String medewerkerbagageZoekenLabelBijzondereKenmerkenLbl) {
        this.medewerkerbagageZoekenLabelBijzondereKenmerkenLbl = medewerkerbagageZoekenLabelBijzondereKenmerkenLbl;
    }

    public String getMedewerkerBagageUpdateLabeltUpdateBagage() {
        return medewerkerBagageUpdateLabeltUpdateBagage;
    }

    public void setMedewerkerBagageUpdateLabeltUpdateBagage(String medewerkerBagageUpdateLabeltUpdateBagage) {
        this.medewerkerBagageUpdateLabeltUpdateBagage = medewerkerBagageUpdateLabeltUpdateBagage;
    }

    public String getMedewerkerBagageUpdateLabelBagageLabelInformatie() {
        return medewerkerBagageUpdateLabelBagageLabelInformatie;
    }

    public void setMedewerkerBagageUpdateLabelBagageLabelInformatie(String medewerkerBagageUpdateLabelBagageLabelInformatie) {
        this.medewerkerBagageUpdateLabelBagageLabelInformatie = medewerkerBagageUpdateLabelBagageLabelInformatie;
    }

    public String getMedewerkerBagageUpdateLabelBagageInformatie() {
        return medewerkerBagageUpdateLabelBagageInformatie;
    }

    public void setMedewerkerBagageUpdateLabelBagageInformatie(String medewerkerBagageUpdateLabelBagageInformatie) {
        this.medewerkerBagageUpdateLabelBagageInformatie = medewerkerBagageUpdateLabelBagageInformatie;
    }

    public String getMedewerkerBagageUpdateButtonZoekenBtn() {
        return medewerkerBagageUpdateButtonZoekenBtn;
    }

    public void setMedewerkerBagageUpdateButtonZoekenBtn(String medewerkerBagageUpdateButtonZoekenBtn) {
        this.medewerkerBagageUpdateButtonZoekenBtn = medewerkerBagageUpdateButtonZoekenBtn;
    }

    public String getMedewerkerBagageUpdatePromptTextType() {
        return medewerkerBagageUpdatePromptTextType;
    }

    public void setMedewerkerBagageUpdatePromptTextType(String medewerkerBagageUpdatePromptTextType) {
        this.medewerkerBagageUpdatePromptTextType = medewerkerBagageUpdatePromptTextType;
    }

    public String getMedewerkerBagageUpdatePromptTextMerk() {
        return medewerkerBagageUpdatePromptTextMerk;
    }

    public void setMedewerkerBagageUpdatePromptTextMerk(String medewerkerBagageUpdatePromptTextMerk) {
        this.medewerkerBagageUpdatePromptTextMerk = medewerkerBagageUpdatePromptTextMerk;
    }

    public String getMedewerkerBagageUpdatePromptTextBagageId() {
        return medewerkerBagageUpdatePromptTextBagageId;
    }

    public void setMedewerkerBagageUpdatePromptTextBagageId(String medewerkerBagageUpdatePromptTextBagageId) {
        this.medewerkerBagageUpdatePromptTextBagageId = medewerkerBagageUpdatePromptTextBagageId;
    }

    public String getMedewerkerBagageUpdatePromptTextKleur() {
        return medewerkerBagageUpdatePromptTextKleur;
    }

    public void setMedewerkerBagageUpdatePromptTextKleur(String medewerkerBagageUpdatePromptTextKleur) {
        this.medewerkerBagageUpdatePromptTextKleur = medewerkerBagageUpdatePromptTextKleur;
    }

    public String getMedewerkerBagageUpdatePromptTextLabelnummerTxt() {
        return medewerkerBagageUpdatePromptTextLabelnummerTxt;
    }

    public void setMedewerkerBagageUpdatePromptTextLabelnummerTxt(String medewerkerBagageUpdatePromptTextLabelnummerTxt) {
        this.medewerkerBagageUpdatePromptTextLabelnummerTxt = medewerkerBagageUpdatePromptTextLabelnummerTxt;
    }

    public String getMedewerkerBagageUpdatePromptTextVluchtnummerTxt() {
        return medewerkerBagageUpdatePromptTextVluchtnummerTxt;
    }

    public void setMedewerkerBagageUpdatePromptTextVluchtnummerTxt(String medewerkerBagageUpdatePromptTextVluchtnummerTxt) {
        this.medewerkerBagageUpdatePromptTextVluchtnummerTxt = medewerkerBagageUpdatePromptTextVluchtnummerTxt;
    }

    public String getMedewerkerBagageUpdatePromptTextBestemmingTxt() {
        return medewerkerBagageUpdatePromptTextBestemmingTxt;
    }

    public void setMedewerkerBagageUpdatePromptTextBestemmingTxt(String medewerkerBagageUpdatePromptTextBestemmingTxt) {
        this.medewerkerBagageUpdatePromptTextBestemmingTxt = medewerkerBagageUpdatePromptTextBestemmingTxt;
    }

    public String getMedewerkerBagageUpdatePromptTextTypeTxt() {
        return medewerkerBagageUpdatePromptTextTypeTxt;
    }

    public void setMedewerkerBagageUpdatePromptTextTypeTxt(String medewerkerBagageUpdatePromptTextTypeTxt) {
        this.medewerkerBagageUpdatePromptTextTypeTxt = medewerkerBagageUpdatePromptTextTypeTxt;
    }

    public String getMedewerkerBagageUpdatePromptTextMerkTxt() {
        return medewerkerBagageUpdatePromptTextMerkTxt;
    }

    public void setMedewerkerBagageUpdatePromptTextMerkTxt(String medewerkerBagageUpdatePromptTextMerkTxt) {
        this.medewerkerBagageUpdatePromptTextMerkTxt = medewerkerBagageUpdatePromptTextMerkTxt;
    }

    public String getMedewerkerBagageUpdatePromptTextKleurTxt() {
        return medewerkerBagageUpdatePromptTextKleurTxt;
    }

    public void setMedewerkerBagageUpdatePromptTextKleurTxt(String medewerkerBagageUpdatePromptTextKleurTxt) {
        this.medewerkerBagageUpdatePromptTextKleurTxt = medewerkerBagageUpdatePromptTextKleurTxt;
    }

    public String getMedewerkerBagageUpdatePromptTextBijzondereKenmerkenTxt() {
        return medewerkerBagageUpdatePromptTextBijzondereKenmerkenTxt;
    }

    public void setMedewerkerBgageUpdatePromptTextBijzondereKenmerkenTxt(String medewerkerBagageUpdatePromptTextBijzondereKenmerkenTxt) {
        this.medewerkerBagageUpdatePromptTextBijzondereKenmerkenTxt = medewerkerBagageUpdatePromptTextBijzondereKenmerkenTxt;
    }

    public String getMedewerkerBagageUpdatePromptTextStatusCombox() {
        return medewerkerBagageUpdatePromptTextStatusCombox;
    }

    public void setMedewerkerBagageUpdatePromptTextStatusCombox(String medewerkerBagageUpdatePromptTextStatusCombox) {
        this.medewerkerBagageUpdatePromptTextStatusCombox = medewerkerBagageUpdatePromptTextStatusCombox;
    }

    public String getMedewerkerBagageUpdateButtonSlaOpBtn() {
        return medewerkerBagageUpdateButtonSlaOpBtn;
    }

    public void setMedewerkerBagageUpdateButtonSlaOpBtn(String medewerkerBagageUpdateButtonSlaOpBtn) {
        this.medewerkerBagageUpdateButtonSlaOpBtn = medewerkerBagageUpdateButtonSlaOpBtn;
    }

    public String getExportToCSFBtn() {
        return exportToCSFBtn;
    }

    public void setExportToCSFBtn(String exportToCSFBtn) {
        this.exportToCSFBtn = exportToCSFBtn;
    }

    public String getExportToPDFBtn() {
        return exportToPDFBtn;
    }

    public void setExportToPDFBtn(String exportToPDFBtn) {
        this.exportToPDFBtn = exportToPDFBtn;
    }
}