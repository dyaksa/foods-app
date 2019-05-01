package com.example.dyaksa.mealapp.api;

import com.example.dyaksa.mealapp.model.Categories;
import com.example.dyaksa.mealapp.model.Meals;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FoodApi {

    @GET("latest.php")
    Call<Meals> getMeal();

    @GET("categories.php")
    Call<Categories> getCategories();

}
