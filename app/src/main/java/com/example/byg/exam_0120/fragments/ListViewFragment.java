package com.example.byg.exam_0120.fragments;

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

import java.io.Serializable;
import java.util.List;

/**
 * Created by byg on 2017-02-15.
 */

public class ListViewFragment extends Fragment {

    private ListView mListView;
    private ListViewAdapter mAdapter;
    private List<String> mData;

    public ListViewFragment() {
    }

    public static ListViewFragment newInstance(List<String> data) {

        ListViewFragment listViewFragment = new ListViewFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("data", (Serializable) data);
        //bundle.putStringArrayList("data", (ArrayList<String>) data);
        listViewFragment.setArguments(bundle);

        return listViewFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_list_view, container, false);
//        Bundle bundle = getArguments();
//        mData = bundle.getStringArrayList("data");
//
//        mListView = (ListView) view.findViewById(R.id.list_view_frag);
//        mAdapter = new ListViewAdapter(mData);
//
//        mListView.setAdapter(mAdapter);
//        return view;

        return inflater.inflate(R.layout.fragment_list_view, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mListView = (ListView) view.findViewById(R.id.list_view_frag);
        Bundle bundle = getArguments();
        mData = (List<String>) bundle.getSerializable("data");
        mAdapter = new ListViewAdapter(mData);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private static class ListViewAdapter extends BaseAdapter {

        private List<String> mmData;

        public ListViewAdapter(List<String> mData) {
            this.mmData = mData;
        }

        @Override
        public int getCount() {
            return mmData.size();
        }

        @Override
        public Object getItem(int i) {
            return mmData.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            ViewHolder viewHolder;

            if (view == null) {

                viewHolder = new ViewHolder();

                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_frag_list_view,
                        viewGroup, false);

                viewHolder.mTextView = (TextView) view.findViewById(R.id.text_view);

                view.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            String data = mmData.get(i);
            viewHolder.mTextView.setText(data);

            return view;
        }
    }

    private static class ViewHolder {
        TextView mTextView;
    }
}
