package com.example.budgettrackingexpense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class UpdateCategoryActivity extends AppCompatActivity {

    //  DATABASE VARIABLES
    String _CATEGORYNAME, _BUDGET;

    //  INTENT VARIABLE IF UPDATE IS SUCCESSFULL
    public static final String UPDATEMESSAGE = "";
    public static final String NODATACHANGEMESSAGE = "";

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_category);

        //  GET CURRENT USER
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        //  GET CURRENT USER UID
        String currentUserUid = currentUser.getUid();

        reference = FirebaseDatabase.getInstance().getReference("users").child(currentUserUid).child("categories");

        //  GRAB INTENT FROM CATEGORYACTIVITY
        Intent in = getIntent();
        Bundle categoryInfo = in.getExtras();
        _CATEGORYNAME = categoryInfo.getString("categoryName");
        _BUDGET = categoryInfo.getString("budget");

        //  GRAB ELEMENTS ON UPDATE CATEGORY
        TextView tvUpdateTitle = findViewById(R.id.tvUpdateTitle);
        EditText etCategoryName = findViewById(R.id.etCategoryName);
        EditText etBudget = findViewById(R.id.etBudget);
        Button btnUpdateCategory = findViewById(R.id.btnUpdateCategory);

        //  SET THE ELEMENTS
        tvUpdateTitle.setText("Update '" + _CATEGORYNAME + "' category");
        etCategoryName.setText(_CATEGORYNAME);
        etBudget.setText(_BUDGET);

        //  ONCLICKLISTENER FOR UPDATE BUTTON
        btnUpdateCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCategoryNameChanged() || isBudgetChanged()) {
                    Intent in = new Intent(UpdateCategoryActivity.this, CategoriesActivity.class);
                    in.putExtra(UPDATEMESSAGE, "Category updated successfully!");
                    startActivity(in);
                } else {
                    Intent in = new Intent(UpdateCategoryActivity.this, CategoriesActivity.class);
                    in.putExtra(NODATACHANGEMESSAGE, "Data is the same and cannot be updated!");
                    startActivity(in);
                }
            }

            private boolean isCategoryNameChanged() {
                if (!_CATEGORYNAME.equals(etCategoryName.getText().toString())) {
                    //  USER IS TRYING TO CHANGE SOMETHING
                    Double newBudget = Double.parseDouble(etBudget.getText().toString());

                    //  DELETE OLD CHILD AND RECREATE IT AGAIN
                    reference.child(_CATEGORYNAME).removeValue();
                    _CATEGORYNAME = etCategoryName.getText().toString();
                    reference.child(_CATEGORYNAME).child("name").setValue(etCategoryName.getText().toString());
                    reference.child(_CATEGORYNAME).child("budget").setValue(newBudget);
                    return true;
                } else {
                    return false;
                }
            }

            private boolean isBudgetChanged() {
                if (!_BUDGET.equals(etBudget.getText().toString())) {
                    //  USER IS TRYING TO CHANGE SOMETHING
                    Double newBudget = Double.parseDouble(etBudget.getText().toString());
                    reference.child(_CATEGORYNAME).child("budget").setValue(newBudget);
                    return true;
                } else {
                    return false;
                }
            }
        });

        //  BACK BUTTON
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //  SET TITLE
        getSupportActionBar().setTitle("Update Category");
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
        }

        return super.onOptionsItemSelected(item);
    }
}