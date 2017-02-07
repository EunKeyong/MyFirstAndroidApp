package com.example.byg.exam_0120;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.byg.exam_0120.adapters.WeatherAdapter;
import com.example.byg.exam_0120.models.Weather;

import java.util.ArrayList;
import java.util.List;

public class WeatherActivity extends AppCompatActivity {

    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        mListView = (ListView) findViewById(R.id.list_view);

        // 날씨 데이터 생성
        List<Weather> weatherList = new ArrayList<>();
        weatherList.add(new Weather(R.drawable.beach, "수원", "27도"));
        weatherList.add(new Weather(R.drawable.beach, "수원", "21도"));
        weatherList.add(new Weather(R.drawable.beach, "수원", "22도"));
        weatherList.add(new Weather(R.drawable.beach, "수원", "23도"));
        weatherList.add(new Weather(R.drawable.beach, "수원", "24도"));
        weatherList.add(new Weather(R.drawable.beach, "수원", "20도"));
        weatherList.add(new Weather(R.drawable.beach, "수원", "18도"));
        weatherList.add(new Weather(R.drawable.beach, "수원", "17도"));

        // 어댑터
        WeatherAdapter adapter = new WeatherAdapter(this, weatherList);

        mListView.setAdapter(adapter);

    }
}
