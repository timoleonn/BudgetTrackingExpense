package com.example.budgettrackingexpense;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Switch;

public class Settings extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    public void submit(View v) {
        CheckBox cyprus = findViewById(R.id.cbCyprus);
        CheckBox astro = findViewById(R.id.cbAstro);
        CheckBox hellenic = findViewById(R.id.cbHellenic);
        CheckBox rcb = findViewById(R.id.cbRcb);
        CheckBox alpha = findViewById(R.id.cbAlpha);


        RadioGroup group = findViewById(R.id.rbGroup);

        String currency = "";

        int selection = group.getCheckedRadioButtonId();

        if (selection == R.id.rbEuro) {
            currency = "Euros";
        } else if (selection == R.id.rbDollars) {
            currency = "Dollars";
        } else if (selection == R.id.rbPound) {
            currency = "Pound";
        }
    }


        public void theme(View v)
        {
            Switch sw=findViewById(R.id.switchBright);
            ConstraintLayout layout=findViewById(R.id.layout);

        if (sw.isChecked())
        {
            layout.setBackgroundColor(Color.GRAY);
            sw.setText("Dark Mode:on");
        }else
        {
            layout.setBackgroundColor(Color.WHITE);
            sw.setText("Dark Mode:off");

        }

    }
}