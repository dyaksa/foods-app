package com.example.dyaksa.mealapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewMealByCategory extends RecyclerView.Adapter<RecyclerViewMealByCategory.myViewHolder> {

    private Context mContext;
    private List<Meals.Meal> mData;
    private RequestOptions options;
    private static ClickListener clickListener;

    public RecyclerViewMealByCategory(Context mContext, List<Meals.Meal> mData){
        this.mContext = mContext;
        this.mData = mData;
        options = new RequestOptions().centerCrop().placeholder(R.drawable.sample_image_meal).error(R.drawable.sample_image_meal);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycler_meal,viewGroup,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder myViewHolder, int i) {
        String strMealName = mData.get(i).getStrMeal();
        myViewHolder.mealName.setText(strMealName);

        String strMealThumb = mData.get(i).getStrMealThumb();
        Glide.with(mContext)
                .load(strMealThumb)
                .apply(options)
                .into(myViewHolder.mealThumb);
    }

    @Override
    public int getItemCount() {
        return (mData != null) ? mData.size() : 0;
    }

    public static class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.mealName)
        TextView mealName;

        @BindView(R.id.mealThumb)
        ImageView mealThumb;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            clickListener.onClick(v,getAdapterPosition());
        }
    }

    public void setOnItemClickListener(ClickListener clickListener){
        RecyclerViewMealByCategory.clickListener = clickListener;
    }

    public interface ClickListener {
        void onClick(View view,int position);
    }
}
