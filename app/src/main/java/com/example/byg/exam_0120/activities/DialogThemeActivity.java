package com.example.byg.exam_0120.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.byg.exam_0120.R;

public class DialogThemeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_theme);

        if (getIntent() != null) {
            String data = getIntent().getStringExtra("data");
            String image = getIntent().getStringExtra("image");
            ((TextView) findViewById(R.id.text_view)).setText(data);
            if (image != null) {
                ((ImageView) findViewById(R.id.image_view)).setImageResource(R.mipmap.ic_launcher);
            }
        }
    }
}
