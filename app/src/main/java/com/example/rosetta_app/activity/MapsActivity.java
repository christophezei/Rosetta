package com.example.rosetta_app.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rosetta_app.R;
import com.example.rosetta_app.animation.ViewAnimation;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private Location currentLocation;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;

    private Set<String> languages;
    private ArrayList<String> languagesList = new ArrayList<>();
    private HashMap<String, String> countriesMap = new HashMap<>();

    private SpinnerDialog spinnerDialog;


    private  boolean isRotate = false;
    @BindView(R.id.fabAutomatic)
    ExtendedFloatingActionButton fabAutomatic;

    @BindView(R.id.fabManual)
    ExtendedFloatingActionButton fabManual;

    @BindView(R.id.translateFrom)
    EditText translateFrom;

    @BindView(R.id.translateTo)
    EditText translateTo;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        ButterKnife.bind(this);
        ViewAnimation.init(fabAutomatic);
        ViewAnimation.init(fabManual);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        this.fetchLocation();
        this.initLanguagesList();
        this.initCountriesMap();
        this.translateFrom.setFocusable(false);
        this.translateFrom.setClickable(true);
        this.translateTo.setFocusable(false);
        this.translateTo.setClickable(true);
        spinnerDialog = new SpinnerDialog(MapsActivity.this, languagesList, "Select Language");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initLanguagesList() {
        languages = Arrays.stream(Locale.getISOLanguages())
                .map(Locale::new)
                .map(Locale::getDisplayLanguage)
                .collect(Collectors.toCollection(TreeSet::new));

        languagesList.addAll(languages);
    }

    private void initCountriesMap(){
        Locale[] locales = Locale.getAvailableLocales();
        for(int i = 0 ; i <locales.length; i++){
            countriesMap.put(locales[i].getLanguage(), locales[i].getCountry());
        }
    }


    private String getCountryNameBasedOnSelectedLanguage(Locale language){
        return countriesMap.get(language.getLanguage());
    }

    private void fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(location -> {
            if (location != null) {
                currentLocation = location;
                SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                assert supportMapFragment != null;
                supportMapFragment.getMapAsync(MapsActivity.this);
            }
        });
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
        LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title("I am here!");
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 5));
        googleMap.addMarker(markerOptions);
        googleMap.getUiSettings().setZoomControlsEnabled(false);
        googleMap.getUiSettings().setZoomGesturesEnabled(false);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    this.fetchLocation();
                }
                break;
        }
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
        Intent intent = new Intent(this, AutomaticMatchActivity.class);
        if(!translateFrom.getText().toString().isEmpty() && !translateTo.getText().toString().isEmpty()){
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),"Translate from and Translate to should not be empty",Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick(R.id.fabManual)
    public void fabManualOnClick(View v) {
        Intent intent = new Intent(this, ManualMatchActivity.class);
        if(!translateFrom.getText().toString().isEmpty() && !translateTo.getText().toString().isEmpty()){
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),"Translate from and Translate to should not be empty",Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.translateFrom)
    public void showLanguagesFrom() {
        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String language, int position) {
                translateFrom.setText(language);
                Locale locale = new Locale(language);
              //  Toast.makeText(getApplicationContext(),getCountryNameBasedOnSelectedLanguage(locale),Toast.LENGTH_SHORT).show();
            }
        });
        spinnerDialog.showSpinerDialog();

    }

    @OnClick(R.id.translateTo)
    public void showLanguagesTo() {
        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String language, int position) {
                translateTo.setText(language);
            }
        });
        spinnerDialog.showSpinerDialog();

    }
}