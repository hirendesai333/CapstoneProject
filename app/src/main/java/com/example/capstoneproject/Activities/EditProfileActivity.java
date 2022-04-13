package com.example.capstoneproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.capstoneproject.R;

public class EditProfileActivity extends AppCompatActivity {
    public ImageView backBtn;
    public TextView mainUsername;
    public EditText username, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        backBtn = findViewById(R.id.back);

        backBtn.setOnClickListener(view -> onBackPressed());

        SharedPreferences shared = getSharedPreferences("Login", MODE_PRIVATE);
        String usernameText = (shared.getString("username", ""));
        String emailText = (shared.getString("email", ""));

        mainUsername = findViewById(R.id.username1);
        username = findViewById(R.id.username2);
        email = findViewById(R.id.etEmail);

        mainUsername.setText(usernameText);
        username.setText(usernameText);
        email.setText(emailText);


    }
}