package alex.amsterdamapp.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.validator.routines.EmailValidator;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import alex.amsterdamapp.R;
import alex.amsterdamapp.models.User;
import alex.amsterdamapp.util.HandleUserSession;

import static alex.amsterdamapp.R.id.language_en;
import static android.R.attr.button;

/**
 * Author: Alex Chorak
 * Voor deze app gebruiken we één activity met daarin verschillende fragments.
 * We moeten fragments gebruiken zodat het slider_menu in ieder scherm beschikbaar is
 */

public class Login extends Fragment {

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */

    //handle user session
    private HandleUserSession session;
    private User user;
    private ProgressDialog nDialog;
    private NavigationView navigationView;
    private TextView textView;


    @Nullable
    @Override
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.login, container, false);

        mEmailView = (AutoCompleteTextView) view.findViewById(R.id.email);
        mPasswordView = (EditText) view.findViewById(R.id.password);
        nDialog = new ProgressDialog(getActivity());
        user = new User();
        session = new HandleUserSession(getActivity());
        mLoginFormView = view.findViewById(R.id.login_form);
        mProgressView = view.findViewById(R.id.login_progress);


        return inflater.inflate(R.layout.login, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        mEmailView = (AutoCompleteTextView) view.findViewById(R.id.email);
        textView = (TextView) view.findViewById(R.id.textView2);
        mPasswordView = (EditText) view.findViewById(R.id.password);

        //listener voor de login actie
        mEmailSignInButton = (Button) view.findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });


        Button btnRegister1 = (Button) view.findViewById(R.id.btnRegister1);
        btnRegister1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();

                Fragment fragment = new Register();
                ft.replace(R.id.content_main, fragment);
                ft.commit();

            }
        });

        registerButton = (Button) (view.findViewById(R.id.btnRegister1));

        mLoginFormView = view.findViewById(R.id.login_form);
        mProgressView = view.findViewById(R.id.login_progress);

        getActivity().setTitle(getString(R.string.login_title_nl));
    }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        //automatisch het kayboard laten verdwijnen zodat deze niet blijft staan vanuit een andere
        //view of actie
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private Button mEmailSignInButton;
    private Button registerButton;
    private View mProgressView;
    private View mLoginFormView;

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        //is het ingevoerde email correct?
        boolean isEmailValid = EmailValidator.getInstance().isValid(email);
        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            if (R.id.language_en == session.getLanguage()) {
                mPasswordView.setError(getString(R.string.error_incorrect_password_en));
            } else if (R.id.language_nl == session.getLanguage()) {
                mPasswordView.setError(getString(R.string.error_incorrect_password_nl));
            } else if (R.id.language_tr == session.getLanguage()) {
                mPasswordView.setError(getString(R.string.error_incorrect_password_tr));
            }
            focusView = mPasswordView;
            cancel = true;
        }

            // Check for a valid email address.
            if (TextUtils.isEmpty(email)) {
                if (R.id.language_en == session.getLanguage()) {
                    mEmailView.setError(getString(R.string.error_field_required_en));
                } else if (R.id.language_nl == session.getLanguage()) {
                    mEmailView.setError(getString(R.string.error_field_required_nl));
                } else if (R.id.language_tr == session.getLanguage()) {
                    mEmailView.setError(getString(R.string.error_field_required_tr));
                }
                focusView = mEmailView;
                cancel = true;
            }

            if (!isEmailValid) {
                if (R.id.language_en == session.getLanguage()) {
                    mEmailView.setError(getString(R.string.error_invalid_email_en));
                } else if (R.id.language_nl == session.getLanguage()) {
                    mEmailView.setError(getString(R.string.error_invalid_email_nl));
                } else if (R.id.language_tr == session.getLanguage()) {
                    mEmailView.setError(getString(R.string.error_invalid_email_tr));
                }
                focusView = mEmailView;
                cancel = true;
            }

            if (cancel) {
                // There was an error; don't attempt login and focus the first
                // form field with an error.
                focusView.requestFocus();
            } else {
                //alle data klopt, log de gebruiker in
                new UserLoginTask().execute("login", email, password);
            }
    }



//    /**
//     * Controleer of het wachtwoord aan de eisen voldoet
//     * het moet minimaal 1 hoofdletter, 1 kleine letter
//     * 1 special character, geen spaties en minimaal 8 tekens lang zijn
//     *
//     * @param password het wachtwoord dat de gebruiker invoert en gecontroleerd wordt
//     * @return true of false
//     */
//    private boolean isPasswordValid(String password) {
//        boolean isPasswordValid = false;
//        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@!#$%^&+=])(?=\\S+$).{8,}$";
//
//        if (password.matches(pattern))
//            isPasswordValid = true;
//        return isPasswordValid;
//    }

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
            case language_en:
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
        getActivity().setTitle(getString(R.string.login_title_en));
        mEmailSignInButton.setText(getString(R.string.login_en));
        mPasswordView.setHint(getString(R.string.password_en));
        mEmailView.setHint(getString(R.string.username_en));
        registerButton.setText(getString(R.string.button_register_en));
    }

    public void changeLanguageNl() {
        getActivity().setTitle(getString(R.string.login_title_nl));
        mEmailSignInButton.setText(getString(R.string.login_nl));
        mPasswordView.setHint(getString(R.string.password_nl));
        mEmailView.setHint(getString(R.string.username_nl));
        registerButton.setText(getString(R.string.button_register_nl));
    }

    public void changeLanguageTr() {
        getActivity().setTitle(getString(R.string.login_title_tr));
        mEmailSignInButton.setText(getString(R.string.login_tr));
        mPasswordView.setHint(getString(R.string.password_tr));
        mEmailView.setHint(getString(R.string.username_tr));
        registerButton.setText(getString(R.string.button_register_tr));
    }


    /**
     * Deze Task logt de gebruiker in op de achtergrond
     */
    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    public class UserLoginTask extends AsyncTask<String, Void, String> {

        AlertDialog.Builder dialog;

        @Override
        protected void onPreExecute() {
            dialog = new AlertDialog.Builder(getActivity());
            nDialog.setMessage("Inloggen");
            nDialog.show();
        }


        /**
         * asynct task om in de achtergrond de gebruiker in te loggen
         *
         * @param params bevat de username en password
         * @return een boolean true= ingelogd, false=niet ingelogd
         */
        protected String doInBackground(String... params) {

            String loggedIn = "";
            if (params.length > 1) {
                String type = params[0];

                //adres waar de login-script draait
                String login_url = "https://pad1617.000webhostapp.com/Login.php";
                if (type.equals("login")) {
                    try {
                        //gebruikers email en wachtwoord ophalen
                        String email = params[1];
                        String password = params[2];

                        //opslaan in model
                        user.setEmail(email);
                        user.setPassword(password);


                        //email en wachtwoord meesturen in een post method
                        URL url = new URL(login_url);
                        HttpURLConnection httpURLConnection =
                                (HttpURLConnection) url.openConnection();
                        httpURLConnection.setRequestMethod("POST");
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setDoInput(true);
                        OutputStream os = httpURLConnection.getOutputStream();
                        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                        String post_data =
                                URLEncoder.encode("email", "UTF-8") + "=" +
                                        URLEncoder.encode(email, "UTF-8") + "&" +
                                        URLEncoder.encode("password", "UTF-8") + "=" +
                                        URLEncoder.encode(password, "UTF-8");
                        bw.write(post_data);

                        //alles afsluiten
                        bw.flush();
                        bw.close();
                        os.close();
                        InputStream is = httpURLConnection.getInputStream();
                        BufferedReader br = new BufferedReader(new InputStreamReader(is, "iso-8859-1"));
                        String result = "";
                        String line = "";
                        while ((line = br.readLine()) != null) {
                            result += line;
                        }
                        br.close();
                        is.close();
                        httpURLConnection.disconnect();
                        if (!result.equals("")) {
                            loggedIn = result;
                        }
                    } catch (MalformedURLException e) {
                        System.out.println("Error:" + e);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
            return loggedIn;
        }


        @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
        @Override
        protected void onPostExecute(String result) {
            //laadvenster laten verdwijnen
            if (nDialog.isShowing()) {
                nDialog.dismiss();
            }

            //er is geen request verstuurd om dat er geen internet is
            if (result == null || result == "") {
                dialog.setMessage("Kon geen verbinding maken met de server");
                dialog.show();
                return;
            }

            //json object omzetten en de waardes opslaan
            jSONParser(result);


            // Doorverwijzen naar een profielpagina en kijken of de user een admin is
            if (!session.getIsAdmin() && session.getId() > 0) {
                session.setEmail(user.getEmail());
                getActivity().recreate();
            } else if (session.getIsAdmin() && session.getId() > 0) {
                session.setEmail(user.getEmail());
                session.setIsAdmin(true);
                getActivity().recreate();
            } else {

                dialog.setMessage("De inloggegevens zijn onjuist");
                dialog.show();
                if (R.id.language_en == session.getLanguage()) {
                    dialog.setMessage(getString(R.string.dialog_en));
                    dialog.show();
                } else if (R.id.language_nl == session.getLanguage()) {
                    dialog.setMessage(getString(R.string.dialog_nl));
                    dialog.show();
                } else if (R.id.language_tr == session.getLanguage()) {
                    dialog.setMessage(getString(R.string.dialog_tr));
                    dialog.show();
                }
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
        }
    }

    /**
     * Convert json naar strings en sla deze op
     *
     * @param result het json object na het inloggen
     */
    private void jSONParser(String result) {
        JSONObject obj = null;
        try {
            obj = new JSONObject(result);
            int id = obj.getInt("id");
            session.setId(id);
            int admin = obj.getInt("admin");

            if (Integer.valueOf(admin).equals(1)) {
                session.setIsAdmin(true);
            } else {
                session.setIsAdmin(false);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
