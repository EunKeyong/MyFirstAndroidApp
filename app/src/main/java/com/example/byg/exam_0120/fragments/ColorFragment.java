package com.example.byg.exam_0120.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.byg.exam_0120.R;


public class ColorFragment extends Fragment {

    // 필수
    public ColorFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_color, container, false);
        view.setBackgroundColor(Color.YELLOW);
        return view;
    }

    public void setColor(int color) {
        getView().setBackgroundColor(color);
    }

}
