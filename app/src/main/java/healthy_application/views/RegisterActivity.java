package healthy_application.views;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
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

public class RegisterActivity extends AppCompatActivity {

    private EditText loginField;
    private EditText passwordField;
    private ImageButton registerButton;

    private DatabaseReference db;
    private FirebaseHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Firebase.setAndroidContext(this);

        registerButton = (ImageButton) findViewById(R.id.btn_register);

        db = FirebaseDatabase.getInstance().getReference();
        helper = new FirebaseHelper(db);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

    }

    private void registerUser(){
        
        try {
            loginField = (EditText) findViewById(R.id.password_field);
            passwordField = (EditText) findViewById(R.id.confirm_password);
        }catch (Exception e){
            Toast.makeText(RegisterActivity.this, "Campos vazios!", Toast.LENGTH_LONG).show();
        }

        int login = Integer.parseInt(loginField.getText().toString());
        String password = passwordField.getText().toString();

        User user = new User(login, password);

        if(login!=0 && !password.equals("")) {
            if (helper.registerUser(user)) {
                loginField.setText("");
                passwordField.setText("");
                Toast.makeText(RegisterActivity.this, "Usuário cadastrado!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }else{
                Toast.makeText(RegisterActivity.this, "Usuário já existe!", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(RegisterActivity.this, "Campos vazios!", Toast.LENGTH_LONG).show();
        }
    }

}
