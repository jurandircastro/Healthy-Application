package healthy_application.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.beltrao.healthy.R;
import com.firebase.client.Firebase;

import healthy_application.webservices.FirebaseService;

public class LoginActivity extends AppCompatActivity {

    private EditText loginField;
    private EditText passwordField;
    private ImageButton loginButton;
    private ImageButton registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Firebase.setAndroidContext(this);

        loginField = (EditText) findViewById(R.id.login_field);
        passwordField = (EditText) findViewById(R.id.password_field);

        loginButton = (ImageButton) findViewById(R.id.btn_login);
        registerButton = (ImageButton) findViewById(R.id.btn_register);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startLogin();
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private void startLogin(){

        int login = Integer.parseInt(loginField.getText().toString());
        String password = passwordField.getText().toString();

        try {
            FirebaseService.doLogin(this, login, password);
        }catch(Exception e){
            Toast.makeText(this, "Problemas no login pelo Firebase!", Toast.LENGTH_LONG).show();
        }
    }

}
