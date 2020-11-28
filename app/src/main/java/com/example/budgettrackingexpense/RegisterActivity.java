package com.example.budgettrackingexpense;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.budgettrackingexpense.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class RegisterActivity extends AppCompatActivity  {
    EditText  mname,mcountry,memail,musername,mpassword,mpasswordconf, moccupation;
    RadioGroup rbgroup, rbOccupation;
    Button submit;
    String gender, occupation = "";
    ProgressBar pb2;

    FirebaseAuth fAuth;
    DatabaseReference reff;

    private String register_file_name = "register.txt";

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
        submit = findViewById(R.id.sumbition);

        rbgroup = findViewById(R.id.rbGroup);
        rbOccupation = findViewById(R.id.rbOccupation);

        reff = FirebaseDatabase.getInstance().getReference("users");
        fAuth = FirebaseAuth.getInstance();

        //  LOADING
        pb2 = findViewById(R.id.pb2);

        //  GET FROM SAVING STATE
        try {
            FileInputStream fin = openFileInput(register_file_name);
            DataInputStream din = new DataInputStream(fin);
            InputStreamReader isr = new InputStreamReader(din);
            BufferedReader br = new BufferedReader(isr);

            int i = 0;
            String lines[] = new String[6];
            String strLine;

            while ((strLine = br.readLine()) != null) {
                lines[i] = strLine;
                i++;
            }

            mname.setText(lines[0]);

            RadioButton male = findViewById(R.id.male);
            RadioButton female = findViewById(R.id.female);
            RadioButton other = findViewById(R.id.other);
            RadioButton occupationStudent = findViewById(R.id.rbOccupationStudent);
            RadioButton occupationOther = findViewById(R.id.rbOccupationOther);

            if (lines[1].equals("")) {
                male.setChecked(false);
                female.setChecked(false);
                other.setChecked(false);
            } else if (lines[1].equals("Male")) {
                male.setChecked(true);
                female.setChecked(false);
                other.setChecked(false);
            } else if (lines[1].equals("Female")) {
                male.setChecked(false);
                female.setChecked(true);
                other.setChecked(false);
            } else if (lines[1].equals("Other")) {
                male.setChecked(false);
                female.setChecked(false);
                other.setChecked(true);
            }

            mcountry.setText(lines[2]);
            musername.setText(lines[3]);
            memail.setText(lines[4]);

            System.out.println("IS EMPTY: " + lines[5].isEmpty());
            System.out.println("1: " + lines[5]);

            if (lines[5].equals("")) {
                occupationStudent.setChecked(false);
                occupationOther.setChecked(false);
            } else if (lines[5].equals("Student")) {
                occupationStudent.setChecked(true);
                occupationOther.setChecked(false);
            } else if (lines[5].equals("Other")) {
                occupationStudent.setChecked(false);
                occupationOther.setChecked(true);
            }

            fin.close();
            Toast.makeText(getApplicationContext(), "You didn't loose anything, all data is still here!", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //  LOGIN HERE
        Button btnLoginHere = findViewById(R.id.btnLoginHere);
        btnLoginHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  SAVING STATE APPLICATION JUST IN CASE OF MISS CLICK
                try {
                    FileOutputStream fout = openFileOutput(register_file_name, 0);
                    String nameText = mname.getText().toString().trim() + "\n";
                    int selection = rbgroup.getCheckedRadioButtonId();
                    if (selection == R.id.male) {
                        gender = "Male" + "\n";
                    } else if(selection == R.id.female)  {
                        gender = "Female" + "\n";
                    } else if(selection == R.id.other) {
                        gender = "Other" + "\n";
                    } else {
                        gender = "\n";
                    }

                    String countryText = mcountry.getText().toString().trim() + "\n";
                    String usernameText = musername.getText().toString().trim() + "\n";
                    String emailText = memail.getText().toString().trim() + "\n";

                    int selection2 = rbOccupation.getCheckedRadioButtonId();
                    if (selection2 == R.id.rbOccupationStudent) {
                        occupation = "Student" + "\n";
                    } else if (selection2 == R.id.rbOccupationOther) {
                        occupation = "Other" + "\n";
                    } else {
                        occupation = "\n";
                    }

                    fout.write(nameText.getBytes());
                    fout.write(gender.getBytes());
                    fout.write(countryText.getBytes());
                    fout.write(usernameText.getBytes());
                    fout.write(emailText.getBytes());
                    fout.write(occupation.getBytes());
                    fout.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Oops, something went wrong.", Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Oops, something went wrong.", Toast.LENGTH_LONG).show();
                }

                Intent in = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(in);
            }
        });

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

                int selection2 = rbOccupation.getCheckedRadioButtonId();
                if (selection2 == R.id.rbOccupationStudent) {
                    occupation = "Student";
                } else if (selection2 == R.id.rbOccupationOther) {
                    occupation = "Other";
                }
                if (fAuth.getCurrentUser() !=null)
                {
                    startActivity(new Intent(RegisterActivity.this,MainActivity.class));
                    finish();
                }
                //  CHECK IF THE FORM IS OKAY WITH NO ERRORS
                if (validateFullName() && validateGender() && validateCountry() && validateUsername() && validateEmail() && validatePassword() && validateConfPass() && validateOccupation()) {
                    pb2.setVisibility(View.VISIBLE);

                    fAuth.createUserWithEmailAndPassword(emailtext,passwordtext).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                User information = new User(name, gender, username, country, occupation);

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
                                pb2.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
                }
            }
        });

        //  SET TITLE
        getSupportActionBar().setTitle("MoneySavy - Register");
    }

    //  VALIDATIONS
    public boolean validateFullName() {
        EditText nameText = findViewById(R.id.name);
        String val = nameText.getText().toString().trim();
        if (val.isEmpty()) {
            nameText.setError("Full name cannot be empty");
            return false;
        } else {
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

    public boolean validateOccupation() {
        int selection = rbOccupation.getCheckedRadioButtonId();
        RadioButton rbLastOption = findViewById(R.id.rbOccupationOther);

        if (selection == -1) {
            rbLastOption.setError("You must specify your occupation");
            return false;
        } else {
            return true;
        }
    }
}