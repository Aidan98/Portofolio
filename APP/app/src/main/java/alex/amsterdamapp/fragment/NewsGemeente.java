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
import android.widget.ScrollView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

import alex.amsterdamapp.R;
import alex.amsterdamapp.util.HandleUserSession;


/**
 * Created by alexa on 16-4-2017.
 */

public class NewsGemeente extends Fragment {
    static TextView article_textview;
    private ProgressDialog nDialog;
    private static int nextPage = 0;
    private ScrollView articleScrollView;
    private HandleUserSession session;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_gemeente, container, false);

        session = new HandleUserSession(getActivity());

        setHasOptionsMenu(true);

        //de view scrollbaar maken als er veel tekst op komt
        article_textview = (TextView) view.findViewById(R.id.article_textview);
        article_textview.setMovementMethod(new ScrollingMovementMethod());
        article_textview.setMovementMethod(LinkMovementMethod.getInstance());
        articleScrollView = (ScrollView) view.findViewById(R.id.articleScrollView);


        //een laad venster aanmaken
        nDialog = new ProgressDialog(getActivity());


        //de artikelen ophalen in een async task
        new Scraper().execute();

        return view;

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Gemeente Nieuw-West");
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //automatisch het keyboard laten verdwijnen zodat deze niet blijft staan vanuit een andere
        //view of actie
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }


    /**
     * schraapt een website voor content...in dit geval het parool voor nieuws over adam-nieuw west
     */
    private class Scraper extends AsyncTask<String, Void, Void> {
        String article_text = "";


        /** progress dialog to show user that the backup is processing. */
        /**
         * application context.
         */
        @Override
        protected void onPreExecute() {
            nDialog.setMessage(getString(R.string.alert_loading_nl));
            nDialog.show();
        }

        //nieuwsartikelen worden in de achtergrond opgehaald
        @Override
        protected Void doInBackground(String... params) {
            try {
                //alle web elementen op slaan

                String URL_WEBSITE = "https://www.amsterdam.nl/nieuw-west-gebied/buurtnieuws-nieuw/";
                final String GEMEENTE_ARTICLES = "resultaten";
                final String ARTICLE_AMOUNT = "counter";
                final String ARTICLE_TITLE = "link";
                final String ARTICLE_BODY = "resultaat";
                final String ARTICLE_FOOTER = "";


                Document doc = Jsoup.connect(URL_WEBSITE).get();

                Elements el = doc.getElementsByClass(ARTICLE_BODY);
                Elements articleCounter = doc.getElementsByClass(ARTICLE_AMOUNT);

                article_text += "<h4>" + articleCounter.text() + "</h4>";
//
//                for(Element element : el){
//                    String article_links = element.select("a").attr("abs:href");
//                    article_text += article_links;
//                }

                for (Element element : el) {
                    String title = element.getElementsByClass(ARTICLE_TITLE).text();

                    String article_footer = element.getElementsByClass(ARTICLE_BODY).text();


                    String article_links = element.select("a").attr("abs:href");

                    article_text += "<p><b><a href=\"gemeente://" + article_links + "\">"
                            + title + "</b></a><br>" + article_footer +
                            "...<a href=\"gemeente://" + article_links + "\">Read more</a></p><p><b></b></p><br>";

                }

            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //dialoogvenster afsluiten als hij nog draait
            if (nDialog.isShowing()) {
                nDialog.dismiss();
            }

            /**  Als er geen internet, weergeef error */
            if (article_text == null || article_text == "") {
                article_textview.setText("Kon geen verbinding maken met de server");
                return;
            }


            //maak de links in de artikelen klikbaar
            article_textview.setClickable(true);
            article_textview.setText(Html.fromHtml(article_text));

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
        nDialog.setMessage(getString(R.string.alert_loading_en));
    }

    public void changeLanguageNl() {
        nDialog.setMessage(getString(R.string.alert_loading_nl));
    }

    public void changeLanguageTr() {
        nDialog.setMessage(getString(R.string.alert_loading_tr));
    }
}
