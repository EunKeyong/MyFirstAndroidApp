package com.example.byg.exam_0120.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.byg.exam_0120.models.Weather;

import java.util.List;

/**
 * Created by byg on 2017-02-07.
 */

public class WeatherAdapter extends BaseAdapter {
    private Context mContext;
    private List<Weather> mData;

    public WeatherAdapter(Context context, List<Weather> data) {
        mContext = context;
        mData = data;
    }

    // 아이템 갯수
    @Override
    public int getCount() {
        return mData.size();
    }

    // position 번째 아이템
    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    // position 번재 아이템 id
    @Override
    public long getItemId(int position) {
        return position;
    }

    // position 번째의 layout
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        // convertView 에서 item 하나의 레이아웃 정의
        return null;
    }
}
