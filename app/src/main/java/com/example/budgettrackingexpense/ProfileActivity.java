package com.example.budgettrackingexpense;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ProfileActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference userRef;
    String USER ="users";
    String file_name="totals.txt";

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

//        Intent getValues = getIntent();
//        Bundle total = getValues.getExtras();
//        expense_label.setText(total.getString("total_expense"));
//        income_label.setText(total.getString("total_income"));


    }
    public void goBack(View v)
    {
        Intent in = new Intent(this,MainActivity.class);
        startActivity(in);

    }
}