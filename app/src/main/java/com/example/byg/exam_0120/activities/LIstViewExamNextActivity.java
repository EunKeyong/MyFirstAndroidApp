package com.example.byg.exam_0120.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.byg.exam_0120.R;
import com.example.byg.exam_0120.adapters.ListViewExamAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LIstViewExamNextActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView mListView;
    private ListViewExamAdapter mListAdapter;
    private List<Integer> mNumberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_exam_next);

        mListView = (ListView) findViewById(R.id.list_view);
        mNumberList = new ArrayList<>();
        findViewById(R.id.back_btn).setOnClickListener(this);

        if (getIntent() != null) {
            mNumberList = (List < Integer >) getIntent().getSerializableExtra("data");
        }
        Collections.reverse(mNumberList);
        mListAdapter = new ListViewExamAdapter(this, mNumberList);
        //String value = getIntent().getStringExtra("value");
        mListView.setAdapter(mListAdapter);
    }


    @Override
    public void onClick(View view) {
        finish();
    }
}
