package com.example.budgettrackingexpense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class RateUsSuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_us_success);

        //  SET ANIMATION FOR CHECK MARK
        ImageView checkMarkImage = findViewById(R.id.imageView2);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.check_mark_animation);
        checkMarkImage.startAnimation(animation);

        //  GRAB INTENT FROM RateUsFragment
        Intent in = getIntent();
        Bundle finalRatingMessage = in.getExtras();

        String name = finalRatingMessage.getString("name");
        String rating = finalRatingMessage.getString("rating");
        String recommend = finalRatingMessage.getString("recommend");
        String buy = finalRatingMessage.getString("buy");
        String features = finalRatingMessage.getString("features");
        String feedback = finalRatingMessage.getString("feedback");

        //  GRAB ALL TEXT VIEW ELEMENTS
        TextView tvHeaderFullName = findViewById(R.id.tvHeaderFullName);
        TextView tvFullName = findViewById(R.id.tvFullName);
        TextView tvHeaderRating = findViewById(R.id.tvHeaderRating);
        TextView tvRating = findViewById(R.id.tvRating);
        TextView tvHeaderRecommend = findViewById(R.id.tvHeaderRecommend);
        TextView tvRecommend = findViewById(R.id.tvRecommend);
        TextView tvHeaderBuy = findViewById(R.id.tvHeaderBuy);
        TextView tvBuy = findViewById(R.id.tvBuy);
        TextView tvHeaderFeatures = findViewById(R.id.tvHeaderFeatures);
        TextView tvFeatures = findViewById(R.id.tvFeatures);
        TextView tvHeaderFeedback = findViewById(R.id.tvHeaderFeedback);
        TextView tvFeedback = findViewById(R.id.tvFeedback);

        //  SET ALL TEXT VIEW ELEMENTS FROM THE DATA IN BUNDLE
        tvHeaderFullName.setText("Full Name: ");
        tvFullName.setText(name);
        tvHeaderRating.setText("Your Rating: ");
        tvRating.setText(rating);
        tvHeaderRecommend.setText("Would you recommend? ");
        tvRecommend.setText(recommend);
        tvHeaderBuy.setText("Would you buy? ");
        tvBuy.setText(buy);
        tvHeaderFeatures.setText("What features would you like to see? ");
        tvFeatures.setText(features);
        tvHeaderFeedback.setText("Feedback Message: ");
        tvFeedback.setText(feedback);

        //  GRAB BUTTON AND SET ON CLICK LISTENER
        Button btnGoHome = findViewById(R.id.btnGoHome);
        btnGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(RateUsSuccessActivity.this, MainActivity.class);
                startActivity(in);
            }
        });

    }
}