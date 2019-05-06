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

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity implements DetailView{

    public static final String EXTRA_DETAIL = "detail";

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


        if(!mealByName.getStrIngredient1().isEmpty() && mealByName.getStrIngredient1() != null){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient1());
        }
        if(!mealByName.getStrIngredient2().isEmpty() && mealByName.getStrIngredient2() != null ){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient2());
        }
        if(!mealByName.getStrIngredient3().isEmpty() && mealByName.getStrIngredient3() != null){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient3());
        }
        if(!mealByName.getStrIngredient4().isEmpty() && mealByName.getStrIngredient4() != null){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient4());
        }
        if(!mealByName.getStrIngredient5().isEmpty() && mealByName.getStrIngredient5() != null){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient5());
        }
        if(!mealByName.getStrIngredient6().isEmpty() && mealByName.getStrIngredient6() != null){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient6());
        }
        if(!mealByName.getStrIngredient7().isEmpty() && mealByName.getStrIngredient7() != null){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient7());
        }
        if(!mealByName.getStrIngredient8().isEmpty() && mealByName.getStrIngredient8() != null){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient8());
        }
        if(!mealByName.getStrIngredient9().isEmpty() && mealByName.getStrIngredient9() != null){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient9());
        }
        if(!mealByName.getStrIngredient10().isEmpty() && mealByName.getStrIngredient10() != null){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient10());
        }
        if(!mealByName.getStrIngredient11().isEmpty() && mealByName.getStrIngredient11() != null){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient11());
        }
        if(!mealByName.getStrIngredient12().isEmpty() && mealByName.getStrIngredient12() != null){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient12());
        }
        if(!mealByName.getStrIngredient13().isEmpty() && mealByName.getStrIngredient13() != null){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient13());
        }
        if(!mealByName.getStrIngredient14().isEmpty() && mealByName.getStrIngredient14() != null){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient14());
        }
        if(!mealByName.getStrIngredient15().isEmpty() && mealByName.getStrIngredient15() != null){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient15());
        }
        if(!mealByName.getStrIngredient16().isEmpty() && mealByName.getStrIngredient16() != null){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient16());
        }
        if(!mealByName.getStrIngredient17().isEmpty() && mealByName.getStrIngredient17() != null){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient17());
        }
        if(!mealByName.getStrIngredient18().isEmpty() && mealByName.getStrIngredient18() != null){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient18());
        }
        if(!mealByName.getStrIngredient19().isEmpty() && mealByName.getStrIngredient19() != null){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient19());
        }
        if(!mealByName.getStrIngredient20().isEmpty() && mealByName.getStrIngredient20() != null){
            ingredient.append("\n \u2022 " + mealByName.getStrIngredient20());
        }

        if(!mealByName.getStrMeasure1().isEmpty() && mealByName.getStrMeasure1() != null){
            measure.append("\n \u2022 " + mealByName.getStrMeasure1());
        }
        if(!mealByName.getStrMeasure2().isEmpty() && mealByName.getStrMeasure2() != null){
            measure.append("\n \u2022 " + mealByName.getStrMeasure2());
        }
        if(!mealByName.getStrMeasure3().isEmpty() && mealByName.getStrMeasure3() != null){
            measure.append("\n \u2022 " + mealByName.getStrMeasure3());
        }
        if(!mealByName.getStrMeasure4().isEmpty() && mealByName.getStrMeasure4() != null){
            measure.append("\n \u2022 " + mealByName.getStrMeasure4());
        }
        if(!mealByName.getStrMeasure5().isEmpty() && mealByName.getStrMeasure5() != null){
            measure.append("\n \u2022 " + mealByName.getStrMeasure5());
        }
        if(!mealByName.getStrMeasure6().isEmpty() && mealByName.getStrMeasure6() != null){
            measure.append("\n \u2022 " + mealByName.getStrMeasure6());
        }
        if(!mealByName.getStrMeasure7().isEmpty() && mealByName.getStrMeasure7() != null){
            measure.append("\n \u2022 " + mealByName.getStrMeasure7());
        }
        if(!mealByName.getStrMeasure8().isEmpty() && mealByName.getStrMeasure8() != null){
            measure.append("\n \u2022 " + mealByName.getStrMeasure8());
        }
        if(!mealByName.getStrMeasure9().isEmpty() && mealByName.getStrMeasure9() != null){
            measure.append("\n \u2022 " + mealByName.getStrMeasure9());
        }
        if(!mealByName.getStrMeasure10().isEmpty() && mealByName.getStrMeasure10() != null){
            measure.append("\n \u2022 " + mealByName.getStrMeasure10());
        }
        if(!mealByName.getStrMeasure11().isEmpty() && mealByName.getStrMeasure11() != null){
            measure.append("\n \u2022 " + mealByName.getStrMeasure11());
        }
        if(!mealByName.getStrMeasure12().isEmpty() && mealByName.getStrMeasure12() != null){
            measure.append("\n \u2022 " + mealByName.getStrMeasure12());
        }
        if(!mealByName.getStrMeasure13().isEmpty() && mealByName.getStrMeasure13() != null){
            measure.append("\n \u2022 " + mealByName.getStrMeasure13());
        }
        if(!mealByName.getStrMeasure14().isEmpty() && mealByName.getStrMeasure14() != null){
            measure.append("\n \u2022 " + mealByName.getStrMeasure14());
        }
        if(!mealByName.getStrMeasure15().isEmpty() && mealByName.getStrMeasure15() != null){
            measure.append("\n \u2022 " + mealByName.getStrMeasure15());
        }
        if(!mealByName.getStrMeasure16().isEmpty() && mealByName.getStrMeasure16() != null){
            measure.append("\n \u2022 " + mealByName.getStrMeasure16());
        }
        if(!mealByName.getStrMeasure17().isEmpty() && mealByName.getStrMeasure17() != null){
            measure.append("\n \u2022 " + mealByName.getStrMeasure17());
        }
        if(!mealByName.getStrMeasure18().isEmpty() && mealByName.getStrMeasure18() != null){
            measure.append("\n \u2022 " + mealByName.getStrMeasure18());
        }
        if(!mealByName.getStrMeasure19().isEmpty() && mealByName.getStrMeasure19() != null){
            measure.append("\n \u2022 " + mealByName.getStrMeasure19());
        }
        if(!mealByName.getStrMeasure20().isEmpty() && mealByName.getStrMeasure20() != null){
            measure.append("\n \u2022 " + mealByName.getStrMeasure20());
        }

        youtube.setOnClickListener(v -> {
            Intent youtubeIntent = new Intent(Intent.ACTION_VIEW);
            youtubeIntent.setData(Uri.parse(mealByName.getStrYoutube()));
            startActivity(youtubeIntent);
        });

        source.setOnClickListener(v -> {
            Intent intentSource = new Intent(Intent.ACTION_VIEW);
            intentSource.setData(Uri.parse(mealByName.getStrSource()));
            startActivity(intentSource);
        });
    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(this,"Error",message);
    }
}
