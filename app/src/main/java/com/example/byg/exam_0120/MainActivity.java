package com.example.byg.exam_0120;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 클래스 이름을 String으로 변환
    private static final String TAG = MainActivity.class.getSimpleName();
    private Button mMinusButton;
    private Button mPlusButton;
    private TextView mQuantityTextView;

    // 수량을 나타내는 전역변수
    private int mQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 초기화
        init();

        // 레이아웃 표시
        setContentView(R.layout.activity_coffeeorder);

        // 레이아웃에서 특정 id를 인스턴스 변수와 연결
        mMinusButton = (Button) findViewById(R.id.minus_button);
        mPlusButton = (Button) findViewById(R.id.plus_button);
        mQuantityTextView = (TextView) findViewById(R.id.quantity_text);

        // 무명 클래스
        mMinusButton.setOnClickListener(new View.OnClickListener() {
            // minusbutton 눌렀을때 확인
            @Override
            public void onClick(View view) {
                // debug
                Log.d(TAG, "마이너스 버튼 클릭");
                // verbose
                Log.v(TAG, "일반로그");
                Log.e(TAG, "에러로그");
                Log.i(TAG, "정보로그");
                Log.w(TAG, "경고로그");


                // 토스트 메세지
                Toast.makeText(MainActivity.this, "마이너스 버튼 클릭", Toast.LENGTH_SHORT).show();


            }
        });
    }

    // 변수초기화하는 메소드드
    private void init() {
        mQuantity = 0;
    }
}
