package com.example.budgettrackingexpense;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private AppBarConfiguration mAppBarConfiguration;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;

    public static final String FULLNAME = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null ) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.nav_host_fragment, new HomeFragment());
            fragmentTransaction.commit();
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //  CHECK WHAT MENU ITEM IN NAVIGATION DRAWER IS PRESSED
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                goToFragment(new HomeFragment());
            case R.id.nav_categories:
                startActivity(new Intent(getApplicationContext(), CategoriesActivity.class));
                overridePendingTransition(0, 0);
            case R.id.nav_my_banks:
                goToFragment(new MyBanksFragment());
            case R.id.nav_all_banks:
                startActivity(new Intent(getApplicationContext(), Tabbed_Bank_Activity.class));
                overridePendingTransition(0, 0);
            case R.id.nav_profile:
//                goToFragment(new ProfileFragment());
            case R.id.nav_rate_us:
                goToFragment(new RateUsFragment());
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //  CALL FRAGMENT
    public void goToFragment(Fragment f) {
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment, f);
        fragmentTransaction.commit();
    }


}
