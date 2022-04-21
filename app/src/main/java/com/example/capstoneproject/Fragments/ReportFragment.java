package com.example.capstoneproject.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.capstoneproject.Activities.UpgradePlanActivity;
import com.example.capstoneproject.R;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class ReportFragment extends Fragment {

    Button downloadBtn;
    String uri = "";

    public ReportFragment() {
        // Required empty public constructor
    }

    SharedPreferences shared;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report, container, false);

        shared = this.requireActivity().getSharedPreferences("Login", MODE_PRIVATE);
        String usernameText = (shared.getString("username", ""));

        LinearLayout linearLayout = view.findViewById(R.id.rootView);
        ImageView imageView = view.findViewById(R.id.blurImg);

        downloadBtn = view.findViewById(R.id.downloadBtn);
        downloadBtn.setOnClickListener(view1 -> initDownload());


        return view;
    }

    private void initDownload() {
        String usernameText = (shared.getString("username", ""));
        String emailText = (shared.getString("email", ""));

        uri = "https://drive.google.com/uc?id=1IDq2c-hUHkIMlkbJodeUazVL0A4TpSNT&export=download";

        download(requireContext(), "WeeklyReport", ".pdf", "Downloads", uri.trim());
    }


    public void startAnotherActivity(Class className) {
        Intent myIntent = new Intent(requireContext(), className);
        startActivity(myIntent);
    }

    private void download(Context context, String Filename, String FileExtension, String DesignationDirectory, String url) {
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(context, DesignationDirectory, Filename + FileExtension);
        assert downloadManager != null;
        downloadManager.enqueue(request);
        Toast.makeText(requireContext(), "Downloading!", Toast.LENGTH_SHORT).show();
    }

}
