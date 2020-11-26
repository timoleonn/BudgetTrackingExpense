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

        System.out.println("1:"+currency);

        List<String> lines = new ArrayList<String>();
        String ans="";
        String ans1="";
        String ans2="";
        String ans3="";
        String ans4="";
        if(cyprus.isChecked())
        {
            lines.add(ans);
            ans="Cyprus";
            System.out.println("1:"+ ans);

        }
        if(astro.isChecked())
        {
            lines.add(ans1);
             ans1="AstroBank";
            System.out.println("1:"+ ans1);
        }

        if(hellenic.isChecked())
        {
            lines.add(ans2);
             ans2="Hellenic Bank";
            System.out.println("1:"+ ans2);
        }

        if(rcb.isChecked())
        {
            lines.add(ans3);
             ans3="Rcb Bank";
            System.out.println("1:"+ ans3);
        }

        if(alpha.isChecked())
        {
            lines.add(ans4);
             ans4="Alpha Bank";
            System.out.println("1:"+ ans4);
        }

        try{
            for (int i=0; i<lines.size(); i++) {
                FileOutputStream fout=openFileOutput(file,0);
                fout.write(ans.getBytes());
                fout.write(ans1.getBytes());
                fout.write(ans2.getBytes());
                fout.write(ans3.getBytes());
                fout.write(ans4.getBytes());
                fout.close();
                System.out.println("2:saved" + ans);
                System.out.println("2:saved" + ans1);
                System.out.println("2:saved" + ans2);
                System.out.println("2:saved" + ans3);
                System.out.println("2:saved" + ans4);
            }

        }catch (Exception ex)
        {
            ex.printStackTrace();
            Toast.makeText(getApplicationContext(),"Something went wrong",Toast.LENGTH_LONG).show();

        }
    }
}