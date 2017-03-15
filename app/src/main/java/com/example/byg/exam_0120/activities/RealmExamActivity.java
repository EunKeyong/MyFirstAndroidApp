package com.example.byg.exam_0120.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.byg.exam_0120.R;
import com.example.byg.exam_0120.models.User;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class RealmExamActivity extends AppCompatActivity implements RealmChangeListener<Realm> {

    private EditText mEmail;
    private EditText mPassword;
    private EditText mNewPassword;
    private TextView mResultText;

    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_exam);

        mEmail = (EditText) findViewById(R.id.email_edit);
        mPassword = (EditText) findViewById(R.id.password_edit);
        mNewPassword = (EditText) findViewById(R.id.new_password_edit);
        mResultText = (TextView) findViewById(R.id.result_text);

        mRealm = Realm.getDefaultInstance();
        mRealm.addChangeListener(this);

        showResult();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 리스너 해제해줘야함
        mRealm.removeAllChangeListeners();
        mRealm.close();
    }

    public void SignIn(View view) {
        if (mRealm.where(User.class)
                .equalTo("email", mEmail.getText().toString())
                .equalTo("password", mPassword.getText().toString())
                .count() > 0) {
            Toast.makeText(this, "로그인 성공", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "등록된 정보가 없습니다", Toast.LENGTH_SHORT).show();
        }
    }

    public void SignUp(View view) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // 이메일이 중복된게 없을때 추가
                if (realm.where(User.class)
                        .equalTo("email", mEmail.getText().toString())
                        .count() == 0) {

                    User user = mRealm.createObject(User.class);
                    user.setEmail(mEmail.getText().toString());
                    user.setPassword(mPassword.getText().toString());

                }
            }
        });
    }

    public void updatePassword(View view) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.where(User.class)
                        .equalTo("email", mEmail.getText().toString())
                        .findFirst();

                user.setPassword(mNewPassword.getText().toString());
            }
        });

    }

    public void deleteAccount(View view) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(User.class)
                        .equalTo("email", mEmail.getText().toString())
                        .findAll()
                        .deleteAllFromRealm();
            }
        });
    }

    // db 상태 뿌려주는 메서드
    private void showResult() {
        RealmResults<User> result = mRealm.where(User.class).findAll();
        mResultText.setText(result.toString());
    }

    @Override
    public void onChange(Realm element) {
        // db가 갱신 될 때 마다 호출
        showResult();
    }
}
