package com.example.budgettrackingexpense;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

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
}