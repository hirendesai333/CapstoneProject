package com.example.capstoneproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.capstoneproject.R;

public class NotificationActivity extends AppCompatActivity {

    public ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        backBtn = findViewById(R.id.back);

        backBtn.setOnClickListener(view -> {
            onBackPressed();
        });
    }
}