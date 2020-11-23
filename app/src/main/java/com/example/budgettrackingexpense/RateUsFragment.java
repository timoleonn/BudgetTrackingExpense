package com.example.budgettrackingexpense;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RateUsFragment extends Fragment {

    DatabaseReference databaseReference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String fullName = "Timoleon Charilaou";

        //  SET FRAGMENT TITLE
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Rate us");


        View root = inflater.inflate(R.layout.fragment_rate_us, container, false);

        //  MAIN FORM ELEMENTS
        Button btn = root.findViewById(R.id.btnSubmitFeedback);
        EditText etFeedback = root.findViewById(R.id.etFeedback);
        RatingBar rbFeedback = root.findViewById(R.id.rbFeedback);
        EditText etFullName = root.findViewById(R.id.etFullName);

        //  GET CURRENT USER
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        //  GET CURRENT USER UID
        String currentUserUid = currentUser.getUid();
        String currentUserEmail = currentUser.getEmail();

        //  GRAB DATA FROM THE DATABASE BASED ON CURRENT USER'S UID
        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(currentUserUid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String _FULLNAME = snapshot.child("fullname").getValue().toString();

                etFullName.setText(_FULLNAME);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //  PROCESS SUBMIT FEEDBACK
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double FeedbackRating = rbFeedback.getRating();
                String RatingBarMessage = FeedbackRating + "/6";
                String etFeedbackText = etFeedback.getText().toString();

//                String message = "Thank you for submitting your feedback!\n" +
//                        "\nName: " + fullName +
//                        "\nRating: " + RatingBarMessage +
//                        "\nMessage: " + etFeedbackText;

//                tvFeedbackResult.setText(message);
                CardView cvFeedback = root.findViewById(R.id.cvFeedback);
                cvFeedback.setVisibility(View.VISIBLE);

                TextView tvThanks = root.findViewById(R.id.tvThanks);
                TextView tvName = root.findViewById(R.id.tvName);
                TextView tvRating = root.findViewById(R.id.tvRating);
                TextView tvFeedback = root.findViewById(R.id.tvFeedback);

                tvThanks.setText("Thank you for submitting your feedback!");
                tvName.setText("Name: " + etFullName.getText().toString());
                tvRating.setText("Rating: " + RatingBarMessage);
                tvFeedback.setText("Message: " + etFeedbackText);
            }
        });

        // Inflate the layout for this fragment
        return root;
    }


}