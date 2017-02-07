package com.example.byg.exam_0120.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.byg.exam_0120.R;
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
        // convertView : 재사용 되는 뷰
        if (convertView == null) {
            // 없으니까 가져옴
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_weather,
                    viewGroup, false);
        }

        //레이아웃 들고오기
        ImageView imageView = (ImageView) convertView.findViewById(R.id.weather_image);
        TextView locationTextView = (TextView) convertView.findViewById(R.id.location_text);
        TextView temperatureTextView = (TextView) convertView.findViewById(R.id.temperature_text);

        // 데이터
        Weather weather = mData.get(position);

        //화면에 뿌리기
        imageView.setImageResource(weather.getImageRes());
        locationTextView.setText(weather.getLocation());
        temperatureTextView.setText(weather.getTemperature());

        // 홀수줄은빨간색
        if (position % 2 == 1) {
            convertView.setBackgroundColor(Color.RED);
        } else {
            convertView.setBackgroundColor(Color.WHITE);
        }

        // 선택된 포지션과 현재 위치가 같으면
        if (mSelectedPosition == position) {
            convertView.setBackgroundColor(Color.YELLOW);
        }

        return convertView;
    }

    // -1이면 선택된게 없다.
    private int mSelectedPosition = -1;

    public void setSelect(int position) {
        mSelectedPosition = position;
    }
}
