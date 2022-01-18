package com.marufalam.efoodcafe.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.marufalam.efoodcafe.R;
import com.marufalam.efoodcafe.databinding.FoodListSampleRowBinding;
import com.marufalam.efoodcafe.models.FoodListModel;

import java.util.List;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.FoodListViewHolder> {
    Context context;
    List<FoodListModel> foodList;

    public FoodListAdapter(Context context, List<FoodListModel> foodList) {
        this.context = context;
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public FoodListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_list_sample_row,parent,false);
        return new FoodListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodListViewHolder holder, @SuppressLint("RecyclerView") int position) {
        FoodListModel foodItemPosition= foodList.get(position);
     //  holder.bind(foodItemList);
        Glide.with(holder.foodItemImage.getContext()).load(foodList.get(holder.getAdapterPosition()).getImage()).placeholder(R.drawable.placeholderimg).into(holder.foodItemImage);
        //holder.doctorImage.setImageResource(doctorDataHolder.get(position).getDoctorImg());
        holder.foodName.setText(foodList.get(holder.getAdapterPosition()).getName());
        holder.foodPrice.setText(foodList.get(holder.getAdapterPosition()).getPrice());
        holder.fooddesc.setText(foodList.get(holder.getAdapterPosition()).getDescription());
        holder.itemView.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("image",foodList.get(position).getImage());
            bundle.putString("name",foodList.get(position).getName());
            bundle.putString("price",foodList.get(position).getPrice());
            bundle.putString("desc",foodList.get(position).getDescription());

            Navigation.findNavController(v).navigate(R.id.action_foodListFragment_to_orderDetatilsFragment,bundle);
        });

    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public static class FoodListViewHolder extends RecyclerView.ViewHolder {
        ImageView foodItemImage;
        TextView foodName;
        TextView foodPrice;
        TextView fooddesc;

        public FoodListViewHolder(@NonNull View itemView) {
            super(itemView);
            foodItemImage = itemView.findViewById(R.id.foodImage);
            foodName = itemView.findViewById(R.id.foodName);
            foodPrice = itemView.findViewById(R.id.foodPrice);
            fooddesc = itemView.findViewById(R.id.foodDesc);
        }
    }
}

