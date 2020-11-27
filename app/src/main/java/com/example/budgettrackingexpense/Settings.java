package com.example.budgettrackingexpense;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Settings extends AppCompatActivity {
    private String file="Banks.txt";
    private String file2="Currency.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Switch swFunfacts = findViewById(R.id.swFunfacts);


        //  SAVE SWITCH STATE IN SHARED PREFERENCES
        SharedPreferences sharedPreferences = getSharedPreferences("swFunFacts", MODE_PRIVATE);
        swFunfacts.setChecked(sharedPreferences.getBoolean("value", false));

        //  CREATE SERVICE INTENT
        Intent serviceIntent = new Intent(Settings.this, AdService.class);

        //  SWITCH SET ON CLICK LISTENER
        swFunfacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (swFunfacts.isChecked()) {
                    SharedPreferences.Editor editor = getSharedPreferences("swFunFacts", MODE_PRIVATE).edit();
                    editor.putBoolean("value", true);
                    editor.apply();
                    swFunfacts.setChecked(true);
                    startService(serviceIntent);
                } else {
                    SharedPreferences.Editor editor = getSharedPreferences("swFunFacts", MODE_PRIVATE).edit();
                    editor.putBoolean("value", false);
                    editor.apply();
                    swFunfacts.setChecked(false);
                    stopService(serviceIntent);
                }
            }
        });
    }

    public void submit(View v) {


        RadioGroup group = findViewById(R.id.rbGroup);
        List<String> currencies = new ArrayList<>();

        String currency = "";

        int selection = group.getCheckedRadioButtonId();

        if (selection == R.id.rbEuro) {
            currency = "€";
            currencies.add(currency);
        }
        if (selection == R.id.rbDollars) {
            currency = "$";
            currencies.add(currency);
        }
        if (selection == R.id.rbPound) {
            currency = "£";
            currencies.add(currency);
        }

        try {
            FileOutputStream fout=openFileOutput(file2,0);
            for (String myStr : currencies) {
                fout.write((myStr + "\n").getBytes());
                System.out.println(currencies);
            }
            fout.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG).show();

        }
        CheckBox cyprus = findViewById(R.id.cbCyprus);
        CheckBox astro = findViewById(R.id.cbAstro);
        CheckBox hellenic = findViewById(R.id.cbHellenic);
        CheckBox rcb = findViewById(R.id.cbRcb);
        CheckBox alpha = findViewById(R.id.cbAlpha);

        List<String> lines = new ArrayList<>();
        String ans="";
        String ans1="";
        String ans2="";
        String ans3="";
        String ans4="";
        if(cyprus.isChecked())
        {
            ans="Bank of Cyprus";
            lines.add(ans);
        }
        if(astro.isChecked())
        {
            ans1="AstroBank";
            lines.add(ans1);
        }

        if(hellenic.isChecked())
        {
            ans2="Hellenic Bank";
            lines.add(ans2);
        }

        if(rcb.isChecked())
        {
            ans3="Rcb Bank";
            lines.add(ans3);
        }

        if(alpha.isChecked())
        {
            ans4="Alpha Bank";
            lines.add(ans4);
        }

        try {
            FileOutputStream fout=openFileOutput(file,0);
            for (String myStr : lines) {
                fout.write((myStr + "\n").getBytes());
                System.out.println(lines);
            }
            fout.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG).show();

        }
        Toast.makeText(getApplicationContext(),"Your Submittion is Successful",Toast.LENGTH_LONG).show();

    }
}