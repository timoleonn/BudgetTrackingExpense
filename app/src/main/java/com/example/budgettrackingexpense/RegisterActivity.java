package com.example.budgettrackingexpense;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText name,country,username,password,passwordConfirm,email;
    Button submit;
    ProgressBar pb2;
    String gender;
    TextView loginHere;
    FirebaseAuth fAuth;
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
        fAuth = FirebaseAuth.getInstance();
        loginHere = findViewById(R.id.loginHere);

        pb2=findViewById(R.id.pb2);

        RadioGroup group = findViewById(R.id.rbGroup);
        int selection= group.getCheckedRadioButtonId();
        if(selection == R.id.male)
        {
            gender="male";
        }else if(selection == R.id.female)
        {
            gender ="female";
        }else if(selection == R.id.other)
        {
            gender = "other";
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailText=email.getText().toString();
                String  passwordText = password.getText().toString();
                String passwordconfText = passwordConfirm.getText().toString();

                if (emailText.isEmpty())
                {
                    email.setError("Email must not be empty");
                }

            }
        });


    }
}