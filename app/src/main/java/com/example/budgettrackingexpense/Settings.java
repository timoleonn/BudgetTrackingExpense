package com.example.budgettrackingexpense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void submit(View v)
    {
        RadioGroup group=findViewById(R.id.rbGroup);

        String currency="";

        int selection=group.getCheckedRadioButtonId();

        if (selection==R.id.rbEuro)
        {
            currency="Euros";
        }else if (selection==R.id.rbDollars)
        {
            currency="Dollars";
        }else if(selection==R.id.rbPound)
        {
            currency="Pound";
        }
    }
}