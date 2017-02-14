package com.example.byg.exam_0120.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.byg.exam_0120.R;
import com.example.byg.exam_0120.fragments.ColorFragment;

public class ColorFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_fragment);

        // xml에서 프래그먼트 가져오기
        ColorFragment colorFragment = (ColorFragment) getSupportFragmentManager().findFragmentById(R.id.color_frag);
        colorFragment.setColor(Color.BLUE);

        // 동적으로 프래그먼트 추가
        ColorFragment colorFragment2 = new ColorFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.container, colorFragment2).commit();


    }

    public void onClick(View view) {
        // 버튼 누를때마다 색변경
        ColorFragment newColorFragment = new ColorFragment();
        int color = Color.YELLOW;
        newColorFragment.setColor(color);
        // 기존의 프래그먼트를 교체
        getSupportFragmentManager().beginTransaction().
                replace(R.id.container,newColorFragment).commit();
    }
}
