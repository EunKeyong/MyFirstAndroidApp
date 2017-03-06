package com.example.byg.exam_0120.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.byg.exam_0120.R;
import com.example.byg.exam_0120.models.Memo;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by byg on 2017-03-06.
 */

public class MemoRecyclerAdapter extends RecyclerView.Adapter<MemoRecyclerAdapter.ViewHolder> {

    // eventbus용 클래스
    public static class ItemClickEvent {
        public ItemClickEvent(int position, long id) {
            this.position = position;
            this.id = id;
        }

        public int position;
        public long id;
    }

    private List<Memo> mData;

    public MemoRecyclerAdapter(List<Memo> data) {
        mData = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_memo, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final Memo memo = mData.get(position);

        holder.titleTextView.setText(memo.getTitle());
        holder.contentTextView.setText(memo.getContent());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // MemoActivity#onItemClick
                EventBus.getDefault().post(new ItemClickEvent(position, memo.getId()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void swap(List<Memo> memoList) {
        mData = memoList;
        notifyDataSetChanged();
    }

    public void insert(List<Memo> memoList) {
        mData = memoList;
        notifyItemInserted(0);
    }

    public void update(List<Memo> memoList, int position) {
        mData = memoList;
        notifyItemChanged(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView contentTextView;

        public ViewHolder(View itemView) {

            super(itemView);

            TextView titleTextView = (TextView) itemView.findViewById(R.id.title_text);
            TextView contentTextView = (TextView) itemView.findViewById(R.id.content_text);

            // 뷰 홀더에 넣는다.
            this.titleTextView = titleTextView;
            this.contentTextView = contentTextView;
        }
    }
}
