package com.example.budgettrackingexpense;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText name,country,username,password,passwordConfirm,email;
    private Button submit;
    private ProgressBar pb2;
    private String gender;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        country = findViewById(R.id.country);
        username = findViewById(R.id.newUsername);
        password = findViewById(R.id.password);
        passwordConfirm = findViewById(R.id.confirmPassword);
        email = findViewById(R.id.email);

        submit = findViewById(R.id.submit);
        pb2 = findViewById(R.id.pb2);

        /*database = FirebaseDatabase.getInstance();
        mDatabase = database.getReference();*/
        mAuth = FirebaseAuth.getInstance();



        RadioGroup group = findViewById(R.id.rbGroup);
        int selection = group.getCheckedRadioButtonId();
        if (selection == R.id.male) {
            gender = "male";
        } else if (selection == R.id.female) {
            gender = "female";
        } else if (selection == R.id.other) {
            gender = "other";
        }
    }

    @Override
    public void onClick(View  v) {
        if(v.getId() == R.id.submit)
        {
            registerUser();
        }
    }

    private void registerUser()
    {
        String nameText = name.getText().toString().trim();
        String emailText = email.getText().toString().trim();
        String usernameText = username.getText().toString().trim();
        String countryText = country.getText().toString().trim();
        String passwordText = password.getText().toString().trim();
        String passwordconfText = passwordConfirm.getText().toString().trim();

        if (emailText.isEmpty())
        {
            email.setError("Fullname is required");
            email.requestFocus();
            return;
        }
        if (usernameText.isEmpty())
        {
            email.setError("username is required");
            email.requestFocus();
            return;
        }
        if (countryText.isEmpty())
        {
            email.setError("Country is required");
            email.requestFocus();
            return;
        }
        if (passwordText.isEmpty())
        {
            email.setError("password is required");
            email.requestFocus();
            return;
        }
        if (passwordText != passwordconfText)
        {
            email.setError("The two password have to match");
            email.requestFocus();
            return;
        }
        pb2.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(emailText,passwordconfText).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    User user = new User(emailText,nameText,passwordText,usernameText,countryText,gender);
                    FirebaseDatabase.getInstance().getReference("User")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(RegisterActivity.this,"The user has been registered succesfully",Toast.LENGTH_SHORT);
                                pb2.setVisibility(View.VISIBLE);
                            }
                            else{
                                Toast.makeText(RegisterActivity.this,"The user has not been registered succesfully",Toast.LENGTH_SHORT);
                                pb2.setVisibility(View.GONE);
                            }
                        }
                    });

                }else
                {
                    Toast.makeText(RegisterActivity.this,"The user has not been registered succesfully",Toast.LENGTH_SHORT);
                    pb2.setVisibility(View.GONE);                }
            }
        });
    }


}






 /* nameText = name.getText().toString();
                 emailText = email.getText().toString();
                 usernameText = username.getText().toString();
                 countryText = country.getText().toString();
                 passwordText = password.getText().toString();
                 passwordconfText = passwordConfirm.getText().toString();
                 if(nameText.isEmpty())
                 {
                     Toast.makeText(RegisterActivity.this, "Please make sure the name field is not empty",
                             Toast.LENGTH_SHORT).show();
                 }
                 if(emailText.isEmpty())
                 {
                     Toast.makeText(RegisterActivity.this, "Please make sure the email field is not empty",
                             Toast.LENGTH_SHORT).show();
                 }
                if(usernameText.isEmpty())
                {
                    Toast.makeText(RegisterActivity.this, "Please make sure the username field is not empty",
                            Toast.LENGTH_SHORT).show();
                }
                if(password.length()<5 )
                {
                    Toast.makeText(RegisterActivity.this, "Please make sure the password field is more than 5 characters",
                            Toast.LENGTH_SHORT).show();
                }
                if(password != passwordConfirm )
                {
                    Toast.makeText(RegisterActivity.this, "Please make sure the two password fields match each other",
                            Toast.LENGTH_SHORT).show();
                }*/





