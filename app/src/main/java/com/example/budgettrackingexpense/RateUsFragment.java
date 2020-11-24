package com.example.budgettrackingexpense;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
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

        //  SET FRAGMENT TITLE
        ((MainActivity) getActivity()).getSupportActionBar().setTitle("Rate us");

        View root = inflater.inflate(R.layout.fragment_rate_us, container, false);

        //  MAIN FORM ELEMENTS
        EditText etFullName = root.findViewById(R.id.etFullName);
        RatingBar rbFeedback = root.findViewById(R.id.rbFeedback);
        RadioGroup rgRecommend = root.findViewById(R.id.rgRecommend);
        RadioGroup rgBuy = root.findViewById(R.id.rgBuy);
        CheckBox cbDarkMode = root.findViewById(R.id.cbDarkMode);
        CheckBox cbCloudSync = root.findViewById(R.id.cbCloudSync);
        CheckBox cbBanks = root.findViewById(R.id.cbBanks);
        CheckBox cbCategories = root.findViewById(R.id.cbCategories);
        Button btn = root.findViewById(R.id.btnSubmitFeedback);
        EditText etFeedback = root.findViewById(R.id.etFeedback);



        //  GET CURRENT USER
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        //  GET CURRENT USER UID
        String currentUserUid = currentUser.getUid();

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

        //  PROCESS SUBMIT FEEDBACK AND SEND BUNDLE TO RateUsSuccessActivity
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullNameText = etFullName.getText().toString();
                double FeedbackRating = rbFeedback.getRating();
                String RatingBarMessage = FeedbackRating + "/6";
                String recommendText = "", buyText = "", features = "";

                int rbRecommendSelector = rgRecommend.getCheckedRadioButtonId();
                if (rbRecommendSelector == R.id.rbRecommendYes) {
                    recommendText = "Yes";
                } else if (rbRecommendSelector == R.id.rbRecommendNo) {
                    recommendText = "No";
                }

                int rbBuySelector = rgBuy.getCheckedRadioButtonId();
                if (rbBuySelector == R.id.rbBuyYes) {
                    buyText = "Yes";
                } else if (rbBuySelector == R.id.rbBuyNo) {
                    buyText = "No";
                } else if (rbBuySelector == R.id.rbBuyMaybe) {
                    buyText = "Maybe";
                }

                if (cbDarkMode.isChecked())
                    features += "Dark Mode\n";
                if (cbCloudSync.isChecked())
                    features += "Cloud Sync\n";
                if (cbBanks.isChecked())
                    features += "Support more banks\n";
                if (cbCategories.isChecked())
                    features += "Edit and delete categories\n";

                String etFeedbackText = etFeedback.getText().toString();

                Bundle finalRatingMessage = new Bundle();
                finalRatingMessage.putString("name", fullNameText);
                finalRatingMessage.putString("rating", RatingBarMessage);
                finalRatingMessage.putString("recommend", recommendText);
                finalRatingMessage.putString("buy", buyText);
                finalRatingMessage.putString("features", features);
                finalRatingMessage.putString("feedback", etFeedbackText);

                Intent in = new Intent(getActivity(), RateUsSuccessActivity.class);
                in.putExtras(finalRatingMessage);
                startActivity(in);
            }
        });

        // Inflate the layout for this fragment
        return root;
    }


}