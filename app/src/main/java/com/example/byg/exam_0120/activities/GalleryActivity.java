package com.example.byg.exam_0120.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.example.byg.exam_0120.R;
import com.example.byg.exam_0120.fragments.GalleryFragment;

public class GalleryActivity extends AppCompatActivity implements GalleryFragment.OnFragmentInteractionListener{

    private Fragment mFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        mFragment = getSupportFragmentManager().findFragmentById(R.id.fragment2);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mFragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
