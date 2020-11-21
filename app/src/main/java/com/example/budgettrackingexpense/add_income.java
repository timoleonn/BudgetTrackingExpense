package com.example.budgettrackingexpense;

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

    //TextView tvDate;
    EditText etDate;
     DatePickerDialog.OnDateSetListener setListener;



    //CalendarView calendar;
    //TextView etDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        //tvDate=findViewById(R.id.tv_Date);
        etDate=findViewById(R.id.etDate);
        Calendar calendar=Calendar.getInstance();





        final int year=calendar.get(Calendar.YEAR);
        final int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);

        //tvDate.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View v) {
              //  DatePickerDialog datePickerDialog=new DatePickerDialog(
                //        add_income.this, android.R.style.Theme_Black,
                  //      setListener,year,month,day);
                //datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //datePickerDialog.show();

            //}
        //});
        //setListener=new DatePickerDialog.OnDateSetListener() {
          //  @Override
           // public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
             //   month=month+1;
               // String date=day+"/"+month+"/"+year;
                //tvDate.setText(date);
           // }
        //};
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








        //calendar = (CalendarView)
               // findViewById(R.id.calendar);
        //etDate = (TextView)
               // findViewById(R.id.etDate);

        //calendar.setOnDateChangeListener(
                //new CalendarView.OnDateChangeListener() {
                  //  @Override
                    //public void onSelectedDayChange(@NonNull CalendarView view, int year,  int month,  int dayOfMonth) {
                      //  String Date = dayOfMonth + "/"
                        //        + (month + 1) + "/" + year;
                        //etDate.setText(Date);
                   // }
                //});

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