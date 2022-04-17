package com.example.capstoneproject.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.capstoneproject.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationFragment extends Fragment {

    public TextView loginBtn;
    public Button signUpBtn;
    public EditText username, email, password, confirmPassword;
    public String userNameString, emailString, passwordString, confirmPasswordString;

    //firebase variables
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    public RegistrationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        loginBtn = (TextView) view.findViewById(R.id.signInBtn);
        signUpBtn = (Button) view.findViewById(R.id.registerBtn);
        username = view.findViewById(R.id.etUsername);
        email = view.findViewById(R.id.etEmail);
        password = view.findViewById(R.id.etPassword);
        confirmPassword = view.findViewById(R.id.etConfirmPassword);

        SharedPreferences sp = this.requireActivity().getSharedPreferences("Login", MODE_PRIVATE);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor Ed = sp.edit();

        LoginFragment loginFragment = new LoginFragment();

        loginBtn.setOnClickListener(loginView -> replaceFragment(loginFragment));

        signUpBtn.setOnClickListener(signupView -> {
            userNameString = username.getText().toString();
            emailString = email.getText().toString();
            passwordString = password.getText().toString();
            confirmPasswordString = confirmPassword.getText().toString();

            if (userNameString.equals("") && emailString.equals("") && passwordString.equals("") && confirmPasswordString.equals("")) {
                Toast.makeText(this.requireContext(), "Please Enter all the values!", Toast.LENGTH_SHORT).show();

                if ((userNameString.equals(""))) {
                    if (username.getText().toString().trim().equalsIgnoreCase(""))
                        username.setError("This field can not be blank");
                }
                if ((emailString.equals(""))) {
                    if (email.getText().toString().trim().equalsIgnoreCase(""))
                        email.setError("This field can not be blank");
                }
                if ((passwordString.equals(""))) {
                    if (password.getText().toString().trim().equalsIgnoreCase(""))
                        password.setError("This field can not be blank");
                }
                if ((confirmPasswordString.equals(""))) {
                    if (confirmPassword.getText().toString().trim().equalsIgnoreCase(""))
                        confirmPassword.setError("This field can not be blank");
                }
            } else {
                if (!passwordString.equals(confirmPasswordString))
                    Toast.makeText(this.requireContext(), "Password and confirm password should be same!", Toast.LENGTH_SHORT).show();
                else {
                    replaceFragment(loginFragment);

                    Ed.putString("username", userNameString);
                    Ed.putString("email", emailString);
                    Ed.putString("password", passwordString);
                    Ed.putString("flag", "login");
                    addDataToFirebase(userNameString, emailString, passwordString, confirmPasswordString);
                    Ed.apply();
                }

            }
        });

        firebaseDatabase = FirebaseDatabase.getInstance();

        return view;
    }

    private void addDataToFirebase(String usernameDB, String emailDB, String passwordDB, String confirmPasswordDB) {
        //child node values
        String child = username.getText().toString();
        databaseReference = firebaseDatabase.getReference("Users").child(child);
        databaseReference.child("username").setValue(usernameDB);
        databaseReference.child("email").setValue(emailDB);
        databaseReference.child("password").setValue(passwordDB);
        databaseReference.child("confirmPassword").setValue(confirmPasswordDB);

        Toast.makeText(requireContext(), "User Added!", Toast.LENGTH_SHORT).show();
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fooContainer, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void startAnotherActivity(Class className) {
        Intent myIntent = new Intent(this.requireContext(), className);
        startActivity(myIntent);
    }

}