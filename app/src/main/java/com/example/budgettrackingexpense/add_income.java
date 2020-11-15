package com.example.budgettrackingexpense;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TextView;

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