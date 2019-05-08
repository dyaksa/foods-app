package com.example.dyaksa.mealapp.view.intro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.example.dyaksa.mealapp.R;
import com.example.dyaksa.mealapp.view.home.MainActivity;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.model.SliderPage;

public class Intro extends AppIntro {

    public static final String EXTRA_INTRO = "intro";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //add slide 1
        SliderPage sliderPage = new SliderPage();
        sliderPage.setTitle(getResources().getString(R.string.slide_title_1));
        sliderPage.setDescription(getResources().getString(R.string.slide_desc_1));
        sliderPage.setImageDrawable(R.drawable.chefhat);
        sliderPage.setBgColor(getResources().getColor(R.color.colorPrimary));
        addSlide(AppIntroFragment.newInstance(sliderPage));

        //add slide 2
        SliderPage sliderPage2 = new SliderPage();
        sliderPage2.setTitle(getResources().getString(R.string.slide_title_2));
        sliderPage2.setDescription(getResources().getString(R.string.slide_desc_2));
        sliderPage2.setImageDrawable(R.drawable.salad);
        sliderPage2.setBgColor(getResources().getColor(R.color.colorPrimary));
        addSlide(AppIntroFragment.newInstance(sliderPage2));

        //add slide 3
        SliderPage sliderPage3 = new SliderPage();
        sliderPage3.setTitle(getResources().getString(R.string.slide_title_3));
        sliderPage3.setDescription(getResources().getString(R.string.slide_desc_3));
        sliderPage3.setImageDrawable(R.drawable.vegetables);
        sliderPage3.setBgColor(getResources().getColor(R.color.colorPrimary));
        addSlide(AppIntroFragment.newInstance(sliderPage3));


        showSkipButton(true);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment){
        super.onSkipPressed(currentFragment);
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment){
        super.onDonePressed(currentFragment);
        finish();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
    }
}
