package com.example.budgettrackingexpense;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;

public class addExpenses extends AppCompatActivity {

    EditText  date,note,amount;

    DatePickerDialog.OnDateSetListener setListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);


         note = findViewById(R.id.etNote);
         amount = findViewById(R.id.etExpense);
        date=findViewById(R.id.date);
        Calendar calendar=Calendar.getInstance();

        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        addExpenses.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month=month+1;
                        String dateinsert=day+"/"+month+"/"+year;
                        date.setText(dateinsert);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        Button btnTestingTim = findViewById(R.id.btnTestingTimexp);
        Button btnTestingTimRead = findViewById(R.id.btnTestingTimReadexp);



        // GRAB THE DATA FROM THE FORM
        String data_to_write= date.toString() + "," + note.toString() + "," + amount.toString() + "\n";

        //  SET FILE NAME
        String file_name = "test5.txt";

        //  WRITE TO FILE
        btnTestingTim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream fout = openFileOutput(file_name, MODE_APPEND);
                    fout.write(data_to_write.getBytes());
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
        getMenuInflater().inflate(R.menu.add_expense_menu, menu);
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
}