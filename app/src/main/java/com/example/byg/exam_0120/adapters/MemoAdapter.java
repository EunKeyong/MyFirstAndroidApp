package com.example.byg.exam_0120.adapters;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.byg.exam_0120.R;
import com.example.byg.exam_0120.models.Memo;

import java.util.List;

/**
 * Created by byg on 2017-02-07.
 */

public class MemoAdapter extends BaseAdapter {
    private Context mContext;
    private List<Memo> mData;

    public MemoAdapter(Context context, List<Memo> data) {
        mContext = context;
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        // convertView : 재사용 되는 뷰
        if (view == null) {
            // 뷰를 새로 만들 때
            viewHolder = new ViewHolder();
            // 없으니까 가져옴
            view = LayoutInflater.from(mContext).inflate(R.layout.item_memo, viewGroup, false);

            //레이아웃 들고오기
            TextView titleTextView = (TextView) view.findViewById(R.id.title_text);
            TextView contentTextView = (TextView) view.findViewById(R.id.content_text);

            // 뷰 홀더에 넣는다.
            viewHolder.titleTextView = titleTextView;
            viewHolder.contentTextView = contentTextView;
            view.setTag(viewHolder);

            // 재사용 할 때
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        // 데이터
        Memo memo = mData.get(i);

        //화면에 뿌리기
//        viewHolder.titleTextView.setText(memo.getTitle());
//        viewHolder.contentTextView.setText(memo.getContent());

        if (TextUtils.isEmpty(viewHolder.titleTextView.getText().toString())) {
            viewHolder.titleTextView.setText("제목 없음");
        } else {
            viewHolder.titleTextView.setText(memo.getTitle());
        }
        if (TextUtils.isEmpty(viewHolder.contentTextView.getText().toString())) {
            viewHolder.contentTextView.setText("내용이 비었습니다");
        } else {
            viewHolder.contentTextView.setText(memo.getContent());
        }
        // 선택된 포지션과 현재 위치가 같으면
//        if (mSelectedPosition == i) {
//
//        }
        return view;
    }

    // -1이면 선택된게 없다.
//    private int mSelectedPosition = -1;
//
//    public void setSelect(int position) {
//        mSelectedPosition = position;
//    }
    // findViewById로 가져온 View 들을 보관
    private static class ViewHolder {
        TextView titleTextView;
        TextView contentTextView;
    }
}
