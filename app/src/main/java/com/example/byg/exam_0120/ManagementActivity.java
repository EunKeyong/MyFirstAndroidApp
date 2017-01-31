package com.example.byg.exam_0120;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ManagementActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_management);

        findViewById(R.id.custom_button).setOnClickListener(this);
        findViewById(R.id.sales_button).setOnClickListener(this);
        findViewById(R.id.product_button).setOnClickListener(this);

        if (getIntent() != null) {
            String id = getIntent().getStringExtra("id");
            String password = getIntent().getStringExtra("password");
            // 관리페이지 넘어갈때 아이디, 패스워드 출력
            Toast.makeText(this, id + "\n" + password, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        intent.putExtra("menu_text", ((Button) view).getText().toString());
        setResult(RESULT_OK, intent);
        // 현재 액티비티 종료
        finish();
//        switch (view.getId()) {
//            case R.id.custom_button:
//                break;
//            case R.id.sales_button:
//                break;
//            case R.id.product_button:
//                break;
//        }
    }
}
