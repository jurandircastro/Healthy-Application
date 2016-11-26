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
    private ImageButton ocrButton;

    private DatabaseReference db;
    private FirebaseHelper helper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Firebase.setAndroidContext(this);

        registerButton = (ImageButton) findViewById(R.id.btn_register);
        ocrButton = (ImageButton) findViewById(R.id.btn_ocr);

        db = FirebaseDatabase.getInstance().getReference();
        helper = new FirebaseHelper(db);

        ocrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivity(intent);
            }
        });

    }

    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    private boolean validatePassword(String password){
        boolean hasUpper =  false;
        boolean hasLower = false;
        boolean hasNumber = false;

        if(password.length() >= 6) {
            for (int i = 0; i < password.length(); i++) {
                if (password.charAt(i) >= 'A' && password.charAt(i) <= 'Z') {
                    Toast.makeText(RegisterActivity.this,"Maiuscula",Toast.LENGTH_LONG).show();
                    hasUpper = true;
                }
                if (password.charAt(i) >= 'a' && password.charAt(i) <= 'z') {
                    Toast.makeText(RegisterActivity.this,"Minuscula",Toast.LENGTH_LONG).show();
                    hasLower = true;
                }
                if (password.charAt(i) >= '0' && password.charAt(i) <= '9') {
                    Toast.makeText(RegisterActivity.this,"Numero",Toast.LENGTH_LONG).show();
                    hasNumber = true;
                }
            }
        }
        return hasUpper == true && hasLower == true && hasNumber == true;
    }
    public void registerUser(View view){

        loginField = (EditText) findViewById(R.id.password_field);
        passwordField = (EditText) findViewById(R.id.confirm_password);
        int login = 0;
        String password = "";

        if (!isEmpty(loginField)) {
            login = Integer.parseInt(loginField.getText().toString());
        }
        else {
            loginField.setError("Valor errado");
        }
        if(validatePassword(passwordField.getText().toString()) || !isEmpty(passwordField)){
            password = passwordField.getText().toString();
        }
        else {
            passwordField.setError("Valor errado");
        }

        User user = new User(login, password);
        helper.registerUser(user);
        Toast.makeText(RegisterActivity.this,"Usuário cadastrado com sucesso",Toast.LENGTH_LONG).show();

        startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
    }
}
