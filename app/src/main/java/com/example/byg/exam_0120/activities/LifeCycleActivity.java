package com.example.byg.exam_0120.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.byg.exam_0120.R;

public class LifeCycleActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = LifeCycleActivity.class.getSimpleName();
    private TextView mScoreTextView;
    private int mScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle);

        mScoreTextView = (TextView) findViewById(R.id.score_text);


        Log.d(TAG, "onCreate: ");

//        // 복원
//        if (savedInstanceState != null) {
//            mScore = savedInstanceState.getInt("score");
//            setScore(mScore);
//        }

        // 읽어오기
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mScore = sharedPreferences.getInt("score", 0);
        setScore(mScore);

    }

    // 복원 여기서도 가능(null 체크 불필요)
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        mScore = savedInstanceState.getInt("score");
        setScore(mScore);
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void setScore(int score) {
        mScoreTextView.setText("점수 :" + score + "");
    }

    @Override
    public void onClick(View view) {
        mScore += 1;
        setScore(mScore);
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart: ");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume: ");
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");

        // 저장
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("score", mScore);
        // 동기
//        editor.commit();
        // 비동기(async)
        editor.apply();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop: ");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    // 인스턴스 상태 저장
    // sharedpreference 사용시 필요 없음
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("score", mScore);
    }
}
