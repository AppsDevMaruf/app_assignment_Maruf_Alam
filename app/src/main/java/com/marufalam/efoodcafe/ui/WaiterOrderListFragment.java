package com.marufalam.efoodcafe.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.marufalam.efoodcafe.R;
import com.marufalam.efoodcafe.adapter.OdersAdapter;
import com.marufalam.efoodcafe.adapter.WaiterAdapter;
import com.marufalam.efoodcafe.databinding.FragmentOrderListBinding;
import com.marufalam.efoodcafe.databinding.FragmentWaiterOrderListBinding;
import com.marufalam.efoodcafe.models.OrdersModel;

import java.util.ArrayList;


public class WaiterOrderListFragment extends Fragment {
    private FragmentWaiterOrderListBinding binding;
    private  ArrayList<OrdersModel> waiterOrders;
    DatabaseReference databaseRef;
    WaiterAdapter waiterAdapter;


    public WaiterOrderListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWaiterOrderListBinding.inflate(inflater, container, false);
        SharedPreferences prefs =  requireActivity().getSharedPreferences("pref",
                Context.MODE_PRIVATE);
        String currentDateTimeString = prefs.getString("currentDateTimeString",null);
        String email = prefs.getString("email",null);
        databaseRef = FirebaseDatabase.getInstance().getReference("Orderlist");
        databaseRef.keepSynced(true);
        binding.waiterOrderRecyV.setHasFixedSize(true);
        binding.waiterOrderRecyV.setLayoutManager(new LinearLayoutManager(getContext()));

        waiterOrders = new ArrayList<>();

        databaseRef.addValueEventListener(new ValueEventListener(){
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                waiterOrders.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    OrdersModel model = dataSnapshot.getValue(OrdersModel.class);
                    waiterOrders.add(model);
                    waiterAdapter = new WaiterAdapter(getContext(),waiterOrders);
                    binding.waiterOrderRecyV.setAdapter(waiterAdapter);
                    waiterAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Data Can't Load On Firebase " + error, Toast.LENGTH_SHORT).show();
            }
        });
        return binding.getRoot();
    }
}