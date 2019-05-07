package com.example.dyaksa.mealapp.view.detail;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dyaksa.mealapp.R;
import com.example.dyaksa.mealapp.Utils;
import com.example.dyaksa.mealapp.model.Meals;
import com.example.dyaksa.mealapp.view.WebViewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements DetailView{

    public static final String EXTRA_DETAIL = "detail";
    public static final String EXTRA_URL = "url";

    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;

    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.mealThumb)
    ImageView mealThumb;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.category)
    TextView category;

    @BindView(R.id.country)
    TextView country;

    @BindView(R.id.instructions)
    TextView instructions;

    @BindView(R.id.ingredient)
    TextView ingredient;

    @BindView(R.id.measure)
    TextView measure;

    @BindView(R.id.youtube)
    TextView youtube;

    @BindView(R.id.source)
    TextView source;

    private RequestOptions options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setupActionBar();

        //Mengambil Nilai Intent
        Intent intent = getIntent();
        String mealName = intent.getStringExtra(EXTRA_DETAIL);

        options = new RequestOptions().centerCrop()
                .placeholder(R.drawable.sample_image_meal)
                .error(R.drawable.sample_image_meal);

        DetailPresenter presenter = new DetailPresenter(this);
        presenter.getMealByName(mealName);
    }

    private void setupActionBar() {
        setSupportActionBar(toolbar);
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.colorWhite));
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(R.color.colorPrimary));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.colorWhite));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_detail,menu);
        MenuItem favoriteItem = menu.findItem(R.id.favorite);
        Drawable favoriteItemColor = favoriteItem.getIcon();
        setupColorActionBarIcon(favoriteItemColor);
        return true;
    }

    void setupColorActionBarIcon(Drawable favoriteItemColor) {
        appBarLayout.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if ((collapsingToolbarLayout.getHeight() + verticalOffset) < (2 * ViewCompat.getMinimumHeight(collapsingToolbarLayout))) {
                if (toolbar.getNavigationIcon() != null)
                    toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                favoriteItemColor.mutate().setColorFilter(getResources().getColor(R.color.colorPrimary),
                        PorterDuff.Mode.SRC_ATOP);

            } else {
                if (toolbar.getNavigationIcon() != null)
                    toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
                favoriteItemColor.mutate().setColorFilter(getResources().getColor(R.color.colorWhite),
                        PorterDuff.Mode.SRC_ATOP);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setMealByName(Meals.Meal mealByName) {
        Glide.with(this)
                .load(mealByName.getStrMealThumb())
                .apply(options)
                .into(mealThumb);
        collapsingToolbarLayout.setTitle(mealByName.getStrMeal());
        category.setText(mealByName.getStrCategory());
        country.setText(mealByName.getStrArea());
        instructions.setText(mealByName.getStrInstructions());


        if(!mealByName.getStrIngredient1().isEmpty()){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient1());
        }
        if(!mealByName.getStrIngredient2().isEmpty()){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient2());
        }
        if(!mealByName.getStrIngredient3().isEmpty()){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient3());
        }
        if(!mealByName.getStrIngredient4().isEmpty()){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient4());
        }
        if(!mealByName.getStrIngredient5().isEmpty()){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient5());
        }
        if(!mealByName.getStrIngredient6().isEmpty()){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient6());
        }
        if(!mealByName.getStrIngredient7().isEmpty()){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient7());
        }
        if(!mealByName.getStrIngredient8().isEmpty()){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient8());
        }
        if(!mealByName.getStrIngredient9().isEmpty()){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient9());
        }
        if(!mealByName.getStrIngredient10().isEmpty()){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient10());
        }
        if(!mealByName.getStrIngredient11().isEmpty()){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient11());
        }
        if(!mealByName.getStrIngredient12().isEmpty()){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient12());
        }
        if(!mealByName.getStrIngredient13().isEmpty()){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient13());
        }
        if(!mealByName.getStrIngredient14().isEmpty()){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient14());
        }
        if(!mealByName.getStrIngredient15().isEmpty()){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient15());
        }
        if(!mealByName.getStrIngredient16().isEmpty()){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient16());
        }
        if(!mealByName.getStrIngredient17().isEmpty()){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient17());
        }
        if(!mealByName.getStrIngredient18().isEmpty()){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient18());
        }
        if(!mealByName.getStrIngredient19().isEmpty()){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient19());
        }
        if(!mealByName.getStrIngredient20().isEmpty()){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient20());
        }

        if(!mealByName.getStrMeasure1().isEmpty()){
            measure.append("\n \u2022 " + mealByName.getStrMeasure1());
        }
        if(!mealByName.getStrMeasure2().isEmpty()){
            measure.append("\n \u2022 " + mealByName.getStrMeasure2());
        }
        if(!mealByName.getStrMeasure3().isEmpty()){
            measure.append("\n \u2022 " + mealByName.getStrMeasure3());
        }
        if(!mealByName.getStrMeasure4().isEmpty()){
            measure.append("\n \u2022 " + mealByName.getStrMeasure4());
        }
        if(!mealByName.getStrMeasure5().isEmpty()){
            measure.append("\n \u2022 " + mealByName.getStrMeasure5());
        }
        if(!mealByName.getStrMeasure6().isEmpty()){
            measure.append("\n \u2022 " + mealByName.getStrMeasure6());
        }
        if(!mealByName.getStrMeasure7().isEmpty()){
            measure.append("\n \u2022 " + mealByName.getStrMeasure7());
        }
        if(!mealByName.getStrMeasure8().isEmpty()){
            measure.append("\n \u2022 " + mealByName.getStrMeasure8());
        }
        if(!mealByName.getStrMeasure9().isEmpty()){
            measure.append("\n \u2022 " + mealByName.getStrMeasure9());
        }
        if(!mealByName.getStrMeasure10().isEmpty()){
            measure.append("\n \u2022 " + mealByName.getStrMeasure10());
        }
        if(!mealByName.getStrMeasure11().isEmpty()){
            measure.append("\n \u2022 " + mealByName.getStrMeasure11());
        }
        if(!mealByName.getStrMeasure12().isEmpty()){
            measure.append("\n \u2022 " + mealByName.getStrMeasure12());
        }
        if(!mealByName.getStrMeasure13().isEmpty()){
            measure.append("\n \u2022 " + mealByName.getStrMeasure13());
        }
        if(!mealByName.getStrMeasure14().isEmpty()){
            measure.append("\n \u2022 " + mealByName.getStrMeasure14());
        }
        if(!mealByName.getStrMeasure15().isEmpty()){
            measure.append("\n \u2022 " + mealByName.getStrMeasure15());
        }
        if(!mealByName.getStrMeasure16().isEmpty()){
            measure.append("\n \u2022 " + mealByName.getStrMeasure16());
        }
        if(!mealByName.getStrMeasure17().isEmpty()){
            measure.append("\n \u2022 " + mealByName.getStrMeasure17());
        }
        if(!mealByName.getStrMeasure18().isEmpty()){
            measure.append("\n \u2022 " + mealByName.getStrMeasure18());
        }
        if(!mealByName.getStrMeasure19().isEmpty()){
            measure.append("\n \u2022 " + mealByName.getStrMeasure19());
        }
        if(!mealByName.getStrMeasure20().isEmpty()){
            measure.append("\n \u2022 " + mealByName.getStrMeasure20());
        }

        youtube.setOnClickListener(v -> {
//            Intent youtubeIntent = new Intent(Intent.ACTION_VIEW);
//            youtubeIntent.setData(Uri.parse(mealByName.getStrYoutube()));
//            startActivity(youtubeIntent);
            Intent intentYoutube = new Intent(this,WebViewActivity.class);
            intentYoutube.putExtra(EXTRA_URL,mealByName.getStrYoutube());
            startActivity(intentYoutube);
        });

        source.setOnClickListener(v -> {
//            Intent intentSource = new Intent(Intent.ACTION_VIEW);
//            intentSource.setData(Uri.parse(mealByName.getStrSource()));
//            startActivity(intentSource);
            Intent intentSource = new Intent(this,WebViewActivity.class);
            intentSource.putExtra(EXTRA_URL,mealByName.getStrSource());
            startActivity(intentSource);
        });
    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(this,"Error",message);
    }
}
