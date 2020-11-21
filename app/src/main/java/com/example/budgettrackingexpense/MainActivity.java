package com.example.budgettrackingexpense;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

import com.example.budgettrackingexpense.ui.login.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
    private Button logout;


    public static final String FULLNAME = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        logout = findViewById(R.id.nav_logout);

        drawer = findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null ) {
            goToFragment(new HomeFragment());
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
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            goToFragment(new HomeFragment());
        } else if (id == R.id.nav_categories) {
            startActivity(new Intent(getApplicationContext(), CategoriesActivity.class));
            overridePendingTransition(0, 0);
        } else if (id == R.id.nav_my_banks) {
            goToFragment(new MyBanksFragment());
        } else if (id == R.id.nav_all_banks) {
            startActivity(new Intent(getApplicationContext(), Tabbed_Bank_Activity.class));
            overridePendingTransition(0, 0);
        } else if (id == R.id.nav_profile) {
            //
        } else if (id == R.id.nav_logout) {
            System.out.println("SOMETHING WENT WRONG 1" );
            signOut();
            startActivity(new Intent(this, LoginActivity.class));
        } else if (id == R.id.nav_rate_us) {
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
    public void signOut()
    {
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("SOMETHING WENT WRONG2" );
                FirebaseAuth.getInstance().signOut();
                System.out.println("SOMETHING WENT WRONG3" );
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                System.out.println("SOMETHING WENT WRONG4" );
            }
        });
    }

}
