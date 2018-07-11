package alex.amsterdamapp;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import alex.amsterdamapp.fragment.About;
import alex.amsterdamapp.fragment.Admin;
import alex.amsterdamapp.fragment.Gallery;
import alex.amsterdamapp.fragment.Info;
import alex.amsterdamapp.fragment.Login;
import alex.amsterdamapp.fragment.Logout;
import alex.amsterdamapp.fragment.News;
import alex.amsterdamapp.fragment.Profile;
import alex.amsterdamapp.fragment.Quiz;
import alex.amsterdamapp.fragment.QuizMenu;
import alex.amsterdamapp.fragment.Survey;
import alex.amsterdamapp.util.HandleUserSession;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private boolean isAdmin = false;
    private String userEmail = "";
    private NavigationView navigationView;
    private HandleUserSession session;
    private TextView textView;
    private MenuItem menuItemNews, menuItemQuiz, menuItemSurvey, menuItemLogin, menuItemInfo,
            menuItemAbout, menuItemGallery, menuItemLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            drawer.setDrawerListener(toggle);
        }
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        menuItemNews = navigationView.getMenu().findItem(R.id.nav_news);
        menuItemQuiz = navigationView.getMenu().findItem(R.id.nav_quiz);
        menuItemSurvey = navigationView.getMenu().findItem(R.id.nav_survey);
        menuItemLogin = navigationView.getMenu().findItem(R.id.nav_login);
        menuItemInfo = navigationView.getMenu().findItem(R.id.nav_info);
        menuItemAbout = navigationView.getMenu().findItem(R.id.nav_about);
        menuItemGallery = navigationView.getMenu().findItem(R.id.nav_gallery);


        /**
         * Welkomstbericht voor de ingelogde gebruiker weergeven
         * in het slider_menu
         */
        session = new HandleUserSession(this);
        isAdmin = session.getIsAdmin();//kijken of de gebruiker een admin is
        userEmail = session.getEmail();//email van de ingelogde gerbuiker

        View headerView = navigationView.getHeaderView(0);//menu item text
        TextView loggedInUserText = (TextView) headerView.findViewById(R.id.userTextView);
        if (userEmail != "") {
            loggedInUserText.setText("Welkom " + session.getEmail());
        } else {
            loggedInUserText.setText(getResources().getString(R.string.subtitle_nl));
        }


        //Nieuwsoverzicht weergeven bij het lanceren van de app
        onMenuItemSelected(R.id.nav_news);
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Afsluiten")
                .setMessage("Weet u zeker dat u wilt afsluiten?")
                .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("Nee", null)
                .show();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        //als de gebruiker een admin is, wordt het admin paneel weergegeven
        navigationView.getMenu().setGroupVisible(R.id.group_admin, isAdmin);

        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        changeLanguage(session.getLanguage());

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        onMenuItemSelected(id);

        changeLanguage(id);

        return super.onOptionsItemSelected(item);
    }

    /**
     * Deze methode zorgt ervoor dat het scherm(fragment) verwisseld wordt
     * als er op een item in het slider_menu wordt geklikt
     */
    private void onMenuItemSelected(int id) {
        // pas als de user is ingelogd kan je de enquete en quiz bereiken
        menuItemQuiz.setEnabled(false);
        menuItemSurvey.setEnabled(false);
        Fragment fragment = null;
        switch (id) {
            case R.id.nav_news:
                fragment = new News();
                break;
            case R.id.nav_login:
                if (session.getEmail() != "") {
                    fragment = new Profile();//als de gebruiker ingelogd is wordt de profielpagina weergegeven
                    break;
                } else {
                    fragment = new Login();
                    break;
                }
            case R.id.nav_quiz:

                if (session.getEmail() != "") {
                    menuItemQuiz.setEnabled(true);
                    fragment = new QuizMenu();
                    break;
                }
                break;
            case R.id.nav_survey:
                if (session.getEmail() != "") {
                    menuItemSurvey.setEnabled(true);
                    fragment = new Survey();
                    break;
                }

                break;
            case R.id.nav_admin:
                fragment = new Admin();
                break;
            case R.id.nav_about:
                fragment = new About();
                break;
            case R.id.nav_info:
                fragment = new Info();
                break;
            case R.id.nav_gallery:
                fragment = new Gallery();
                break;
        }

        if (fragment != null) {
            //begin met het verwisselen van de scherm
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_main, fragment);
            ft.commit();
        }

        //sluit het menu nadat een item is geselecteerd
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        onMenuItemSelected(id);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * Deze methode vertaalt de app in de geselcteerde taal
     * wanneer er rechtsboven in het menu op een taalgeklikt wordt.
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
        textView = (TextView) (findViewById(R.id.textView2));
        textView.setText(getString(R.string.title_en));
        textView = (TextView) (findViewById(R.id.userTextView));
        textView.setText(getString(R.string.subtitle_en));
        menuItemNews.setTitle(getString(R.string.nav_news_en));
        menuItemQuiz.setTitle(getString(R.string.fragment_quiz_title_en));
        menuItemSurvey.setTitle(getString(R.string.fragment_survey_title_en));
        menuItemLogin.setTitle(getString(R.string.login_title_en));
        menuItemInfo.setTitle(getString(R.string.fragment_info_title_en));
        menuItemAbout.setTitle(getString(R.string.fragment_about_title_en));
        menuItemGallery.setTitle(getString(R.string.fragment_gallery_title_en));
    }


    public void changeLanguageNl() {
        textView = (TextView) (findViewById(R.id.textView2));
        textView.setText(getString(R.string.title_nl));
        textView = (TextView) (findViewById(R.id.userTextView));
        textView.setText(getString(R.string.subtitle_nl));
        menuItemNews.setTitle(getString(R.string.nav_news_nl));
        menuItemQuiz.setTitle(getString(R.string.fragment_quiz_title_nl));
        menuItemSurvey.setTitle(getString(R.string.fragment_survey_title_nl));
        menuItemLogin.setTitle(getString(R.string.login_title_nl));
        menuItemInfo.setTitle(getString(R.string.fragment_info_title_nl));
        menuItemAbout.setTitle(getString(R.string.fragment_about_title_nl));
        menuItemGallery.setTitle(getString(R.string.fragment_gallery_title_nl));
    }

    public void changeLanguageTr() {
        textView = (TextView) (findViewById(R.id.textView2));
        textView.setText(getString(R.string.title_tr));
        textView = (TextView) (findViewById(R.id.userTextView));
        textView.setText(getString(R.string.subtitle_tr));
        menuItemNews.setTitle(getString(R.string.nav_news_tr));
        menuItemQuiz.setTitle(getString(R.string.fragment_quiz_title_tr));
        menuItemSurvey.setTitle(getString(R.string.fragment_survey_title_tr));
        menuItemLogin.setTitle(getString(R.string.login_title_tr));
        menuItemInfo.setTitle(getString(R.string.fragment_info_title_tr));
        menuItemAbout.setTitle(getString(R.string.fragment_about_title_tr));
        menuItemGallery.setTitle(getString(R.string.fragment_gallery_title_tr));
    }

}


