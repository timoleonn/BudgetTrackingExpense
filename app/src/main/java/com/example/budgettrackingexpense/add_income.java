package com.example.budgettrackingexpense;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;

public class add_income extends AppCompatActivity {

    // TextView tvDate;
    EditText etDate;
    DatePickerDialog.OnDateSetListener setListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etDate=findViewById(R.id.etDate);
        Calendar calendar=Calendar.getInstance();

        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        add_income.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month=month+1;
                        String date=day+"/"+month+"/"+year;
                        etDate.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });


        Button btnaddincome=findViewById(R.id.btnaddincome);
        btnaddincome.setEnabled(false);

        etDate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                if (!etDate.getText().toString().isEmpty()) {
                    btnaddincome.setEnabled(true);
                }
            }
        });
        TextView etNumber=findViewById(R.id.etNumber);
        btnaddincome.setEnabled(false);

        etNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                if (!etNumber.getText().toString().isEmpty()) {
                    btnaddincome.setEnabled(true);
                }
            }
        });
        TextView etNotes=findViewById(R.id.etNotes);
        btnaddincome.setEnabled(false);

        etNotes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                if (!etNotes.getText().toString().isEmpty()) {
                    btnaddincome.setEnabled(true);
                }
            }
        });

        //  SET DATA (TEMPORARY)
        //  IN THE APP, WE WILL BE GRABING THE DATA FROM THE FORM
                

        //  SET FILE NAME
        String file_name = "test4.txt";

        //  WRITE TO FILE
        btnaddincome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String date=etDate.getText().toString();
                String number=etNumber.getText().toString();
                String notes=etNotes.getText().toString();


                String final_to_write = date + "," + notes + "," + number+ "\n";

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


        Button btnTestingTimRead=findViewById(R.id.btnTestingTimRead);
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
                        System.out.println("1: " + strLine);
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

}