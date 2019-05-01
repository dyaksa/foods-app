package com.example.dyaksa.mealapp.view.category;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dyaksa.mealapp.R;
import com.example.dyaksa.mealapp.Utils;
import com.example.dyaksa.mealapp.adapter.RecyclerViewMealByCategory;
import com.example.dyaksa.mealapp.model.Meals;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment implements CategoryView {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.imageCategory)
    ImageView imageCategory;

    @BindView(R.id.imageCategoryBg)
    ImageView imageCategoryBg;

    @BindView(R.id.textCategory)
    TextView textCategory;

    private RequestOptions options;
    AlertDialog.Builder descDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        options = new RequestOptions().centerCrop().placeholder(R.drawable.sample_image_meal).error(R.drawable.sample_image_meal);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //check kondisi Bundle argument dari ViewPagerCategoryAdapter
        if(getArguments() != null){
            //set text title
            textCategory.setText(getArguments().getString("EXTRA_DATA_DESC"));
            //set image
            Glide.with(getActivity().getApplicationContext())
                    .load(getArguments().getString("EXTRA_DATA_IMAGE"))
            .apply(options)
            .into(imageCategory);
            //set Image Background
            Glide.with(getActivity().getApplicationContext())
                    .load(getArguments().getString("EXTRA_DATA_IMAGE"))
                    .apply(options)
                    .into(imageCategoryBg);

            descDialog = new AlertDialog.Builder(getActivity())
                    .setTitle(getArguments().getString("EXTRA_DATA_NAME"))
                    .setMessage(getArguments().getString("EXTRA_DATA_DESC"));

            //presenter parameter args yang diambil dari ViewPagerCategoryAdapter
            CategoryPresenter presenter = new CategoryPresenter(this);
            presenter.getMealByCategory(getArguments().getString("EXTRA_DATA_NAME"));
        }
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setMeals(List<Meals.Meal> meals) {
        //List Meal sudah di set pada CategoryPresenter
        RecyclerViewMealByCategory adapter = new RecyclerViewMealByCategory(getActivity(),meals);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter.setOnItemClickListener((view, position) -> {
            Toast.makeText(getActivity(),meals.get(position).getStrMeal(),Toast.LENGTH_LONG).show();
        });

    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(getActivity(), "Error ", message);
    }

    @OnClick(R.id.cardCategory)
    public void onClick() {
        descDialog.setPositiveButton("CLOSE", (dialog, which) -> dialog.dismiss());
        descDialog.show();
    }



}
