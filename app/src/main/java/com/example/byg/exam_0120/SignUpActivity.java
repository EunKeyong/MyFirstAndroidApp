package com.example.byg.exam_0120;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int REQUEST_CODE = 1000;
    private EditText mIdEditText;
    private EditText mPassWordEditText;
    private EditText mPassWordCheckEditText;
    private EditText mEmailEditText;

    private RadioGroup mGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mIdEditText = (EditText) findViewById(R.id.id_edit);
        mPassWordEditText = (EditText) findViewById(R.id.password_edit);
        mPassWordCheckEditText = (EditText) findViewById(R.id.password_check_edit);
        mEmailEditText = (EditText) findViewById(R.id.email_edit);

        mGender = (RadioGroup) findViewById(R.id.gender_radiogroup);

        findViewById(R.id.init_button).setOnClickListener(this);
        findViewById(R.id.join_button).setOnClickListener(this);

        findViewById(R.id.activity_sign_up).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        // 화면 터치시 키보드 숨김
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mIdEditText.getWindowToken(), 0);

        boolean isSelected = false;
        String gender = "";
        RadioButton rd;
        // 라디오 버튼 체크시
        if (mGender.getCheckedRadioButtonId() != -1) {
            rd = (RadioButton) findViewById(mGender.getCheckedRadioButtonId());
            gender = rd.getText().toString();
            isSelected = true;
        }

        switch (view.getId()) {
            // 초기화 버튼눌렀을때
            case R.id.init_button:
                init();
                break;
            case R.id.join_button:
                // 입력이 빠졌을때
                if (TextUtils.isEmpty(mIdEditText.getText().toString())
                        || TextUtils.isEmpty(mPassWordEditText.getText().toString())
                        || TextUtils.isEmpty(mPassWordCheckEditText.getText().toString())
                        || TextUtils.isEmpty(mEmailEditText.getText().toString())
                        || !isSelected
                        ) {
                    Toast.makeText(this, "모두 입력해주셔야 합니다", Toast.LENGTH_SHORT).show();

                    // 비밀번호, 비밀번호확인 입력값이 다를 경우
                } else if (!mPassWordEditText.getText().toString().equals(mPassWordCheckEditText.getText().toString())) {
                    Toast.makeText(this, "비밀번호가 다릅니다", Toast.LENGTH_SHORT).show();

                } else {
                    Intent intent = new Intent(this, SignUpCheckActivity.class);
                    intent.putExtra("result", "아이디 : " + mIdEditText.getText().toString() + "\n"
                            + "비밀번호 : " + mPassWordEditText.getText().toString() + "\n" + "이메일 : "
                            + mEmailEditText.getText().toString() + "\t\t\t성별 : " + gender);

                    startActivityForResult(intent, REQUEST_CODE);
                }
                break;
            default:
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String result = data.getStringExtra("result");
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();

        }
    }

    private void init() {
        mIdEditText.setText("");
        mPassWordEditText.setText("");
        mPassWordCheckEditText.setText("");
        mEmailEditText.setText("");
        mGender.clearCheck();
    }
}