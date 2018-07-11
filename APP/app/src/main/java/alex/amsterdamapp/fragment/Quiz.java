package alex.amsterdamapp.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import alex.amsterdamapp.R;
import alex.amsterdamapp.database.QuizRequest;
import alex.amsterdamapp.util.HandleUserSession;
import alex.amsterdamapp.util.QuizLibrary;

import static alex.amsterdamapp.R.id.language_en;

/**
 * Created by alexa on 16-4-2017.
 */

public class Quiz extends Fragment {

    private QuizLibrary mQuestionLibrary = new QuizLibrary();
    private AlertDialog.Builder scoreDialog;


    private TextView mScoreView;
    private TextView mScoreText;
    private TextView mQuestionView;
    private Button mButtonChoice1;
    private Button mButtonChoice2;
    private Button mButtonChoice3;

    private String mAnswer;
    private int mScore = 0;
    private int highScore;
    private int loggedInUser;
    private HandleUserSession session;
    private int mQuestionNumber = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.quiz, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        getActivity().setTitle(getString(R.string.fragment_quiz_title_nl));

        //de ingelogde gebruiker opslaan
        session = new HandleUserSession(getActivity());
        loggedInUser = session.getId();

        mScoreView = (TextView) view.findViewById(R.id.score);
        mScoreText = (TextView) view.findViewById(R.id.score_text);
        mQuestionView = (TextView) view.findViewById(R.id.question);
        mButtonChoice1 = (Button) view.findViewById(R.id.choice1);
        mButtonChoice2 = (Button) view.findViewById(R.id.choice2);
        mButtonChoice3 = (Button) view.findViewById(R.id.choice3);
        scoreDialog = new AlertDialog.Builder(getActivity());


        //Start of Button Listener for Button1
        mButtonChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //My logic for button goes here

                if (mButtonChoice1.getText() == mAnswer) {
                    mScore++; // this updates the score int variable
                    updateScore(mScore); // this converts the int variable to a string and adds it to mScoreview

                    //performs this check before you update the question
                    if (mQuestionNumber == QuizLibrary.mQuestions.length || mQuestionNumber == QuizLibrary.mQuestionsEn.length) {
                        scoreDialog.setMessage(setText() + mScore);
                        scoreDialog.setPositiveButton(setTextPlayAgain(), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Response.Listener<String> responseListener = new Response.Listener<String>() {

                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonresponse = new JSONObject(response);
                                            boolean succes = jsonresponse.getBoolean("success");
//                            if (succes) {
//                                Intent intent = new Intent(Register.this, Login.class);
//                                Register.this.startActivity(intent);
//                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };
                                QuizRequest registerRequest = new QuizRequest(loggedInUser, mScore, responseListener);
                                RequestQueue queue = Volley.newRequestQueue(getActivity());
                                queue.add(registerRequest);

                                FragmentTransaction ft = getFragmentManager().beginTransaction();

                                ft.replace(R.id.content_main, new Quiz());
                                ft.commit();
                            }

                        });
                        scoreDialog.setNegativeButton(setTextClose(), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Response.Listener<String> responseListener = new Response.Listener<String>() {

                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonresponse = new JSONObject(response);
                                            boolean succes = jsonresponse.getBoolean("success");
//                            if (succes) {
//                                Intent intent = new Intent(Register.this, Login.class);
//                                Register.this.startActivity(intent);
//                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };
                                QuizRequest registerRequest = new QuizRequest(loggedInUser, mScore, responseListener);
                                RequestQueue queue = Volley.newRequestQueue(getActivity());
                                queue.add(registerRequest);
                                getActivity().recreate();
                            }

                        });
                        scoreDialog.show();
                    } else {
                        updateQuestion();
                    }
                    //if the answer is wrong
                } else {

                    if (mQuestionNumber == QuizLibrary.mQuestions.length || mQuestionNumber == QuizLibrary.mQuestionsEn.length) {
                        scoreDialog.setMessage(setText() + mScore);
                        scoreDialog.setPositiveButton(setTextPlayAgain(), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Response.Listener<String> responseListener = new Response.Listener<String>() {

                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonresponse = new JSONObject(response);
                                            boolean succes = jsonresponse.getBoolean("success");
//                            if (succes) {
//                                Intent intent = new Intent(Register.this, Login.class);
//                                Register.this.startActivity(intent);
//                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };
                                QuizRequest registerRequest = new QuizRequest(loggedInUser, mScore, responseListener);
                                RequestQueue queue = Volley.newRequestQueue(getActivity());
                                queue.add(registerRequest);

                                FragmentTransaction ft = getFragmentManager().beginTransaction();

                                ft.replace(R.id.content_main, new Quiz());
                                ft.commit();
                            }

                        });
                        scoreDialog.setNegativeButton(setTextClose(), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Response.Listener<String> responseListener = new Response.Listener<String>() {

                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonresponse = new JSONObject(response);
                                            boolean succes = jsonresponse.getBoolean("success");
//                            if (succes) {
//                                Intent intent = new Intent(Register.this, Login.class);
//                                Register.this.startActivity(intent);
//                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };
                                QuizRequest registerRequest = new QuizRequest(loggedInUser, mScore, responseListener);
                                RequestQueue queue = Volley.newRequestQueue(getActivity());
                                queue.add(registerRequest);
                                getActivity().recreate();
                            }

                        });
                        scoreDialog.show();
                    } else {
                        updateQuestion();
                    }
                }
            }
        });

        //Start of Button Listner for Button1
        mButtonChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //My logic for button goes here

                if (mButtonChoice2.getText() == mAnswer) {
                    mScore++; // this updates the score int variable
                    updateScore(mScore); // this converts the int variable to a string and adds it to mScoreview

                    //performs this check before you update the question
                    if (mQuestionNumber == QuizLibrary.mQuestions.length || mQuestionNumber == QuizLibrary.mQuestionsEn.length) {
                        scoreDialog.setMessage(setText() + mScore);
                        scoreDialog.setPositiveButton(setTextPlayAgain(), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Response.Listener<String> responseListener = new Response.Listener<String>() {

                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonresponse = new JSONObject(response);
                                            boolean succes = jsonresponse.getBoolean("success");
//                            if (succes) {
//                                Intent intent = new Intent(Register.this, Login.class);
//                                Register.this.startActivity(intent);
//                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };
                                QuizRequest registerRequest = new QuizRequest(loggedInUser, mScore, responseListener);
                                RequestQueue queue = Volley.newRequestQueue(getActivity());
                                queue.add(registerRequest);

                                FragmentTransaction ft = getFragmentManager().beginTransaction();

                                ft.replace(R.id.content_main, new Quiz());
                                ft.commit();
                            }

                        });
                        scoreDialog.setNegativeButton(setTextClose(), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Response.Listener<String> responseListener = new Response.Listener<String>() {

                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonresponse = new JSONObject(response);
                                            boolean succes = jsonresponse.getBoolean("success");
//                            if (succes) {
//                                Intent intent = new Intent(Register.this, Login.class);
//                                Register.this.startActivity(intent);
//                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };
                                QuizRequest registerRequest = new QuizRequest(loggedInUser, mScore, responseListener);
                                RequestQueue queue = Volley.newRequestQueue(getActivity());
                                queue.add(registerRequest);
                                getActivity().recreate();
                            }

                        });
                        scoreDialog.show();
                    } else {
                        updateQuestion();
                    }
                    //if the answer is wrong
                } else {
                    if (mQuestionNumber == QuizLibrary.mQuestions.length || mQuestionNumber == QuizLibrary.mQuestionsEn.length) {
                        scoreDialog.setMessage(setText() + mScore);
                        scoreDialog.setPositiveButton(setTextPlayAgain(), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Response.Listener<String> responseListener = new Response.Listener<String>() {

                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonresponse = new JSONObject(response);
                                            boolean succes = jsonresponse.getBoolean("success");
//                            if (succes) {
//                                Intent intent = new Intent(Register.this, Login.class);
//                                Register.this.startActivity(intent);
//                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };
                                QuizRequest registerRequest = new QuizRequest(loggedInUser, mScore, responseListener);
                                RequestQueue queue = Volley.newRequestQueue(getActivity());
                                queue.add(registerRequest);

                                FragmentTransaction ft = getFragmentManager().beginTransaction();

                                ft.replace(R.id.content_main, new Quiz());
                                ft.commit();
                            }

                        });
                        scoreDialog.setNegativeButton(setTextClose(), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Response.Listener<String> responseListener = new Response.Listener<String>() {

                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonresponse = new JSONObject(response);
                                            boolean succes = jsonresponse.getBoolean("success");
//                            if (succes) {
//                                Intent intent = new Intent(Register.this, Login.class);
//                                Register.this.startActivity(intent);
//                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };
                                QuizRequest registerRequest = new QuizRequest(loggedInUser, mScore, responseListener);
                                RequestQueue queue = Volley.newRequestQueue(getActivity());
                                queue.add(registerRequest);
                                getActivity().recreate();
                            }

                        });
                        scoreDialog.show();
                    } else {
                        updateQuestion();
                    }
                }
            }
        });

        //Start of Button Listner for Button1
        mButtonChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //My logic for button goes here

                if (mButtonChoice3.getText() == mAnswer) {
                    mScore++; // this updates the score int variable
                    updateScore(mScore); // this converts the int variable to a string and adds it to mScoreview

                    //performs this check before you update the question
                    if (mQuestionNumber == QuizLibrary.mQuestions.length || mQuestionNumber == QuizLibrary.mQuestionsEn.length) {
                        scoreDialog.setMessage(setText() + mScore);
                        scoreDialog.setPositiveButton(setTextPlayAgain(), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Response.Listener<String> responseListener = new Response.Listener<String>() {

                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonresponse = new JSONObject(response);
                                            boolean succes = jsonresponse.getBoolean("success");
//                            if (succes) {
//                                Intent intent = new Intent(Register.this, Login.class);
//                                Register.this.startActivity(intent);
//                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };
                                QuizRequest registerRequest = new QuizRequest(loggedInUser, mScore, responseListener);
                                RequestQueue queue = Volley.newRequestQueue(getActivity());
                                queue.add(registerRequest);

                                FragmentTransaction ft = getFragmentManager().beginTransaction();

                                ft.replace(R.id.content_main, new Quiz());
                                ft.commit();
                            }

                        });
                        scoreDialog.setNegativeButton(setTextClose(), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Response.Listener<String> responseListener = new Response.Listener<String>() {

                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonresponse = new JSONObject(response);
                                            boolean succes = jsonresponse.getBoolean("success");
//                            if (succes) {
//                                Intent intent = new Intent(Register.this, Login.class);
//                                Register.this.startActivity(intent);
//                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };
                                QuizRequest registerRequest = new QuizRequest(loggedInUser, mScore, responseListener);
                                RequestQueue queue = Volley.newRequestQueue(getActivity());
                                queue.add(registerRequest);
                                getActivity().recreate();
                            }

                        });
                        scoreDialog.show();
                    } else {
                        updateQuestion();
                    }
                    //if the answer is wrong
                } else {
                    if (mQuestionNumber == QuizLibrary.mQuestions.length || mQuestionNumber == QuizLibrary.mQuestionsEn.length) {
                        scoreDialog.setMessage(setText() + mScore);
                        scoreDialog.setPositiveButton(setTextPlayAgain(), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Response.Listener<String> responseListener = new Response.Listener<String>() {

                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonresponse = new JSONObject(response);
                                            boolean succes = jsonresponse.getBoolean("success");
//                            if (succes) {
//                                Intent intent = new Intent(Register.this, Login.class);
//                                Register.this.startActivity(intent);
//                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };
                                QuizRequest registerRequest = new QuizRequest(loggedInUser, mScore, responseListener);
                                RequestQueue queue = Volley.newRequestQueue(getActivity());
                                queue.add(registerRequest);

                                FragmentTransaction ft = getFragmentManager().beginTransaction();

                                ft.replace(R.id.content_main, new Quiz());
                                ft.commit();
                            }

                        });
                        scoreDialog.setNegativeButton(setTextClose(), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Response.Listener<String> responseListener = new Response.Listener<String>() {

                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonresponse = new JSONObject(response);
                                            boolean succes = jsonresponse.getBoolean("success");
//                            if (succes) {
//                                Intent intent = new Intent(Register.this, Login.class);
//                                Register.this.startActivity(intent);
//                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };
                                QuizRequest registerRequest = new QuizRequest(loggedInUser, mScore, responseListener);
                                RequestQueue queue = Volley.newRequestQueue(getActivity());
                                queue.add(registerRequest);
                                getActivity().recreate();
                            }

                        });
                        scoreDialog.show();
                    } else {
                        updateQuestion();
                    }
                }
            }
        });


    }

    private void updateQuestion() {
        if (R.id.language_en == session.getLanguage()) {
            mQuestionView.setText(mQuestionLibrary.getQuestionEn(mQuestionNumber));
            mButtonChoice1.setText(mQuestionLibrary.getChoice1En(mQuestionNumber));
            mButtonChoice2.setText(mQuestionLibrary.getChoice2En(mQuestionNumber));
            mButtonChoice3.setText(mQuestionLibrary.getChoice3En(mQuestionNumber));

            mAnswer = mQuestionLibrary.getCorrectAnswerEn(mQuestionNumber);
            mQuestionNumber++;
        } else if (R.id.language_nl == session.getLanguage()) {
            mQuestionView.setText(mQuestionLibrary.getQuestion(mQuestionNumber));
            mButtonChoice1.setText(mQuestionLibrary.getChoice1(mQuestionNumber));
            mButtonChoice2.setText(mQuestionLibrary.getChoice2(mQuestionNumber));
            mButtonChoice3.setText(mQuestionLibrary.getChoice3(mQuestionNumber));

            mAnswer = mQuestionLibrary.getCorrectAnswer(mQuestionNumber);
            mQuestionNumber++;
        } else if (R.id.language_tr == session.getLanguage()) {
            mQuestionView.setText(mQuestionLibrary.getQuestionTr(mQuestionNumber));
            mButtonChoice1.setText(mQuestionLibrary.getChoice1Tr(mQuestionNumber));
            mButtonChoice2.setText(mQuestionLibrary.getChoice2Tr(mQuestionNumber));
            mButtonChoice3.setText(mQuestionLibrary.getChoice3Tr(mQuestionNumber));

            mAnswer = mQuestionLibrary.getCorrectAnswerTr(mQuestionNumber);
            mQuestionNumber++;
        }

    }

    private String setText(){
        String score = "Your score is: ";
        String score2 = "Uw score is: ";
        String score3 = "Skorunuz: ";

        if (R.id.language_en == session.getLanguage()) {
            return score;
        } else if (R.id.language_nl == session.getLanguage()) {
            return score2;
        } else if (R.id.language_tr == session.getLanguage()) {
            return score3;
        }
        return score;
    }

    private String setTextPlayAgain(){
        String playAgain = "Nog een keer spelen";
        String playAgain2 = "Play again";
        String playAgain3 = "Hala bir kez oynar";

        if (R.id.language_en == session.getLanguage()) {
            return playAgain2;
        } else if (R.id.language_nl == session.getLanguage()) {
            return playAgain;
        } else if (R.id.language_tr == session.getLanguage()) {
            return playAgain3;
        }
        return playAgain;
    }

    private String setTextClose(){
        String close = "Close";
        String close2 = "Afsluiten";
        String close3 = "Yakın: ";

        if (R.id.language_en == session.getLanguage()) {
            return close;
        } else if (R.id.language_nl == session.getLanguage()) {
            return close2;
        } else if (R.id.language_tr == session.getLanguage()) {
            return close3;
        }
        return close2;
    }

    private void updateScore(int mScore) {
        mScoreView.setText("" + mScore);
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
        getActivity().setTitle(getString(R.string.fragment_quiz_title_en));
        mQuestionView.setText(getString(R.string.textview_question_en));
        mScoreText.setText(getString(R.string.score_text_en));
        mButtonChoice1.setText(getString(R.string.button_choice1_en));
        mButtonChoice2.setText(getString(R.string.button_choice2_en));
        mButtonChoice3.setText(getString(R.string.button_choice3_en));
    }

    public void changeLanguageNl() {
        getActivity().setTitle(getString(R.string.fragment_quiz_title_nl));
        mQuestionView.setText(getString(R.string.textview_question_nl));
        mScoreText.setText(getString(R.string.score_text_nl));
        mButtonChoice1.setText(getString(R.string.button_choice1_nl));
        mButtonChoice2.setText(getString(R.string.button_choice2_nl));
        mButtonChoice3.setText(getString(R.string.button_choice3_nl));
    }

    public void changeLanguageTr() {
        getActivity().setTitle(getString(R.string.fragment_quiz_title_tr));
        mQuestionView.setText(getString(R.string.textview_question_tr));
        mScoreText.setText(getString(R.string.score_text_tr));
        mButtonChoice1.setText(getString(R.string.button_choice1_tr));
        mButtonChoice2.setText(getString(R.string.button_choice2_tr));
        mButtonChoice3.setText(getString(R.string.button_choice3_tr));
    }

}





