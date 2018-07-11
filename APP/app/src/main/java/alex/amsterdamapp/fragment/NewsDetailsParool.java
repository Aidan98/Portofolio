package alex.amsterdamapp.fragment;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import alex.amsterdamapp.R;
import alex.amsterdamapp.util.HandleUserSession;

/**
 * Weergeef het volledige artikel
 */
public class NewsDetailsParool extends AppCompatActivity {

    private static TextView article_textview;
    private static String article_URL = "";
    private Button terugKnop;
    private HandleUserSession session;





    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_details_parool);


        article_textview = (TextView) findViewById(R.id.article_textview);
        Uri data = getIntent().getData();//url van het artikel
        article_URL = data.toString().replace("parool://", "");//link trimmen
        article_textview.setMovementMethod(new ScrollingMovementMethod());

        terugKnop = (Button) findViewById(R.id.back_to_articles_button);//terug knop listener
        terugKnop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        new Scraper().execute();
    }






    /**
     * Haalt asynchroon de artikelen op van Het Parool
     */
    private class Scraper extends AsyncTask<Void, Void, Void> {
        //De tekst van het opgehaalde artikel
        String article_text = "";

        private ProgressDialog dialog = new ProgressDialog(NewsDetailsParool.this);

        /** progress dialog to show user that the backup is processing. */
        /**
         * application context.
         */
        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Laden");
            this.dialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                //ping de site en haal de html op
                Document doc = Jsoup.connect(article_URL)
                        .cookie("nl_cookiewall_version", "1")
                        .get();

                //all artikelen met deze class naam worden opgeslagen

                String main_article = doc.getElementsByClass("article__body__paragraph").html();
                String article_title = doc.getElementsByClass("article__title").text();
                String article_intro = doc.getElementsByClass("article__intro").text();





                article_text += "<h4 style=\"color:#FF4181;\">" + article_title + "</h4><p></p><p>" + article_intro + "</p><p>" + main_article + "</p>";


            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }

            terugKnop.setVisibility(View.VISIBLE);//knop zichtbaar maken zodra het artikel geladen is

            super.onPostExecute(aVoid);
            article_textview.setText(Html.fromHtml(article_text));


        }
    }

}
