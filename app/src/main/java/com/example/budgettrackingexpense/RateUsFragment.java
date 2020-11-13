package com.example.budgettrackingexpense;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class RateUsFragment extends Fragment {

    public RateUsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        String fullName = getArguments().getString("fullName");

        Button btnSubmitFeedback = getView().findViewById(R.id.btnSubmitFeedback);
        btnSubmitFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = getView().findViewById(R.id.textView13);
                tv.setText(fullName);
            }
        });




        return inflater.inflate(R.layout.fragment_rate_us, container, false);
    }
}