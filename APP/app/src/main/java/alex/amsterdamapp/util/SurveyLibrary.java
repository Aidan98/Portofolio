package alex.amsterdamapp.util;

/**
 * Created by aidan on 15/05/2017.
 */

public class SurveyLibrary {
    public static String mQuestions[] = {
            "Wilt u dat de speeltoestellen aan de Comeniusstraat vervangen worden?",
            "Vind u dat de burgersparticipatie in Nieuw - West moet worden verhoogd?",
            "Heeft u wel eens van slimme verlichting gehoord?",
            "Heeft u ooit een product zoals dit gebruikt?",
            "Heeft u wel eens van een website genaamd spullendelen.nl of peerby.com gehoord?",
            "Heeft u ooit een dienst zoals dit gebruikt?"

    };

    public static String mQuestionsEn[] = {
            "Do you want the play equipment to be replaced on the Comenius Street?",
            "Do you think citizens need to be increased in Nieuw-West?",
            "Have you ever heard of smart lighting?",
            "Have you ever used a product like this?",
            "Have you ever heard of a website called spullendelen.nl or peerby.com?",
            "Have you ever had a service like this?"
    };

    public static String mQuestionsTr[] = {
            "Eğer oyun Comeniusstraat değiştirilecek istiyor musunuz?",
            "Nieuw-West yükseltilmelidir Yeni'de kamu yararı düşünüyor musunuz?",
            "Hiç akıllı aydınlatma duydunuz mu?",
            "Hiç böyle bir ürün kullandınız mı?",
            "Hiç bir web sitesi olarak adlandırılan spullendelen.nl veya peerby.com duydunuz mu?",
            "Hiç böyle bir hizmeti kullanmış mı?"
    };


    private String mChoices[][] = {
            {"ja", "nee"},
            {"ja", "nee"},
            {"ja", "nee"},
            {"ja", "nee"},
            {"ja", "nee"},
            {"ja", "nee"},
            {"ja", "nee"},
    };

    private String mChoicesEn[][] = {
            {"Yes", "No"},
            {"Yes", "No"},
            {"Yes", "No"},
            {"Yes", "No"},
            {"Yes", "No"},
            {"Yes", "No"},
            {"Yes", "No"},
    };

    private String mChoicesTr[][] = {
            { "Evet", "hayır"},
            { "Evet", "hayır"},
            { "Evet", "hayır"},
            { "Evet", "hayır"},
            { "Evet", "hayır"},
            { "Evet", "hayır"},
            { "Evet", "hayır"},
    };


    public String getQuestion(int a) {
        String question = mQuestions[a];
        return question;
    }

    public String getQuestionEn(int a) {
        String question = mQuestionsEn[a];
        return question;
    }

    public String getQuestionTr(int a) {
        String question = mQuestionsTr[a];
        return question;
    }

    public String getChoice1(int a) {
        String choice0 = mChoices[a][0];
        return choice0;
    }

    public String getChoice2(int a) {
        String choice1 = mChoices[a][1];
        return choice1;
    }
    public String getChoice3(int a) {
        String choice0 = mChoices[a][0];
        return choice0;
    }
    public String getChoice4(int a) {
        String choice0 = mChoices[a][0];
        return choice0;
    }
    public String getChoice5(int a) {
        String choice0 = mChoices[a][0];
        return choice0;
    }

    public String getChoice1En(int a) {
        String choice0 = mChoicesEn[a][0];
        return choice0;
    }

    public String getChoice2En(int a) {
        String choice1 = mChoicesEn[a][1];
        return choice1;
    }
    public String getChoice3En(int a) {
        String choice0 = mChoicesEn[a][0];
        return choice0;
    }
    public String getChoice4En(int a) {
        String choice0 = mChoicesEn[a][0];
        return choice0;
    }
    public String getChoice5En(int a) {
        String choice0 = mChoicesEn[a][0];
        return choice0;
    }

    public String getChoice1Tr(int a) {
        String choice0 = mChoicesTr[a][0];
        return choice0;
    }

    public String getChoice2Tr(int a) {
        String choice1 = mChoicesTr[a][1];
        return choice1;
    }
    public String getChoice3Tr(int a) {
        String choice0 = mChoicesTr[a][0];
        return choice0;
    }
    public String getChoice4Tr(int a) {
        String choice0 = mChoicesTr[a][0];
        return choice0;
    }
    public String getChoice5Tr(int a) {
        String choice0 = mChoicesTr[a][0];
        return choice0;
    }
}