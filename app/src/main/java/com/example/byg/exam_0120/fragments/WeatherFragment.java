package com.example.byg.exam_0120.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.byg.exam_0120.R;
import com.example.byg.exam_0120.adapters.WeatherAdapter;
import com.example.byg.exam_0120.models.Weather;

import java.io.Serializable;
import java.util.List;

/**
 * Created by byg on 2017-02-16.
 */

public class WeatherFragment extends Fragment {

    private List<Weather> mWeatherList;

    public WeatherFragment() {
    }

    public static WeatherFragment newInstance(List<Weather> weatherList) {
        WeatherFragment fragment = new WeatherFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", (Serializable) weatherList);
        fragment.setArguments(bundle);
        return fragment;
    }
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_view, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mWeatherList = (List<Weather>) getArguments().getSerializable("data");
        ListView listView = (ListView) view.findViewById(R.id.list_view);
        WeatherAdapter adapter = new WeatherAdapter(getActivity(), mWeatherList);

        listView.setAdapter(adapter);
    }

}
