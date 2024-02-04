package com.example.justfoodtrucks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class DiningHallAdapter extends RecyclerView.Adapter<DiningViewHolder>{
    Context context;
    List<DiningHall> diningHalls;

    public DiningHallAdapter(Context applicationContext, List<DiningHall> diningHalls) {
        this.context = applicationContext;
        this.diningHalls = diningHalls;
    }

    @NonNull
    @Override
    public DiningViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DiningViewHolder(LayoutInflater.from(context).inflate(R.layout.dining_item_view, parent,
                false));
    }

    // pass the values from the dining hall list
    @Override
    public void onBindViewHolder(@NonNull DiningViewHolder holder, int position) {
        holder.nameView.setText(diningHalls.get(position).getName());
        DiningDay today = diningHalls.get(position).getDiningDay();
        if (today != null) {
            holder.statusView.setText(today.getStatus());
            holder.hoursView.setText(today.getHours());
        } else {
            // no information for today
            holder.statusView.setText(R.string.not_available);
        }
        Glide.with(context).load(diningHalls.get(position).getImage()).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return diningHalls.size();
    }
}
