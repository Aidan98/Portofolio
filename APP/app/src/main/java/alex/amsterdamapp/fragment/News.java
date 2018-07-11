package alex.amsterdamapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import alex.amsterdamapp.R;
import alex.amsterdamapp.util.HandleUserSession;

/**
 * Created by alexa on 24-5-2017.
 */

public class News extends Fragment {

    //UI controls
    private Button paroolBtn;
    private Button gemeenteAdamBtn;
    private Button volkskrant;
    private TextView textView;
    private HandleUserSession session;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        session = new HandleUserSession(getActivity());
        setHasOptionsMenu(true);
        getActivity().setTitle(getString(R.string.fragment_news_title_nl));
        return inflater.inflate(R.layout.news, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


        super.onViewCreated(view, savedInstanceState);

        textView = (TextView) view.findViewById(R.id.textView7);

        volkskrant = (Button) view.findViewById(R.id.volkskrant_button);
        volkskrant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadVolkskrant();
            }
        });

        paroolBtn = (Button) view.findViewById(R.id.parool_button);
        paroolBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadParool();
            }
        });

        gemeenteAdamBtn = (Button) view.findViewById(R.id.adam_button);
        gemeenteAdamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAdam();
            }
        });
    }

    private void loadVolkskrant() {
        //laadt het nieuws van het parool
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_main, new NewsVolkskrant());
        ft.commit();
    }

    private void loadParool() {
        //laadt het nieuws van het parool
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_main, new NewsParool());
        ft.commit();
    }

    private void loadAdam() {
        //laadt het nieuws van het parool
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_main, new NewsGemeente());
        ft.commit();
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
        getActivity().setTitle(getString(R.string.fragment_news_title_en));
        textView.setText(getString(R.string.text_title_en));
    }

    public void changeLanguageNl() {
        getActivity().setTitle(getString(R.string.fragment_news_title_nl));
        textView.setText(getString(R.string.text_title_nl));
    }

    public void changeLanguageTr() {
        getActivity().setTitle(getString(R.string.fragment_news_title_tr));
        textView.setText(getString(R.string.text_title_tr));
    }

}
