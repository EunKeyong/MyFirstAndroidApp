package com.example.byg.exam_0120.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.byg.exam_0120.R;
import com.example.byg.exam_0120.fragments.TextFragment;

public class FragmentExam1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_exam1);
    }

    private void addFragment(int containerId, int color, String text) {
        TextFragment textFragment = new TextFragment();
        textFragment.setColor(color);

        getSupportFragmentManager().beginTransaction()
                .add(containerId, textFragment).commit();
        textFragment.setText(text);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                addFragment(R.id.container_1, Color.YELLOW, "1번 플래그");
                break;
            case R.id.btn2:
                addFragment(R.id.container_2, Color.RED, "2번 플래그");
                break;
            case R.id.btn3:
                addFragment(R.id.container_3, Color.BLUE, "3번 플래그");
                break;
        }
    }
}
