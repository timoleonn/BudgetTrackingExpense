package com.example.budgettrackingexpense;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.BuddhistCalendar;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.Buffer;
import java.util.Arrays;

public class add_income extends AppCompatActivity {

    CalendarView calendar;
    TextView etDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        calendar = (CalendarView)
                findViewById(R.id.calendar);
        etDate = (TextView)
                findViewById(R.id.etDate);

        calendar.setOnDateChangeListener(
                new CalendarView.OnDateChangeListener() {
                    @Override

                    // In this Listener have one method
                    // and in this method we will
                    // get the value of DAYS, MONTH, YEARS
                    public void onSelectedDayChange(@NonNull CalendarView view, int year,  int month,  int dayOfMonth) {
                        // Store the value of date with
                        // format in String type Variable
                        // Add 1 in month because month
                        // index is start with 0
                        String Date = dayOfMonth + "/"
                                + (month + 1) + "/" + year;

                        // set this date in TextView for Display
                        etDate.setText(Date);
                    }
                });

        Button btnTestingTim = findViewById(R.id.btnTestingTim);
        Button btnTestingTimRead = findViewById(R.id.btnTestingTimRead);

        //  SET DATA (TEMPORARY)
        //  IN THE APP, WE WILL BE GRABING THE DATA FROM THE FORM
        String date = "20/11/2020";
        String note = "Monthly Cheque";
        Double amount = 2550.50;
        String final_to_write = date + "," + note + "," + amount.toString() + "\n";

        //  SET FILE NAME
        String file_name = "test4.txt";

        //  WRITE TO FILE
        btnTestingTim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fout = openFileOutput(file_name, MODE_APPEND);
                    fout.write(final_to_write.getBytes());
                    fout.close();
                    System.out.println("SUCCESS");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    System.out.println("1: " + e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("2: " + e.getMessage());
                }
            }
        });

        //  READ FILE
        btnTestingTimRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    FileInputStream fin = openFileInput(file_name);
                    DataInputStream din = new DataInputStream(fin);
                    InputStreamReader isr = new InputStreamReader(din);
                    BufferedReader br = new BufferedReader(isr);

                    String strLine;
                    while((strLine = br.readLine()) != null) {
                        System.out.println(strLine);
                    }

                    fin.close();
                    Toast.makeText(getApplicationContext(), "Read successfully", Toast.LENGTH_LONG).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    System.out.println("1: " + e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("2: " + e.getMessage());
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.addincome_menu, menu);
        return true;

    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add_income) {
            Intent in = new Intent(this, Settings.class);
            startActivity(in);
        }

        return super.onOptionsItemSelected(item);
    }

    public void add(View v)
    {
        EditText number=findViewById(R.id.etNumber);
        EditText notes=findViewById(R.id.etNotes);
        EditText date=findViewById(R.id.etDate);




    }
}