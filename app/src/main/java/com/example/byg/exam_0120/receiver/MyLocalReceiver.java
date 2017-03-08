package com.example.byg.exam_0120.receiver;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

import com.example.byg.exam_0120.R;
import com.example.byg.exam_0120.activities.BroadcastReceiverActivity;

public class MyLocalReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BATTERY_LOW)) {
            Toast.makeText(context, "로컬 브로드캐스트 리시버다", Toast.LENGTH_SHORT).show();

        } else if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)) {
            Toast.makeText(context, "전원이 뽑혔습니다", Toast.LENGTH_SHORT).show();

        } else if (intent.getAction().equals("com.example.exam_0120.broadcast.ACTION_TEST")) {
            Toast.makeText(context, "나만의 액션받기 성공", Toast.LENGTH_SHORT).show();
            showNotification(context);
        }
    }

    private void showNotification(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setContentTitle("나만의 알림");
        builder.setContentText("나만의 텍스트");
        builder.setSmallIcon(R.mipmap.ic_launcher);

        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
        builder.setLargeIcon(bitmap);

        // 알림을 클릭하면 수행될 인텐트
        Intent resultIntent = new Intent(context, BroadcastReceiverActivity.class);

        // 클릭하면 그액티비티로 돌아감
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        // 클릭하면 알림 지우기
        builder.setAutoCancel(true);

        // 색상
        builder.setColor(Color.YELLOW);

        // 기본 알림음
        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(uri);

        // 진동
        builder.setVibrate(new long[]{100, 200, 300});

        // 액션
        builder.addAction(R.mipmap.ic_launcher, "확인", pendingIntent);
        builder.addAction(R.mipmap.ic_launcher, "취소", pendingIntent);

        // 알림 표시
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
        managerCompat.notify(0, builder.build());

    }
}
