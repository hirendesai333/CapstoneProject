package com.example.capstoneproject.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.capstoneproject.Activities.CredentialsActivity;
import com.example.capstoneproject.Activities.NotificationActivity;
import com.example.capstoneproject.Activities.UpgradePlanActivity;
import com.example.capstoneproject.R;
import com.google.android.material.card.MaterialCardView;

public class SettingsFragment extends Fragment {
    Button logoutBtn;
    MaterialCardView account, notification, premiunm, contactCard, AboutCard;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        logoutBtn = view.findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(view1 -> {
            SharedPreferences preferences = requireActivity().getSharedPreferences("Login", MODE_PRIVATE);
            preferences.edit().remove("email").apply();
            preferences.edit().remove("password").apply();
            Intent intent = new Intent(requireContext(), CredentialsActivity.class);
            startActivity(intent);
        });

        account = view.findViewById(R.id.accountCv);
        notification = view.findViewById(R.id.notificationDv);
        premiunm = view.findViewById(R.id.premiumAccount);
        contactCard = view.findViewById(R.id.contactCard);
        AboutCard = view.findViewById(R.id.aboutUsCard);

        account.setOnClickListener(view12 -> {
            EditProfileFragment editProfileFragment = new EditProfileFragment();
            replaceFragment(editProfileFragment);
        });

        notification.setOnClickListener(view13 -> startAnotherActivity(NotificationActivity.class));

        premiunm.setOnClickListener(view14 -> startAnotherActivity(UpgradePlanActivity.class));

        contactCard.setOnClickListener(view15 -> {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(requireContext());
            LayoutInflater layoutInflater = requireActivity().getLayoutInflater();
            View dialogView = layoutInflater.inflate(R.layout.contact_label, null);
            dialogBuilder.setView(dialogView);
            AlertDialog alertDialog = dialogBuilder.create();
            alertDialog.show();
        });

        AboutCard.setOnClickListener(view16 -> {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(requireContext());
            LayoutInflater layoutInflater = requireActivity().getLayoutInflater();
            View dialogView = layoutInflater.inflate(R.layout.about_label, null);
            dialogBuilder.setView(dialogView);
            AlertDialog alertDialog = dialogBuilder.create();
            alertDialog.show();
        });

        return view;
    }

    public void startAnotherActivity(Class className) {
        Intent myIntent = new Intent(requireContext(), className);
        startActivity(myIntent);
    }

    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainContainer, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}