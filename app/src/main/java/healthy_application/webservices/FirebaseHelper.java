package healthy_application.webservices;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

import healthy_application.models.User;

/**
 * Created by gleydson on 10/11/2016.
 */

public class FirebaseHelper {

    private DatabaseReference db;
    private boolean registred;
    private ArrayList<User> users;

    public FirebaseHelper(DatabaseReference db){
        this.db = db;
        users = new ArrayList<>();
    }

    public boolean doLogin(User user) {

        users = getUsers();
        boolean loged = false;


        for (User u: users) {
            if (u.getLogin() == user.getLogin()) {
                if (u.getPassword().equals(user.getPassword())) {
                    loged = true;
                    break;
                }
            }
        }
        return loged;
    }

    public boolean registerUser(User user){

        users = getUsers();

        if(user==null){
            registred = false;
        } else{
             try{
                 if (!users.contains(user)) {
                     db.child("Users").push().setValue(user);
                     registred = true;
                 }
            }catch(DatabaseException e){
                e.printStackTrace();
                registred = false;
            }

        }
        return registred;
    }

    private void updateList(DataSnapshot dataSnapshot){

        users.clear();

        for (DataSnapshot ds: dataSnapshot.getChildren()) {
            User user = ds.getValue(User.class);
            users.add(user);
        }
    }

    private ArrayList<User> getUsers(){
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                updateList(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                updateList(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                updateList(dataSnapshot);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return users;
    }




}
