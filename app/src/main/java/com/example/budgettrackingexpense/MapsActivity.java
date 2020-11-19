package com.example.budgettrackingexpense;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


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

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Intent in = getIntent();
        Bundle location = in.getExtras();
        String marker = location.getString("marker");

        if (marker.equals("Alpha Bank")) {
            LatLng alpha = location.getParcelable("coordinates");
            LatLng alpha1 = location.getParcelable("coordinates1");
            LatLng alpha2 = location.getParcelable("coordinates2");
            LatLng alpha3 = location.getParcelable("coordinates3");

            mMap.addMarker(new MarkerOptions().position(alpha).title("Alpha Bank"));
            mMap.addMarker(new MarkerOptions().position(alpha1).title("Alpha Bank"));
            mMap.addMarker(new MarkerOptions().position(alpha2).title("Alpha Bank"));
            mMap.addMarker(new MarkerOptions().position(alpha3).title("Alpha Bank"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(alpha, 8));

        } else if (marker.equals("AstroBank")) {
            LatLng astro = location.getParcelable("coordinates4");
            LatLng astro1 = location.getParcelable("coordinates5");
            LatLng astro2 = location.getParcelable("coordinates6");


            mMap.addMarker(new MarkerOptions().position(astro).title("AstroBank"));
            mMap.addMarker(new MarkerOptions().position(astro1).title("AstroBank"));
            mMap.addMarker(new MarkerOptions().position(astro2).title("AstroBank"));

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(astro, 8));
        } else if (marker.equals("Bank of cyprus")) {
            LatLng cyprus = location.getParcelable("coordinates");
            LatLng cyprus1 = location.getParcelable("coordinates1");
            LatLng cyprus2 = location.getParcelable("coordinates2");
            LatLng cyprus3 = location.getParcelable("coordinates3");
            LatLng cyprus4 = location.getParcelable("coordinates4");
            LatLng cyprus5 = location.getParcelable("coordinates5");
            LatLng cyprus6 = location.getParcelable("coordinates6");
            LatLng cyprus7 = location.getParcelable("coordinates7");
            LatLng cyprus8 = location.getParcelable("coordinates8");
            LatLng cyprus9 = location.getParcelable("coordinates9");
            LatLng cyprus10 = location.getParcelable("coordinates10");
            LatLng cyprus11 = location.getParcelable("coordinates11");
            LatLng cyprus12 = location.getParcelable("coordinates12");
            LatLng cyprus13 = location.getParcelable("coordinates13");


            mMap.addMarker(new MarkerOptions().position(cyprus).title("Bank of cyprus"));
            mMap.addMarker(new MarkerOptions().position(cyprus1).title("Bank of cyprus"));
            mMap.addMarker(new MarkerOptions().position(cyprus2).title("Bank of cyprus"));
            mMap.addMarker(new MarkerOptions().position(cyprus3).title("Bank of cyprus"));
            mMap.addMarker(new MarkerOptions().position(cyprus4).title("Bank of cyprus"));
            mMap.addMarker(new MarkerOptions().position(cyprus5).title("Bank of cyprus"));
            mMap.addMarker(new MarkerOptions().position(cyprus6).title("Bank of cyprus"));
            mMap.addMarker(new MarkerOptions().position(cyprus7).title("Bank of cyprus"));
            mMap.addMarker(new MarkerOptions().position(cyprus8).title("Bank of cyprus"));
            mMap.addMarker(new MarkerOptions().position(cyprus9).title("Bank of cyprus"));
            mMap.addMarker(new MarkerOptions().position(cyprus10).title("Bank of cyprus"));
            mMap.addMarker(new MarkerOptions().position(cyprus11).title("Bank of cyprus"));
            mMap.addMarker(new MarkerOptions().position(cyprus12).title("Bank of cyprus"));
            mMap.addMarker(new MarkerOptions().position(cyprus13).title("Bank of cyprus"));


            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cyprus, 8));


        }else if (marker.equals("Hellenic Bank")) {
            LatLng hellenic = location.getParcelable("coordinates");
            LatLng hellenic1 = location.getParcelable("coordinates1");
            LatLng hellenic2 = location.getParcelable("coordinates2");
            LatLng hellenic3 = location.getParcelable("coordinates3");


            mMap.addMarker(new MarkerOptions().position(hellenic).title("Hellenic Bank"));
            mMap.addMarker(new MarkerOptions().position(hellenic1).title("Hellenic Bank"));
            mMap.addMarker(new MarkerOptions().position(hellenic2).title("Hellenic Bank"));
            mMap.addMarker(new MarkerOptions().position(hellenic3).title("Hellenic Bank"));

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hellenic, 8));
        }else if (marker.equals("RcbBank")) {
            LatLng rcb = location.getParcelable("coordinates");
            LatLng rcb1 = location.getParcelable("coordinates1");
            LatLng rcb2 = location.getParcelable("coordinates2");
            LatLng rcb3 = location.getParcelable("coordinates3");


            mMap.addMarker(new MarkerOptions().position(rcb).title("Rcb Bank"));
            mMap.addMarker(new MarkerOptions().position(rcb1).title("Rcb Bank"));
            mMap.addMarker(new MarkerOptions().position(rcb2).title("Rcb Bank"));
            mMap.addMarker(new MarkerOptions().position(rcb3).title("Rcb Bank"));

            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(rcb, 8));
        }
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);


    }
}