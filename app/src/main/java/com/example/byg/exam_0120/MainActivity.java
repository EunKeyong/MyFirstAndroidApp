package com.example.byg.exam_0120;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // 클래스 이름을 String으로 변환
    private static final String TAG = MainActivity.class.getSimpleName();
    public static final int QUANTITY_MIN = 0;
    public static final int QUANTITY_MAX = 10;
    public static final int COFFEE_PRICE = 3000;

    private Button mMinusButton;
    private Button mPlusButton;
    private Button mOrderButton;
    private TextView mQuantityTextView;
    private TextView mResultTextView;

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
        mOrderButton = (Button) findViewById(R.id.order_button);
        mQuantityTextView = (TextView) findViewById(R.id.quantity_text);
        mResultTextView = (TextView) findViewById(R.id.result_text);

        // 무명 클래스
        mMinusButton.setOnClickListener(new View.OnClickListener() {
            // minusbutton 눌렀을때 확인
            @Override
            public void onClick(View view) {
                mQuantity--;
                if (mQuantity < QUANTITY_MIN) {
                    mQuantity = QUANTITY_MIN;
                }
                displayResult();
            }
        });

        mPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mQuantity++;
                if (mQuantity > QUANTITY_MAX) {
                    mQuantity = QUANTITY_MAX;
                }
                displayResult();
            }
        });

        mOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = mResultTextView.getText().toString();
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayResult() {
        mQuantityTextView.setText("" + mQuantity);
//        int price = COFFEE_PRICE;
        String result = "가격 : " + (COFFEE_PRICE * mQuantity) + "원\n감사합니다.";
        mResultTextView.setText(result);
    }

    // 변수초기화하는 메소드드
    private void init() {
        mQuantity = QUANTITY_MIN;
    }
}
