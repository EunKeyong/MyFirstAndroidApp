package com.example.byg.exam_0120.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.byg.exam_0120.R;
import com.example.byg.exam_0120.adapters.AccountInfoAdapter;
import com.example.byg.exam_0120.managers.Bankmanager;

public class AdminModeActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView mAccountInfoListView;
    private AccountInfoAdapter mAdapter;
   // private ArrayList<AccountInfo> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_mode);

        mAccountInfoListView = (ListView) findViewById(R.id.account_info_list);

        Bankmanager bankmanager = Bankmanager.newInstance();

        mAdapter = new AccountInfoAdapter(bankmanager.getAccountList());

//        if (getIntent() != null) {
//            mDataList = (ArrayList<AccountInfo>) getIntent().getSerializableExtra("data");
//        }

       // mAdapter = new AccountInfoAdapter(this, mDataList);
        mAccountInfoListView.setAdapter(mAdapter);

        findViewById(R.id.end_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
