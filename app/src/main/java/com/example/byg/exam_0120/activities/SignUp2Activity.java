package com.example.byg.exam_0120.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
public class SignUp2Activity extends AppCompatActivity implements View.OnClickListener {

    private AutoCompleteTextView mEmailView;
    private AutoCompleteTextView mNickNameView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private UserApi mUserApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mNickNameView = (AutoCompleteTextView) findViewById(R.id.nickname);
        mPasswordView = (EditText) findViewById(R.id.password);

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);


        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);

        mUserApi = new RetrofitUtil().getmUserApi();
        mEmailSignInButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Call<ResultModel> call = mUserApi.signup(mEmailView.getText().toString(),
                mNickNameView.getText().toString(),
                mPasswordView.getText().toString());

        // 비동기 네트워크 처리
        call.enqueue(new Callback<ResultModel>() {
            @Override
            public void onResponse(Call<ResultModel> call, Response<ResultModel> response) {
                // 정상결과
                ResultModel result = response.body();

                if (result.getResult().equals("ok") && result.getResult_code().equals("100")) {
                    // 성공
                    Intent intent = new Intent();
                    intent.putExtra("result", "회원가입 완료");
                    setResult(RESULT_OK, intent);
                    // 현재 액티비티 종료
                    finish();
                    //startActivity(new Intent(SignUp2Activity.this, LoginActivity.class));
                } else if(result.getResult().equals("error") && result.getResult_code().equals("300")){
                    Toast.makeText(SignUp2Activity.this, "공백입니다. 다시입력해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SignUp2Activity.this, "이미 존재하는 이메일 입니다. 다시입력해주세요", Toast.LENGTH_SHORT).show();
                }
            }

            // 네트워크 문제
            @Override
            public void onFailure(Call<ResultModel> call, Throwable t) {
                Toast.makeText(SignUp2Activity.this, "네트워크 연결 실패", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

