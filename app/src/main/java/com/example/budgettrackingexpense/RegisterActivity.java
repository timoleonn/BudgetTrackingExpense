package com.example.budgettrackingexpense;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        if (fAuth.getCurrentUser() != null)
        {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailText=email.getText().toString();
                String  passwordText = password.getText().toString();
                String passwordconfText = passwordConfirm.getText().toString();

                // CHECK FOR THE VALID EMAIL AND PASSWORD
                if (emailText.isEmpty())
                {
                    email.setError("Email must not be empty");
                    return;
                }
                /*if (passwordText.isEmpty() )
                    {
                        password.setError("The password must not be empty");
                        return;
                    }
                else*/
                Pattern pattern;
                Matcher matcher;
                final String PASSWORD_PATTERN ="(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])";
                pattern = Pattern.compile(PASSWORD_PATTERN);
                matcher = pattern.matcher(passwordText);

                if ((passwordText.length()<5) || matcher.equals("")) {
                    password.setError("Not a valid password. Your password must be at least 5 characters and contain at least one letter, one number and one special character(@#$%^+=!)");
                }

                if (passwordconfText != passwordText )
                {
                    passwordConfirm.setError("Please check again the password. The two fields do not match");
                    return;
                }

                pb2.setVisibility(View.VISIBLE);
                // REGISTER THE USER IN THE FIREBASE
                 fAuth.createUserWithEmailAndPassword(emailText,passwordconfText).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if (task.isSuccessful())
                         {
                             Toast.makeText(RegisterActivity.this,"User succesfully Registerd",Toast.LENGTH_SHORT).show();
                             startActivity(new Intent(getApplicationContext(), MainActivity.class));
                         }else{
                             Toast.makeText(RegisterActivity.this,"Their was an error with our resgistration"+ task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                         }
                     }
                 });
            }
        });


    }
}