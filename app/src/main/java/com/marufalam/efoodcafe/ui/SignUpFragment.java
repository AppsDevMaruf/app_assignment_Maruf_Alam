package com.marufalam.efoodcafe.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.marufalam.efoodcafe.R;
import com.marufalam.efoodcafe.daos.UserDao;
import com.marufalam.efoodcafe.databinding.FragmentSignUpBinding;
import com.marufalam.efoodcafe.db.CreateDatabase;
import com.marufalam.efoodcafe.models.User;
import com.marufalam.efoodcafe.viewmodel.UserViewModel;

public class SignUpFragment extends Fragment {
    private FragmentSignUpBinding binding;
    private String userRole = "Customer";
    private UserViewModel viewModel;
    UserDao db;
    CreateDatabase dataBase;
    private UserDao userDao;


    public SignUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
       /* binding.btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.etEmail.getText().toString();
                String name = binding.etFullName.getText().toString();
                String mobileNumber = binding.etMobile.getText().toString();
                String password = binding.etPassword.getText().toString();
                binding.RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int i) {

                         final RadioButton rb = radioGroup.findViewById(i);
                         userRole = rb.getText().toString();
                    }
                });

                User user = new User(email,name,mobileNumber,password,userRole);
                viewModel.crateUser(user);


            }
        });*/
        binding.RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                final RadioButton rb = radioGroup.findViewById(i);
                userRole = rb.getText().toString();
                Toast.makeText(requireActivity(), "" + userRole, Toast.LENGTH_SHORT).show();
            }
        });
        binding.btnSignup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email = binding.etEmail.getText().toString();
                String name = binding.etFullName.getText().toString();
                String mobileNumber = binding.etMobile.getText().toString();
                String password = binding.etPassword.getText().toString();
                String passwordConf = binding.etConfirmPass.getText().toString();


                if (email.equals("") && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    binding.showError.setError("Enter Valid email");
                    Toast.makeText(requireContext(), "Enter A valid email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (name.equals("")) {
                    binding.showError.setError("Enter Valid Name");
                    Toast.makeText(requireContext(), "Enter A valid Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (mobileNumber.equals("") || mobileNumber.length() < 10) {
                    binding.showError.setError("Enter Valid MobileNumber");
                    Toast.makeText(requireContext(), "Enter A valid Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.equals("")) {
                    binding.showError.setError("Enter Valid Password");
                    Toast.makeText(requireContext(), "Enter A valid Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.equals(passwordConf)) {
                    Toast.makeText(requireActivity(), "" + userRole, Toast.LENGTH_SHORT).show();
                    User user = new User(email, name, mobileNumber, password, userRole);

                    viewModel.crateUser(user);
                    Navigation.findNavController(v).navigate(R.id.action_signUpFragment_to_logInFragment);


                } else {
                    Toast.makeText(requireActivity(), "Password is not matching", Toast.LENGTH_SHORT).show();

                }
            }
        });
        binding.haveAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_signUpFragment_to_logInFragment);
            }
        });
        return binding.getRoot();

    }

}