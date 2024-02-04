package com.example.justfoodtrucks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//this is the activity that shows up when you click on a specific food truck. it display all
//the details from the recycler view along with some additional info like phone number and hours.
//To exit this activity, use the back around built into Android devices
public class TruckDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truck_details);
        String name = getIntent().getStringExtra("Name");
        String additionalInfo = getIntent().getStringExtra("Details");
        String rating = String.valueOf(getIntent().getDoubleExtra("Rating", 0.0));
        String cuisine = getIntent().getStringExtra("Cuisine");
        String address = getIntent().getStringExtra("Address");

        TextView nameView = findViewById(R.id.name);
        TextView addressView = findViewById(R.id.address);
        TextView ratingView = findViewById(R.id.rating);
        TextView cuisineView = findViewById(R.id.cuisine);
        TextView additionalInfoView = findViewById(R.id.additionalInfo);

        nameView.setText(name);
        addressView.setText(address);
        ratingView.setText(rating);
        cuisineView.setText(cuisine);
        additionalInfoView.setText(additionalInfo);
    }
    // preserve the action bar with time and date
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
            // 1 minute
            handler.postDelayed(this, 60000);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        dateTimeUpdater.run();
    }
}