package com.marufalam.efoodcafe.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.marufalam.efoodcafe.R;
import com.marufalam.efoodcafe.databinding.FragmentOrderDetatilsBinding;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderDetatilsFragment extends Fragment {

    private DatabaseReference mDatabase;
    private FragmentOrderDetatilsBinding binding;
    int count = 1;




    public OrderDetatilsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentOrderDetatilsBinding.inflate(inflater, container, false);
        Bundle bundle = getArguments();
        assert bundle != null;
        final String image = bundle.getString("image");
        final String name = bundle.getString("name");
        final String price = bundle.getString("price");
        final String desc = bundle.getString("desc");
        AtomicInteger total = new AtomicInteger(Integer.parseInt(price));
        binding.foodPriceId.setText("" + price);
        binding.increment.setOnClickListener(v -> {
            count++;
            binding.quantity.setText("" + count);
            int quantity = Integer.parseInt(binding.quantity.getText().toString());
            int amount = Integer.valueOf(price);
             total.set(quantity * amount);
            binding.foodPriceId.setText("" + total);
            Toast.makeText(getContext(), ""+total, Toast.LENGTH_SHORT).show();
        });
        binding.decrement.setOnClickListener(v -> {
            if (count <= 0) count = 0;
            else count--;
            binding.quantity.setText("" + count);
            int quantity = Integer.parseInt(binding.quantity.getText().toString());
            int amount = Integer.parseInt(price);
             total.set(quantity * amount);
            binding.foodPriceId.setText("" + total);
            Toast.makeText(getContext(), ""+total, Toast.LENGTH_SHORT).show();
        });

        Glide.with(binding.detailsImageId).load(image)
                .placeholder(R.drawable.placeholderimg)
                .into(binding.detailsImageId);
        binding.orderedFoodName.setText(name);
        binding.detailDescription.setText(desc);

        SharedPreferences prefs =  requireActivity().getSharedPreferences("pref",
                Context.MODE_PRIVATE);
        String email = prefs.getString("email",null);




        mDatabase = FirebaseDatabase.getInstance().getReference("Orderlist");

        String currentDateTimeString = java.text.DateFormat.getDateTimeInstance().format(new Date());
        SharedPreferences preferences = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor myEdit = preferences.edit();
        myEdit.putString("currentDateTimeString",currentDateTimeString);
        myEdit.commit();
             binding.btnOrder.setOnClickListener(v -> {
                 String reciverName = binding.edtPersonName.getText().toString().trim();
                 String phoneNumber = binding.editTextPhone.getText().toString().trim();
                 if(reciverName.equals("") || reciverName.equals(null)) {
                     Toast.makeText(requireContext(), "Enter A valid Name", Toast.LENGTH_SHORT).show();
                     return;
                 }if (phoneNumber.equals("") || phoneNumber.equals(null)||phoneNumber.length()<10) {
                     Toast.makeText(requireContext(), "Enter A valid Number", Toast.LENGTH_SHORT).show();
                     return;
                 }


            //HashMap for insert order to database
                 HashMap<String, String> orderlistMap = new HashMap<>();
                 orderlistMap.put("reciverName",reciverName);
                 orderlistMap.put("phoneNumber",phoneNumber);

                 orderlistMap.put("orderImage",image);
                 orderlistMap.put("soldItemName",name);
                 orderlistMap.put("price", String.valueOf(total.get()));
                 orderlistMap.put("currentDate",currentDateTimeString);

                 mDatabase.child(email).push().setValue(orderlistMap);
                 Toast.makeText(requireContext(), "Data Save", Toast.LENGTH_SHORT).show();
                 Navigation.findNavController(v).navigate(R.id.action_orderDetatilsFragment_to_foodListFragment);



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