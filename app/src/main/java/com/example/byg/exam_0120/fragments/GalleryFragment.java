package com.example.byg.exam_0120.fragments;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.byg.exam_0120.R;
import com.example.byg.exam_0120.fragments.MusicPlayer.MusicPlayerListViewFragment;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GalleryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GalleryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GalleryFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private GridView mGridView;

    public GalleryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // 리스너 연결
        // acitivity 가 listener 구현하고있는지
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Bundle 받기
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 뷰가져오기
        return inflater.inflate(R.layout.fragment_gallery, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // 뷰 가져온 이후에 할것
        // 뷰
        mGridView = (GridView) view.findViewById(R.id.grid_view);

        // 사진 정보
        // 미디어(사진, 동영상, 음악) media db
        // provider 로 media db 정보를 가져와야 됨
        Cursor cursor = getActivity().getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                null
        );

        // 사진 뿌릴 어댑터
        MyCursorAdapter adapter = new MyCursorAdapter(getActivity(), cursor);
        mGridView.setAdapter(adapter);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private static class MyCursorAdapter extends CursorAdapter {

        public MyCursorAdapter(Context context, Cursor c) {
            super(context, c, false);
        }

        // baseadapter 에서 convertview 가 null인 경우 처리하는 부분
        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {

            View convertView = LayoutInflater.from(context).inflate(R.layout.item_gallery, parent, false);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.mimageView = (ImageView) convertView.findViewById(R.id.image_view);
            convertView.setTag(viewHolder);

            return convertView;
        }

        // 데이터 세팅하는 부분
        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            ViewHolder viewHolder = (ViewHolder) view.getTag();
            String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));

            // uri 형태로 변환해서 뿌리기
            viewHolder.mimageView.setImageURI(Uri.parse(path));
        }
    }
    private static class ViewHolder {
        ImageView mimageView;
    }
}
