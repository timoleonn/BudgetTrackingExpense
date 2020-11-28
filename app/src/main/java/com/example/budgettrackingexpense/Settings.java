package com.example.budgettrackingexpense;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.muddzdev.styleabletoast.StyleableToast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Settings extends AppCompatActivity {
    private String file="Banks.txt";
    private String file2="Currency.txt";
    private String currencySymbol = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Switch swFunfacts = findViewById(R.id.swFunfacts);

        CheckBox cbCyprus = findViewById(R.id.cbCyprus);
        CheckBox cbHellenic = findViewById(R.id.cbHellenic);
        CheckBox cbRcb = findViewById(R.id.cbRcb);
        CheckBox cbAstro = findViewById(R.id.cbAstro);
        CheckBox cbAlpha = findViewById(R.id.cbAlpha);

        //  SAVE CHECKBOX STATE
        SharedPreferences cbCyprusSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editorCyprus = cbCyprusSharedPreferences.edit();
        if (cbCyprusSharedPreferences.getBoolean("checkedCyprus", false) == true) {
            cbCyprus.setChecked(true);
            System.out.println("Cyprus 1");
        } else {
            System.out.println(cbCyprusSharedPreferences.contains("checkedCyprus"));
            System.out.println(cbCyprusSharedPreferences.getBoolean("checkedCyprus", false) == true);
            cbCyprus.setChecked(false);
            System.out.println("Cyprus 2");
        }
        cbCyprus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cbCyprus.isChecked()) {
                    System.out.println("Tim1");
                    editorCyprus.putBoolean("checkedCyprus", true);
                } else {
                    System.out.println("Tim2");
                    editorCyprus.putBoolean("checkedCyprus", false);
                }
                editorCyprus.apply();
            }
        });


        SharedPreferences cbHellenicSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editorHellenic = cbHellenicSharedPreferences.edit();
        if (cbHellenicSharedPreferences.getBoolean("checkedHellenic", false) == true) {
            cbHellenic.setChecked(true);
            System.out.println("Cyprus 3");
        } else {
            cbHellenic.setChecked(false);
            System.out.println("Cyprus 4");
        }
        cbHellenic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cbHellenic.isChecked()) {
                    editorHellenic.putBoolean("checkedHellenic", true);
                    editorHellenic.apply();
                } else {
                    editorHellenic.putBoolean("checkedHellenic", false);
                    editorHellenic.apply();
                }
            }
        });


        SharedPreferences cbRcbSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editorRcb = cbRcbSharedPreferences.edit();
        if (cbRcbSharedPreferences.getBoolean("checkedRcb", false) == true) {
            cbRcb.setChecked(true);
            System.out.println("Cyprus 5");
        } else {
            cbRcb.setChecked(false);
            System.out.println("Cyprus 6");
        }
        cbRcb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cbRcb.isChecked()) {
                    editorRcb.putBoolean("checkedRcb", true);
                    editorRcb.apply();
                } else {
                    editorRcb.putBoolean("checkedRcb", false);
                    editorRcb.apply();
                }
            }
        });

        SharedPreferences cbAstroSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editorAstro = cbAstroSharedPreferences.edit();
        if (cbAstroSharedPreferences.getBoolean("checkedAstro", false) == true) {
            cbAstro.setChecked(true);
            System.out.println("Cyprus 7");
        } else {
            cbAstro.setChecked(false);
            System.out.println("Cyprus 8");
        }
        cbAstro.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cbAstro.isChecked()) {
                    editorAstro.putBoolean("checkedAstro", true);
                    editorAstro.apply();
                } else {
                    editorAstro.putBoolean("checkedAstro", false);
                    editorAstro.apply();
                }
            }
        });

        SharedPreferences cbAlphaSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        final SharedPreferences.Editor editorAlpha = cbAlphaSharedPreferences.edit();
        if (cbAlphaSharedPreferences.getBoolean("checkedAlpha", false) == true) {
            cbAlpha.setChecked(true);
            System.out.println("Cyprus 9");
        } else {
            cbAlpha.setChecked(false);
            System.out.println("Cyprus 10");
        }
        cbAlpha.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (cbAlpha.isChecked()) {
                    editorAlpha.putBoolean("checkedAlpha", true);
                    editorAlpha.apply();
                } else {
                    editorAlpha.putBoolean("checkedAlpha", false);
                    editorAlpha.apply();
                }
            }
        });

        //  SAVE SWITCH STATE IN SHARED PREFERENCES
        SharedPreferences sharedPreferences = getSharedPreferences("swFunFacts", MODE_PRIVATE);
        swFunfacts.setChecked(sharedPreferences.getBoolean("value", false));

        //  CHECK IF FUN FACT SERVICE IS RUNNING
        Intent serviceIntent = new Intent(Settings.this, AdService.class);
        if (sharedPreferences.getBoolean("value", false) == true) {
            startService(serviceIntent);
        } else {
            stopService(serviceIntent);
        }

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

        //  GET CURRENCY RADIO BUTTONS
        //  IF CURRENCY ALREADY SET, READ THE CURRENCY FILE TO SET RADIO BUTTON CHECKED
        RadioButton rbEuro = findViewById(R.id.rbEuro);
        RadioButton rbDollars = findViewById(R.id.rbDollars);
        RadioButton rbPound = findViewById(R.id.rbPound);

        try {
            FileInputStream fin = openFileInput("Currency.txt");
            DataInputStream din = new DataInputStream(fin);
            InputStreamReader isr = new InputStreamReader(din);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    currencySymbol = line;
                }
            }
            br.close();

            System.out.println("ANSWER: " + currencySymbol + " " + (currencySymbol.equals("EUR")));
            if (currencySymbol.equals("€")) {
                rbEuro.setChecked(true);
            } else if (currencySymbol.equals("$")) {
                rbDollars.setChecked(true);
            } else if (currencySymbol.equals("£")) {
                rbPound.setChecked(true);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Oops, something went wrong!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_go_to_home) {
            Intent in = new Intent(this, MainActivity.class);
            Intent serviceIntent = new Intent(Settings.this, AdService.class);
            stopService(serviceIntent);
            startActivity(in);
        }

        return super.onOptionsItemSelected(item);
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
            Toast.makeText(getApplicationContext(),"1Something went wrong",Toast.LENGTH_LONG).show();
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
            ans3="RCB Bank";
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
            StyleableToast.makeText(getApplicationContext(), "Your options for Banks and the Currency has been added succeesful!", Toast.LENGTH_LONG, R.style.customToast).show();
            Intent in = new Intent(Settings.this, MainActivity.class);
            startActivity(in);
        } catch (Exception ex) {
            ex.printStackTrace();
            StyleableToast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG, R.style.mistakeToast).show();

        }
    }
}