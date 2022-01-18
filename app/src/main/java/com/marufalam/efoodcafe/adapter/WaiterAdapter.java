package com.marufalam.efoodcafe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.marufalam.efoodcafe.R;
import com.marufalam.efoodcafe.databinding.WaiterOrderListSampleRowBinding;
import com.marufalam.efoodcafe.models.OrdersModel;

import java.util.List;

public class WaiterAdapter extends RecyclerView.Adapter<WaiterAdapter.WaiterViewHolder> {
    Context context;
    private final List<OrdersModel> waiterOrderList;

    public WaiterAdapter(Context context, List<OrdersModel> waiterOrderList) {
        this.context = context;
        this.waiterOrderList = waiterOrderList;
    }

    @NonNull
    @Override
    public WaiterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        WaiterOrderListSampleRowBinding binding = WaiterOrderListSampleRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new WaiterViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull WaiterViewHolder holder, int position) {
        OrdersModel waiterOrders = waiterOrderList.get(position);
        Glide.with(holder.binding.orderImageId.getContext()).load(waiterOrders.getOrderImage()).placeholder(R.drawable.placeholderimg).into(holder.binding.orderImageId);

        holder.bind(waiterOrders);

    }

    @Override
    public int getItemCount() {
        return waiterOrderList.size();
    }

    public static class WaiterViewHolder extends RecyclerView.ViewHolder {
        WaiterOrderListSampleRowBinding binding;

        public WaiterViewHolder(WaiterOrderListSampleRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(OrdersModel waiterOrders) {
            binding.orderItemNameId.setText(waiterOrders.getSoldItemName());
            Glide.with(binding.orderImageId.getContext())
                    .load(waiterOrders.getOrderImage())
                    .placeholder(R.drawable.placeholderimg)
                    .into(binding.orderImageId);
            binding.orderPriceId.setText(waiterOrders.getPrice());
            binding.currentdate.setText(waiterOrders.getCurrentDate());


        }
    }
}
