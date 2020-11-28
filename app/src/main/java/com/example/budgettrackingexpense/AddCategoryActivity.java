package com.example.budgettrackingexpense;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.muddzdev.styleabletoast.StyleableToast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AddCategoryActivity extends AppCompatActivity {

    public static final String SUCCESSMESSAGE = "";

    EditText etCategoryName, etBudget;
    Button btnCreateCategory;

    DatabaseReference reffCategories;
    Categories categories;

    //  SET FILE NAME
    String file_name_1 = "categories.txt";
    String file_name_2 = "budget.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        //  GRAB INTENT FROM ADD EXPENSES WHEN THERE ARE NO CATEGORIES
        Intent in = getIntent();
        if (in.hasExtra(addExpenses.NO_CATEGORY_MESSAGE)) {
            String NO_CATEGORY_MESSAGE = in.getStringExtra(addExpenses.NO_CATEGORY_MESSAGE);
            Toast.makeText(getApplicationContext(), NO_CATEGORY_MESSAGE, Toast.LENGTH_LONG).show();
        }

        etCategoryName = findViewById(R.id.etCategoryName);
        etBudget = findViewById(R.id.etBudget);
        btnCreateCategory = findViewById(R.id.btnUpdateCategory);

        categories = new Categories();

        //  GET CURRENT USER
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        //  GET CURRENT USER UID
        String currentUserUid = currentUser.getUid();

        reffCategories = FirebaseDatabase.getInstance().getReference("users").child(currentUserUid);

        btnCreateCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long budget = Long.parseLong(etBudget.getText().toString().trim());
                categories.setName(etCategoryName.getText().toString());
                categories.setBudget(budget);

                reffCategories.child("categories").child(etCategoryName.getText().toString()).setValue(categories);

                //  WRITE CATEGORY NAME TO FILE
                try {
                    String categoryName = etCategoryName.getText().toString() + "\n";
                    FileOutputStream fout = openFileOutput(file_name_1, MODE_APPEND);
                    fout.write(categoryName.getBytes());
                    fout.close();
                    System.out.println("SUCCESS");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    StyleableToast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG, R.style.mistakeToast).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    StyleableToast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_LONG, R.style.mistakeToast).show();
                }

                //  WRITE BUDGET TO FILE
                try {
                    FileOutputStream fout = openFileOutput(file_name_2, MODE_APPEND);
                    fout.write(etBudget.getText().toString().getBytes());
                    fout.close();
                    System.out.println("SUCCESS");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    System.out.println("1: " + e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("2: " + e.getMessage());
                }

                Intent in = new Intent(AddCategoryActivity.this, CategoriesActivity.class);
                String message = "You have added the category " + etCategoryName.getText().toString() + " successfully!";
                in.putExtra(SUCCESSMESSAGE, message);
                startActivity(in);
            }
        });

        //  BACK BUTTON
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //  SET TITLE
        getSupportActionBar().setTitle("Add a category");
    }

    //  FOR BACK BUTTON
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.standard_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_go_to_home) {
            Intent in = new Intent(this, MainActivity.class);
            startActivity(in);
        } else if (id == R.id.action_settings) {
            Intent in = new Intent(this, Settings.class);
            startActivity(in);
        }

        return super.onOptionsItemSelected(item);
    }
}