package com.example.capstoneproject.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.capstoneproject.R;


public class NotificationFragment extends Fragment {

    public NotificationFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        ImageView back = view.findViewById(R.id.back);
        back.setOnClickListener(view1 -> {
            HomeFragment homeFragment = new HomeFragment();
            replaceFragment(homeFragment);
        });

        return view;
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainContainer, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}