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

                pb2.setVisibility(View.VISIBLE);

                //  CHECK IF THE FORM IS OKAY WITH NO ERRORS
                if (validateName() && validateSurname()) {
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
        EditText nameText = findViewById(R.id.etName);
        String val = nameText.getText().toString().trim();
        if (val.isEmpty()) {
            nameText.setError("Name can not be empty");
            return false;
        } else {
            nameText.setError(null);
            return true;
        }
    }

    public boolean validateSurname() {
        EditText surnameText = findViewById(R.id.etSurname);
        String val = surnameText.getText().toString().trim();
        if (val.isEmpty()) {
            surnameText.setError("Surname can not be empty");
            return false;
        } else {
            surnameText.setError(null);
            return true;
        }
    }

    public boolean validateUsername() {
        EditText usernameText = findViewById(R.id.etEmailAddress);
        String val = usernameText.getText().toString().trim();
        int count = 0;

        for(int i=0 ; i<val.length();i++) {
            if (Character.isDigit(val.charAt(i))) {
                count++;
            }
        }

        if(count == 0) {
            usernameText.setError("Username must contains at least one number");
            return false;
        }  else if (val.isEmpty()) {
            usernameText.setError("Username can not be empty");
            return false;
        } else if (val.length() > 20) {
            usernameText.setError("Username is too large");
            return false;
        } else if (val.length() < 6) {
            usernameText.setError("Username must contains up to 6 characters");
            return false;
        } else {
            usernameText.setError(null);
            return true;
        }
    }

    private boolean validateEmail() {
        EditText emailText = findViewById(R.id.etEmail);
        String val = emailText.getText().toString().trim();
        if (val.isEmpty()) {
            emailText.setError("Email can not be empty");
            return false;
        } else if (!val.contains("@")) {
            emailText.setError("Email must contains @");
            return false;
        }else {
            emailText.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        EditText password = findViewById(R.id.etPassword);
        String val = password.getText().toString().trim();

        String passType = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[.!@#$%^&+=])(?=\\S+$).{6,30}$";

        if(!val.matches(passType))
        {
            password.setError("Password must contains more tha 6 characters,lower case,capital case,number and symbol");
            return false;
        }
        else if(val.isEmpty())
        {
            password.setError("Field can not be empty");
            return false;
        }
        else
        {
            return true;
        }
    }

    private boolean validateConfPass() {
        EditText password = findViewById(R.id.etPassword);
        String val1 = password.getText().toString().trim();
        EditText conf = findViewById(R.id.etConfirmPass);
        String val2 = conf.getText().toString().trim();

        if(val2.isEmpty()) {
            password.setError("Field can not be empty");
            return false;
        } else if(!val1.equals(val2)) {
            conf.setError("Passwords do not match!");
            return false;
        } else {
            return true;
        }
    }
}