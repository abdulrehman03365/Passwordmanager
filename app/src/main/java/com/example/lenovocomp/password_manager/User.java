package com.example.lenovocomp.password_manager;

/**
 * Created by lenovo comp on 5/9/2018.
 */

public class User {
public  String username;
    public  String password;



    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    void User(){

    }

    public  User( String Username,String password)
    {

        username=Username;
        this.password=password;
    }
}
