package com.example.capstoneproject.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.capstoneproject.Fragments.EditProfileFragment;
import com.example.capstoneproject.Fragments.HomeFragment;
import com.example.capstoneproject.Fragments.ReportFragment;
import com.example.capstoneproject.Fragments.SettingsFragment;
import com.example.capstoneproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

import me.ibrahimsn.lib.SmoothBottomBar;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton addProfileButton;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HomeFragment homeFragment = new HomeFragment();
        EditProfileFragment editProfileFragment = new EditProfileFragment();
        ReportFragment reportFragment = new ReportFragment();
        SettingsFragment settingsFragment = new SettingsFragment();

        replaceFragment(homeFragment);

        addProfileButton = findViewById(R.id.fab);
        addProfileButton.setOnClickListener(view -> startAnotherActivity(AddProfileActivity.class));

        bottomNavigationView = findViewById(R.id.bottomNavigaitonView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_home:
                    replaceFragment(homeFragment);
                    bottomNavigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
                    break;
                case R.id.nav_reports:
                    replaceFragment(reportFragment);
                    bottomNavigationView.getMenu().findItem(R.id.nav_reports).setChecked(true);
                    break;
                case R.id.nav_profile:
                    replaceFragment(editProfileFragment);
                    bottomNavigationView.getMenu().findItem(R.id.nav_profile).setChecked(true);
                    break;
                case R.id.nav_setting:
                    replaceFragment(settingsFragment);
                    bottomNavigationView.getMenu().findItem(R.id.nav_setting).setChecked(true);
                    break;
            }
            return false;
        });

    }

    public void startAnotherActivity(Class className) {
        Intent myIntent = new Intent(this, className);
        startActivity(myIntent);
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainContainer, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
