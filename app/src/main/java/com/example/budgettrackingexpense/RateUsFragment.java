package com.example.budgettrackingexpense;

import android.os.Bundle;

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

public class RateUsFragment extends Fragment {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String fullName = "Timoleon Charilaou";

        View root = inflater.inflate(R.layout.fragment_rate_us, container, false);

        //  MAIN FORM ELEMENTS
        Button btn = root.findViewById(R.id.btnSubmitFeedback);
        EditText etFeedback = root.findViewById(R.id.etFeedback);
        RatingBar rbFeedback = root.findViewById(R.id.rbFeedback);
        TextView tvFeedbackResult = root.findViewById(R.id.tvFeedbackResult);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String etFeedbackText = etFeedback.getText().toString();
                double FeedbackRating = rbFeedback.getRating();
                String RatingBarMessage = FeedbackRating + "/6";

                String message = "Thank you for submitting your feedback!\n" +
                        "\nName: " + fullName +
                        "\nRating: " + RatingBarMessage +
                        "\nMessage: " + etFeedbackText;

                tvFeedbackResult.setText(message);
            }
        });

        // Inflate the layout for this fragment
        return root;
    }


}