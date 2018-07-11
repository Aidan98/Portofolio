package aidan.library;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class thread2Fragment extends AppCompatActivity {

    private CardView cardView;
    private EditText et;
    private TextView txt;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_thread2);

        cardView = findViewById(R.id.send2);
        txt = findViewById(R.id.post_comment2);
        et = findViewById(R.id.post2ET);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String str = et.getText().toString();
                txt.setText(str);
            }
        });
    }

}
