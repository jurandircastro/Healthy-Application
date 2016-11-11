package healthy_application.views;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.beltrao.healthy.R;
import com.firebase.client.Firebase;

import healthy_application.webservices.FirebaseService;

public class RegisterActivity extends AppCompatActivity {

    private EditText loginField;
    private EditText passwordField;
    private ImageButton registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Firebase.setAndroidContext(this);

        loginField = (EditText) findViewById(R.id.password_field);
        passwordField = (EditText) findViewById(R.id.confirm_password);
        registerButton = (ImageButton) findViewById(R.id.btn_register);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

    }

    private void registerUser(){

        AlertDialog alertDialog = new AlertDialog.Builder(RegisterActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("CHEGOU AQUI");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

        int login = Integer.parseInt(loginField.getText().toString());
        String password = passwordField.getText().toString();

        try {
            FirebaseService.registerUser(this, login, password);
        }catch (Exception e){
            Toast.makeText(this, "Problemas no cadastro de usuário pelo Firebase!", Toast.LENGTH_LONG).show();
        }

    }

}
