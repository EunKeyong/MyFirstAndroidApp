package com.example.byg.exam_0120;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class BusinessManagementActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int REQUEST_LOGIN = 1000;

    private EditText mIdEdit;
    private EditText mPassWordEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_management);

        mIdEdit = (EditText) findViewById(R.id.id_edit);
        mPassWordEdit = (EditText) findViewById(R.id.password_edit);

        findViewById(R.id.login_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // 관리페이지로 넘어감
        Intent intent = new Intent(this, ManagementActivity.class);
        intent.putExtra("id", mIdEdit.getText().toString());
        intent.putExtra("password", mPassWordEdit.getText().toString());

        startActivityForResult(intent, REQUEST_LOGIN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_LOGIN) {
            String result = data.getStringExtra("menu_text");
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        }
    }
}
