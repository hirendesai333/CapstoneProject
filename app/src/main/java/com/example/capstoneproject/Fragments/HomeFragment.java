package com.example.capstoneproject.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.capstoneproject.Activities.AddProfileActivity;
import com.example.capstoneproject.Activities.ChildActivity;
import com.example.capstoneproject.Activities.NotificationActivity;
import com.example.capstoneproject.Activities.UpgradePlanActivity;
import com.example.capstoneproject.Adapters.ChildActivityAdapter;
import com.example.capstoneproject.Adapters.ChildAdapter;
import com.example.capstoneproject.DataClass.Child;
import com.example.capstoneproject.DataClass.User;
import com.example.capstoneproject.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    public ImageView hamMenu, notification, addProfile;
    public Button btn1, btn2, upgradePlanBtn;

    ChildAdapter childAdapter;
    ChildActivityAdapter childActivityAdapter;
    ArrayList<Child> childList, childList2;
    RecyclerView childRv, childRv2;

    DatabaseReference databaseReference;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        hamMenu = view.findViewById(R.id.ham_menu);
        notification = view.findViewById(R.id.notificationBtn);
        btn1 = view.findViewById(R.id.activityBtn1);
        btn2 = view.findViewById(R.id.activityBtn2);
        addProfile = view.findViewById(R.id.addChildProfile);
        upgradePlanBtn = view.findViewById(R.id.upgradeNowBtn);
        upgradePlanBtn.setOnClickListener(view1 -> startAnotherActivity(UpgradePlanActivity.class));

        AddProfileFragment addProfileFragment = new AddProfileFragment();
        NotificationFragment notificationFragment = new NotificationFragment();
        ChildFragment childFragment = new ChildFragment();

        addProfile.setOnClickListener(view2 -> startAnotherActivity(AddProfileActivity.class));

        notification.setOnClickListener(v -> replaceFragment(notificationFragment));
//        btn1.setOnClickListener(v -> startAnotherActivity(ChildActivity.class));
//        btn2.setOnClickListener(v -> startAnotherActivity(ChildActivity.class));

        //recyclerview 1
        childRv = view.findViewById(R.id.childList);
        childRv.setHasFixedSize(true);
        childRv.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));

        childList = new ArrayList<>();
        childAdapter = new ChildAdapter(this.getContext(), childList);
        childRv.setAdapter(childAdapter);

        //recyclerview 2
        childRv2 = view.findViewById(R.id.activityList);
        childRv2.setHasFixedSize(true);
        childRv2.setLayoutManager(new LinearLayoutManager(requireContext()));

        childList2 = new ArrayList<>();
        childActivityAdapter = new ChildActivityAdapter(this.getContext(), childList2);
        childRv2.setAdapter(childActivityAdapter);

        SharedPreferences shared = this.requireActivity().getSharedPreferences("Login", MODE_PRIVATE);
        String usernameText = (shared.getString("username", ""));

        databaseReference = FirebaseDatabase.getInstance().getReference("Users").child(usernameText).child("Childs");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                childList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Child child = dataSnapshot.getValue(Child.class);
                    childList.add(child);
                    childList2.add(child);
                }
                childAdapter.notifyDataSetChanged();
                childActivityAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
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