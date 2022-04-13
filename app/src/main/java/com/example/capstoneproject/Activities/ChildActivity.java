package com.example.capstoneproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.capstoneproject.R;

public class ChildActivity extends AppCompatActivity {

    public ImageView backBtn;
    public Button upgradeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        backBtn = findViewById(R.id.back);
        upgradeBtn = findViewById(R.id.upgradeNowBtn);

        upgradeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startAnotherActivity(UpgradePlanActivity.class);
            }
        });

        backBtn.setOnClickListener(view -> {
            onBackPressed();
        });

    }

    public void startAnotherActivity(Class className) {
        Intent myIntent = new Intent(this, className);
        startActivity(myIntent);
    }
}