package alex.amsterdamapp.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import alex.amsterdamapp.R;
import alex.amsterdamapp.util.HandleUserSession;


/**
 * Created by alexa on 16-4-2017.
 */

public class NewsVolkskrant extends Fragment {
    // UI references
    static TextView article_textview;
    private ProgressDialog nDialog;
    private ScrollView articleScrollView;
    private Button nextPageButton;
    private Button previousPageButton;
    private static int nextPage = 0;
    private HandleUserSession session;
    private final String URL_WEBSITE = "http://www.volkskrant.nl/alle-nieuws-over-amsterdam-nieuw-west/";


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_volkskrant, container, false);

        session = new HandleUserSession(getActivity());
        setHasOptionsMenu(true);

        //de view scrollbaar maken als er veel tekst op komt
        article_textview = (TextView) view.findViewById(R.id.article_textview);
        article_textview.setMovementMethod(new ScrollingMovementMethod());
        article_textview.setMovementMethod(LinkMovementMethod.getInstance());


        //meer artikelen ophalen
        nextPageButton = (Button) view.findViewById(R.id.next_page_button);
        //vorige artikelen
        previousPageButton = (Button) view.findViewById(R.id.previous_page_button);

        articleScrollView = (ScrollView) view.findViewById(R.id.articleScrollView);//de scrollview


        //een laad venster aanmaken
        nDialog = new ProgressDialog(getActivity());


        //de artikelen ophalen in een async task
        new Scraper().execute();

        return view;

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("De Volkskrant");

        // een clicklistener instellen voor de volgende/vorige knoppen
        nextPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Scraper().execute(nextArticlesPage());
                articleScrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        });

        previousPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Scraper().execute(previousArticlesPage());
                articleScrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //automatisch het keyboard laten verdwijnen zodat deze niet blijft staan vanuit een andere
        //view of actie
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    //haal de url van de nieuwe pagina
    private String nextArticlesPage() {
        /**Deze string wordt gebruikt om naar een volgende pagina te navigeren */
        String offset = "?offset=1494533022379-";
        nextPage += 10;
        String link = URL_WEBSITE + offset + nextPage;
        return link;
    }

    //haal de url van de vorige pagina
    private String previousArticlesPage() {
        String link;
        /**Deze string wordt gebruikt om naar een volgende pagina te navigeren */
        String offset = "?offset=1494533022379-";

        if (Integer.valueOf(nextPage).equals(0)) {
            link = URL_WEBSITE;
        } else {
            nextPage -= 10;
            link = URL_WEBSITE + offset + nextPage;
        }
        return link;
    }


    /**
     * Schraapt een website voor content
     * In dit geval het parool voor nieuws over adam-nieuw west
     */
    private class Scraper extends AsyncTask<String, Void, Void> {
        String article_text = "";


        /** Deze methode wordt aangeroepen VOORDAT de artikelen worden opgehaald */

        @Override
        protected void onPreExecute() {
            nDialog.setMessage("Laden");
            nDialog.show();
        }

        /** Nieuwsartikelen worden in de achtergrond opgehaald */
        @Override
        protected Void doInBackground(String... params) {
            try {
                /** alle web class names op slaan */
                final String BYPASS_COOKIE_KEY = "nl_cookiewall_version";
                final String BYPASS_COOKIE_VALUE = "1";
                final String PAROOL_ARTICLE_CSS = "article article--extended";
                final String PAROOL_ARTICLE_TITLE_CSS = "article__title";
                final String PAROOL_ARTICLE_BODY_CSS = "article__body";
                final String PAROOL_ARTICLE_FOOTER_CSS = "article__footer";
                final String PAROOL_ARTICLE_AMOUNT = "header__title";

                /** Hier wordt de betreffende ebsite geschraapt */
                Document doc = Jsoup.connect(URL_WEBSITE)
                        .cookie(BYPASS_COOKIE_KEY, BYPASS_COOKIE_VALUE)
                        .get();

                /** */
                Elements el = doc.getElementsByClass(PAROOL_ARTICLE_CSS);
                /** */
                Elements elmnt = doc.getElementsByClass(PAROOL_ARTICLE_AMOUNT);

                article_text += "<h4>" + elmnt.text() + "</h4>";


                /** Loop door het document om zo alle artikelen individueel op te halen */
                for (Element element : el) {
                    String title = element.getElementsByClass(PAROOL_ARTICLE_TITLE_CSS).text();
                    String article_body = element.getElementsByClass(PAROOL_ARTICLE_BODY_CSS).text();
                    String article_footer = element.getElementsByClass(PAROOL_ARTICLE_FOOTER_CSS).text();


                    /** Haal alle links van de artikelen */
                    String article_links = element.select("a").attr("abs:href");

                    /** De lay-out voor het nieuws wordt hier gemaakt */
                        article_text += "<p><b><a href=\"parool://" + article_links + "\">" + title + "</b></a><br>" +
                                article_body + "...<a href=\"parool://" + article_links + "\">Read more</a></p><p><b>" + article_footer + "</b></p><br>";


                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        /** */
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            /** Dialoogvenster afsluiten als hij nog draait */
            if (nDialog.isShowing()) {
                nDialog.dismiss();
            }

            /**  Als er geen internet, weergeef error */
            if(article_text == null || article_text == ""){
                article_textview.setText("Kon geen verbinding maken met de server");
                return;
            }

            /** Maak de links in de artikelen klikbaar */
            article_textview.setClickable(true);
            article_textview.setText(Html.fromHtml(article_text));

            /** Pas nadade artikelen geladen zijn word de knop zichtbaar
             *  Anders zweeft de knop eerst in het scherm zonder dat er artikelen zijn */
            nextPageButton.setVisibility(View.VISIBLE);

            /** Vorige knop onzichtbaar als pagina > 1 is */
            if (Integer.valueOf(nextPage).equals(0)) {
                previousPageButton.setVisibility(View.INVISIBLE);
            } else {
                previousPageButton.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        changeLanguage(session.getLanguage());

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        changeLanguage(id);

        return super.onOptionsItemSelected(item);
    }

    /**
     * Deze methode vertaalt de app in de geselcteerde taal
     * wanneer er rechtsboven in het menu op een taalgeklikt wordt.
     *
     * @param id = Het id van het geselecteerde menu item
     * @return
     */
    public void changeLanguage(int id) {
        session.setLanguage(id);
        switch (id) {
            case R.id.language_en:
                changeLanguageEn();
                break;
            case R.id.language_nl:
                changeLanguageNl();
                break;
            case R.id.language_tr:
                changeLanguageTr();
                break;
        }

    }

    public void changeLanguageEn() {
        nextPageButton.setText(getString(R.string.button_next_en));
        previousPageButton.setText(getString(R.string.button_previous_en));
        nDialog.setMessage(getString(R.string.alert_loading_en));
    }

    public void changeLanguageNl() {
        nextPageButton.setText(getString(R.string.button_next_nl));
        previousPageButton.setText(getString(R.string.button_previous_nl));
        nDialog.setMessage(getString(R.string.alert_loading_nl));
    }

    public void changeLanguageTr() {
        nextPageButton.setText(getString(R.string.button_next_tr));
        previousPageButton.setText(getString(R.string.button_previous_tr));
        nDialog.setMessage(getString(R.string.alert_loading_tr));
    }
}
