package com.example.justfoodtrucks;

import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.justfoodtrucks.Fragments.DiningHallFragment;
import com.example.justfoodtrucks.Fragments.FoodTruckFragment;
import com.example.justfoodtrucks.Fragments.MapsFragment;

import java.util.ArrayList;
import java.util.List;

public class MyViewPagerAdapter extends FragmentStateAdapter {
    ArrayList<FoodTruck> foodTrucks;
    double longitude;
    double latitude;
    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, ArrayList<FoodTruck> foodTrucks,
                              double latitude, double longitude) {
        super(fragmentActivity);
        this.foodTrucks = foodTrucks;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // pass the necessary information into fragments as arguments
        Bundle bundle = new Bundle();
        bundle.putSerializable("foodTrucks", foodTrucks);
        bundle.putDouble("currLatitude", latitude);
        bundle.putDouble("currLongitude", longitude);
        switch (position) {
            case 1:
                MapsFragment mapsFragment = new MapsFragment();
                mapsFragment.setArguments(bundle);
                return mapsFragment;
            case 2:
                return new DiningHallFragment();
            default: // the app will begin on the food truck page
                FoodTruckFragment foodTruckFragment = new FoodTruckFragment();
                foodTruckFragment.setArguments(bundle);
                return foodTruckFragment;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
