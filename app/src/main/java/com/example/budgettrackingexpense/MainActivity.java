package com.example.budgettrackingexpense;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.budgettrackingexpense.ui.login.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

    DatabaseReference databaseReference;

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

        //  GRAB HEADER VALUES SO WE CAN SET THE VALUES FROM THE DATABASE
        NavigationView mNavigationView = findViewById(R.id.nav_view);
        TextView tvHeaderFullName = mNavigationView.getHeaderView(0).findViewById(R.id.tv_header_full_name);
        TextView tvHeaderEmail = mNavigationView.getHeaderView(0).findViewById(R.id.tv_header_email);
        TextView tvHeaderOccupation = mNavigationView.getHeaderView(0).findViewById(R.id.tv_header_occupation);
        ImageView ivProfile = mNavigationView.getHeaderView(0).findViewById(R.id.ivProfile);

        //  GET CURRENT USER
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        //  GET CURRENT USER UID
        String currentUserUid = currentUser.getUid();
        String currentUserEmail = currentUser.getEmail();



        //  GRAB DATA FROM THE DATABASE BASED ON CURRENT USER'S UID
        databaseReference = FirebaseDatabase.getInstance().getReference("users").child(currentUserUid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String _FULLNAME = snapshot.child("fullname").getValue().toString();
                String _OCCUPATION = snapshot.child("occupation").getValue().toString();
                String _GENDER = snapshot.child("gender").getValue().toString();

                tvHeaderFullName.setText(_FULLNAME);
                tvHeaderEmail.setText(currentUserEmail);
                tvHeaderOccupation.setText(_OCCUPATION);

                if (_GENDER.equals("Male")) {
                    ivProfile.setBackgroundResource(R.drawable.ic_iconfinder_malecostume_403022);
                } else if (_GENDER.equals("Female")) {
                    ivProfile.setBackgroundResource(R.drawable.ic_iconfinder_female1_403023);
                } else if (_GENDER.equals("Other")) {
                    ivProfile.setBackgroundResource(R.drawable.ic_iconfinder_other);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


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
            signOut();
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

    //  SIGN OUT
    public void signOut()
    {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }

}
