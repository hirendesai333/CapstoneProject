package com.example.capstoneproject.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.capstoneproject.Activities.CredentialsActivity;
import com.example.capstoneproject.R;

public class EditProfileFragment extends Fragment {
    public TextView mainUsername;
    public EditText username, email;
    Button logoutBtn;

    public EditProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        SharedPreferences shared = this.requireActivity().getSharedPreferences("Login", MODE_PRIVATE);
        String usernameText = (shared.getString("username", ""));
        String emailText = (shared.getString("email", ""));

        mainUsername = view.findViewById(R.id.username1);
        username = view.findViewById(R.id.username2);
        email = view.findViewById(R.id.etEmail);

        mainUsername.setText(usernameText);
        username.setText(usernameText);
        email.setText(emailText);

        logoutBtn = view.findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(view1 -> {
            SharedPreferences preferences = requireActivity().getSharedPreferences("Login", MODE_PRIVATE);
            preferences.edit().remove("email").apply();
            preferences.edit().remove("password").apply();

            Intent intent = new Intent(requireContext(), CredentialsActivity.class);
            startActivity(intent);
        });

        return view;
    }
}