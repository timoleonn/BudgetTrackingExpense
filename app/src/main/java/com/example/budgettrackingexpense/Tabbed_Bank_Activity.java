package com.example.budgettrackingexpense;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.budgettrackingexpense.ui.main.SectionsPagerAdapter;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

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
                        LatLng alpha = new LatLng(34.917018, 33.608868);
                        LatLng alpha1 = new LatLng(35.171103, 33.354261);
                        LatLng alpha2 = new LatLng(35.163987, 33.318087);
                        LatLng alpha3 = new LatLng(34.930513, 33.637572);

                        Bundle alphab = new Bundle();
                        alphab.putString("marker", "Alpha Bank");
                        alphab.putParcelable("coordinates", alpha);
                        alphab.putParcelable("coordinates1", alpha1);
                        alphab.putParcelable("coordinates2", alpha2);
                        alphab.putParcelable("coordinates3", alpha3);

                        Intent in = new Intent(Tabbed_Bank_Activity.this,
                                MapsActivity.class);
                        in.putExtras(alphab);
                        startActivity(in);
                        break;
                    case 1:
                        LatLng astro = new LatLng(35.111182, 33.383313);
                        LatLng astro1 = new LatLng(35.145242, 33.344548);
                        LatLng astro2 = new LatLng(35.154031, 33.361593);

                        Bundle astrob = new Bundle();
                        astrob.putString("marker", "AstroBank");
                        astrob.putParcelable("coordinates4", astro);
                        astrob.putParcelable("coordinates5", astro1);
                        astrob.putParcelable("coordinates6", astro2);

                        Intent in1 = new Intent(Tabbed_Bank_Activity.this,
                                MapsActivity.class);
                        in1.putExtras(astrob);
                        startActivity(in1);
                        break;
                    case 2:
                        LatLng cyprus = new LatLng(35.165165, 33.318063);
                        LatLng cyprus1 = new LatLng(35.163129, 33.332397);
                        LatLng cyprus2 = new LatLng(35.168865, 33.335895);
                        LatLng cyprus3 = new LatLng(35.177038, 33.339306);
                        LatLng cyprus4 = new LatLng(35.164443, 33.339996);
                        LatLng cyprus5 = new LatLng(35.161752, 33.346001);
                        LatLng cyprus6 = new LatLng(35.161799, 33.367064);
                        LatLng cyprus7 = new LatLng(34.674117, 33.043826);
                        LatLng cyprus8 = new LatLng(34.670843, 33.040958);
                        LatLng cyprus9 = new LatLng(34.674073, 33.043875);
                        LatLng cyprus10 = new LatLng(34.777063, 32.422922);
                        LatLng cyprus11 = new LatLng(34.773560, 32.418914);
                        LatLng cyprus12 = new LatLng(34.773879, 32.428929);
                        LatLng cyprus13 = new LatLng(34.917222, 33.611487);

                        Bundle cyprusb = new Bundle();
                        cyprusb.putString("marker", "Bank of cyprus");
                        cyprusb.putParcelable("coordinates", cyprus);
                        cyprusb.putParcelable("coordinates1", cyprus1);
                        cyprusb.putParcelable("coordinates2", cyprus2);
                        cyprusb.putParcelable("coordinates3", cyprus3);
                        cyprusb.putParcelable("coordinates4", cyprus4);
                        cyprusb.putParcelable("coordinates5", cyprus5);
                        cyprusb.putParcelable("coordinates6", cyprus6);
                        cyprusb.putParcelable("coordinates7", cyprus7);
                        cyprusb.putParcelable("coordinates8", cyprus8);
                        cyprusb.putParcelable("coordinates9", cyprus9);
                        cyprusb.putParcelable("coordinates10", cyprus10);
                        cyprusb.putParcelable("coordinates11", cyprus11);
                        cyprusb.putParcelable("coordinates12", cyprus12);
                        cyprusb.putParcelable("coordinates13", cyprus13);

                        Intent in2 = new Intent(Tabbed_Bank_Activity.this,
                                MapsActivity.class);

                        in2.putExtras(cyprusb);
                        startActivity(in2);
                        break;
                    case 3:
                        LatLng hellenic = new LatLng(34.919293, 33.614502);
                        LatLng hellenic1 = new LatLng(34.922267, 33.613418);
                        LatLng hellenic2 = new LatLng(34.930839, 33.621196);
                        LatLng hellenic3=new LatLng(34.928693, 33.637203);

                        Bundle hellenicb = new Bundle();
                        hellenicb.putString("marker", "Hellenic Bank");
                        hellenicb.putParcelable("coordinates", hellenic);
                        hellenicb.putParcelable("coordinates1", hellenic1);
                        hellenicb.putParcelable("coordinates2", hellenic2);
                        hellenicb.putParcelable("coordinates3", hellenic3);

                        Intent in3 = new Intent(Tabbed_Bank_Activity.this,
                                MapsActivity.class);
                        in3.putExtras(hellenicb);
                        startActivity(in3);
                        break;

                    case 4:
                        LatLng rcb = new LatLng(34.919293, 33.614502);
                        LatLng rcb1 = new LatLng(34.922267, 33.613418);
                        LatLng rcb2 = new LatLng(34.930839, 33.621196);
                        LatLng rcb3=new LatLng(34.928693, 33.637203);

                        Bundle rcbBank = new Bundle();
                        rcbBank.putString("marker", "Hellenic Bank");
                        rcbBank.putParcelable("coordinates", rcb);
                        rcbBank.putParcelable("coordinates1", rcb1);
                        rcbBank.putParcelable("coordinates2", rcb2);
                        rcbBank.putParcelable("coordinates3", rcb3);

                        Intent in4 = new Intent(Tabbed_Bank_Activity.this,
                                MapsActivity.class);

                        in4.putExtras(rcbBank);
                        startActivity(in4);

                        break;
                }
            }
        });

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