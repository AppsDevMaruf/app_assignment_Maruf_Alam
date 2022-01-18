package com.marufalam.efoodcafe.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.marufalam.efoodcafe.adapter.FoodListAdapter;
import com.marufalam.efoodcafe.adapter.OdersAdapter;
import com.marufalam.efoodcafe.databinding.FragmentFoodListBinding;
import com.marufalam.efoodcafe.databinding.FragmentOrderListBinding;
import com.marufalam.efoodcafe.models.FoodListModel;
import com.marufalam.efoodcafe.models.OrdersModel;
import com.marufalam.efoodcafe.ui.LogInFragment;

import java.util.ArrayList;


public class OrderListFragment extends Fragment {
    private FragmentOrderListBinding binding;
    private ArrayList<OrdersModel> ordersModels;
    DatabaseReference databaseRef;
    OdersAdapter ordersAdapter;



    public OrderListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOrderListBinding.inflate(inflater, container, false);
        databaseRef = FirebaseDatabase.getInstance().getReference("Orderlist");
        databaseRef.keepSynced(true);
        binding.orderListRecycler.setHasFixedSize(true);
        binding.orderListRecycler.setLayoutManager(new LinearLayoutManager(getContext()));

        ordersModels = new ArrayList<>();
        SharedPreferences prefs =  requireActivity().getSharedPreferences("pref",
                Context.MODE_PRIVATE);
        String email = prefs.getString("email",null);
        databaseRef.child(email).addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ordersModels.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    OrdersModel model = dataSnapshot.getValue(OrdersModel.class);
                    ordersModels.add(model);
                    ordersAdapter = new OdersAdapter(ordersModels ,getContext());
                    binding.orderListRecycler.setAdapter(ordersAdapter);
                    ordersAdapter.notifyDataSetChanged();
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