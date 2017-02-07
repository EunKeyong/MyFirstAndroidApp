package com.example.byg.exam_0120;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.byg.exam_0120.adapters.WeatherAdapter;
import com.example.byg.exam_0120.models.Weather;

import java.util.ArrayList;
import java.util.List;

public class WeatherActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView mListView;
    private WeatherAdapter madapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        mListView = (ListView) findViewById(R.id.list_view);

        // 날씨 데이터 생성
        List<Weather> weatherList = new ArrayList<>();
        weatherList.add(new Weather(R.drawable.sunny, "수원", "27도"));
        weatherList.add(new Weather(R.drawable.blizzard, "안양", "23도"));
        weatherList.add(new Weather(R.drawable.cloudy, "안산", "24도"));
        weatherList.add(new Weather(R.drawable.rainy, "평택", "22도"));
        weatherList.add(new Weather(R.drawable.snow, "서울", "26도"));
        weatherList.add(new Weather(R.drawable.sunny, "부산", "21도"));
        weatherList.add(new Weather(R.drawable.sunny, "도쿄", "10도"));
        weatherList.add(new Weather(R.drawable.sunny, "워싱턴", "5도"));
        weatherList.add(new Weather(R.drawable.snow, "평양", "6도"));
        weatherList.add(new Weather(R.drawable.snow, "베이징", "12도"));
        weatherList.add(new Weather(R.drawable.rainy, "수원", "49도"));
        weatherList.add(new Weather(R.drawable.rainy, "수원", "27도"));
        weatherList.add(new Weather(R.drawable.blizzard, "수원", "27도"));
        weatherList.add(new Weather(R.drawable.blizzard, "수원", "27도"));
        weatherList.add(new Weather(R.drawable.sunny, "수원", "27도"));
        weatherList.add(new Weather(R.drawable.sunny, "수원", "27도"));
        weatherList.add(new Weather(R.drawable.sunny, "수원", "27도"));
        weatherList.add(new Weather(R.drawable.sunny, "수원", "27도"));

        // 어댑터
        madapter = new WeatherAdapter(this, weatherList);

        mListView.setAdapter(madapter);
        mListView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        madapter.setSelect(position);

        // 데이터가 변경됨을 알려줌 / 다시그려라
        madapter.notifyDataSetChanged();
    }
}
