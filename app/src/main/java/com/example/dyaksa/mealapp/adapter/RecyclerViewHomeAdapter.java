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
import com.example.dyaksa.mealapp.model.Categories;
import com.example.dyaksa.mealapp.model.Meals;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerViewHomeAdapter extends RecyclerView.Adapter<RecyclerViewHomeAdapter.myViewHolder> {
    private List<Categories.Category> mData;
    private Context mContext;
    private myViewHolder viewHolder;
    private static ClickListener clickListener;
    private RequestOptions options;

    public RecyclerViewHomeAdapter(Context mContext, List<Categories.Category> mData){
        this.mContext = mContext;
        this.mData = mData;
        options = new RequestOptions().centerCrop().placeholder(R.drawable.ic_circle).error(R.drawable.ic_circle);
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycler_category,viewGroup,false);
        viewHolder = new myViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder viewHolder, int i) {
        String strCategoryName = mData.get(i).getStrCategory();
        viewHolder.categoryName.setText(strCategoryName);

        String strCategoryThumb = mData.get(i).getStrCategoryThumb();
        Glide.with(mContext).load(strCategoryThumb).apply(options).into(viewHolder.categoryThumb);
    }

    @Override
    public int getItemCount() {
        return (mData != null) ? mData.size() : 0;
    }


    public static class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.categoryThumb)
        ImageView categoryThumb;

        @BindView(R.id.categoryName)
        TextView categoryName;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v,getAdapterPosition());
        }
    }

    public void setOnItemClickListener(ClickListener clickListener){
        RecyclerViewHomeAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onClick(View view, int position);
    }

}
