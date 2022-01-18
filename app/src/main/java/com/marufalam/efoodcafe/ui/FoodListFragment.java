package com.marufalam.efoodcafe.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
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
import com.marufalam.efoodcafe.R;
import com.marufalam.efoodcafe.adapter.FoodListAdapter;
import com.marufalam.efoodcafe.databinding.FragmentFoodListBinding;
import com.marufalam.efoodcafe.databinding.FragmentLogInBinding;
import com.marufalam.efoodcafe.models.FoodListModel;

import java.util.ArrayList;


public class FoodListFragment extends Fragment {
    private FragmentFoodListBinding binding;
    private ArrayList<FoodListModel> foodListModels;
    DatabaseReference databaseRef;
    FoodListAdapter foodListAdapter;

    public FoodListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFoodListBinding.inflate(inflater, container, false);
        databaseRef = FirebaseDatabase.getInstance().getReference("foodItemList");
        databaseRef.keepSynced(true);
        binding.recyViewFoodList.setHasFixedSize(true);
        binding.recyViewFoodList.setLayoutManager(new LinearLayoutManager(getContext()));
        foodListModels = new ArrayList<>();
        databaseRef.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                foodListModels.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    FoodListModel model = dataSnapshot.getValue(FoodListModel.class);
                    foodListModels.add(model);
                    foodListAdapter = new FoodListAdapter(requireActivity(), foodListModels);
                    binding.recyViewFoodList.setAdapter(foodListAdapter);
                    foodListAdapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getContext(), "Data Can't Load On Firebase " + error, Toast.LENGTH_SHORT).show();

            }
        });
        return binding.getRoot();
    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Do something that differs the Activity's menu here
        inflater.inflate(R.menu.menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.myorders:

                AppCompatActivity activity = (AppCompatActivity) getActivity();
                Fragment myFragment = new OrderListFragment();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_frag, myFragment).addToBackStack(null).commit();
                return true;
            case R.id.signout:

                AppCompatActivity activity2 = (AppCompatActivity) getActivity();
                Fragment myFragment2 = new LogInFragment();
                activity2.getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_frag, myFragment2).addToBackStack(null).commit();
                return true;
            default:
                break;
        }

        return false;
    }


}