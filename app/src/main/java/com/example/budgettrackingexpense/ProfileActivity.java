package com.example.budgettrackingexpense;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class ProfileActivity extends AppCompatActivity {
    DatabaseReference userRef;
    String USER ="users";
    TextView day;


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
         day = findViewById(R.id.day);

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

        Date currentTime = Calendar.getInstance().getTime();
        String formattedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentTime);

        String[] splitDate = formattedDate.split(",");
        Log.d("myLOG", currentTime.toString());
        Log.d("myLOG", formattedDate);
        day.setText(formattedDate);

        //  SET TITLE
        getSupportActionBar().setTitle("My Profile");
    }

    public void goBack(View v)
    {
        Intent in = new Intent(this,MainActivity.class);
        startActivity(in);

    }
}