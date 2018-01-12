package com.example.prithwi.mplay.utils;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Prithwi on 13/01/18.
 */

public class MPayService extends Service {
    MediaPlayer mMediaPlayer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        if (mMediaPlayer != null)
        {
            mMediaPlayer.release();
        }
    }
}
