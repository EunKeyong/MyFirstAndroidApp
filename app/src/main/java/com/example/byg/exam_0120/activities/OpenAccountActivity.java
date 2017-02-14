package com.example.byg.exam_0120.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.byg.exam_0120.R;
import com.example.byg.exam_0120.adapters.AccountInfoAdapter;
import com.example.byg.exam_0120.managers.Bankmanager;
import com.example.byg.exam_0120.models.AccountInfo;

import java.util.List;

public class OpenAccountActivity extends AppCompatActivity implements View.OnClickListener {

    private AccountInfoAdapter mAdapter;
    private List<AccountInfo> mDataList;

    private EditText mIdEditText;
    private EditText mPassWordEditText;
    private EditText mPassCheckEditText;
    private EditText mBalanceEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_account);

        mIdEditText = (EditText) findViewById(R.id.id_edit);
        mPassWordEditText = (EditText) findViewById(R.id.password_edit);
        mPassCheckEditText = (EditText) findViewById(R.id.password_check_edit);
        mBalanceEditText = (EditText) findViewById(R.id.balance_edit);

        findViewById(R.id.re_input_btn).setOnClickListener(this);
        findViewById(R.id.open_btn).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.re_input_btn:
                init();
                break;
            case R.id.open_btn:
                Bankmanager bankmanager = Bankmanager.newInstance();
                String id = mIdEditText.getText().toString();
                String password = mPassWordEditText.getText().toString();
                String password_check = mPassCheckEditText.getText().toString();
                int balance = Integer.parseInt(mBalanceEditText.getText().toString());

                AccountInfo accountInfo = new AccountInfo(id,password,balance);

//                String id = mIdEditText.getText().toString();
//                String password = mPassWordEditText.getText().toString();
//                String password_check = mPassCheckEditText.getText().toString();
                if (!password.equals(password_check)) {
                    Toast.makeText(this, "비밀 번호 확인을 다시 입력해 주세요", Toast.LENGTH_SHORT).show();
                } else {
                    bankmanager.open(accountInfo);
                    Toast.makeText(this, "개좌가 개설되었습니다", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent();
//                    // intent.putExtra("id", mId);
//                    intent.putExtra("account_title", id);
//                    intent.putExtra("password", password);
//                    intent.putExtra("balance", mBalanceEditText.getText().toString());
//                    setResult(RESULT_OK, intent);
                    finish();
                }
                break;
        }
    }

    private void init() {
        mIdEditText.setText("");
        mPassWordEditText.setText("");
        mPassCheckEditText.setText("");
        mBalanceEditText.setText("");
    }
}
