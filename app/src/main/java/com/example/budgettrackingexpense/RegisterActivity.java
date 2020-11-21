package com.example.budgettrackingexpense;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.budgettrackingexpense.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity  {
    EditText  mname,mcountry,memail,musername,mpassword,mpasswordconf;
    RadioGroup rbgroup;
    Button submit;
    String gender = "";
    ProgressBar pb2;

    FirebaseAuth fAuth;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mname = findViewById(R.id.name);
        musername = findViewById(R.id.newUsername);
        memail = findViewById(R.id.email);
        mpassword = findViewById(R.id.newPassword);
        mpasswordconf = findViewById(R.id.confirmPassword);
        mcountry = findViewById(R.id.country);
        submit = findViewById(R.id.submit);

        rbgroup = findViewById(R.id.rbGroup);

        reff = FirebaseDatabase.getInstance().getReference("users");
        fAuth = FirebaseAuth.getInstance();

        /*if (fAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }*/

        //  LOADING
        pb2 = findViewById(R.id.pb2);

        //  REGISTER BUTTON
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailtext = memail.getText().toString().trim();
                String name = mname.getText().toString().trim();
                String username = musername.getText().toString().trim();
                String passwordtext = mpassword.getText().toString().trim();
                String passwordconf = mpasswordconf.getText().toString().trim();
                String country = mcountry.getText().toString().trim();

                int selection = rbgroup.getCheckedRadioButtonId();
                if (selection == R.id.male) {
                    gender = "Male";
                } else if(selection == R.id.female)  {
                    gender = "Female";
                } else if(selection == R.id.other) {
                    gender = "Other";
                }

                //  CHECK IF THE FORM IS OKAY WITH NO ERRORS
                if (validateFullName() && validateGender() && validateCountry() && validateUsername() && validateEmail() && validatePassword() && validateConfPass()) {
                    pb2.setVisibility(View.VISIBLE);

                    fAuth.createUserWithEmailAndPassword(emailtext,passwordtext).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                User information = new User(name, gender, username, country);

                                FirebaseDatabase.getInstance().getReference("users")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()) {
                                            Toast.makeText(RegisterActivity.this, "The user registered succesfully",Toast.LENGTH_SHORT);
                                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                        }
                                        else {
                                            Toast.makeText(RegisterActivity.this, "The user  was not registered succesfully",Toast.LENGTH_SHORT);
                                        }
                                    }
                                });
                            } else {
                                Toast.makeText(getApplicationContext(), "Error: " + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }

    //  VALIDATIONS
    public boolean validateFullName() {
        EditText nameText = findViewById(R.id.name);
        String val = nameText.getText().toString().trim();
        if (val.isEmpty()) {
            nameText.setError("Full name cannot be empty");
            return false;
        } else {
            nameText.setError(null);
            return true;
        }
    }

    public boolean validateGender() {
        int selection = rbgroup.getCheckedRadioButtonId();
        RadioButton rbLastOption = findViewById(R.id.other);

        if (selection == -1) {
            rbLastOption.setError("You must specify your gender");
            return false;
        } else {
            rbLastOption.setError(null);
            return true;
        }
    }

    public boolean validateCountry() {
        EditText countryText = findViewById(R.id.country);
        String val = countryText.getText().toString().trim();
        if (val.isEmpty()) {
            countryText.setError("Country cannot be empty");
            return false;
        } else {
            countryText.setError(null);
            return true;
        }
    }

    public boolean validateUsername() {
        EditText usernameText = findViewById(R.id.newUsername);
        String val = usernameText.getText().toString().trim();
        int count = 0;

        if (val.isEmpty()) {
            usernameText.setError("Username cannot be empty");
            return false;
        } else if (val.length() > 20) {
            usernameText.setError("Username is too large");
            return false;
        } else if (val.length() < 6) {
            usernameText.setError("Username must have a length > 6");
            return false;
        } else {
            usernameText.setError(null);
            return true;
        }
    }

    private boolean validateEmail() {
        EditText emailText = findViewById(R.id.email);
        String val = emailText.getText().toString().trim();
        if (val.isEmpty()) {
            emailText.setError("Email cannot be empty");
            return false;
        } else if (!val.contains("@")) {
            emailText.setError("Email must contain '@'");
            return false;
        }else {
            emailText.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        EditText password = findViewById(R.id.newPassword);
        String val = password.getText().toString().trim();

        String passType = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[.!@#$%^&+=])(?=\\S+$).{6,30}$";

        if (!val.matches(passType)) {
            password.setError("Password must contain more tha 6 characters, a lower case character, a capital case character, a number and symbol");
            return false;
        } else if (val.isEmpty()) {
            password.setError("Password cannot be empty");
            return false;
        } else {
            return true;
        }
    }

    private boolean validateConfPass() {
        EditText password = findViewById(R.id.newPassword);
        String val1 = password.getText().toString().trim();
        EditText conf = findViewById(R.id.confirmPassword);
        String val2 = conf.getText().toString().trim();

        if (val2.isEmpty()) {
            password.setError("Password cannot be empty");
            return false;
        } else if (!val1.equals(val2)) {
            conf.setError("Passwords do not match");
            return false;
        } else {
            return true;
        }
    }
}