package com.example.byg.exam_0120.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.byg.exam_0120.R;

public class SignUpCheckActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_check);

        mResultTextView = (TextView) findViewById(R.id.result_text);
        findViewById(R.id.confirmation_button).setOnClickListener(this);

        if (getIntent() != null) {
            String result = getIntent().getStringExtra("result");
                mResultTextView.setText(result);
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.putExtra("result", "확인 버튼을 누르셨습니다");
        setResult(RESULT_OK, intent);
        // 현재 액티비티 종료
        finish();
    }
}
