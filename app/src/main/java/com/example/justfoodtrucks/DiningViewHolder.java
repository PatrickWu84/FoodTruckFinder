package com.example.justfoodtrucks;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DiningViewHolder extends RecyclerView.ViewHolder {
    TextView nameView, hoursView, statusView;
    ImageView imageView;

    public DiningViewHolder(@NonNull View itemView) {
        super(itemView);
        nameView = itemView.findViewById(R.id.name);
        imageView = itemView.findViewById(R.id.image);
        hoursView = itemView.findViewById(R.id.hours);
        statusView = itemView.findViewById(R.id.status);
    }

}
