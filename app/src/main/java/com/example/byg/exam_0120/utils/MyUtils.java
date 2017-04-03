package com.example.byg.exam_0120.utils;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.byg.exam_0120.R;

/**
 * Created by byg on 2017-03-10.
 */

public class MyUtils {

    public static Toast makeToast(Context context, CharSequence message, int duration) {
        Toast result = new Toast(context);

        View v = LayoutInflater.from(context).inflate(R.layout.custom_toast, null);
        TextView tv = (TextView) v.findViewById(R.id.message_text);
        tv.setText("나만의 토스트");

        result.setView(v);

        result.setDuration(duration);
        result.setGravity(Gravity.CENTER_HORIZONTAL,
                0, -300);
        return result;
    }

    public static String getRealPath(Context context, Uri uri) {
        String strDocId = DocumentsContract.getDocumentId(uri);
        String[] strSplittedDocId = strDocId.split(":");
        String strId = strSplittedDocId[strSplittedDocId.length - 1];

        Cursor crsCursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.MediaColumns.DATA},
                "_id=?",
                new String[]{strId},
                null
        );
        crsCursor.moveToFirst();
        String filePath = crsCursor.getString(0);

        return filePath;
    }

    public static int sum(int a, int b) {
        return a + b;

    }
}
