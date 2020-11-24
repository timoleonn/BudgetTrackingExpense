package com.example.budgettrackingexpense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class RateUsSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_us_success);

        Intent in = getIntent();
        Bundle finalRatingMessage = in.getExtras();

        String name = finalRatingMessage.getString("name");
        String rating = finalRatingMessage.getString("rating");
        String recommend = finalRatingMessage.getString("recommend");
        String buy = finalRatingMessage.getString("buy");
        String features = finalRatingMessage.getString("features");
        String feedback = finalRatingMessage.getString("feedback");

    }
}