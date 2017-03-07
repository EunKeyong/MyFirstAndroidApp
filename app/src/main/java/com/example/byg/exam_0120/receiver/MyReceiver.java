package com.example.byg.exam_0120.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
       String action = intent.getAction();

        if(action.equals(Intent.ACTION_BATTERY_CHANGED)) {

        }
    }
}
