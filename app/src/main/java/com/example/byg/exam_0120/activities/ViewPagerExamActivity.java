package com.example.byg.exam_0120.activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.byg.exam_0120.R;
import com.example.byg.exam_0120.fragments.ListViewFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerExamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_exam);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(ListViewFragment.newInstance(createCharacterList('a', 'z')));
        fragmentList.add(ListViewFragment.newInstance(createCharacterList('A', 'Z')));
        fragmentList.add(ListViewFragment.newInstance(createCharacterList('ㄱ', 'ㅎ')));
        fragmentList.add(ListViewFragment.newInstance(createCharacterList('0', '9')));

        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager(), fragmentList, new String[]{
                "영어 소문자", "영어 대문자", "한글", "숫자"
        });
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private List<String> createCharacterList(char start, char end) {
        List<String> list = new ArrayList<>();
        for (char i = start; i <= end; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }

    private List<String> createLowerCaseAlphabetList() {
        List<String> list = new ArrayList<>();
        char ch = 'a';
        for (char i = ch; i <= 'z'; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }

    private List<String> createUpperrCaseAlphabetList() {
        List<String> list = new ArrayList<>();
        char ch = 'A';
        for (char i = ch; i <= 'Z'; i++) {
            list.add(String.valueOf(i));

        }
        return list;
    }

    private List<String> createHangulList() {
        List<String> list = new ArrayList<>();
        char ch = 'ㄱ';
        for (char i = ch; i <= 'ㅎ'; i++) {
            list.add(String.valueOf(i));

        }
        return list;
    }

    private List<String> createNumberList() {
        List<String> list = new ArrayList<>();
        char ch = '0';
        for (char i = ch; i <= '9'; i++) {
            list.add(String.valueOf(i));
        }
        return list;
    }

    private static class MyPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> mmFragmentList;
        private String[] mmPageTitles;

        public MyPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, String[] pagetitle) {
            super(fm);
            mmFragmentList = fragmentList;
            mmPageTitles = pagetitle;
        }

        @Override
        public Fragment getItem(int position) {
            return mmFragmentList.get(position);
//            switch (position) {
//                case 0:
//                    return ColorFragment.newInstance(Color.RED);
//                case 1:
//                    return ColorFragment.newInstance(Color.BLUE);
//                case 2:
//                    return ColorFragment.newInstance(Color.YELLOW);
//                case 3:
//                    return ColorFragment.newInstance(Color.WHITE);
//                case 4:
//                    return ColorFragment.newInstance(Color.MAGENTA);
//            }
//            return null;
        }

        // 페이지 수 몇 장인지
        @Override
        public int getCount() {
            //return 4;
            return mmFragmentList.size();
        }

        // 제목 표시
        @Override
        public CharSequence getPageTitle(int position) {
//            switch (position) {
//                case 0:
//                    return "영어 소문자";
//                case 1:
//                    return "영어 대문자";
//                case 2:
//                    return "한글";
//                case 3:
//                    return "숫자";
//            }
            return mmPageTitles[position];
        }
    }
}
