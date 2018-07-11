package aidan.library;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.Menu;
import aidan.library.loginFragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private android.widget.EditText Name;
    private android.widget.EditText Password;
    private android.widget.TextView Info;
    private CardView cardView;
    private int counter = 5;
    private TextView errorMessage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);
        errorMessage = findViewById(R.id.errorLogin);
        Name = findViewById(R.id.etName);
        Password = findViewById(R.id.etPassword);
        Info = findViewById(R.id.tvInfo);
        cardView = findViewById(R.id.btnLogin);
        Info.setText("Number of attempts remaining: " + counter);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Name.getText().toString(), Password.getText().toString());
            }
        });
    }

    private void validate(String userName, String userPassword){
        if((userName.equals("Admin")) && (userPassword.equals("1234"))){
            Intent intent = new Intent(MainActivity.this, loginFragment.class);
            startActivity(intent);
        }else{
            counter--;
            errorMessage.setText("Username or password is invalid, try again");
            Info.setText("No of attempts remaining: " + String.valueOf(counter));

            if(counter == 0){
                cardView.setEnabled(false);
            }
        }
    }
}
