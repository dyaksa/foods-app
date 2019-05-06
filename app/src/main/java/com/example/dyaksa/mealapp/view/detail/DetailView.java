package com.example.dyaksa.mealapp.view.detail;

import com.example.dyaksa.mealapp.model.Meals;

import java.util.List;

public interface DetailView {
    void showLoading();
    void hideLoading();
    void setMealByName(Meals.Meal mealByName);
    void onErrorLoading(String message);
}
