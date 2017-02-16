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
import com.example.byg.exam_0120.fragments.MusicPlayer.MusicPlayerListViewFragment;
import com.example.byg.exam_0120.fragments.MusicPlayer.PlayerFragment;
import com.example.byg.exam_0120.models.Song;

import java.util.ArrayList;
import java.util.List;

public class MusicPlayerExamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player_exam);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);

        ArrayList<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new PlayerFragment());
        fragmentList.add(ListViewFragment.newInstance(createArtistData()));
        fragmentList.add(MusicPlayerListViewFragment.newInstance(createSongData()));

        MyPagerAdapter adapter = new MyPagerAdapter(
                getSupportFragmentManager(),
                fragmentList,
                new String[]{"플레이어", "아티스트", "노래"
                });

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private ArrayList<String> createArtistData() {
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            list.add("가수 " + i);
        }
        return list;
    }

    private ArrayList<Song> createSongData() {
        ArrayList<Song> list = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            String title = "제목 " + i;
            String artist = "아티스트 " + i;
            list.add(new Song(title, artist));
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
        }

        @Override
        public int getCount() {
            return mmFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mmPageTitles[position];
        }
    }
}
