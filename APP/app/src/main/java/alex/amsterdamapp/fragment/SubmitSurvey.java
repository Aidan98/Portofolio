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

import java.util.HashMap;
import java.util.Map;

import alex.amsterdamapp.R;
import alex.amsterdamapp.util.HandleUserSession;

import static alex.amsterdamapp.R.id.language_en;

/**
 * Created by aidan on 31/05/2017.
 */

public class SubmitSurvey extends Fragment {
    //UI controls
    private TextView surveyTxt;
    private EditText opinionTxt;
    private Button btnSave;
    private AlertDialog dialog;
    private ProgressDialog pDialog;
    private HandleUserSession user;
    //Attribuut voor text editor
    private String opinion;
    private int id;
    private HandleUserSession session;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        session = new HandleUserSession(getActivity());
        setHasOptionsMenu(true);
        getActivity().setTitle(getString(R.string.fragment_survey_title_nl));
        View view = inflater.inflate(R.layout.login, container, false);
        pDialog = new ProgressDialog(getActivity());
        return inflater.inflate(R.layout.submit_survey, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        surveyTxt = (TextView) view.findViewById(R.id.TxtSurvey);
        opinionTxt = (EditText) view.findViewById(R.id.opinion);
        btnSave = (Button) view.findViewById(R.id.BtnSave);

        user = new HandleUserSession(getActivity());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveOpinion();
            }
        });
    }

    private void saveOpinion() {
        dialog = new AlertDialog.Builder(getActivity()).create();
        pDialog.setMessage("Opslaan");
        pDialog.show();

        opinion = opinionTxt.getText().toString();
        id = user.getId();
        //URL van de php file
        String url = "https://pad1617.000webhostapp.com/enquete.php";
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        StringRequest registerOpinion = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    //de response is een JSON object
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean succes = jsonResponse.getBoolean("success");
                    if (succes) {
                        //een leuke timer die voor 2 seconden blijft staan
                        new CountDownTimer(2000, 1000) {
                            @Override
                            public void onTick(long millisUntilFinished) {
                                pDialog.dismiss();
                                dialog.setMessage(savedText());
                                dialog.show();
                            }

                            //naar het inlogscherm gaan als het registreren is afgerond
                            public void onFinish() {
                                dialog.dismiss();
                                FragmentTransaction ft = getFragmentManager().beginTransaction();
                                ft.replace(R.id.content_main, new Survey());
                                ft.commit();
                            }
                        }.start();
                    } else {
                        dialog.setMessage(errorText());
                        dialog.show();
                    }
                } catch (JSONException e) {
                    dialog.setMessage(e.getMessage());
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.setMessage(error.getMessage());
            }
        }) {
            //parameters meesturen
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap();
                params.put("opinion", opinion);
                params.put("id", Integer.valueOf(id).toString());
                return params;
            }
        };
        queue.add(registerOpinion);
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
        getActivity().setTitle(getString(R.string.fragment_survey_title_en));
        btnSave.setText(getString(R.string.button_save_en));
        opinionTxt.setHint(getString(R.string.extra_en));
        surveyTxt.setText(getString(R.string.fragment_survey_title_en));
    }

    public void changeLanguageNl() {
        getActivity().setTitle(getString(R.string.fragment_survey_title_nl));
        btnSave.setText(getString(R.string.button_save_nl));
        opinionTxt.setHint(getString(R.string.extra_nl));
        surveyTxt.setText(getString(R.string.fragment_survey_title_nl));
    }

    public void changeLanguageTr() {
        getActivity().setTitle(getString(R.string.fragment_survey_title_tr));
        btnSave.setText(getString(R.string.button_save_tr));
        opinionTxt.setHint(getString(R.string.extra_tr));
        surveyTxt.setText(getString(R.string.fragment_survey_title_tr));
    }

    private String savedText(){
        String save = "Uw antwoorden zijn opgeslagen";
        String save2 = "Your answers have been saved";
        String save3 = "Cevaplarınız kaydedilir";

        if (R.id.language_en == session.getLanguage()) {
            return save2;
        } else if (R.id.language_nl == session.getLanguage()) {
            return save;
        } else if (R.id.language_tr == session.getLanguage()) {
            return save3;
        }
        return save;
    }

    private String errorText(){
        String error = "Er is iets misgegaan, probeer het later nog eens";
        String error2 = "Something went wrong, try again later ";
        String error3 = "Bir şeyler ters gitti, daha sonra tekrar deneyin";

        if (R.id.language_en == session.getLanguage()) {
            return error;
        } else if (R.id.language_nl == session.getLanguage()) {
            return error2;
        } else if (R.id.language_tr == session.getLanguage()) {
            return error3;
        }
        return error;
    }

}



