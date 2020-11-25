package com.example.budgettrackingexpense;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Switch;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class Settings extends AppCompatActivity {

    SharedPreferences sharedPreferences = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Switch swDarkMode = findViewById(R.id.switchBright);
//        sharedPreferences = getSharedPreferences("night", 0);
//        Boolean booleanValue = sharedPreferences.getBoolean("night_mode", false);
//
//        if (booleanValue) {
//            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//            swDarkMode.setChecked(true);
//        }

        swDarkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                    swDarkMode.setChecked(true);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putBoolean("night_mode", true);
//                    editor.commit();
                } else {
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                    swDarkMode.setChecked(false);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putBoolean("night_mode", false);
//                    editor.commit();
                }
            }
        });
    }

//    public void submit(View v) {
//        CheckBox cyprus = findViewById(R.id.cbCyprus);
//        CheckBox astro = findViewById(R.id.cbAstro);
//        CheckBox hellenic = findViewById(R.id.cbHellenic);
//        CheckBox rcb = findViewById(R.id.cbRcb);
//        CheckBox alpha = findViewById(R.id.cbAlpha);
//
//        RadioGroup group = findViewById(R.id.rbGroup);
//
//        String currency = "";
//
//        int selection = group.getCheckedRadioButtonId();
//
//        if (selection == R.id.rbEuro) {
//            currency = "Euros";
//        } else if (selection == R.id.rbDollars) {
//            currency = "Dollars";
//        } else if (selection == R.id.rbPound) {
//            currency = "Pound";
//        }
//
//    }
//        public void theme(View v)
//        {
//            Switch sw = findViewById(R.id.switchBright);
//
//            if (sw.isChecked())
//            {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                sw.setText("Dark Mode: On");
//            } else {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                sw.setText("Dark Mode: Off");
//
//            }
//
//        }
}