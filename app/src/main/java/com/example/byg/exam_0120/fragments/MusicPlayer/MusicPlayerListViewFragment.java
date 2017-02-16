package com.example.byg.exam_0120.fragments.MusicPlayer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.byg.exam_0120.R;
import com.example.byg.exam_0120.models.Song;

import java.util.ArrayList;

/**
 * Created by byg on 2017-02-16.
 */

public class MusicPlayerListViewFragment extends Fragment {

    private MusicListViewAdapter mAdapter;
    private ListView mListView;
    private ArrayList<Song> mData;

    public static MusicPlayerListViewFragment newInstance(ArrayList<Song> data) {

        MusicPlayerListViewFragment listViewFragment = new MusicPlayerListViewFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", data);
        listViewFragment.setArguments(bundle);
        return listViewFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mListView = (ListView) view.findViewById(R.id.list_view_frag);
        Bundle bundle = getArguments();
        mData = (ArrayList<Song>) bundle.getSerializable("data");
        mAdapter = new MusicListViewAdapter(mData);
        mListView.setAdapter(mAdapter);
    }

    private static class MusicListViewAdapter extends BaseAdapter {

        private ArrayList<Song> mmData;

        public MusicListViewAdapter(ArrayList<Song> mmData) {
            this.mmData = mmData;
        }

        @Override
        public int getCount() {
            return mmData.size();
        }

        @Override
        public Object getItem(int position) {
            return mmData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;

            if (convertView == null) {
                viewHolder = new ViewHolder();

                convertView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_music_list_view
                                , parent, false);

                //레이아웃 들고와서 뷰홀더에 넣기
                viewHolder.mTitleTextView = (TextView) convertView.findViewById(R.id.title_text);
                viewHolder.mArtistTextView = (TextView) convertView.findViewById(R.id.artist_text);

                convertView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            Song song = mmData.get(position);

            viewHolder.mTitleTextView.setText(song.getTitle());
            viewHolder.mArtistTextView.setText(song.getArtist());

            return convertView;
        }
    }

    public static class ViewHolder {
        TextView mTitleTextView;
        TextView mArtistTextView;
    }
}
