package com.example.byg.exam_0120.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.byg.exam_0120.R;

public class StartActivityForResultActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int REQUEST_CODE_EXAMPLE = 1000;
    public static final int REQUEST_NEW_MEMO = 2000;
    public static final int REQUEST_UPDATE_MEMO = 3000;
    private EditText mValueEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_for_result);

        mValueEditText = (EditText) findViewById(R.id.value_edit);
        findViewById(R.id.submit_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, TargetActivity.class);
        intent.putExtra("value", mValueEditText.getText().toString());
        //startActivity(intent);
        // 주거니 받거니
        startActivityForResult(intent, REQUEST_CODE_EXAMPLE);

    }
    // 받을 때 호출되는 콜백 메서드
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // requestCode 로 구분
        if (requestCode == 1000 && requestCode == RESULT_OK && data != null) {
            //Log.d(TAG, "onActivityResult: " + requestCode);
            String result = data.getStringExtra("result");
            //default 값 줘야함
            int value = data.getIntExtra("int", -1);
            Toast.makeText(this, result + ", int : " + value, Toast.LENGTH_SHORT).show();
        } else if (requestCode == REQUEST_NEW_MEMO) {
        } else if (requestCode == REQUEST_UPDATE_MEMO) {

        }
    }
}
