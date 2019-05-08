package com.example.dyaksa.mealapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.dyaksa.mealapp.api.FoodApi;
import com.example.dyaksa.mealapp.api.FoodClient;
import com.example.dyaksa.mealapp.view.home.MainActivity;
import com.example.dyaksa.mealapp.view.intro.Intro;

public class Utils {

    public static FoodApi getApi() {
        return FoodClient.getFoodClient().create(FoodApi.class);
    }

    public static AlertDialog showDialogMessage(Context context, String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).setTitle(title).setMessage(message).show();
        if (alertDialog.isShowing()) {
            alertDialog.cancel();
        }
        return alertDialog;
    }

    public static void checkAppIntro(Context context, String name) {
        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(context);
        if (!sharedPreferences.getBoolean(name, false)) {
            context.startActivity(new Intent(context, Intro.class));
        }
    }
}
