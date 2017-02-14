package com.example.byg.exam_0120.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.byg.exam_0120.R;

/**
 * Created by byg on 2017-02-14.
 */

public class LifeCycleFragment extends Fragment {

    // 필수
    //  빈 생성자가 필요, 파라미터를 가질 수 없다
    // (액티비티의 라이프사이클에 맞게 동작하게 하기 위해)
    public LifeCycleFragment() {
    }

    // 액티비티에 붙을 때
    @Override
    public void onAttach(Context context) {
        // context : 액티비티
        super.onAttach(context);
    }

    // 프래그먼트가 생성될 때
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        // savedInstanceState 로 복원 처리 가능
        super.onCreate(savedInstanceState);
    }

    // 액티비티가 실제로 생성된 직후 호출
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        // savedInstanceState 로 복원 처리 가능
        super.onActivityCreated(savedInstanceState);
    }

    // 액티비티의 onCreate()에 해당 : 레이아웃 완성
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // savedInstanceState 로 복원 처리 가능
        return inflater.inflate(R.layout.fragment_color, container, false);
    }

    // 뷰 소멸
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    // 프래그먼트 소멸
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    // 액티비티에서 떨어짐
    @Override
    public void onDetach() {
        super.onDetach();
    }
}
