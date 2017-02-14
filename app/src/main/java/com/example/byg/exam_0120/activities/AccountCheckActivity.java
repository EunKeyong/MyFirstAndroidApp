package com.example.byg.exam_0120.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.byg.exam_0120.R;
import com.example.byg.exam_0120.managers.Bankmanager;
import com.example.byg.exam_0120.models.AccountInfo;

public class AccountCheckActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mIdEditText;
    private EditText mPassWordEditText;
    private TextView mResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_check);

        mIdEditText = (EditText) findViewById(R.id.id_edit);
        mPassWordEditText = (EditText) findViewById(R.id.password_edit);
        mResultTextView = (TextView) findViewById(R.id.result_text);

        findViewById(R.id.login_button).setOnClickListener(this);
        findViewById(R.id.close_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_button:
                Bankmanager bankmanager = Bankmanager.newInstance();

                String id = mIdEditText.getText().toString();
                String password = mPassWordEditText.getText().toString();

                AccountInfo accountInfo = bankmanager.login(id, password);
                if(accountInfo != null) {
                    mResultTextView.setText(accountInfo.toString());
                }
                break;
            case R.id.close_btn:
                finish();
                break;
        }
    }
}
