package com.example.byg.exam_0120.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.byg.exam_0120.R;

public class ThreadActivity extends AppCompatActivity {

    private TextView mTextView;
    private int mNumber;
    private Handler mHandler = new Handler();
//    private Handler mHandler2 = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            mTextView.setText(mNumber + "");
//            mTextView.setText(msg.what + "");
//        }
//    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        mTextView = (TextView) findViewById(R.id.text_view);

        // 메인 스레드에서는 UI 갱신이 가능


        new Thread(new Runnable() {
            @Override
            public void run() {
                // 복잡하고 오래 걸리는 처리
                for (int i = 0; i < 10; i++) {
                    mNumber++;
                    try {
                        Thread.sleep(500);
                        // 1. 스레드에서는 UI 갱신이 안 됨
                        // 2. 스레드에서 Handler를 생성 할 수 없음
                        // Handler를 사용해서 UI 갱신을 해야 됨
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                // 글자 변경
                                mTextView.setText(mNumber + "");
                            }
                        });

                        // 위랑 같은 코드 1
//                        mHandler2.sendEmptyMessage(0);
//                        mHandler2.sendEmptyMessage(mNumber);

                        // 모든 view는 handler를 가지고 있음
                        // 위랑 같은 코드 2
//                        mTextView.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                mTextView.setText(mNumber + "");
//                            }
//                        });

                        // 위와 같은 코드 3 (액티비티가 제공하는 것)
                        // 가장 좋음 간단
//                        runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                mTextView.setText(mNumber + "");
//                            }
//                        });

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
