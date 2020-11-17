package com.example.budgettrackingexpense;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

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

        //  GET INTENT FROM AddCategoryActivity (SUCCESS MESSAGE)
        try {
            successMessageFromAddCategory = getIntent().getStringExtra(AddCategoryActivity.SUCCESSMESSAGE);
            Toast.makeText(getApplicationContext(), successMessageFromAddCategory, Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            //  DO NOTHING
        }

        recyclerView = findViewById(R.id.rvCategories);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        categories = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("users").child("-sdfsdfsdfsdf").child("categories");
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

}