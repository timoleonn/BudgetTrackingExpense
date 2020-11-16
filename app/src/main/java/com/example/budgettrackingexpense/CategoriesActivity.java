package com.example.budgettrackingexpense;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CategoriesActivity extends AppCompatActivity {

    ListView lvCategory;
    FloatingActionButton fab;
    CategoryAdapter adapter;

    DatabaseReference rootReference = FirebaseDatabase.getInstance().getReference();
    DatabaseReference categoriesReference = rootReference.child("categories");

    //  Cloud Data Source
    DataSnapshot categoriesSnapshot;

    //  Local Data Source
    List<String> categories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lvCategory = findViewById(R.id.lvCategory);
        fab = findViewById(R.id.fabAddCategory);

        adapter = new CategoryAdapter(this, categories);
        lvCategory.setAdapter(adapter);

        lvCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int index = 0;

                for (DataSnapshot childSnapshot : categoriesSnapshot.getChildren()) {
                    if (index == position) {
                        DatabaseReference currentCategorieReference = childSnapshot.getRef();

                        currentCategorieReference.removeValue();
                    }

                    index++;
                }
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                categoriesReference.push().setValue("testCategory");
                Intent in = new Intent(CategoriesActivity.this, AddCategoryActivity.class);
                startActivity(in);
            }
        });

        //  BACK ARROW TO MAIN SCREEN
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();

        categoriesReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                categoriesSnapshot = snapshot;

                categories.clear();

                for (DataSnapshot childSnapshot : snapshot.getChildren()) {
//                    categories.add(childSnapshot.getValue(String.class));
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //  BACK ARROW TO MAIN SCREEN
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}