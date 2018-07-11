package alex.amsterdamapp.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;

import java.util.HashMap;
import java.util.Map;

import alex.amsterdamapp.R;

import alex.amsterdamapp.util.HandleUserSession;


/**
 * Klasse voor het registreren van de gebruiker
 */

public class Register extends Fragment {

    //UI controls
    private EditText postcodeTxt;
    private EditText voornaamTxt;
    private EditText emailTxt;
    private EditText wachwoordTxt;
    private Button btnRegister2;
    private TextView registerError;
    private AlertDialog dialog;
    private ProgressDialog pDialog;
    private HandleUserSession session;
    private String name, zipcode, email, passwordDB, password;

//    private String value;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        session = new HandleUserSession(getActivity());
        getActivity().setTitle(getString(R.string.fragment_register_title_nl));



        return inflater.inflate(R.layout.register, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        registerError = (TextView) view.findViewById(R.id.registerError);
        postcodeTxt = (EditText) view.findViewById(R.id.etZipcode);
        voornaamTxt = (EditText) view.findViewById(R.id.etName);
        emailTxt = (EditText) view.findViewById(R.id.etEmail);
        wachwoordTxt = (EditText) view.findViewById(R.id.etWachtwoord);
        btnRegister2 = (Button) view.findViewById(R.id.btnRegister2);

        changeLanguageNl();

        pDialog = new ProgressDialog(getActivity());

        btnRegister2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                get_SHA_512_SecurePassword("Welkom123!", "SALTpx09");

                //registreren
                registerUser();

            }
        });
    }

    /**
     * Registreert asynchroon de gebruiker
     */
    private void registerUser() {



        password = wachwoordTxt.getText().toString();
        name = voornaamTxt.getText().toString();
        zipcode = postcodeTxt.getText().toString();
        email = emailTxt.getText().toString();

        if (validateForm()) {

            pDialog.setMessage("Registreren");
            pDialog.show();

            String url = "https://pad1617.000webhostapp.com/Register.php";

            RequestQueue queue = Volley.newRequestQueue(getActivity());

            StringRequest registerRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        pDialog.dismiss();
                        dialog = new AlertDialog.Builder(getActivity()).create();
                        //de response is een JSON object
                        JSONObject jsonResponse = new JSONObject(response);
                        boolean succes = jsonResponse.getBoolean("success");
                        if (succes) {
                            //een leuke timer die voor 2 seconden blijft staan
                            new CountDownTimer(2000, 1000) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                    pDialog.setMessage("U heeft geregistreerd");
                                    pDialog.show();
                                }

                                //naar het inlogscherm gaan als het registreren is afgerond
                                public void onFinish() {
                                    pDialog.dismiss();
                                    dialog.dismiss();
                                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                                    ft.replace(R.id.content_main, new Login());
                                    ft.commit();
                                }
                            }.start();
                        } else {
                            if (R.id.language_en == session.getLanguage()) {
                                dialog.setMessage("Email is in use");
                                dialog.show();
                            } else if (R.id.language_nl == session.getLanguage()) {
                                dialog.setMessage("Email is in gebruik");
                                dialog.show();
                            } else if (R.id.language_tr == session.getLanguage()) {
                                dialog.setMessage("E-posta kullanılır");
                                dialog.show();
                            }

                        }
                    } catch (JSONException e) {
                        registerError.setText(e.getMessage());
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    registerError.setText(error.getMessage());
                }
            }) {
                //parameters meesturen met de request naa de webserver
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap();
                    params.put("email", email);
                    params.put("naam", name);
                    params.put("postcode", zipcode);
                    params.put("wachtwoord", password);
                    return params;
                }
            };

            queue.add(registerRequest);
        } else {
            if (R.id.language_en == session.getLanguage()) {
                voornaamTxt.setError(getString(R.string.error_field_required_en));
                postcodeTxt.setError(getString(R.string.error_field_required_en));
                wachwoordTxt.setError(getString(R.string.error_field_required_en));
                emailTxt.setError(getString(R.string.error_field_required_en));
            } else if (R.id.language_nl == session.getLanguage()) {
                voornaamTxt.setError(getString(R.string.error_field_required_nl));
                postcodeTxt.setError(getString(R.string.error_field_required_nl));
                wachwoordTxt.setError(getString(R.string.error_field_required_nl));
                emailTxt.setError(getString(R.string.error_field_required_nl));
            } else if (R.id.language_tr == session.getLanguage()) {
                voornaamTxt.setError(getString(R.string.error_field_required_tr));
                postcodeTxt.setError(getString(R.string.error_field_required_tr));
                wachwoordTxt.setError(getString(R.string.error_field_required_tr));
                emailTxt.setError(getString(R.string.error_field_required_tr));
            }
        }
    }

    /**
     * Check of het registratieformulier volledig is ingevuld
     *
     * @return true als het ingevuld is, anders false
     */
    private boolean validateForm() {
        name = voornaamTxt.getText().toString();
        zipcode = postcodeTxt.getText().toString();
        password = wachwoordTxt.getText().toString();
        email = emailTxt.getText().toString();


        if (!name.isEmpty() && !zipcode.isEmpty() && !passwordDB.isEmpty() && !passwordDB.isEmpty()) {
            if (!name.isEmpty() && !zipcode.isEmpty() && !passwordDB.isEmpty() && !passwordDB.isEmpty()) {
                get_SHA_512_SecurePassword(passwordDB, "salt");
                return true;
            } else {
                return false;
            }
        }
        return false;
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
        getActivity().setTitle(getString(R.string.fragment_register_title_en));
        postcodeTxt.setHint(getString(R.string.text_zipcode_en));
        voornaamTxt.setHint(getString(R.string.text_name_en));
        emailTxt.setHint(getString(R.string.text_email_en));
        wachwoordTxt.setHint(getString(R.string.text_password_en));
        btnRegister2.setText(getString(R.string.button_new_user_en));
    }

    public void changeLanguageNl() {
        getActivity().setTitle(getString(R.string.fragment_register_title_nl));
        postcodeTxt.setHint(getString(R.string.text_zipcode_nl));
        voornaamTxt.setHint(getString(R.string.text_name_nl));
        emailTxt.setHint(getString(R.string.text_email_nl));
        wachwoordTxt.setHint(getString(R.string.text_password_nl));
        btnRegister2.setText(getString(R.string.button_new_user_nl));
    }

    public void changeLanguageTr() {
        getActivity().setTitle(getString(R.string.fragment_register_title_tr));
        postcodeTxt.setHint(getString(R.string.text_zipcode_tr));
        voornaamTxt.setHint(getString(R.string.text_name_tr));
        emailTxt.setHint(getString(R.string.text_email_tr));
        wachwoordTxt.setHint(getString(R.string.text_password_tr));
        btnRegister2.setText(getString(R.string.button_new_user_tr));
    }


    /**
     * Encrypt het wachtwoord met SHA-512
     * =======
     * <p>
     * <p>
     * /**
     * Encryt het wachtwoord met SHA-512
     * <p>
     * >>>>>>> 8605ba894e8b7535ed4af9789f3430b634517c85
     *
     * @param passwordToHash = het wachtwoord van de gebruiker
     * @param salt           =  de salt die gebruikt wordt bij het hashen
     */
    public void get_SHA_512_SecurePassword(String passwordToHash, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes("UTF-8"));
            byte[] bytes = md.digest(passwordToHash.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            passwordDB = sb.toString();
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

}




