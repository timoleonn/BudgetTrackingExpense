package com.example.budgettrackingexpense;

import android.widget.EditText;

public class User {
    public String fullname;
    public String country;
    public String username;
    public String gender;

    public User() {
    }

    public User(String nameText, String gender, String usernameText , String countryText) {
        this.fullname = nameText;
        this.country = countryText;
        this.username = usernameText;
        this.gender = gender;
    }
}
