package com.example.byg.exam_0120.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.byg.exam_0120.R;
import com.example.byg.exam_0120.adapters.ListViewExamAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ListViewExamActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView mListView;
    private ListViewExamAdapter mListAdapter;
    private List<Integer> mNumberList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_exam);

        mListView = (ListView) findViewById(R.id.list_view);
        mNumberList = new ArrayList<>();
        findViewById(R.id.next_btn).setOnClickListener(this);

        for (int i = 0; i < 101; i++) {
            mNumberList.add(i);
        }
        mListAdapter = new ListViewExamAdapter(this, mNumberList);
        mListView.setAdapter(mListAdapter);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, LIstViewExamNextActivity.class);
        intent.putExtra("data", (Serializable) mNumberList);
        startActivity(intent);
    }
}
