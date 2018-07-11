package alex.amsterdamapp.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import alex.amsterdamapp.R;
import alex.amsterdamapp.util.HandleUserSession;

import static alex.amsterdamapp.R.drawable.bestuurscommissie_nieuw_west;
import static android.R.attr.id;

/**
 * Created by Winston Nahar on 4-6-2017.
 */

public class Gallery extends Fragment implements View.OnClickListener{

    private ViewFlipper viewFlipper, viewFlipper2, viewFlipper3;
    private Button buttonNext, buttonPrevious;
    private HandleUserSession session;
    private TextView caption1, caption2, caption3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        session = new HandleUserSession(getActivity());
        setHasOptionsMenu(true);
        getActivity().setTitle(getString(R.string.fragment_gallery_title_nl));

        return inflater.inflate(R.layout.fragment_gallery, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        viewFlipper = (ViewFlipper) view.findViewById(R.id.viewFlipper);
        viewFlipper2 = (ViewFlipper) view.findViewById(R.id.viewFlipper2);
        viewFlipper3 = (ViewFlipper) view.findViewById(R.id.viewFlipper3);
        buttonNext = (Button) view.findViewById(R.id.buttonNext);
        buttonPrevious = (Button) view.findViewById(R.id.buttonPrevious);
        caption1 = (TextView) view.findViewById(R.id.caption1);
        caption2 = (TextView) view.findViewById(R.id.caption2);
        caption3 = (TextView) view.findViewById(R.id.caption3);

        buttonNext.setOnClickListener(this);
        buttonPrevious.setOnClickListener(this);

        super.onViewCreated(view, savedInstanceState);
    }

    public void onClick(View v){
        if (v == buttonNext){
            viewFlipper.showNext();
            viewFlipper2.showNext();
            viewFlipper3.showNext();
        } else if (v == buttonPrevious){
            viewFlipper.showPrevious();
            viewFlipper2.showPrevious();
            viewFlipper3.showPrevious();
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
        getActivity().setTitle(getString(R.string.fragment_gallery_title_en));
        buttonNext.setText(getString(R.string.button_gallery_next_en));
        buttonPrevious.setText(getString(R.string.button_gallery_previous_en));
        caption1.setText(getString(R.string.caption1_en));
        caption2.setText(getString(R.string.caption2_en));
        caption3.setText(getString(R.string.caption3_en));
    }

    public void changeLanguageNl() {
        getActivity().setTitle(getString(R.string.fragment_gallery_title_nl));
        buttonNext.setText(getString(R.string.button_gallery_next_nl));
        buttonPrevious.setText(getString(R.string.button_gallery_previous_nl));
        caption1.setText(getString(R.string.caption1_nl));
        caption2.setText(getString(R.string.caption2_nl));
        caption3.setText(getString(R.string.caption3_nl));
    }

    public void changeLanguageTr() {
        getActivity().setTitle(getString(R.string.fragment_gallery_title_tr));
        buttonNext.setText(getString(R.string.button_gallery_next_tr));
        buttonPrevious.setText(getString(R.string.button_gallery_previous_tr));
        caption1.setText(getString(R.string.caption1_tr));
        caption2.setText(getString(R.string.caption2_tr));
        caption3.setText(getString(R.string.caption3_tr));
    }

}


