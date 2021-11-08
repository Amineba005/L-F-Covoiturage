package tn.esprit.lfcovoiturage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import tn.esprit.lfcovoiturage.dao.UserdDAO;
import tn.esprit.lfcovoiturage.database.MyDatabase;
import tn.esprit.lfcovoiturage.entities.User;


public class MainActivity extends AppCompatActivity {
    EditText usernameEt ;
    EditText passEt ;
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor ;
    Button signInBtn ;
    TextView forgtPw ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        } catch (Exception e) {

        }
        setContentView(R.layout.activity_main);

        usernameEt = findViewById(R.id.usernameEt);
        passEt = findViewById(R.id.passEt);
        signInBtn = findViewById(R.id.signinBtn);

        sharedPreferences = getSharedPreferences("my_pref",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        forgtPw = findViewById(R.id.ForgetPw);

        forgtPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,HomeActivity.class));
            }
        });

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = usernameEt.getText().toString();
                final String password = passEt.getText().toString();
                if (username.isEmpty() || (password.isEmpty())){
                    Toast.makeText(getApplicationContext(),"Fill all fields ",Toast.LENGTH_SHORT).show();
                }
                else {
                    MyDatabase myDatabase = MyDatabase.getDatabase(getApplicationContext());
                    UserdDAO userDAO = myDatabase.userdDAO();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            User user = userDAO.login(username,password);
                            if (user == null) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                user.setConnected(true);
                                    String userName = user.getUsername();
                                    startActivity(new Intent(MainActivity.this,HomeActivity.class)
                                    .putExtra("username",userName));
                                }
                            }

                    }).start();
                }
            }
        });

        TextView tvSignUp = findViewById(R.id.tvSignUp);
        TextView tvSignUpSecond = findViewById(R.id.tvSignUpSecond);

        tvSignUp.setOnClickListener(onClickSignUp());
        tvSignUpSecond.setOnClickListener(onClickSignUp());
    }


    private View.OnClickListener onClickSignUp() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                MainActivity.this.startActivity(intent);
            }
        };
    }
}