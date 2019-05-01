package com.example.dyaksa.mealapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dyaksa.mealapp.R;
import com.example.dyaksa.mealapp.model.Meals;

import java.util.List;

public class ViewPagerHeaderAdapter extends PagerAdapter {
    private List<Meals.Meal> mData;
    private Context mContext;
    private RequestOptions options;
    private static ClickListener clickListener;

    public ViewPagerHeaderAdapter(Context mContext, List<Meals.Meal> mData){
        this.mContext = mContext;
        this.mData = mData;
        options = new RequestOptions().centerCrop().placeholder(R.drawable.sample_image_meal).error(R.drawable.sample_image_meal);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position){
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_view_pager_header,container,false);

        ImageView mealThumb = view.findViewById(R.id.mealThumb);
        TextView mealName = view.findViewById(R.id.mealName);

        String strMealThumb = mData.get(position).getStrMealThumb();
        Glide.with(mContext).load(strMealThumb).apply(options).into(mealThumb);

        String strMealName = mData.get(position).getStrMeal();
        mealName.setText(strMealName);

        view.setOnClickListener(v -> clickListener.onClick(v,position));
        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object){
        container.removeView((View)object);
    }


    @Override
    public int getCount() {
        return (mData != null) ? mData.size() : 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }

    public interface ClickListener {
        void onClick(View v, int position);
    }

    public void setOnItemClickListener(ClickListener clickListener){
        ViewPagerHeaderAdapter.clickListener = clickListener;
    }
}
