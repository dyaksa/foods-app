package com.example.dyaksa.mealapp.view.detail;

import com.example.dyaksa.mealapp.Utils;
import com.example.dyaksa.mealapp.model.Meals;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPresenter {

    DetailView view;

    public DetailPresenter(DetailView view){
        this.view = view;
    }

    void getMealByName(String search){
        view.showLoading();
        Call<Meals> mealsCall = Utils.getApi().getMealByName(search);
        mealsCall.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                view.hideLoading();
                if(response.isSuccessful() && response.body() != null){
                    view.setMealByName(response.body().getMeals().get(0));
                }else{
                    view.onErrorLoading(response.message());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }
}
