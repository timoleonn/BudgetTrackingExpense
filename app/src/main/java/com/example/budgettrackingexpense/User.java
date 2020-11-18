package com.example.budgettrackingexpense;

import android.widget.EditText;

public class User {
    public String email;
    public String password;
    public String fullname;
    public String country;
    public String username;
    public String gender;

    public User(EditText memail, String name, String password, String username, String country, String gender) {
    }

    public User(String emailText,String nameText, String passwordText, String usernameText, String countryText,String gender) {
        this.email = emailText;
        this.password = passwordText;
        this.fullname = nameText;
        this.country = countryText;
        this.username = usernameText;
        this.gender = gender;
    }
}
