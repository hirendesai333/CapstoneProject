package com.example.capstoneproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.capstoneproject.R;

public class UpgradePlanActivity extends AppCompatActivity {
    ImageView closeImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upgrade_plan);

        TextView t = findViewById(R.id.wasPrice);
        t.setPaintFlags(t.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        closeImageView = findViewById(R.id.close);
        closeImageView.setOnClickListener(view -> onBackPressed());
    }
}