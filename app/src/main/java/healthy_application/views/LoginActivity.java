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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import healthy_application.models.User;
import healthy_application.webservices.FirebaseHelper;

public class LoginActivity extends AppCompatActivity {

    private EditText loginField;
    private EditText passwordField;
    private ImageButton loginButton;
    private ImageButton registerButton;

    private DatabaseReference db;
    private FirebaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Firebase.setAndroidContext(this);

        db = FirebaseDatabase.getInstance().getReference();
        helper = new FirebaseHelper(db);

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

        loginField = (EditText) findViewById(R.id.login_field);
        passwordField = (EditText) findViewById(R.id.password_field);

        int login = Integer.parseInt(loginField.getText().toString());
        String password = passwordField.getText().toString();

        User user = new User(login, password);

        if(login!=0 && password!= null && password.length()>0) {
            if(helper.doLogin(user)){
                loginField.setText("");
                passwordField.setText("");
                Toast.makeText(LoginActivity.this, "Login efetuado com sucesso!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(LoginActivity.this, StartScreenActivity.class));
            }else{
                Toast.makeText(LoginActivity.this, "Login e/ou senha incorretos!", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(LoginActivity.this, "Campos vazios!", Toast.LENGTH_LONG).show();
        }
    }
}
