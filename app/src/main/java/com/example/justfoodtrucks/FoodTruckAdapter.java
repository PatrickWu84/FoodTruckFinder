package com.example.justfoodtrucks;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FoodTruckAdapter extends RecyclerView.Adapter<FoodTruckViewHolder> {
    private final RecyclerViewInterface recyclerViewInterface;
    Context context;
    ArrayList<FoodTruck> foodTrucks;

    public FoodTruckAdapter(Context applicationContext, ArrayList<FoodTruck> foodTrucks,
                            RecyclerViewInterface recyclerViewInterface) {
        this.context = applicationContext;
        this.foodTrucks = foodTrucks;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public FoodTruckViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FoodTruckViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent,
                false), recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodTruckViewHolder holder, int position) {
        holder.nameView.setText(foodTrucks.get(position).getName());
        holder.ratingView.setText(String.valueOf(foodTrucks.get(position).getRating()) + " / 5.0");
        holder.addressView.setText(foodTrucks.get(position).getAddress());
        holder.cuisineView.setText(foodTrucks.get(position).getCuisine() + ".");
    }

    @Override
    public int getItemCount() {
        return foodTrucks.size();
    }
}
