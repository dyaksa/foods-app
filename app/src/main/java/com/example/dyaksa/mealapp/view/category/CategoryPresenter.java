package com.example.dyaksa.mealapp.view.category;

import android.support.annotation.NonNull;

import com.example.dyaksa.mealapp.Utils;
import com.example.dyaksa.mealapp.model.Meals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryPresenter {

    private CategoryView view;

    public CategoryPresenter(CategoryView view){
        this.view = view;
    }

    void getMealByCategory(String str){
        view.showLoading();
        Call<Meals> mealCall = Utils.getApi().getMealByCategory(str);
        mealCall.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(@NonNull Call<Meals> call, @NonNull Response<Meals> response) {
                view.hideLoading();
                if(response.isSuccessful() && response.body() != null){
                    view.setMeals(response.body().getMeals());
                }else{
                    view.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<Meals> call, @NonNull Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });

    }
}
