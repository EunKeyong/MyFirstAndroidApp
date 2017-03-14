package com.example.byg.exam_0120.fragments;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.byg.exam_0120.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GalleryFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the  factory method to
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

        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            // 설명을 보여줄것인가
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                // 사용자 응답을 기다리는 설명을 비동기로 보여주기
                // 권한 체크를 안하면 이 기능을 사용할수 없다고 어필하고
                // 다시 권한요청
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1000);

            } else {

                // No explanation needed, we can request the permission.
                // 권한을 요청

                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        1000);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }else  {
            // 이미 권한이 있을 때
            getPicture();
        }

//        // 사진 정보
//        // 미디어(사진, 동영상, 음악) media db
//        // provider 로 media db 정보를 가져와야 됨
//        Cursor cursor = getActivity().getContentResolver().query(
//                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                null,
//                null,
//                null,
//                null
//        );
//
//        // 사진 뿌릴 어댑터
//        MyCursorAdapter adapter = new MyCursorAdapter(getActivity(), cursor);
//        mGridView.setAdapter(adapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case 1000: {
                // If request is cancelled, the result arrays are empty.
                // 승인받은권한이몇개인지
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 사용자가 수락한경우 처리
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                    // 승인 됨
                    Toast.makeText(getActivity(), "권한 승인됨", Toast.LENGTH_SHORT).show();
                    //        // 사진 정보
                    getPicture();

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    // 다이얼로그 표시(이 권한을 수락하지 않으면 이 기능 사용할 수 없습니다)
                    // 권한을 설정하시려면 설정 > 애플리케이션 > 앱이름 가서 설정하세요
                    // 앱 종료
                    Toast.makeText(getActivity(), "권한 거부됨", Toast.LENGTH_SHORT).show();

                    getActivity().finish();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }

    }

    private void getPicture() {
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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

            Glide.with(context).load(path).into(viewHolder.mimageView);

            // Bitmap bitmap = BitmapFactory.decodeFile()
            // uri 형태로 변환해서 뿌리기
            // viewHolder.mimageView.setImageURI(Uri.parse(path));
        }
    }

    private static class ViewHolder {
        ImageView mimageView;
    }
}
