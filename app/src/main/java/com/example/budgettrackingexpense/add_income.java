package com.example.budgettrackingexpense;

import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class add_income extends AppCompatActivity {

    public static String SUCCESS_MESSAGE_ADD_INCOME = "";
    EditText etDate;
    DatePickerDialog.OnDateSetListener setListener;
    Double new_total,new_amount;

    String file_name = "income.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etDate=findViewById(R.id.etDate);
        Calendar calendar = Calendar.getInstance();

        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        add_income.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month + 1;
                        String date = day + "/" + month + "/" + year;
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O )
        {
            NotificationChannel channel = new NotificationChannel("My notification","Notification TItle", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        //  WRITE TO FILE
        btnaddincome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = etDate.getText().toString();
                String number = etNumber.getText().toString();
                String notes = etNotes.getText().toString();

                String final_to_write = date + "," + notes + "," + number+ "\n";
                System.out.println(final_to_write);

                try {
                    FileOutputStream fout = openFileOutput(file_name, MODE_APPEND);
                    fout.write(final_to_write.getBytes());
                    fout.close();

                    Intent pass_total = getIntent();
                    Bundle total = pass_total.getExtras();
                    new_amount= Double.parseDouble(number);
                    new_total = Double.parseDouble(total.getString("total_income")) + new_amount;
                    String total_income = "Your total income is:  "+ new_total.toString() +" euros!";
                    NotificationCompat.Builder notification = new NotificationCompat.Builder(add_income.this,"My notification");
                    notification.setSmallIcon(R.drawable.notifications);
                    notification.setContentTitle("You have a new notification");
                    notification.setContentText(total_income);
                    notification.setAutoCancel(true);

                    NotificationManagerCompat managerCompat = NotificationManagerCompat.from(add_income.this);
                    managerCompat.notify(2,notification.build());

                    Intent in = new Intent(add_income.this, MainActivity.class);
                    String successMessage = "You have successfully recorded your income of €" + number;
                    in.putExtra(SUCCESS_MESSAGE_ADD_INCOME, successMessage);
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
        getMenuInflater().inflate(R.menu.addincome_menu, menu);
        return true;
    }
}