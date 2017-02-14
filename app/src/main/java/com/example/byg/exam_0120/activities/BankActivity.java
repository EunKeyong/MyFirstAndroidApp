package com.example.byg.exam_0120.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.byg.exam_0120.R;
import com.example.byg.exam_0120.managers.Bankmanager;

public class BankActivity extends AppCompatActivity implements View.OnClickListener {

//    public static final int REQUEST_CODE_OPEN_ACCOUNT = 1000;
//    public static final int REQUEST_CODE_ACCOUNT_CHECK = 1001;
//    public static final int REQUEST_CODE_ADMIN_MODE = 1002;
//
//    private AccountInfoAdapter mAdapter;
//    private List<AccountInfo> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);
//
//        mDataList = new ArrayList<>();
//        mAdapter = new AccountInfoAdapter(this, mDataList);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // 개좌 개설
            case R.id.open_account_btn:
                startOpenAccount();
                break;
            // 계좌 조회
            case R.id.account_check_btn:
                startAccountCheck();
                break;
            // 관리자 모드
            case R.id.admin_mode_btn:
                showAdminDialog();
                break;
            // 종료
            case R.id.end_btn:
                finish();
                break;
        }
    }

    // 계좌조회페이지로
    private void startAccountCheck() {
        Intent intent1 = new Intent(this, AccountCheckActivity.class);
        //intent1.putExtra("data", (Serializable) mDataList);
        startActivity(intent1);
    }

    // 개좌개설페이지로
    private void startOpenAccount() {
        Intent intent = new Intent(this, OpenAccountActivity.class);
        startActivity(intent);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        // 계좌 개설 했을 때
//        if (requestCode == REQUEST_CODE_OPEN_ACCOUNT && resultCode == RESULT_OK) {
//            String id = data.getStringExtra("id");
//            String password = data.getStringExtra("password");
//            String balance = data.getStringExtra("balance");
//            // 리스트에 계좌정보 저장
//            mDataList.add(new AccountInfo(id, password, balance));
//            mAdapter.notifyDataSetChanged();
//            Toast.makeText(this, "계좌가 개설되었습니다", Toast.LENGTH_SHORT).show();
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }

    // AdminLoginDialog
    private void showAdminDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_login, null, false);

        final EditText idEditText = (EditText) view.findViewById(R.id.id_edit);
        final EditText passWordEditText = (EditText) view.findViewById(R.id.password_edit);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        builder.setTitle("관리자 모드");
        // 로그인 버튼
        builder.setPositiveButton("로그인", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String id = idEditText.getText().toString();
                String pass = passWordEditText.getText().toString();

                Bankmanager bankmanager = Bankmanager.newInstance();
                // 관리자라면 관리자 페이지로
                if (bankmanager.isAdmin(id, pass)) {
                    startAdminMode();
                }
                // 아이디와 비밀번호가 일치하면 관리자모드 페이지로 넘어감
//                if (id.equals("admin") && pass.equals("admin")) {
//                    startAdminMode();
//                }
                else {
                    Toast.makeText(BankActivity.this, "다시 입력해주세요", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // 닫기 버튼
        builder.setNegativeButton("닫기", null);
        builder.show();
    }

    // 관리자모드 페이지로
    private void startAdminMode() {
        Intent intent2 = new Intent(BankActivity.this, AdminModeActivity.class);
        //intent2.putExtra("data", (Serializable) mDataList);
        startActivity(intent2);
    }
}
