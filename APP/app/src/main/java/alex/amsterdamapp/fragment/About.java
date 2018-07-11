package alex.amsterdamapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import alex.amsterdamapp.R;
import alex.amsterdamapp.util.HandleUserSession;

/**
 * Created by alexa on 23-5-2017.
 */

public class About extends Fragment {
    private HandleUserSession session;
    private TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setHasOptionsMenu(true);

        session = new HandleUserSession(getActivity());
        getActivity().setTitle(getString(R.string.fragment_about_title_nl));
        return inflater.inflate(R.layout.about, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView = (TextView)(view.findViewById(R.id.textView3));
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
        getActivity().setTitle(getString(R.string.fragment_about_title_en));
        textView.setText(getString(R.string.text_about_en));
    }

    public void changeLanguageNl() {
        getActivity().setTitle(getString(R.string.fragment_about_title_nl));
        textView.setText(getString(R.string.text_about_nl));
    }

    public void changeLanguageTr() {
        getActivity().setTitle(getString(R.string.fragment_about_title_tr));
        textView.setText(getString(R.string.text_about_tr));
    }
}
