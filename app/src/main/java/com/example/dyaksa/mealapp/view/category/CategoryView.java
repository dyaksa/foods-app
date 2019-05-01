package com.example.dyaksa.mealapp.view.category;

import com.example.dyaksa.mealapp.model.Meals;

import java.util.List;

public interface CategoryView {
    void showLoading();
    void hideLoading();
    void setMeals(List<Meals.Meal> meals);
    void onErrorLoading(String message);
}
