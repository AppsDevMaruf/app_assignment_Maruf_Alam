package com.marufalam.efoodcafe.ui;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.marufalam.efoodcafe.R;
import com.marufalam.efoodcafe.daos.UserDao;
import com.marufalam.efoodcafe.databinding.FragmentLogInBinding;
import com.marufalam.efoodcafe.db.CreateDatabase;
import com.marufalam.efoodcafe.models.User;
import com.marufalam.efoodcafe.viewmodel.UserViewModel;


public class LogInFragment extends Fragment {
    FragmentLogInBinding binding;
    private String userRole = "Customer";
    private UserViewModel viewModel;
    UserDao db;
    CreateDatabase dataBase;
    public SharedPreferences preferences;


    public LogInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLogInBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
        dataBase = Room.databaseBuilder(requireActivity(), CreateDatabase.class, "efoodcafe_db")
                .allowMainThreadQueries()
                .build();

        db = dataBase.getUserDao();


        binding.RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                final RadioButton rb = radioGroup.findViewById(i);
                userRole = rb.getText().toString();

            }
        });



        binding.btnNewUser.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.action_logInFragment_to_signUpFragment));


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);
        binding.btnLogin.setOnClickListener(v -> {
            String email = binding.etEmail.getText().toString().trim();
            String password = binding.etPassword.getText().toString().trim();


            User user = db.getUser(email, password);
            if (user != null) {
                if (userRole.equals("Customer")) {
                    Toast.makeText(requireActivity(), "LoginSuccessFull", Toast.LENGTH_SHORT).show();
                    navController.navigate(R.id.action_logInFragment_to_foodListFragment);
                } else if (userRole.equals("Waiter")) {

                    Toast.makeText(requireActivity(), "WaiterLoginSuccessFull", Toast.LENGTH_SHORT).show();
                    navController.navigate(R.id.action_logInFragment_to_waiterOrderListFragment);
                    //Navigation.findNavController(v.findViewById(R.id.nav_host_frag)).navigate(R.id.foodListFragment);
                    //Navigation.findNavController(v).navigate(R.id.action_logInFragment_to_waiterOrderListFragment);

                } else
                    Toast.makeText(requireActivity(), "select Any one", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(requireActivity(), "Unregistered user, or incorrect", Toast.LENGTH_SHORT).show();
            }

            // Storing data into SharedPreferences
            SharedPreferences preferences = getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE);
            // Creating an Editor object to edit(write to the file)
            SharedPreferences.Editor myEdit = preferences.edit();
            // Storing the key and its value as the data fetched from edittext
            myEdit.putString("email",email);
            myEdit.putString("password",password);
            // Once the changes have been made,
            // we need to commit to apply those changes made,
            // otherwise, it will throw an error
            myEdit.commit();
        });


    }
}