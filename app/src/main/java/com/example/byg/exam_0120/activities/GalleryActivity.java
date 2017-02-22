package com.example.byg.exam_0120.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.byg.exam_0120.R;
import com.example.byg.exam_0120.fragments.GalleryFragment;

public class GalleryActivity extends AppCompatActivity implements GalleryFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
