package com.marufalam.efoodcafe.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.marufalam.efoodcafe.R;
import com.marufalam.efoodcafe.databinding.OrderSampleRowBinding;
import com.marufalam.efoodcafe.models.OrdersModel;
import com.marufalam.efoodcafe.ui.OrderDetatilsFragment;
import com.marufalam.efoodcafe.ui.OrderListFragment;


import java.util.ArrayList;

public class OdersAdapter extends RecyclerView.Adapter<OdersAdapter.OrderViewholder> {
     private final ArrayList<OrdersModel> orderlist;
    Context context;

    public OdersAdapter(ArrayList<OrdersModel> orderlist, Context context) {
        this.orderlist = orderlist;
        this.context = context;
    }

    @NonNull
    @Override
    public OrderViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       OrderSampleRowBinding binding = OrderSampleRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new OrderViewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewholder holder, int position) {
        OrdersModel ordersModel = orderlist.get(position);
        holder.bind(ordersModel);

    }


    @Override
    public int getItemCount() {
        return orderlist.size();
    }



    public class OrderViewholder extends RecyclerView.ViewHolder {

        OrderSampleRowBinding binding;
        public OrderViewholder(OrderSampleRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }


        public void bind(OrdersModel orderlist) {
            binding.orderItemNameId.setText(orderlist.getSoldItemName());
            Glide.with(binding.orderImageId.getContext()).load(orderlist.getOrderImage()).placeholder(R.drawable.placeholderimg).into(binding.orderImageId);
            binding.orderPriceId.setText(orderlist.getPrice());
            binding.currentdate.setText(orderlist.getCurrentDate());
            binding.orderItemDelete.setOnClickListener(v -> {

                removeAt(getAdapterPosition());
            });
            binding.update.setOnClickListener(v -> Toast.makeText(v.getContext(), "Work Ongoing...", Toast.LENGTH_SHORT).show());

        }
    }
    public void removeAt(int position) {
        orderlist.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, orderlist.size());
    }


}
