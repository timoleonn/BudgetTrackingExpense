package com.example.budgettrackingexpense;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CategoriesActivity extends AppCompatActivity {

    List<Categories> categories;
    RecyclerView recyclerView;
    CategoriesAdapter categoriesAdapter;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String successMessageFromAddCategory;
        String successMessageFromUpdateCategory;
        String noDataChangeMessage;

        //  GET INTENT FROM AddCategoryActivity (SUCCESS MESSAGE)
        try {
            successMessageFromAddCategory = getIntent().getStringExtra(AddCategoryActivity.SUCCESSMESSAGE);
            Toast.makeText(getApplicationContext(), successMessageFromAddCategory, Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        //  GET INTENT FROM UpdateCategoryActivity (SUCCESS MESSAGE)
        try {
            successMessageFromUpdateCategory = getIntent().getStringExtra(UpdateCategoryActivity.UPDATEMESSAGE);
            Toast.makeText(getApplicationContext(), successMessageFromUpdateCategory, Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        //  GET INTENT FROM UpdateCategoryActivity (NO DATA CHANGE MESSAGE)
        try {
            noDataChangeMessage = getIntent().getStringExtra(UpdateCategoryActivity.NODATACHANGEMESSAGE);
            Toast.makeText(getApplicationContext(), noDataChangeMessage, Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

        recyclerView = findViewById(R.id.rvCategories);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        categories = new ArrayList<>();

        //  GET CURRENT USER
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        //  GET CURRENT USER UID
        String currentUserUid = currentUser.getUid();

        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(currentUserUid).child("categories");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot ds:snapshot.getChildren()) {
                        Categories data = ds.getValue(Categories.class);
                        categories.add(data);
                    }
                    categoriesAdapter = new CategoriesAdapter(categories);
                    recyclerView.setAdapter(categoriesAdapter);
                } else {
                    LinearLayout linearLayoutCategoriesMessage = findViewById(R.id.linearLayoutCategoriesMessage);
                    TextView tvCategoriesMessage = findViewById(R.id.tvCategoriesMessage);
                    linearLayoutCategoriesMessage.setVisibility(View.VISIBLE);
                    tvCategoriesMessage.setText("No categories to display. Please start by clicking on the floating action button to add categories!");
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);


        //  ADD CATEGORY
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(CategoriesActivity.this, AddCategoryActivity.class);
                startActivity(in);
            }
        });



        //  BACK BUTTON
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //  SET TITLE
        getSupportActionBar().setTitle("Categories");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
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