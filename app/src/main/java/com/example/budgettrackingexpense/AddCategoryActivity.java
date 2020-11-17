package com.example.budgettrackingexpense;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddCategoryActivity extends AppCompatActivity {

    public static final String SUCCESSMESSAGE = "";

    EditText etCategoryName, etBudget;
    Button btnCreateCategory;

    DatabaseReference reffCategories;
//    DatabaseReference reffUsers_Categories;
    Categories categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);

        etCategoryName = findViewById(R.id.etCategoryName);
        etBudget = findViewById(R.id.etBudget);
        btnCreateCategory = findViewById(R.id.btnCreateCategory);

        categories = new Categories();

        reffCategories = FirebaseDatabase.getInstance().getReference("users").child("-sdfsdfsdfsdf");

        btnCreateCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double budget = Double.parseDouble(etBudget.getText().toString().trim());
                categories.setName(etCategoryName.getText().toString());
                categories.setBudget(budget);

                reffCategories.child("categories").child(etCategoryName.getText().toString()).setValue(categories);

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
}