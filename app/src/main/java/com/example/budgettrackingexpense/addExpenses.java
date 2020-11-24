package com.example.budgettrackingexpense;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class addExpenses extends AppCompatActivity {
    //  test

    public static String SUCCESS_MESSAGE_ADD_EXPENSE = "";
    EditText  date, note, amount;
    DatePickerDialog.OnDateSetListener setListener;

    String file_name = "expenses.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);

        note = findViewById(R.id.etNote);
        amount = findViewById(R.id.etExpense);
        date = findViewById(R.id.date);
        Spinner spinner = findViewById(R.id.spinner);

        //  CREATE ARRAY LIST OF STRINGS FOR CATEGORIES
        ArrayList<String> categories = new ArrayList();

        //  READ CATEGORIES FROM TEXT FILE THAT IS CREATED IN AddCategoryActivity
        //  ADD EACH CATEGORY TO THE ArrayList<String> categories
        //  COUNT HOW MANY CATEGORIES YOU HAVE (WILL NEED TO CREATE ARRAY)
        try {
            FileInputStream fin = openFileInput("categories.txt");
            DataInputStream din = new DataInputStream(fin);
            InputStreamReader isr = new InputStreamReader(din);
            BufferedReader br = new BufferedReader(isr);
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    categories.add(line);
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button btnAddExpenses = findViewById(R.id.btnAddExpenses);
        Calendar calendar = Calendar.getInstance();

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
                        String dateinsert = day+"/"+month+"/"+year;
                        date.setText(dateinsert);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        //  WRITE TO FILE
        btnAddExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // GRAB THE DATA FROM THE FORM
                String data_to_write = date.getText().toString() + "," + spinner.getSelectedItem().toString() + "," + note.getText().toString() + "," + amount.getText().toString() + "\n";
                try {
                    FileOutputStream fout = openFileOutput(file_name, MODE_APPEND);
//                    fout.write(("").getBytes());
                    fout.write(data_to_write.getBytes());
                    fout.close();
                    Intent in = new Intent(addExpenses.this, MainActivity.class);
                    String successMessage = "You have successfully recorded your expense of €" + amount.getText().toString() + " for " + spinner.getSelectedItem().toString();
                    in.putExtra(SUCCESS_MESSAGE_ADD_EXPENSE, successMessage);
                    startActivity(in);
//                    System.out.println("SUCCESS");
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
}