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

public class QuizMenu extends Fragment {

    //UI controls
    private Button quiz_btn, topo_btn;
    private TextView textView;
    private HandleUserSession session;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setHasOptionsMenu(true);

        session = new HandleUserSession(getActivity());
        getActivity().setTitle(getString(R.string.fragment_quizmenu_title_nl));

        return inflater.inflate(R.layout.fragment_quiz_menu, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView = (TextView) view.findViewById(R.id.textView7);

        quiz_btn = (Button) view.findViewById(R.id.quiz_quiz);
        quiz_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadQuiz();
            }
        });

        topo_btn = (Button) view.findViewById(R.id.topoquiz_button);
        topo_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadTopoQuiz();
            }
        });
        
    }


    private void loadQuiz() {
        //laadt de quiz
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_main, new Quiz());
        ft.commit();
    }

    private void loadTopoQuiz() {
        //laadt de topoQuiz
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.content_main, new TopoQuiz());
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
        getActivity().setTitle(getString(R.string.fragment_quizmenu_title_en));
        quiz_btn.setText(getString(R.string.button_quiz_en));
        topo_btn.setText(getString(R.string.button_topoquiz_en));
        textView.setText(getString(R.string.text_quiz_en));
    }

    public void changeLanguageNl() {
        getActivity().setTitle(getString(R.string.fragment_quizmenu_title_nl));
        quiz_btn.setText(getString(R.string.button_quiz_nl));
        topo_btn.setText(getString(R.string.button_topoquiz_nl));
        textView.setText(getString(R.string.text_quiz_nl));
    }

    public void changeLanguageTr() {
        getActivity().setTitle(getString(R.string.fragment_quizmenu_title_tr));
        quiz_btn.setText(getString(R.string.button_quiz_tr));
        topo_btn.setText(getString(R.string.button_topoquiz_tr));
        textView.setText(getString(R.string.text_quiz_tr));
    }
}
