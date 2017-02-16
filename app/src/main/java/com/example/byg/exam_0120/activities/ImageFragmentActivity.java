package com.example.byg.exam_0120.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.byg.exam_0120.R;
import com.example.byg.exam_0120.fragments.ImageFragment;

public class ImageFragmentActivity extends AppCompatActivity implements ImageFragment.OnImageTouchListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_fragment);

        ImageFragment imageFragment = (ImageFragment) getSupportFragmentManager()
                .findFragmentById(R.id.image_frag);
        imageFragment.setOnImageTouchListener(this);

    }

    @Override
    public void onImageTouch(ImageView view, String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
