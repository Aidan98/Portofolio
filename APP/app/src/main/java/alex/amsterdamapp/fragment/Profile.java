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
import android.widget.Button;
import android.widget.TextView;

import alex.amsterdamapp.R;
import alex.amsterdamapp.util.HandleUserSession;

/**
 * Created by alexa on 12-5-2017.
 */

public class Profile extends Fragment {
    private HandleUserSession session;
    private Button signOutButton;
    private TextView lastname, firstname;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        session = new HandleUserSession(getActivity());
        setHasOptionsMenu(true);

        return inflater.inflate(R.layout.profile, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Mijn Profiel");
        session = new HandleUserSession(getActivity());

        signOutButton = (Button) view.findViewById(R.id.sign_out_button);
        lastname = (TextView) view.findViewById(R.id.lastname);
        firstname = (TextView) view.findViewById(R.id.firstname);

        //uitloggen en de session verwijderen
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                session.removeSession();

//                Intent i = getActivity().getPackageManager()
//                        .getLaunchIntentForPackage( getActivity().getPackageName() );
//                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//                startActivity(i);

                getActivity().recreate();

                //doorverwijzen naar de login pagina
//                Fragment fragment = new Login();
//                FragmentTransaction ft = getFragmentManager().beginTransaction();
//                ft.replace(R.id.content_main, fragment);
//                ft.commit();
            }
        });
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
        getActivity().setTitle("My Profile");
        signOutButton.setText("Log Out");
        lastname.setHint("Last Name");
        firstname.setHint("First Name");
    }

    public void changeLanguageNl() {
        getActivity().setTitle("Mijn Profiel");
        signOutButton.setText("Uitloggen");
        lastname.setHint("Achternaam");
        firstname.setHint("Voornaam");
    }

    public void changeLanguageTr() {
        getActivity().setTitle("Benim Profilim");
        signOutButton.setText("Oturumu");
        lastname.setHint("Soyadı");
        firstname.setHint("İsim");
    }
}
