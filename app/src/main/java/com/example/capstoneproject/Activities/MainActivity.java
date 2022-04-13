package com.example.capstoneproject.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.capstoneproject.Fragments.EditProfileFragment;
import com.example.capstoneproject.Fragments.HomeFragment;
import com.example.capstoneproject.Fragments.ReportFragment;
import com.example.capstoneproject.R;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class MainActivity extends AppCompatActivity {

    SmoothBottomBar navigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HomeFragment homeFragment = new HomeFragment();
        EditProfileFragment editProfileFragment = new EditProfileFragment();
        ReportFragment reportFragment = new ReportFragment();
        replaceFragment(homeFragment);

        navigationBar = findViewById(R.id.bottom_navigation);
        navigationBar.setOnItemSelectedListener((OnItemSelectedListener) i -> {
            switch (i) {
                case 0:
                    replaceFragment(homeFragment);
                    break;
                case 1:
                    replaceFragment(reportFragment);
                    break;
                case 2:
                    replaceFragment(editProfileFragment);
                    break;

            }
            return false;
        });
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainContainer, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
