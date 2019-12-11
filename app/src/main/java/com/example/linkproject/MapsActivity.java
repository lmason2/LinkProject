package com.example.linkproject;
/*
  MapsActivity.java
  Luke Mason & JD Gruber
  CPSC 312 Final Project
  Link
  Sets up a google map of the Gonzaga area with
  predefined markers in the map representing events
*/

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnMyLocationClickListener {
    static final int MY_LOCATION_REQUEST_CODE = 1;
    static final int MARKER_REQUEST_CODE = 2;

    // working with Firebase
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mEventsDatabaseReference;
    ChildEventListener mEventsChildEventListener;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        setupFirebase();
    }

    private void setupFirebase() {
        // initialize the firebase references
        mFirebaseDatabase =
                FirebaseDatabase.getInstance();
        mEventsDatabaseReference =
                mFirebaseDatabase.getReference()
                        .child("events");
        mEventsChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Toast.makeText(MapsActivity.this, "Here", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
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
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
            mMap.setOnMyLocationClickListener(this);
        }
        else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_LOCATION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_LOCATION_REQUEST_CODE) {
            if (permissions.length == 1
                    && permissions[0].equals(Manifest.permission.ACCESS_FINE_LOCATION)
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
                mMap.setOnMyLocationClickListener(this);
            }
        }
    }

    @Override
    public void onMyLocationClick(@NonNull Location location) {
        Toast.makeText(this, "You're at " + location.getLatitude() + ", " + location.getLongitude(), Toast.LENGTH_SHORT).show();
    }

    public void onNewEventClick(View view) {
        Intent intent = new Intent(MapsActivity.this, addEventActivity.class);
//        startActivityForResult(intent, MARKER_REQUEST_CODE);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Intent intent = getIntent();
        if (requestCode == MARKER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            String title = intent.getStringExtra("title");
            String description = intent.getStringExtra("description");
            String date = intent.getStringExtra("date");
            String start = intent.getStringExtra("start");
            String end = intent.getStringExtra("end");

            String location = intent.getStringExtra("location");
            LatLng locationLatLng = getLatLngUsingGeoCoding(location);

            // create a marker
            MarkerOptions locationMarkerOptions = new MarkerOptions();
            locationMarkerOptions.title(location);
            locationMarkerOptions.snippet(title + description + date);
            locationMarkerOptions.position(locationLatLng);
            mMap.addMarker(locationMarkerOptions);

            // move the camera
            CameraUpdate locationCameraUpdate = CameraUpdateFactory.newLatLngZoom(locationLatLng, 15.0f);
            mMap.moveCamera((locationCameraUpdate));
        }
    }

    private LatLng getLatLngUsingGeoCoding(String addressStr){
        LatLng latLng = null;
        Geocoder geocoder = new Geocoder(this);
        try{
            List<Address> addressList = geocoder.getFromLocationName(addressStr, 1);
            if(addressList != null && addressList.size() > 0){
                Address adressResult = addressList.get(0);
                latLng = new LatLng(adressResult.getLatitude(), adressResult.getLongitude());
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return latLng;
    }
}