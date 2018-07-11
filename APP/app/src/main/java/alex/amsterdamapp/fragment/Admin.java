package alex.amsterdamapp.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import alex.amsterdamapp.R;

/**
 * Created by alexa on 23-5-2017.
 */

public class Admin extends Fragment {

    private ListView highscoreListView;
    private Button top3Button;
    private ProgressDialog dialog;
    private TextView mTextView;
    private AlertDialog.Builder alert;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Beheer");

        return inflater.inflate(R.layout.admin, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        highscoreListView = (ListView) view.findViewById(R.id.highscore_listview);
        mTextView = (TextView) view.findViewById(R.id.top3_textview);
        alert = new AlertDialog.Builder(getActivity());

        getTop3();

        if (highscoreListView != null) {
            highscoreListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    alert.setMessage(parent.getItemAtPosition(position).toString());
                    alert.show();
                }
            });
        }
    }


    /**
     * Haalt de gebruikers met de hoogste score op uit de database
     */
    private void getTop3() {
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Laden...");
        dialog.show();
        // requestQueue
        final RequestQueue queue = Volley.newRequestQueue(getActivity());
        String url = "https://pad1617.000webhostapp.com/QuizHighscore.php";

        // jsonarray object wat je terugkrijgt van de database request
        JsonArrayRequest jsArrRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        dialog.dismiss();

                        ArrayList<String> listdata = new ArrayList<>();
                        if (response != null) {
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    String regex = "/\\\\|\\{|\\}|\"|/g";
                                    listdata.add(response.getString(i).replaceAll(regex, "").replaceAll(",", " - "));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            //De ArrayList in een ListView zetten.
                            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                                    getActivity(),
                                    android.R.layout.simple_list_item_1,
                                    listdata);

                            highscoreListView.setAdapter(arrayAdapter);
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dialog.dismiss();
                        mTextView.setText(error.getMessage());
                    }
                });
        // Add the request to the RequestQueue.
        queue.add(jsArrRequest);
    }
}
