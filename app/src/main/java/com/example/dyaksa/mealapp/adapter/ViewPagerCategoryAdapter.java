package com.example.dyaksa.mealapp.adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dyaksa.mealapp.model.Categories;
import com.example.dyaksa.mealapp.view.category.CategoryFragment;

import java.util.List;

public class ViewPagerCategoryAdapter extends FragmentPagerAdapter {

    private List<Categories.Category> mData;

    public ViewPagerCategoryAdapter(FragmentManager fm, List<Categories.Category> mData){
        super(fm);
        this.mData = mData;
    }

    @Override
    public Fragment getItem(int i) {
        CategoryFragment fragment = new CategoryFragment();
        Bundle args = new Bundle();

        //set argumen untuk CategoryFragment.class
        args.putString("EXTRA_DATA_NAME",mData.get(i).getStrCategory());
        args.putString("EXTRA_DATA_DESC",mData.get(i).getStrCategoryDescription());
        args.putString("EXTRA_DATA_IMAGE",mData.get(i).getStrCategoryThumb());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return (mData != null) ? mData.size() : 0;
    }

    @NonNull
    @Override
    public CharSequence getPageTitle(int position){
        return mData.get(position).getStrCategory();
    }
}
