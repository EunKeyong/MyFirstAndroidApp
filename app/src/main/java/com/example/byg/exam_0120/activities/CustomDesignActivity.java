package com.example.byg.exam_0120.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.byg.exam_0120.R;
import com.example.byg.exam_0120.utils.MyUtils;

public class CustomDesignActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_design);
    }

    public void showToast(View view) {
//        Toast result = new Toast(this);
//
//        View v = getLayoutInflater().inflate(R.layout.custom_toast, null);
//        TextView tv = (TextView) v.findViewById(R.id.message_text);
//        tv.setText("나만의 토스트");
//
//        result.setView(v);
//
//        result.setDuration(Toast.LENGTH_SHORT);
//        result.setGravity(Gravity.CENTER_HORIZONTAL,
//                0, -300);
//        result.show();
        MyUtils.makeToast(this, "나만의 토스트", Toast.LENGTH_SHORT).show();
    }
}
