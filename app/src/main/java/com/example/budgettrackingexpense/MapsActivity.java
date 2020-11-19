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
            LatLng astro = location.getParcelable("coordinates7");
            LatLng astro1 = location.getParcelable("coordinates4");
            LatLng astro2 = location.getParcelable("coordinates5");
            LatLng astro3 = location.getParcelable("coordinates6");

            mMap.addMarker(new MarkerOptions().position(astro).title("AstroBank"));
            mMap.addMarker(new MarkerOptions().position(astro1).title("AstroBank"));
            mMap.addMarker(new MarkerOptions().position(astro2).title("AstroBank"));
            mMap.addMarker(new MarkerOptions().position(astro3).title("AstroBank"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(astro, 8));
        }

        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }




}