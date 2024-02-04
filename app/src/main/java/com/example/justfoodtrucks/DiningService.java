package com.example.justfoodtrucks;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DiningService {
    @GET("api/dining/venues/")
    Call<List<DiningHall>> getDiningHalls();
}

