package com.example.byg.exam_0120.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.byg.exam_0120.R;
import com.example.byg.exam_0120.models.Memo;
import com.example.byg.exam_0120.utils.MyUtils;

public class MemoWriteActivity extends AppCompatActivity {

    private EditText mTitleEditText;
    private EditText mContentEditText;
    private long mId = -1;

    private ImageView mImageView;
    private String mImagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 메모 추가
        setContentView(R.layout.activity_memo_write);


        mTitleEditText = (EditText) findViewById(R.id.title_edit_text);
        mContentEditText = (EditText) findViewById(R.id.content_edit_text);
        mImageView = (ImageView) findViewById(R.id.appbar_image);

        // 툴바가 액션바 대체
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 타이틀 없애기
        getSupportActionBar().setTitle("");

        if (getIntent() != null) {
            // 보여주기
            if (getIntent().hasExtra("memo")) {

                mId = getIntent().getLongExtra("id", -1);
                mImagePath = getIntent().getStringExtra("image");
                if (mImagePath != null) {

                    Glide.with(this).load(mImagePath).into(mImageView);
                }

                Memo memo = (Memo) getIntent().getSerializableExtra("memo");
                mTitleEditText.setText(memo.getTitle());
                mContentEditText.setText(memo.getContent());
                //새메모
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_memo, menu);
        //return super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cancel_settings:
                cancel();
                return true;
            case R.id.save_settings:
                // 새메모
                save();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void cancel() {
        setResult(RESULT_CANCELED);
        finish();
    }

    private void save() {
        Intent intent = new Intent();
        intent.putExtra("id", mId);
        intent.putExtra("message1", "저장");
        intent.putExtra("title", mTitleEditText.getText().toString());
        intent.putExtra("content", mContentEditText.getText().toString());
        intent.putExtra("image", mImagePath);
        int position = getIntent().getIntExtra("position", -1);
        intent.putExtra("position", position);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void onImageClick(View view) {
        // 그림 줘
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
            mImagePath = MyUtils.getRealPath(this, imageUri);

            // 라이브러리
            Glide.with(this).load(mImagePath).into(mImageView);

            // 라이브러리
            //Glide.with(this).load(imageUri.toString()).into(mImageView);

            // 이미지 썸네일 얻기
            // Glide.with(this).loadFromMediaStore(imageUri).thumbnail(0.1f).into(mImageView);
            // 안드로이드 기본사용
            // Bitmap thumbnail = ThumbnailUtils.extractThumbnail(bitmap, 100, 100);

        }
    }

}

