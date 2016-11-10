package com.example.beltrao.healthy.entities;

/**
 * Created by Home on 25/10/2016.
 */

public class User {
    private int id;
    private String password;
    private int login;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() { return password; }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLogin(){
        return login;
    }

    public void setLogin(int login){
        this.login = login;
    }
}
