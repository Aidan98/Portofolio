package aidan.library;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

public class thread4Fragment extends AppCompatActivity {


private CardView cardView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_book1_info);

        cardView = findViewById(R.id.buy_book1);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(thread4Fragment.this, buyFragment.class);
                startActivity(intent);
            }
        });
    }

}
