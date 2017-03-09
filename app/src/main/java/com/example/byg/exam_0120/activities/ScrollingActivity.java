package com.example.byg.exam_0120.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.byg.exam_0120.R;

public class ScrollingActivity extends AppCompatActivity {

    private ImageView mImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mImageView = (ImageView) findViewById(R.id.appbar_image);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void onImageClick(View view) {
        Toast.makeText(this, "이미지 클릭", Toast.LENGTH_SHORT).show();

        // 암시적 인텐트(이미지얻기)
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");

        startActivityForResult(intent, 1000);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1000 && resultCode == RESULT_OK && data != null) {
            // 그림이 정상적으로 선택되었을 때

            // 사진 경로
            Uri imageUri = data.getData();

            // 사진을 bitmap 으로 얻기
            // Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
            // 이미지 뷰에 bitmap 설정
            // mImageView.setImageBitmap(bitmap);

            Glide.with(this).loadFromMediaStore(imageUri).into(mImageView);
            // 라이브러리
            Glide.with(this).load(imageUri.toString()).into(mImageView);

            // 이미지 썸네일 얻기
            // Glide.with(this).loadFromMediaStore(imageUri).thumbnail(0.1f).into(mImageView);
            // 안드로이드 기본사용
            // Bitmap thumbnail = ThumbnailUtils.extractThumbnail(bitmap, 100, 100);

        }
    }
}
