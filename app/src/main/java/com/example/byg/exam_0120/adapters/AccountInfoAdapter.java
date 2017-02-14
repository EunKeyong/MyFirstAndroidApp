package com.example.byg.exam_0120.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.byg.exam_0120.R;
import com.example.byg.exam_0120.models.AccountInfo;

import java.util.List;

/**
 * Created by byg on 2017-02-08.
 */

public class AccountInfoAdapter extends BaseAdapter {

    //private Context mContext;
    private final List<AccountInfo> mData;

    public AccountInfoAdapter(List<AccountInfo> data) {
       // mContext = context;
        mData = data;
    }

    // 아이템 갯수
    @Override
    public int getCount() {
        return mData.size();
    }

    // position 번째 아이템
    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    // position 번재 아이템 id
    @Override
    public long getItemId(int position) {
        return position;
    }

    // position 번째의 layout
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        ViewHolder viewHolder;
        // convertView : 재사용 되는 뷰
        if (convertView == null) {
            // 뷰를 새로 만들 때
            viewHolder = new ViewHolder();
            // 없으니까 가져옴
            convertView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_account_info,
                    viewGroup, false);

            //레이아웃 들고오기
            TextView accountTitleTextView = (TextView) convertView.findViewById(R.id.accoun_title_text);
            TextView balanceTextView = (TextView) convertView.findViewById(R.id.balance_text);

            // 뷰 홀더에 넣는다.
            viewHolder.accountTitleTextView = accountTitleTextView;
            viewHolder.balanceTextView = balanceTextView;

            convertView.setTag(viewHolder);
            // 재사용 할 때

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // 데이터
        AccountInfo accountInfo = mData.get(position);

        //화면에 뿌리기
        viewHolder.accountTitleTextView.setText(accountInfo.getId());
        viewHolder.balanceTextView.setText("" + accountInfo.getBalance());

//        // 홀수줄은빨간색
//        if (position % 2 == 1) {
//            convertView.setBackgroundColor(Color.RED);
//        } else {
//            convertView.setBackgroundColor(Color.WHITE);
//        }
//
//        // 선택된 포지션과 현재 위치가 같으면
//        if (mSelectedPosition == position) {
//            convertView.setBackgroundColor(Color.YELLOW);
//        }
//
        return convertView;
    }
//
//    // -1이면 선택된게 없다.
//    private int mSelectedPosition = -1;
//
//    public void setSelect(int position) {
//        mSelectedPosition = position;
//    }
//    // findViewById로 가져온 View 들을 보관

    private static class ViewHolder {
        TextView accountTitleTextView;
        TextView balanceTextView;
    }
}