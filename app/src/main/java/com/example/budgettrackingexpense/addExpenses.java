package com.example.budgettrackingexpense;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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

    public static String SUCCESS_MESSAGE_ADD_EXPENSE = "";
    EditText  date, note, amount;
    DatePickerDialog.OnDateSetListener setListener;

    String file_name = "expenses.txt";
    Double new_total,new_amount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        btnAddExpenses.setEnabled(false);

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

        btnAddExpenses.setEnabled(false);

        date.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                if (!date.getText().toString().isEmpty()) {
                    btnAddExpenses.setEnabled(true);
                }
            }
        });
        btnAddExpenses.setEnabled(false);

        amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                if (!amount.getText().toString().isEmpty()) {
                    btnAddExpenses.setEnabled(true);
                }
            }
        });
        btnAddExpenses.setEnabled(false);

        note.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                if (!note.getText().toString().isEmpty()) {
                    btnAddExpenses.setEnabled(true);
                }
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O )
        {
            NotificationChannel channel = new NotificationChannel("My notification","This is not the latest android version. Please try updating your device and the launching the application again",NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

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

                    Intent pass_total = getIntent();
                    Bundle total = pass_total.getExtras();
                    new_amount= Double.parseDouble(amount.getText().toString());
                    new_total = Double.parseDouble(total.getString("total_expense")) + new_amount;
                    String total_expense = "Your total expenses is:  "+ new_total.toString() +" euros!";
                    NotificationCompat.Builder notification = new NotificationCompat.Builder(addExpenses.this,"My notification");
                    notification.setSmallIcon(R.drawable.notifications);
                    notification.setContentTitle("You have a new notification");
                    notification.setContentText(total_expense);
                    notification.setAutoCancel(true);

                    NotificationManagerCompat managerCompat = NotificationManagerCompat.from(addExpenses.this);
                    managerCompat.notify(1,notification.build());

                    Intent in = new Intent(addExpenses.this, MainActivity.class);
                    String successMessage = "You have successfully recorded your expense of €" + amount.getText().toString() + " for " + spinner.getSelectedItem().toString();
                    in.putExtra(SUCCESS_MESSAGE_ADD_EXPENSE, successMessage);
                    startActivity(in);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
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
    //  CHECK WHAT BUTTON IS PRESSED ON TOOLBAR TOP
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent in = new Intent(this, Settings.class);
            startActivity(in);
        }

        return super.onOptionsItemSelected(item);
    }
}