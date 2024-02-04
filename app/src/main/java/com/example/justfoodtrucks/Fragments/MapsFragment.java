package com.example.justfoodtrucks.Fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.justfoodtrucks.FoodTruck;
import com.example.justfoodtrucks.R;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;

public class MapsFragment extends Fragment {
    ArrayList<FoodTruck> foodTrucks;
    double currentLatitude;
    double currentLongitude;
    GoogleMap map;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            map = googleMap;
            // the map begins here
            LatLng defaultCenter = new LatLng(39.9529, -75.197098);
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultCenter, 14));

            // add the food truck markers
            for (FoodTruck foodTruck : foodTrucks) {
                String name = foodTruck.getName();
                double latitude = foodTruck.getLatitude();
                double longitude = foodTruck.getLongitude();
                LatLng truck = new LatLng(latitude, longitude);
                map.addMarker(new MarkerOptions()
                        .position(truck)
                        .title(name));
            }
            // this is the user's location
            LatLng currentLocation = new LatLng(currentLatitude, currentLongitude);
            map.addMarker(new MarkerOptions().position(currentLocation).title("My Location") .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            map.getUiSettings().setZoomControlsEnabled(true);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        foodTrucks = getArguments().getParcelableArrayList("foodTrucks");
        currentLatitude = getArguments().getDouble("currLatitude");
        currentLongitude = getArguments().getDouble("currLongitude");
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}