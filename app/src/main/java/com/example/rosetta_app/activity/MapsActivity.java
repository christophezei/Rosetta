package com.example.rosetta_app.activity;

import androidx.fragment.app.FragmentActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import android.os.Bundle;
import android.view.View;

import com.example.rosetta_app.R;
import com.example.rosetta_app.animation.ViewAnimation;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;


    private  boolean isRotate = false;
    @BindView(R.id.fabAutomatic)
    ExtendedFloatingActionButton fabAutomatic;

    @BindView(R.id.fabManual)
    ExtendedFloatingActionButton fabManual;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        ButterKnife.bind(this);
        ViewAnimation.init(fabAutomatic);
        ViewAnimation.init(fabManual);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @OnClick(R.id.fab)
    public void fabOnClick(View v) {
        isRotate = ViewAnimation.rotateFab(v,!isRotate);
        if(isRotate){
            ViewAnimation.showIn(fabManual);
            ViewAnimation.showIn(fabAutomatic);
        }else{
            ViewAnimation.showOut(fabManual);
            ViewAnimation.showOut(fabAutomatic);
        }

    }
    @OnClick(R.id.fabAutomatic)
    public void fabAutoOnClick() {

    }

    @OnClick(R.id.fabManual)
    public void fabManualOnClick(View v) {

    }
}