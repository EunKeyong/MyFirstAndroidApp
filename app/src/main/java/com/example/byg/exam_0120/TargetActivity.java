package com.example.byg.exam_0120;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TargetActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mValueTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);

        mValueTextView = (TextView) findViewById(R.id.value_text);
        findViewById(R.id.result_button).setOnClickListener(this);

        if(getIntent() != null) {
            String value = getIntent().getStringExtra("value");
            mValueTextView.setText(value);
        }
    }

    @Override
    public void onClick(View view) {
        // 결과 전달
        // intent 실어서 전달
        Intent intent = new Intent();
        intent.putExtra("result", "이것은 내가 지정한 문구다");
        intent.putExtra("int", 50);
        setResult(RESULT_OK, intent);
        // 현재 액티비티 종료
        finish();
    }
}
