package healthy_application.webservices;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import healthy_application.models.User;
import healthy_application.views.RegisterActivity;

/**
 * Created by gleyd on 10/11/2016.
 */

public class FirebaseService {
    private static final Firebase FIREBASE = new Firebase("https://healthy-application-2161e.firebaseio.com/");

    public static void doLogin(final Activity activity, final int login, final String password) {

        FIREBASE.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                List<User> users = (ArrayList<User>) snapshot.getValue();
                for (User user : users) {
                  if (user.getLogin()==login){
                      if (user.getPassword().equals(password)) {
                           activity.startActivity(new Intent(activity, RegisterActivity.class));
                          break;
                      } else {
                          Toast.makeText(activity, "Usuário/Senha incorreta!", Toast.LENGTH_LONG).show();
                          break;
                      }
                  }
                }
            }
            @Override public void onCancelled(FirebaseError error) {}
        });
    }

    public static void registerUser(final Activity activity, int login, final String password){
        final User user = new User(login, password);
        List<User> users = new ArrayList<>();
        users.add(user);

                FIREBASE.child("users").setValue(users);

        /*FIREBASE.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              List<User> users = (ArrayList<User>) dataSnapshot.getValue();
                if(!users.contains(user)) {
                    users.add(user);
                }else{
                    Toast.makeText(activity, "Usuário já existe!", Toast.LENGTH_LONG).show();
                }
                    FIREBASE.child("users").setValue(users);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
*/

    }

}
