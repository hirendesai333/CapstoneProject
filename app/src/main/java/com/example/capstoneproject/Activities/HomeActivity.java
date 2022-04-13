package com.example.capstoneproject.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.capstoneproject.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {

    public DrawerLayout navDrawer;
    public ImageView hamMenu, notification, addProfile;
    public Button btn1, btn2, upgradePlanBtn;
    public NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        hamMenu = findViewById(R.id.ham_menu);
        notification = findViewById(R.id.notificationBtn);
        btn1 = findViewById(R.id.activityBtn1);
        btn2 = findViewById(R.id.activityBtn2);
        navigationView = findViewById(R.id.navigationView);
        navDrawer = findViewById(R.id.my_drawer_layout);
        addProfile = findViewById(R.id.addChildProfile);
        upgradePlanBtn = findViewById(R.id.upgradeNowBtn);

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.upgrade_label_editor, null);
        dialogBuilder.setView(dialogView);
        AlertDialog alertDialog = dialogBuilder.create();

        upgradePlanBtn.setOnClickListener(view -> alertDialog.show());

        addProfile.setOnClickListener(view -> startAnotherActivity(AddProfileActivity.class));

        SharedPreferences shared = getSharedPreferences("Login", MODE_PRIVATE);
        String usernameText = (shared.getString("username", ""));
        String emailText = (shared.getString("email", ""));

        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.navUserName);
        navUsername.setText(usernameText);
        TextView navEmail = (TextView) headerView.findViewById(R.id.navEmail);
        navEmail.setText(emailText);

        ImageView navClose = (ImageView) headerView.findViewById(R.id.closeNav);
        navClose.setOnClickListener(v -> navDrawer.closeDrawers());

        hamMenu.setOnClickListener(v -> {
            if (!navDrawer.isDrawerOpen(Gravity.START)) navDrawer.openDrawer(Gravity.START);
            else navDrawer.closeDrawer(Gravity.END);
        });

        notification.setOnClickListener(v -> startAnotherActivity(NotificationActivity.class));
        btn1.setOnClickListener(v -> startAnotherActivity(ChildActivity.class));
        btn2.setOnClickListener(v -> startAnotherActivity(ChildActivity.class));

        TextView mBottton = findViewById(R.id.bottomBtn);
        mBottton.setOnClickListener(v -> showBottomSheetDialog());
    }

    private void showBottomSheetDialog() {

        final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog_layout);

        Button home = bottomSheetDialog.findViewById(R.id.home);
        assert home != null;
        home.setOnClickListener(view -> bottomSheetDialog.cancel());

        Button editProfile = bottomSheetDialog.findViewById(R.id.profile);
        assert editProfile != null;
        editProfile.setOnClickListener(view -> startAnotherActivity(EditProfileActivity.class));

        Button logout = bottomSheetDialog.findViewById(R.id.logout);
        assert logout != null;

        bottomSheetDialog.show();
    }

    public void startAnotherActivity(Class className) {
        Intent myIntent = new Intent(this, className);
        startActivity(myIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (item.getItemId() == R.id.nav_home) {
            navDrawer.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
