package com.example.byg.exam_0120.fragments.MusicPlayer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.byg.exam_0120.R;

/**
 * Created by byg on 2017-02-16.
 */

public class PlayerFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_player, container, false);
        return view;
    }
}
