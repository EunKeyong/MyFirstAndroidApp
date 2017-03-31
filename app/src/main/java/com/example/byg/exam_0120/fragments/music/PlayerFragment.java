package com.example.byg.exam_0120.fragments.music;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.byg.exam_0120.R;
import com.example.byg.exam_0120.services.MusicService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Locale;

/**
 * Created by byg on 2017-03-16.
 */

public class PlayerFragment extends Fragment  {

    private MusicService mService;
    private boolean mBound = false;
    private ImageView mAlbumImageView;
    private TextView mDurationTextView;

    private SeekBar mSeekBar;
    private TextView mCurrentTimeTextView;
    private CountDownTimer mCountDownTimer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.music_player, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mSeekBar = (SeekBar) view.findViewById(R.id.seekBar);
        mDurationTextView = (TextView) view.findViewById(R.id.duration_text);
        mCurrentTimeTextView = (TextView) view.findViewById(R.id.currenttime_text);
        mAlbumImageView = (ImageView) view.findViewById(R.id.album_image);


        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mService.getMediaPlayer().seekTo(seekBar.getProgress());
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

        Intent intent = new Intent(getActivity(), MusicService.class);
        getActivity().bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        if (mBound) {
            getActivity().unbindService(mConnection);
            mBound = false;
        }
    }


    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            MusicService.LocalBinder binder = (MusicService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;

            // UI 갱신
            updateUI(mService.isPlaying());
            //  updateMetaData(mService.getMetaDataRetriever());
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };

    private void updateUI(boolean playing) {
        if (playing) {
            MediaMetadataRetriever retriever = mService.getMetaDataRetriever();
            if (retriever != null) {
//
//            String title = retriever.extractMetadata((MediaMetadataRetriever.METADATA_KEY_TITLE));
//            String artist = retriever.extractMetadata((MediaMetadataRetriever.METADATA_KEY_ARTIST));

                // String duration = retriever.extractMetadata((MediaMetadataRetriever.METADATA_KEY_DURATION));
                int longDuration = mService.getMediaPlayer().getDuration();

                int min = longDuration / 1000 / 60;
                int sec = longDuration / 1000 % 60;

                mDurationTextView.setText(String.format(Locale.KOREA, "%d:%02d", min, sec));

                // 초단위로
                mSeekBar.setMax(longDuration);

                // 오디오 앨범 자켓 이미지
                byte albumImage[] = retriever.getEmbeddedPicture();
                if (null != albumImage) {
                    // Bitmap bitmap = BitmapFactory.decodeByteArray(albumImage, 0, albumImage.length);
                    Glide.with(this).load(albumImage).into(mAlbumImageView);
                } else {
                    // 앨범 이미지 없는 경우 기본이미지 세팅
                    Glide.with(this).load(R.mipmap.ic_launcher).into(mAlbumImageView);
                }

//
//                int duration = mService.getMediaPlayer().getDuration() - mService.getMediaPlayer().getCurrentPosition();
//                // 카운트 다운 시작
//                if (mCountDownTimer != null) {
//                    mCountDownTimer.cancel();
//                    mCountDownTimer = null;
//                }
//                mCountDownTimer = new CountDownTimer(duration, 1000) {
//                    @Override
//                    public void onTick(long millisUntilFinished) {
//                        int currentPosition = mService.getMediaPlayer().getCurrentPosition();
//                        mSeekBar.setProgress(currentPosition);
//                        int min = currentPosition / 1000 / 60;
//                        int sec = currentPosition / 1000 % 60;
//                        mCurrentTimeTextView.setText(String.format(Locale.KOREA, "%d:%02d", min, sec));
//                    }
//
//                    @Override
//                    public void onFinish() {
//                        mCountDownTimer = null;
//                    }
//                }.start();

            }
        }
        updateTimer(playing);
    }

    @Subscribe
    public void updateTimer(boolean isPlaying) {
        if (!isPlaying) {
            if (mCountDownTimer != null) {
                mCountDownTimer.cancel();
            }
            mCountDownTimer = null;

        } else  {
            int duration = mService.getMediaPlayer().getDuration() - mService.getMediaPlayer().getCurrentPosition();
            // 카운트 다운 시작
            if (mCountDownTimer != null) {
                mCountDownTimer.cancel();
                mCountDownTimer = null;
            }
            mCountDownTimer = new CountDownTimer(duration, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    int currentPosition = mService.getMediaPlayer().getCurrentPosition();
                    mSeekBar.setProgress(currentPosition);
                    int min = currentPosition / 1000 / 60;
                    int sec = currentPosition / 1000 % 60;
                    mCurrentTimeTextView.setText(String.format(Locale.KOREA, "%d:%02d", min, sec));
                }

                @Override
                public void onFinish() {
                    mCountDownTimer = null;
                }
            }.start();
        }
    }
}
