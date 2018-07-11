package alex.amsterdamapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import alex.amsterdamapp.R;
import alex.amsterdamapp.util.HandleUserSession;

/**
 * Created by alexa on 16-4-2017.
 */

public class FragmentSurvey extends Fragment {

    private HandleUserSession session;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        session = new HandleUserSession(getActivity());
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.survey, container, false);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle(getString(R.string.fragment_survey_title_nl));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //automatisch het kayboard laten verdwijnen zodat deze niet blijft staan vanuit een andere
        //view of actie
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
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
        getActivity().setTitle(getString(R.string.fragment_survey_title_en));
    }

    public void changeLanguageNl() {
        getActivity().setTitle(getString(R.string.fragment_survey_title_nl));
    }

    public void changeLanguageTr() {
        getActivity().setTitle(getString(R.string.fragment_survey_title_tr));
    }
}


