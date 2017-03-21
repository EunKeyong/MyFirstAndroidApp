package com.example.byg.exam_0120.services;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

/**
 * Created by byg on 2017-03-16.
 */

public class MusicService extends Service {
    public static String ACTION_PLAY = "play";
    public static String ACTION_RESUME = "resume";
    // public static String ACTION_PAUSE = "pause";

    private MediaPlayer mMediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
//        mMediaPlayer = new MediaPlayer();
//        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        EventBus.getDefault().register(this);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();
        if (ACTION_PLAY.equals(action)) {
            // 플레이중이면 중지
            playMusic((Uri) intent.getParcelableExtra("uri"));

        } else if (ACTION_RESUME.equals(action)) {
            clickResumePlayButton();
        }
        return START_STICKY;
    }


    public void playMusic(Uri uri) {
        try {
            if (mMediaPlayer == null) {

                mMediaPlayer = new MediaPlayer();
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            }
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
                mMediaPlayer.release();
                mMediaPlayer = new MediaPlayer();
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            }

            // 음악이재생중이면 그음악 중지시키고 재생성
//            mMediaPlayer.stop();
//            mMediaPlayer.release();
//            mMediaPlayer = new MediaPlayer();
//            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

            mMediaPlayer.setDataSource(this, uri);
            mMediaPlayer.prepareAsync();
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();

                    /**
                     * {@link com.example.byg.exam_0120.fragments.music.MusicControllerFragment#updatePlayButton(Boolean)}
                     */
                    EventBus.getDefault().post(isPlaying());
                }
            });

        } catch (IOException e)

        {
            e.printStackTrace();
        }

    }

    public void clickResumePlayButton() {
        if (isPlaying()) {
            mMediaPlayer.pause();
        } else {
            mMediaPlayer.start();
        }

        /**
         * {@link com.example.byg.exam_0120.fragments.music.MusicControllerFragment#updatePlayButton(Boolean)}
         */
        EventBus.getDefault().post(isPlaying());
    }

    public boolean isPlaying() {
        if (mMediaPlayer != null) {

            return mMediaPlayer.isPlaying();
        }
        return false;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
