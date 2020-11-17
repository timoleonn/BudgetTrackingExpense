package com.example.budgettrackingexpense;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.budgettrackingexpense.ui.main.SectionsPagerAdapter;

public class Tabbed_Bank_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed__bank_);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fabGoToBanks);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = tabs.getSelectedTabPosition();

                switch (position) {
                    case 0:
//                        fab.setImageDrawable(ContextCompat.getDrawable(Tabbed_Bank_Activity.this, iconIntArray[0]));
                        Intent in = new Intent(Tabbed_Bank_Activity.this,
                                MapsActivity.class);
//                        intent.putExtra("USER_ID",USER_ID);
//                        intent.putExtra("FRAGMENT",0);
//                        intent.putExtra("TITLE",getResources().getString(R.string.Enter_Expense_title));
//                        intent.putExtra("FILED",getResources().getString(R.string.Enter_Expense_text));
                        startActivity(in);
                        break;
                    case 1:
                        Intent in1 = new Intent(Tabbed_Bank_Activity.this,
                                add_income.class);
//                        intent.putExtra("USER_ID",USER_ID);
//                        intent.putExtra("FRAGMENT",0);
//
//                        intent.putExtra("TITLE",getResources().getString(R.string.Enter_Expense_title));
//                        intent.putExtra("FILED",getResources().getString(R.string.Enter_Expense_text));

                        startActivity(in1);
                        break;
                    case 2:
                        Intent in2 = new Intent(Tabbed_Bank_Activity.this,
                                Settings.class);
//                        intent.putExtra("USER_ID",USER_ID);
//                        intent.putExtra("FRAGMENT",0);
//
//                        intent.putExtra("TITLE",getResources().getString(R.string.Enter_Expense_title));
//                        intent.putExtra("FILED",getResources().getString(R.string.Enter_Expense_text));

                        startActivity(in2);
                        break;

                    case 3:
                        Intent in3 = new Intent(Tabbed_Bank_Activity.this,
                                add_income.class);
//                        intent.putExtra("USER_ID",USER_ID);
//                        intent.putExtra("FRAGMENT",0);
//
//                        intent.putExtra("TITLE",getResources().getString(R.string.Enter_Expense_title));
//                        intent.putExtra("FILED",getResources().getString(R.string.Enter_Expense_text));

                        startActivity(in3);
                        break;
                    case 4:
//                        fab.setImageDrawable(ContextCompat.getDrawable(Tabbed_Bank_Activity.this, iconIntArray[0]));
                        Intent in4 = new Intent(Tabbed_Bank_Activity.this,
                                Settings.class);
//                        intent.putExtra("USER_ID",USER_ID);
//                        intent.putExtra("FRAGMENT",0);
//                        intent.putExtra("TITLE",getResources().getString(R.string.Enter_Expense_title));
//                        intent.putExtra("FILED",getResources().getString(R.string.Enter_Expense_text));
                        startActivity(in4);
                        break;
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
}