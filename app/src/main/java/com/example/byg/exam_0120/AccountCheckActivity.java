package com.example.byg.exam_0120;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AccountCheckActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mIdEditText;
    private EditText mPassWordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_check);

        mIdEditText = (EditText) findViewById(R.id.id_edit);
        mPassWordEditText = (EditText) findViewById(R.id.password_edit);

        findViewById(R.id.login_button).setOnClickListener(this);
        findViewById(R.id.close_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:

                break;
            case R.id.close_btn:
                finish();
                break;
        }
    }
}
