package com.example.byg.exam_0120.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.byg.exam_0120.R;

import java.util.List;

/**
 * Created by byg on 2017-02-09.
 */

public class ListViewExamAdapter extends BaseAdapter {

    private Context mContext;
    private List<Integer> mNumber;

    public ListViewExamAdapter(Context mContext, List<Integer> mNumber) {
        this.mContext = mContext;
        this.mNumber = mNumber;
    }

    @Override
    public int getCount() {
        return mNumber.size();
    }

    @Override
    public Object getItem(int i) {
        return mNumber.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            // 뷰를 새로 만들 때
            viewHolder = new ViewHolder();
            // 없으니까 가져옴
            view = LayoutInflater.from(mContext).inflate(R.layout.item_listview_exam,
                    viewGroup, false);

            //레이아웃 들고오기
            TextView numberTextView = (TextView) view.findViewById(R.id.numInt_text);

            // 뷰 홀더에 넣는다.
            viewHolder.numberTextView = numberTextView;

            view.setTag(viewHolder);

            // 재사용 할 때
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        // 데이터
       // Weather weather = mData.get(position);

        //화면에 뿌리기
        viewHolder.numberTextView.setText(mNumber.get(i)+"");
       // viewHolder.weatherImage.setImageResource(weather.getImageRes());

        // 홀수줄은빨간색
        if(i == 0) {
            view.setBackgroundColor(Color.BLACK);
        } else {
            if (i % 2 == 0) {
                view.setBackgroundColor(Color.BLUE);
            } else {
                view.setBackgroundColor(Color.YELLOW);
            }
        }
        return view;
    }

    private static class ViewHolder {
        TextView numberTextView;
    }
}
