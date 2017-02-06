package com.example.byg.exam_0120;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListViewActivity extends AppCompatActivity {

    private ListView mListView;
    private ArrayList<Map<String, Object>> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        // 뷰
        mListView = (ListView) findViewById(R.id.list_view);

        // 데이터
        mDataList = new ArrayList<>();
        //Map<String, Object> map = new HashMap<>();
        addItem("농구 점수판", "Button, OnClickListener 연습", BasketballActivity.class);
        addItem("커피앱", "checkBox, 이메일 전송", MainActivity.class);
        //addItem("암시적 인텐트", "암시적 인텐트, intent filter 연습", ImplicitActivity.class);
        addItem("로그인 화면", "RadioButton", SignUpActivity.class);
        addItem("웹 브라우저", "WebView, Vector Asset, Option Menu", WebBrowserActivity.class);

//        for (int i = 0; i < 100; i++) {
//            mDataList.add("이것은 데이타" + i);
//        }
        // 어댑터
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_list_item_1, mDataList);

        SimpleAdapter adapter = new SimpleAdapter(this, mDataList,
                android.R.layout.simple_list_item_2,
                new String[]{"title", "desc"},
                new int[]{android.R.id.text1, android.R.id.text2});

        mListView.setAdapter(adapter);

        // 클릭이벤트
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Map<String, Object> map = (Map<String, Object>) adapterView.getItemAtPosition(position);
                Intent intent = (Intent) map.get("intent");
                startActivity(intent);
                //Toast.makeText(ListViewActivity.this, "그냥 클릭"+ position , Toast.LENGTH_SHORT).show();
            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(ListViewActivity.this, "롱클릭" + position, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
    // ArrayAdapter

    private void addItem(String title, String desc, Class cls) {
        Map<String, Object> map = new HashMap<>();
        map.put("title", title);
        map.put("desc", desc);
        map.put("intent", new Intent(this, cls));
        mDataList.add(map);
    }

//    private static class MyAdapter extends BaseAdapter {
//
//        private final List<Map<String, Object>> mData;
//
//        public MyAdapter(List<Map<String, Object>> data) {
//            mData = data;
//        }
//        @Override
//        public int getCount() {
//            return mData.size();
//        }
//
//        @Override
//        public Object getItem(int i) {
//            return mData.get(i);
//        }
//
//        @Override
//        public long getItemId(int i) {
//            return i;
//        }
//
//        @Override
//        public View getView(int i, View view, ViewGroup viewGroup) {
//
//            if(view == null) {
//                view = LayoutInflater.from(viewGroup.getContext()).inflate
//                        (android.R.layout.simple_list_item_2, viewGroup, false);
//            }
//            TextView text1 = (TextView) view.findViewById(android.R.id.text1);
//            TextView text2 = (TextView) view.findViewById(android.R.id.text2);
//
//            return view;
//        }
//    }
}
