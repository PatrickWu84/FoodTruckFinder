package com.example.justfoodtrucks.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.justfoodtrucks.DiningHall;
import com.example.justfoodtrucks.DiningHallAdapter;
import com.example.justfoodtrucks.DiningService;
import com.example.justfoodtrucks.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DiningHallFragment extends Fragment {
    List<DiningHall> diningHalls;
    DiningHallAdapter diningHallAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dining_hall, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.dining_recyclerview);

        // read dining hall data
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pennmobile.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DiningService service = retrofit.create(DiningService.class);
        service.getDiningHalls().enqueue(new Callback<List<DiningHall>>() {
            @Override
            public void onResponse(Call<List<DiningHall>> call, Response<List<DiningHall>> response) {
                if (response.isSuccessful()) {
                    diningHalls = response.body();
                    diningHallAdapter = new DiningHallAdapter(getActivity(), diningHalls);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(diningHallAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<DiningHall>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}