package com.example.capstoneproject.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.application.isradeleon.notify.Notify;
import com.example.capstoneproject.DataClass.Child;
import com.example.capstoneproject.R;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Locale;

public class ChildActivity extends AppCompatActivity {

    public ImageView backBtn;
    public Button upgradeBtn;
    int hour, minute;
    TextView appLimit, gameLimit;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        backBtn = findViewById(R.id.back);
        upgradeBtn = findViewById(R.id.upgradeNowBtn);

        upgradeBtn.setOnClickListener(view -> startAnotherActivity(UpgradePlanActivity.class));

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater layoutInflater = this.getLayoutInflater();
        View dialogView = layoutInflater.inflate(R.layout.alert_limit, null);
        dialogBuilder.setView(dialogView);
        AlertDialog alertDialog = dialogBuilder.create();
        Button button = dialogView.findViewById(R.id.setLimit);

        appLimit = findViewById(R.id.setAppLimit);
        gameLimit = findViewById(R.id.setGameLimit);

        appLimit.setOnClickListener(view -> {
            alertDialog.show();
        });

        shared = getSharedPreferences("Login", MODE_PRIVATE);

        firebaseDatabase = FirebaseDatabase.getInstance();


        button.setOnClickListener(view -> {
            TimePickerDialog.OnTimeSetListener onTimeSetListener = (timePicker, i, i1) -> {
                hour = i;
                minute = i1;

                addDataToFirebase(hour, minute);

                startAnotherActivity(MainActivity.class);
                showNofitication();

            };
            TimePickerDialog timePickerDialog = new TimePickerDialog(ChildActivity.this, onTimeSetListener, hour, minute, true);
            timePickerDialog.setTitle("Select Time");

            timePickerDialog.show();
        });

        gameLimit.setOnClickListener(view -> alertDialog.show());

        backBtn.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private void addDataToFirebase(int hours, int minutes) {
        //child node values
        String child = (shared.getString("username", ""));
        databaseReference = firebaseDatabase.getReference("Users").child(child);
        databaseReference.child("Time_limit").setValue(hours + "hr. " + minutes + "min.");
    }

    public void showNofitication() {
        Notify.build(getApplicationContext())
                .setTitle("Limit Set!")
                .setContent("Applicaiton limit has been set!")
                .setColor(R.color.text)
                .largeCircularIcon()
                .show(); // Show notification
    }

    public void startAnotherActivity(Class className) {
        Intent myIntent = new Intent(this, className);
        startActivity(myIntent);
    }
}