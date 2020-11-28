package com.example.budgettrackingexpense;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProfileActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference userRef;
    String USER ="users";
    String fileName="totals.txt";
    String currency_file_name = "Currency.txt";
    String income , expense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView username = findViewById(R.id.username_field);
        TextView fullname = findViewById(R.id.fullname_field);
        TextView occupation =  findViewById(R.id.ocupation_field);
        TextView gender = findViewById(R.id.tvGender);
        TextView email = findViewById(R.id.tvemail);
        TextView country = findViewById(R.id.tvcountry);
        ImageView profile_image = findViewById(R.id.profile_image);
        TextView expense_label = findViewById(R.id.expense_label);
        TextView income_label = findViewById(R.id.income_label);

        //  GET CURRENT USER
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        //  GET CURRENT USER UID
        String currentUserUid = currentUser.getUid();
        String currentUserEmail = currentUser.getEmail();

        userRef = FirebaseDatabase.getInstance().getReference(USER).child(currentUserUid);
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren())
                {
                    String _FULLNAME = snapshot.child("fullname").getValue().toString();
                    String _OCCUPATION = snapshot.child("occupation").getValue().toString();
                    String _GENDER = snapshot.child("gender").getValue().toString();
                    String _USERNAME = snapshot.child("username").getValue().toString();
                    String _COUNTRY = snapshot.child("country").getValue().toString();

                    username.setText(_USERNAME);
                    fullname.setText(_FULLNAME);
                    occupation.setText(_OCCUPATION);
                    gender.setText(_GENDER);
                    country.setText(_COUNTRY);
                    email.setText(currentUserEmail);

                    if (_GENDER.equals("Male")) {
                        profile_image.setBackgroundResource(R.drawable.ic_iconfinder_malecostume_403022);
                    } else if (_GENDER.equals("Female")) {
                        profile_image.setBackgroundResource(R.drawable.ic_iconfinder_female1_403023);
                    } else if (_GENDER.equals("Other")) {
                        profile_image.setBackgroundResource(R.drawable.ic_iconfinder_other);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //  READ INCOME AND EXPENSE
//        try {
//            FileInputStream fin = getApplicationContext().openFileInput(fileName);
//            DataInputStream din = new DataInputStream(fin);
//            InputStreamReader isr = new InputStreamReader(din);
//            BufferedReader br = new BufferedReader(isr);
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] tokens = line.split("[,]", 0);
//                System.out.println(tokens);
//                for(String myStr: tokens) {
//                    System.out.println(myStr);
//                    //  ADDS THE FOUR VARIABLES TO THE STRING ARRAY
//                    double latitude = Double.parseDouble(tokens[0]);
//                    double longitude = Double.parseDouble(tokens[1]);
//
//                    String total_expense =String.valueOf(latitude);
//                    String total_income =String.valueOf(longitude);
//                    expense_label.setText(total_expense);
//                    income_label.setText(total_income);
//                }
//
//            }
//            br.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//            Toast.makeText(getApplicationContext(), "Oops, something went wrong!", Toast.LENGTH_LONG).show();
//        }
    }
    public void goBack(View v)
    {
        Intent in = new Intent(this,MainActivity.class);
        startActivity(in);

    }
}