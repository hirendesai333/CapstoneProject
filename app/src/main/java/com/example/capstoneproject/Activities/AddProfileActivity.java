package com.example.capstoneproject.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.application.isradeleon.notify.Notify;
import com.example.capstoneproject.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddProfileActivity extends AppCompatActivity {

    public EditText username, email;
    String usernameStr, emailStr;

    Button addChild;
    SharedPreferences sp;
    //firebase variables
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_profile);

        username = findViewById(R.id.childUserName);
        email = findViewById(R.id.childEmail);
        back = findViewById(R.id.back);

        back.setOnClickListener(view -> onBackPressed());

        addChild = findViewById(R.id.addProfileBtn);
        firebaseDatabase = FirebaseDatabase.getInstance();

        sp = this.getSharedPreferences("Login", MODE_PRIVATE);

        addChild.setOnClickListener(view -> {
            usernameStr = username.getText().toString();
            emailStr = email.getText().toString();
            addDataToFirebase(usernameStr, emailStr);
            Log.d("test", usernameStr + emailStr);

            Intent myIntent = new Intent(AddProfileActivity.this, MainActivity.class);
            startActivity(myIntent);

            Notify.build(getApplicationContext())
                    .setTitle("Child Added!")
                    .setContent(usernameStr + " has been added to the list!")
//                    .setSmallIcon(R.drawable.ic_notifications_none_white_24dp)
                    .setColor(R.color.text)
                    .largeCircularIcon()
                    .show(); // Show notification
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.os_menu, menu);
        return true;
    }

    private void addDataToFirebase(String usernameDB, String emailDB) {
        String usernameText = (sp.getString("username", ""));

        //child node values
        String child = username.getText().toString();
        databaseReference = firebaseDatabase.getReference("Users").child(usernameText).child("Childs").child(child);
        databaseReference.child("username").setValue(usernameDB);
        databaseReference.child("email").setValue(emailDB);

        Toast.makeText(this, "Child Profile Added!", Toast.LENGTH_SHORT).show();
    }

}