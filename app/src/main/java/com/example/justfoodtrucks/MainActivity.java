package com.example.justfoodtrucks;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    // foodTrucks will be used in both the Food Truck and Maps fragments, so it is declared here
    ArrayList<FoodTruck> foodTrucks = new ArrayList<>();
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    MyViewPagerAdapter myViewPagerAdapter;
    // variables to store the location of the user
    double latitude;
    double longitude;
    FusedLocationProviderClient fusedClient;
    private static final int REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fusedClient = LocationServices.getFusedLocationProviderClient(this);
        getLocation();

        //Read and parse the FoodTruckData from text file
        try {
            InputStream is = getAssets().open("FoodTruckData.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.isEmpty()) {
                    String name = line.trim();
                    String ratingLine = reader.readLine().trim();
                    double rating = Double.parseDouble(ratingLine.substring(ratingLine.indexOf(":") + 1, ratingLine.indexOf("/")).trim());
                    String addressLine = reader.readLine().trim();
                    String address = addressLine.split("- Coordinates:")[0].trim();
                    String[] coords = addressLine.split("- Coordinates:")[1].trim().split(",");
                    double latitude = Double.parseDouble(coords[0].trim());
                    double longitude = Double.parseDouble(coords[1].trim());

                    String cuisine = reader.readLine().trim();
                    String additionalInfo = "";
                    if ((line = reader.readLine()) != null && !line.isEmpty()) {
                        additionalInfo = line.trim();
                    }
                    foodTrucks.add(new FoodTruck(name, cuisine, rating, address, latitude, longitude, additionalInfo));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        tabLayout = findViewById(R.id.tab_layout);
        viewPager2 = findViewById(R.id.view_pager);
        myViewPagerAdapter = new MyViewPagerAdapter(this, foodTrucks, latitude, longitude);
        viewPager2.setAdapter(myViewPagerAdapter);
        viewPager2.setUserInputEnabled(false);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void getLocation() {
        // Check if we have location permission
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_CODE); // request permission
        }
      fusedClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                myViewPagerAdapter = new MyViewPagerAdapter(MainActivity.this, foodTrucks, location.getLatitude(), location.getLongitude());
                viewPager2.setAdapter(myViewPagerAdapter);
                if (location != null) {
                    latitude = location.getLatitude();
                    longitude = location.getLatitude();

                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLocation();
            } else {
                Toast.makeText(this, "Please Grant Permission!", Toast.LENGTH_SHORT).show();
            }
        }
    }
    // ActionBar always displays the current date and time
    public void setActionBar() {
        LocalDateTime now =  LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm a E, MMM d yyyy");
        String formattedDateTime = now.format(formatter);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(formattedDateTime);
        }
    }

    private final Handler handler = new Handler(Looper.getMainLooper());
    private final Runnable dateTimeUpdater = new Runnable() {
        @Override
        public void run() {
            setActionBar();
            // Schedule this runnable again in 1 minute
            handler.postDelayed(this, 60000);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        dateTimeUpdater.run(); // Start updating the ActionBar title
    }


}