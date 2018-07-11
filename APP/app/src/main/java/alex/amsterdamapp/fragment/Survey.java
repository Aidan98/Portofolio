package alex.amsterdamapp.fragment;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import alex.amsterdamapp.R;
import alex.amsterdamapp.util.HandleUserSession;
import alex.amsterdamapp.util.SurveyLibrary;

/**
 * Created by alexa on 16-4-2017.
 */

public class Survey extends Fragment {
    private SurveyLibrary questionLibrary = new SurveyLibrary();
    private HandleUserSession session;
    private TextView mQuestionView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;
    private Button mButtonChoice4;
    private Button mButtonChoice5;
    private TextView scoreText;
    private String mAnswer;
    private TextView questionView;
    private TextView questionTotal;
    private int mQuestionNumber = 0;
    private int qNumber = 1;
    private int total;
    private int one = 1;
    private AlertDialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        session = new HandleUserSession(getActivity());
        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.login, container, false);
        return inflater.inflate(R.layout.survey, container, false);

    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        getActivity().setTitle(getString(R.string.fragment_survey_title_nl));
        scoreText = (TextView) view.findViewById(R.id.score_text);

        getActivity().setTitle("Enquête");
        questionView = (TextView) view.findViewById(R.id.number);
        questionTotal = (TextView) view.findViewById(R.id.total_text);


        mQuestionView = (TextView) view.findViewById(R.id.question);
        mButtonChoice1 = (Button) view.findViewById(R.id.choice1);
        mButtonChoice2 = (Button) view.findViewById(R.id.choice2);
        mButtonChoice3 = (Button) view.findViewById(R.id.choice3);
        mButtonChoice4 = (Button) view.findViewById(R.id.choice4);
        mButtonChoice5 = (Button) view.findViewById(R.id.choice5);

        total = SurveyLibrary.mQuestions.length + one;
        questionTotal.setText("" + total);
        mButtonChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (qNumber == total) {
//                    dialog.setMessage("Klaar!");
//                    dialog.show();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    Fragment fragment = new SubmitSurvey();
                    ft.replace(R.id.content_main, fragment);
                    ft.commit();
                } else {
                    qNumber = qNumber + one;
                    updateNumber(qNumber);
                    updateQuestion();
                }
            }
        });
        mButtonChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (qNumber == total) {
//                    dialog.setMessage("Klaar!");
//                    dialog.show();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    Fragment fragment = new SubmitSurvey();
                    ft.replace(R.id.content_main, fragment);
                    ft.commit();
                } else {
                    qNumber = qNumber + one;
                    updateNumber(qNumber);
                    updateQuestion();
                }
                //This line of code is optiona
//                    Toast.makeText(Survey.this, "okay", Toast.LENGTH_SHORT).show();


            }
        });

        mButtonChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (qNumber == total) {
//                    dialog.setMessage("Klaar!");
//                    dialog.show();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    Fragment fragment = new SubmitSurvey();
                    ft.replace(R.id.content_main, fragment);
                    ft.commit();
                } else {
                    qNumber = qNumber + one;
                    updateNumber(qNumber);
                    updateQuestion();
                }
            }
        });
        mButtonChoice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (qNumber == total) {
//                    dialog.setMessage("Klaar!");
//                    dialog.show();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    Fragment fragment = new SubmitSurvey();
                    ft.replace(R.id.content_main, fragment);
                    ft.commit();
                } else {
                    qNumber = qNumber + one;
                    updateNumber(qNumber);
                    updateQuestion();
                }
            }
        });
        mButtonChoice5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (qNumber == total) {
//                    dialog.setMessage("Klaar!");
//                    dialog.show();
                    FragmentTransaction ft = getFragmentManager().beginTransaction();
                    Fragment fragment = new SubmitSurvey();
                    ft.replace(R.id.content_main, fragment);
                    ft.commit();
                } else {
                    qNumber = qNumber + one;
                    updateNumber(qNumber);
                    updateQuestion();
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //automatisch het keyboard laten verdwijnen zodat deze niet blijft staan vanuit een andere
        //view of actie
        final InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    private void updateQuestion() {
        if (R.id.language_en == session.getLanguage()) {
            mQuestionView.setText(questionLibrary.getQuestionEn(mQuestionNumber));
            mButtonChoice1.setText(questionLibrary.getChoice1En(mQuestionNumber));
            mButtonChoice2.setText(questionLibrary.getChoice2En(mQuestionNumber));
            mButtonChoice3.setText(questionLibrary.getChoice3En(mQuestionNumber));
            mButtonChoice4.setText(questionLibrary.getChoice4En(mQuestionNumber));
            mButtonChoice5.setText(questionLibrary.getChoice5En(mQuestionNumber));

            mButtonChoice3.setVisibility(View.INVISIBLE);
            mButtonChoice4.setVisibility(View.INVISIBLE);
            mButtonChoice5.setVisibility(View.INVISIBLE);
            mQuestionNumber++;
        } else if (R.id.language_nl == session.getLanguage()) {
            mQuestionView.setText(questionLibrary.getQuestion(mQuestionNumber));
            mButtonChoice1.setText(questionLibrary.getChoice1(mQuestionNumber));
            mButtonChoice2.setText(questionLibrary.getChoice2(mQuestionNumber));
            mButtonChoice3.setText(questionLibrary.getChoice3(mQuestionNumber));
            mButtonChoice4.setText(questionLibrary.getChoice4(mQuestionNumber));
            mButtonChoice5.setText(questionLibrary.getChoice5(mQuestionNumber));

            mButtonChoice3.setVisibility(View.INVISIBLE);
            mButtonChoice4.setVisibility(View.INVISIBLE);
            mButtonChoice5.setVisibility(View.INVISIBLE);
            mQuestionNumber++;
        } else if (R.id.language_tr == session.getLanguage()) {
            mQuestionView.setText(questionLibrary.getQuestionTr(mQuestionNumber));
            mButtonChoice1.setText(questionLibrary.getChoice1Tr(mQuestionNumber));
            mButtonChoice2.setText(questionLibrary.getChoice2Tr(mQuestionNumber));
            mButtonChoice3.setText(questionLibrary.getChoice3Tr(mQuestionNumber));
            mButtonChoice4.setText(questionLibrary.getChoice4Tr(mQuestionNumber));
            mButtonChoice5.setText(questionLibrary.getChoice5Tr(mQuestionNumber));

            mButtonChoice3.setVisibility(View.INVISIBLE);
            mButtonChoice4.setVisibility(View.INVISIBLE);
            mButtonChoice5.setVisibility(View.INVISIBLE);
            mQuestionNumber++;
        }

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
        scoreText.setText(getString(R.string.score_text_survey_en));
        mQuestionView.setText(getString(R.string.textview_age_en));
    }

    public void changeLanguageNl() {
        getActivity().setTitle(getString(R.string.fragment_survey_title_nl));
        scoreText.setText(getString(R.string.score_text_survey_nl));
        mQuestionView.setText(getString(R.string.textview_age_nl));
    }

    public void changeLanguageTr() {
        getActivity().setTitle(getString(R.string.fragment_survey_title_tr));
        scoreText.setText(getString(R.string.score_text_survey_tr));
        mQuestionView.setText(getString(R.string.textview_age_tr));
    }


    private void updateNumber(int point) {
        questionView.setText("" + qNumber);
    }

}

