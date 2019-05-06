package com.example.dyaksa.mealapp.view.home;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dyaksa.mealapp.R;
import com.example.dyaksa.mealapp.Utils;
import com.example.dyaksa.mealapp.adapter.RecyclerViewHomeAdapter;
import com.example.dyaksa.mealapp.adapter.ViewPagerHeaderAdapter;
import com.example.dyaksa.mealapp.model.Categories;
import com.example.dyaksa.mealapp.model.Meals;
import com.example.dyaksa.mealapp.view.category.CategoryActivity;
import com.example.dyaksa.mealapp.view.detail.DetailActivity;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements HomeView {

    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_DETAIL = "detail";

    @BindView(R.id.viewHeader)
    ViewPager viewPagerHeader;

    @BindView(R.id.rv_category)
    RecyclerView rv_category;

    HomePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        presenter = new HomePresenter(this);
        presenter.getMeal();
        presenter.getCategories();
    }

    @Override
    public void showLoading() {
        findViewById(R.id.shimmerMeal).setVisibility(View.VISIBLE);
        findViewById(R.id.shimmerCategory).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        findViewById(R.id.shimmerMeal).setVisibility(View.GONE);
        findViewById(R.id.shimmerCategory).setVisibility(View.GONE);
    }

    @Override
    public void setMeal(List<Meals.Meal> meal) {
        //for each di java
//        for(Meals.Meal mealResult : meal){
//            Log.w("meal name : ", mealResult.getStrMeal());
//        }
        ViewPagerHeaderAdapter headerAdapter = new ViewPagerHeaderAdapter(this,meal);
        viewPagerHeader.setAdapter(headerAdapter);
        viewPagerHeader.setPadding(20,0,150,0);
        headerAdapter.notifyDataSetChanged();

        //setOnItemClick ke detail activity dengan pengambilan data dari TextView
        headerAdapter.setOnItemClickListener((v, position) -> {
            TextView mealName = v.findViewById(R.id.mealName);
            Intent intent = new Intent(this,DetailActivity.class);
            intent.putExtra(EXTRA_DETAIL,mealName.getText().toString());
            startActivity(intent);
        });

    }

    @Override
    public void setCategories(List<Categories.Category> categories) {
        RecyclerViewHomeAdapter categoryAdapter = new RecyclerViewHomeAdapter(this,categories);
        rv_category.setAdapter(categoryAdapter);

        //untuk grid layout,adapula linaerlayoutmanager
        GridLayoutManager layoutManager = new GridLayoutManager(this,3,
                GridLayoutManager.VERTICAL,false);
        rv_category.setLayoutManager(layoutManager);
        rv_category.setNestedScrollingEnabled(true);
        categoryAdapter.notifyDataSetChanged();

        //setOnClickListener
        categoryAdapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(this,CategoryActivity.class);

            //serializable implements dari model Category
            intent.putExtra(EXTRA_CATEGORY,(Serializable) categories);
            intent.putExtra(EXTRA_POSITION, position);
            startActivity(intent);
        });
    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(this,"Title",message);
    }
}
