package com.example.byg.exam_0120.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.byg.exam_0120.R;
import com.example.byg.exam_0120.login.ResultModel;
import com.example.byg.exam_0120.login.RetrofitUtil;
import com.example.byg.exam_0120.login.UserApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements OnClickListener {

    public static final int REQUEST_CODE = 1000;
    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private UserApi mUserApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);

        Button mEmailSignInButton = (Button) findViewById(R.id.login_button);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        mUserApi = new RetrofitUtil().getmUserApi();

        mEmailSignInButton.setOnClickListener(this);
        findViewById(R.id.sign_up_button).setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_button:
                Call<ResultModel> call = mUserApi.login(mEmailView.getText().toString(),
                        mPasswordView.getText().toString());

                // 비동기 네트워크 처리
                call.enqueue(new Callback<ResultModel>() {
                    @Override
                    public void onResponse(Call<ResultModel> call, Response<ResultModel> response) {
                        // 정상결과
                        ResultModel result = response.body();
                        if (result.getResult().equals("ok")) {
                            // 성공
                            startActivity(new Intent(LoginActivity.this, MemoActivity.class));
                        } else if(result.getResult().equals("error") && result.getResult_code().equals("100")){
                            Toast.makeText(LoginActivity.this, "비밀번호가 틀렸습니다", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(LoginActivity.this, "등록되지 않은 이메일 입니다", Toast.LENGTH_SHORT).show();
                        }
                    }

                    // 네트워크 문제
                    @Override
                    public void onFailure(Call<ResultModel> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "네트워크 연결 실패", Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case R.id.sign_up_button:
                startActivityForResult(new Intent(LoginActivity.this, SignUp2Activity.class), REQUEST_CODE);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            String result = data.getStringExtra("result");
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        }
    }
}

