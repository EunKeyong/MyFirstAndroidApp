package com.example.byg.exam_0120.activities;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.byg.exam_0120.R;
import com.example.byg.exam_0120.receiver.MyLocalReceiver;

/**
 * 매니페스트에 리시버를 등록 하지 않는다
 * onStart(), onStop() 코드로 리시버 등록, 해제한다
 */

public class BroadcastReceiverActivity extends AppCompatActivity implements View.OnClickListener {

    private BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_reciver);
        findViewById(R.id.button2).setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();

        // 리시버 등록
        mReceiver = new MyLocalReceiver();

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_BATTERY_LOW);
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction("com.example.exam_0120.broadcast.ACTION_TEST");
        registerReceiver(mReceiver,filter);
    }

    @Override
    protected void onStop() {
        super.onStop();

        // 리시버 해제
        unregisterReceiver(mReceiver);
    }

    @Override
    public void onClick(View v) {

        // 나만의 액션을 쏘기
        Intent intent = new Intent("com.example.exam_0120.broadcast.ACTION_TEST");

        // 순서 없는
        sendBroadcast(intent);

        // 순서 있는
        //sendOrderedBroadcast();

    }
}
