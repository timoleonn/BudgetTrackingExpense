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
    FirebaseAuth fAuth;
    ProgressBar pb2;
    int selection;
    String gender;
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
        selection = rbgroup.getCheckedRadioButtonId();
        if(selection == R.id.male)
        {
            gender ="male";
        }else if(selection == R.id.female)
        {
            gender ="female";
        }else if(selection == R.id.other)
        {
            gender ="other";
        }

        pb2 = findViewById(R.id.pb2);

        fAuth = FirebaseAuth.getInstance();

        /*if (fAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }*/

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = memail.getText().toString().trim();
                String name = mname.getText().toString().trim();
                String username = musername.getText().toString().trim();
                String password = mpassword.getText().toString().trim();
                String passwordconf = mpasswordconf.getText().toString().trim();
                String country = mcountry.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    memail.setError("Email is requested");
                    return;
                }
                if(TextUtils.isEmpty(name))
                {
                    mname.setError("Name is requested");
                    return;
                }
                if(TextUtils.isEmpty(username))
                {
                    musername.setError("username is requested");
                    return;
                }
                if(TextUtils.isEmpty(password) || (password.length()<4))
                {
                    mpassword.setError("Password is requested or the password is too short");
                    return;
                }
                if(TextUtils.isEmpty(country) )
                {
                    mcountry.setError("Country is requested");
                    return;
                }
                /*if (password != passwordconf)
                {
                    mpasswordconf.setError("The 2 passwords have to match");
                    return;
                }*/

                pb2.setVisibility(View.VISIBLE);
                System.out.println("SOMETHING WENT WRONG 1" );
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            System.out.println("SOMETHING WENT WRONG2" + task.getException().getMessage());
                            User user = new User(email,name,password,username,country,gender);
                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful())
                                    {
                                        Toast.makeText(RegisterActivity.this, "THe user registered succesfully",Toast.LENGTH_SHORT);
                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                    }
                                    else
                                    {
                                        Toast.makeText(RegisterActivity.this, "THe user  was not registered succesfully",Toast.LENGTH_SHORT);
                                    }
                                    System.out.println("SOMETHING WENT WRONG 3" + task.getException().getMessage());
                                }
                            });

                        }
                        else
                        {
                            Toast.makeText(RegisterActivity.this, "THe user  was not registered succesfully",Toast.LENGTH_SHORT);
                        }
                    }
                });
            }
        });
    }
}