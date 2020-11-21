package com.example.budgettrackingexpense;

import android.widget.EditText;

public class User {
    public String fullname;
    public String country;
    public String username;
    public String gender;
    public String occupation;

    public User(String nameText, String gender, String usernameText , String countryText, String occupation) {
        this.fullname = nameText;
        this.country = countryText;
        this.username = usernameText;
        this.gender = gender;
        this.occupation = occupation;
    }
}
