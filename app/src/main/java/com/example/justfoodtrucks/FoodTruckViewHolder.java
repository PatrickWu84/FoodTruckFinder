package com.example.justfoodtrucks;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FoodTruckViewHolder extends RecyclerView.ViewHolder {
    TextView nameView, ratingView, addressView, cuisineView;

    public FoodTruckViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
        super(itemView);
        nameView = itemView.findViewById(R.id.name);
        ratingView = itemView.findViewById(R.id.rating);
        cuisineView = itemView.findViewById(R.id.cuisine);
        addressView = itemView.findViewById(R.id.address);
        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (recyclerViewInterface != null) {
                    int pos = getBindingAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        recyclerViewInterface.onItemClick(pos);
                    }
                }
            }
        });

    }
}
