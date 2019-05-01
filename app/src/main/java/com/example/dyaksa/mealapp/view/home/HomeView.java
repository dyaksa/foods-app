package com.example.dyaksa.mealapp.view.home;


import com.example.dyaksa.mealapp.model.Categories;
import com.example.dyaksa.mealapp.model.Meals;

import java.util.List;

public interface HomeView {

    void showLoading();
    void hideLoading();
    void setMeal(List<Meals.Meal> meal);
    void setCategories(List<Categories.Category> categories);
    void onErrorLoading(String message);

}
