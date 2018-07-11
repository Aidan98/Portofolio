package alex.amsterdamapp.util;

import alex.amsterdamapp.R;

/**
 * Created by alexa on 23-5-2017.
 */

//All the Questions and answers Hardcoded
public class QuizLibrary {

    public static String[] mQuestions = new String[]{
//write down the questions




            "Hoe heet heet winkelcentrum bij de halte 40-45",
            "Waarom heet Plein 40-45 zo",
            "Wanneer is de parkeergarage gebouwd",
            "Hoeveel inwoners zijn er in Amsterdam Nieuw-West",
            "Welke Buurt hoort NIET bij Amsterdam Nieuw-West",
            "Wat is de oppervlakte van Amsterdam Nieuw-West",
            "Waar zit het stadsdeelkantoor",
            "Amsterdam Nieuw-West ligt aan welke snelweg",
            "Wat is BOOT",
            "Wanneer is Amsterdam Nieuw-West gebouwd",

            "Hoe heet het winkelcentrum bij de halte 40-45?",
            "Waarom heet Plein 40-45 zo?",
            "Wanneer is de parkeergarage gebouwd?",
            "Hoeveel inwoners zijn er in Amsterdam Nieuw-West?",
            "Welke Buurt hoort NIET bij Amsterdam Nieuw-West?",
            "Wat is de oppervlakte van Amsterdam Nieuw-West?",
            "Waar zit het stadsdeelkantoor?",
            "Amsterdam Nieuw-West ligt aan welke snelweg?",
            "Wat is BOOT?",
            "Wanneer is Amsterdam Nieuw-West gebouwd?"

    };

    public static String[] mQuestionsEn = new String[]{
//write down the questions
            "What is the name of the shopping center at the stop 40-45?",
            "Why is Plein 40-45 called Plein 40-45?",
            "When was the parking garage built?",
            "How many inhabitants are there in Amsterdam Nieuw-West?",
            "Which Neighborhood Does NOT belong to Amsterdam Nieuw-West?",
            "What is the surface of Amsterdam Nieuw-West?",
            "Where is the district office?",
            "Amsterdam Nieuw-West is on which highway?",
            "What is BOOT?",
            "When was Amsterdam Nieuw-West built?"
    };

    public static String[] mQuestionsTr = new String[]{
//write down the questions
            "Durağında 40-45 at alışveriş merkezi nedir?",
            "Neden bu kadar Plein 40-45 adlandırılan mı?",
            "Ne zaman garaj inşa?",

            "Kaç kişi Amsterdam Nieuw-West vardır?",

            "Kaç kişi Amsterdam Nieuw-West vardır?",
            "Hangi Mahalle Amsterdam Nieuw-West ait DEĞİL mu?",
            "Yüzey veya Amsterdam Nieuw-West nedir?",
            "Nerede bölge ofisi mi?",
            "Amsterdam Nieuw-West karayolu üzerinde kaç takipçileri?",
            "BOOT nedir?",
            "Ne zaman Amsterdam Nieuw-West inşa edildi?"

    };


    private String mChoices[][] = {
//write down the choices
            {"Winkelcentrum 40-45", "Oostport", "Criterion"},
            {"Ze hadden een naam nodig", "Het herrinert aan de Tweede Wereld Oorlog", "Het is gemaakt in het jaar 1940"},
            {"1990", "1945", "1961"},
            {"146700", "189600", "25000"},
            {"Slotervaart", "Osdorp", "De Jordaan"},
            {"32.38", "50.28", "12.98"},
            {"Plein 40-45", "Jan van Gaalenstraat", "Osdropplein"},
            {"A10", "A1", "A44"},
            {"Een boot", "Een kenniscentrum", "Een bedrijf"},
            {"1990", "1945", "2000"}
    };

    private String mChoicesEn[][] = {
//write down the choices
            {"Mall 40-45", "Oostport", "Criterion"},
            {"They needed a name", "It recalls the Second World War", "It was made in the year 1940"},
            {"1990", "1945", "1961"},
            {"146700", "189600", "25000"},
            {"Slotervaart", "Osdorp", "The Jordaan"},
            {"32.38", "50.28", "12.98"},
            {"Plein 40-45", "Jan van Gaalenstraat", "Osdropplein"},
            {"A10", "A1", "A44"},
            {"A boat", "A knowledge center", "A company"},
            {"1990", "1945", "2000"}
    };

    private String mChoicesTr[][] = {
//write down the choices
            {"Alışveriş 40-45", "Oostport", "Criterion"},
            {"Onlar bir isim gerekli", "İkinci Dünya Savaşı biraz anımsatan", "Bu 1940 yılında yapıldı"},
            {"1990", "1945", "1961"},
            {"146700", "189600", "25000"},
            {"Slotervaart", "Osdorp", "The Jordaan"},
            {"32.38", "50.28", "12.98"},
            {"Plein 40-45", "Jan van Gaalenstraat", "Osdropplein"},
            {"A10", "A1", "A44"},
            {"Bir tekne", "Bir bilgi", "Bir şirket"},
            {"1990", "1945", "2000"}
            };

    private String mCorrectAnswers[] = {
//Write down the correct answers
            //make sure the right answer is exactly the same as the choice
            "Winkelcentrum 40-45",
            "Het herrinert aan de Tweede Wereld Oorlog",
            "1990",
            "146700",
            "De Jordaan",
            "32.38",
            "Osdropplein",
            "A10",
            "Een kenniscentrum",
            "1990"
    };

    private String mCorrectAnswersEn[] = {
//Write down the correct answers
            //make sure the right answer is exactly the same as the choice
            "Mall 40-45", "It recalls the Second World War",
            "1990", "146700",
            "Mall 40-45",
            "It recalls the Second World War",
            "1990",
            "146700",
            "De Jordaan",
            "32.38",
            "Osdropplein",
            "A10",
            "A knowledge center",
            "1990"
    };

    private String mCorrectAnswersTr[] = {
//Write down the correct answers
            //make sure the right answer is exactly the same as the choice
            "Alışveriş 40-45", "İkinci Dünya Savaşı biraz anımsatan",
            "1990", "146700",
            "Alışveriş 40-45",
            "İkinci Dünya Savaşı biraz anımsatan",
            "1990",
            "146700",
            "De Jordaan",
            "32.38",
            "Osdropplein",
            "A10",
            "Bir bilgi",
            "1990"
    };


    public String getQuestion(int a) {
        //getter for questions
        String question = mQuestions[a];
        return question;
    }

    public String getQuestionEn(int a) {
        //getter for questions
        String question = mQuestionsEn[a];
        return question;
    }

    public String getQuestionTr(int a) {
        //getter for questions
        String question = mQuestionsTr[a];
        return question;
    }

    public String getChoice1(int a) {
        //getter for choice #
        //maybe for all the choices
        String choice0 = mChoices[a][0];
        return choice0;
    }

    public String getChoice2(int a) {
        //getter for choice #
        //maybe for all the choices
        String choice1 = mChoices[a][1];
        return choice1;
    }

    public String getChoice3(int a) {
        //getter for choice #
        //maybe for all the choices
        String choice2 = mChoices[a][2];
        return choice2;
    }

    public String getChoice1En(int a) {
        //getter for choice #
        //maybe for all the choices
        String choice0 = mChoicesEn[a][0];
        return choice0;
    }

    public String getChoice2En(int a) {
        //getter for choice #
        //maybe for all the choices
        String choice1 = mChoicesEn[a][1];
        return choice1;
    }

    public String getChoice3En(int a) {
        //getter for choice #
        //maybe for all the choices
        String choice2 = mChoicesEn[a][2];
        return choice2;
    }

    public String getChoice1Tr(int a) {
        //getter for choice #
        //maybe for all the choices
        String choice0 = mChoicesTr[a][0];
        return choice0;
    }

    public String getChoice2Tr(int a) {
        //getter for choice #
        //maybe for all the choices
        String choice1 = mChoicesTr[a][1];
        return choice1;
    }

    public String getChoice3Tr(int a) {
        //getter for choice #
        //maybe for all the choices
        String choice2 = mChoicesTr[a][2];
        return choice2;
    }

    public String getCorrectAnswer(int a) {
        String answer = mCorrectAnswers[a];
        return answer;
    }

    public String getCorrectAnswerEn(int a) {
        String answer = mCorrectAnswersEn[a];
        return answer;
    }

    public String getCorrectAnswerTr(int a) {
        String answer = mCorrectAnswersTr[a];
        return answer;
    }

    /////////////////////////////////////////topoquiz//////////////////////////////////////////////

    public static int[] streets = new int[]{
            /*Jan Tooropstraat*/R.drawable.img1,
            /*Sloterweg*/R.drawable.img2,
            /*Naritaweg*/ R.drawable.img003,
            /*Ditlaar*/R.drawable.img4,
            /*Kortrijk*/R.drawable.img5,
            /*Burgermeester Venig Meineslaan*/R.drawable.img6,
            /*Pieter Calandlaan*/R.drawable.img7,
            /*Oostoever*/R.drawable.img8,
            /*Johan Huizingalaan*/R.drawable.img9
    };

    private String mStreetChoices[][] = {
//write down the choices
            {"Jan Tooropstraat", "Jan van Gaalenstraat", "Bilderdijkstraat"},
            {"Vrije Geer", "Ditlaar", "Sloterweg"},
            {"Krakelingseweg", "Naritaweg", "Bilderdijkstraat"},
            {"Lineausstraat", "Ditlaar", "Pretoriusstraat"},
            {"Naritaweg", "Kortrijk", "Plesmanlaan"},
            {"Ditlaar", "Jan Tooropstraat", "Burgermeester Venig Meineslaan"},
            {"Pieter Calandlaan", "Westoever", "Oostoever"},
            {"Pradolaan", "Oostoever", "Jan Evertsenlaan"},
            {"Billie Holidaystraat", "Johan Huizingalaan", "Sloterweg"}

    };

    private String mCorrectStreet[] = {
//Write down the correct answers
            //make sure the right answer is exactly the same as the choice

            "Jan Tooropstraat",
            "Sloterweg",
            "Naritaweg",
            "Ditlaar",
            "Kortrijk",
            "Burgermeester Venig Meineslaan",
            "Pieter Calandlaan",
            "Oostoever",
            "Johan Huizingalaan"
    };


    public String getStreet1(int a) {
        //getter for choice #
        //maybe for all the choices
        String street0 = mStreetChoices[a][0];
        return street0;
    }

    public String getStreet2(int a) {
        //getter for choice #
        //maybe for all the choices
        String street1 = mStreetChoices[a][1];
        return street1;
    }

    public String getStreet3(int a) {
        //getter for choice #
        //maybe for all the choices
        String street2 = mStreetChoices[a][2];
        return street2;
    }

    public String getCorrectStreet(int a) {
        String answer = mCorrectStreet[a];
        return answer;
    }


}
