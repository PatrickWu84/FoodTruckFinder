package com.example.justfoodtrucks.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.justfoodtrucks.FoodTruck;
import com.example.justfoodtrucks.FoodTruckAdapter;
import com.example.justfoodtrucks.R;
import com.example.justfoodtrucks.RecyclerViewInterface;
import com.example.justfoodtrucks.TruckDetailsActivity;

import java.util.ArrayList;
import java.util.Collections;


public class FoodTruckFragment extends Fragment implements RecyclerViewInterface {
    ArrayList<FoodTruck> foodTrucks;
    double userLatitude, userLongitude;
    FoodTruckAdapter myAdapter;
    Menu menu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_food_truck, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        foodTrucks = getArguments().getParcelableArrayList("foodTrucks");
        userLatitude = getArguments().getDouble("currLatitude");
        userLongitude = getArguments().getDouble("currLongitude");
        myAdapter = new FoodTruckAdapter(getActivity(), foodTrucks, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(myAdapter);
        setHasOptionsMenu(true);
        return view;
    }

    // pass along information to TruckDetailsActivity and open it
    @Override
    public void onItemClick(int pos) {
        Intent intent = new Intent(getContext(), TruckDetailsActivity.class);
        intent.putExtra("Name", foodTrucks.get(pos).getName());
        intent.putExtra("Details", foodTrucks.get(pos).getAdditionalInfo());
        intent.putExtra("Latitude", foodTrucks.get(pos).getLatitude());
        intent.putExtra("Longitude", foodTrucks.get(pos).getLongitude());
        intent.putExtra("Cuisine", foodTrucks.get(pos).getCuisine());
        intent.putExtra("Rating", foodTrucks.get(pos).getRating());
        intent.putExtra("Address", foodTrucks.get(pos).getAddress());
        startActivity(intent);
    }

    // drop down menu where users can sort the list in three different ways
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_rating_hi) {
            Collections.sort(foodTrucks, FoodTruck.ratingHiLoComparator);
            myAdapter.notifyDataSetChanged();
            return true;
        } else if (item.getItemId() == R.id.menu_rating_lo) {
            Collections.sort(foodTrucks, FoodTruck.ratingLoHiComparator);
            myAdapter.notifyDataSetChanged();
            return true;
        } else if (item.getItemId() == R.id.menu_distance) {
            for (FoodTruck f: foodTrucks) {
                f.computeDistance(userLatitude, userLongitude); // distance from user
            }
            Collections.sort(foodTrucks, FoodTruck.distanceComparator);
            myAdapter.notifyDataSetChanged();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.sort_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}