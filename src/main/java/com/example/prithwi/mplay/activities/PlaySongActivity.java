package com.example.prithwi.mplay.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.preference.TwoStatePreference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prithwi.mplay.R;
import com.example.prithwi.mplay.models.Song;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import wseemann.media.FFmpegMediaMetadataRetriever;

public class PlaySongActivity extends AppCompatActivity implements View.OnClickListener,SeekBar.OnSeekBarChangeListener {
    Song songObj;
    public  MediaPlayer mp ;
    SeekBar seekBar;
    Button btPlay,btPrevious,btPause,btNext,btRepeat,btRepeatOne,btBack;
    TextView tvSongInfo,tvSinger,tvSongStart,tvSongEnd;
    boolean isPlayBtEnable=false,isRepeat=false,isPlaying=false;
    RelativeLayout rel;
    int progressValue,length;
    int songSerialNumber;
    private List<Song> songList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);
        songObj = getIntent().getExtras().getParcelable("songDetails");
        this.songList=getIntent().getParcelableArrayListExtra("allSongs");
        this.songSerialNumber=getIntent().getIntExtra("songSerialNumber",0);
        if(mp!=null){
            mp.stop();
            mp.release();
            mp=null;
        }
        init();
        updateUi();



    }

    private void updateUi(){
//        FFmpegMediaMetadataRetriever retriever = new FFmpegMediaMetadataRetriever();
//        retriever.setDataSource(String.valueOf(Uri.parse(songObj.getData())));
//        byte [] data = retriever.getEmbeddedPicture();
//
//        if(data!=null&&data.length>0){
//            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//
//
//            BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
//            rel.setBackground(bitmapDrawable);
//
//            retriever.release();
//        }
        this.tvSongInfo.setText(songObj.getTitle());
        this.tvSinger.setText(songObj.getArtist());

        setEndTimeDuration();
    }

    private void setEndTimeDuration(){

        String duration = songObj.getDuration();
        String out = "";
        long dur = Long.parseLong(duration);
        String seconds = String.valueOf((dur % 60000) / 1000);
        String minutes = String.valueOf(dur / 60000);
        out = minutes + ":" + seconds;
        if (seconds.length() == 1) {
            tvSongEnd.setText("0" + minutes + ":0" + seconds);
        }else {
            tvSongEnd.setText("0" + minutes + ":" + seconds);
        }

    }

    private Handler mSeekbarUpdateHandler = new Handler();
    private Runnable mUpdateSeekbar = new Runnable() {
        @Override
        public void run() {
            if(mp!=null){
            seekBar.setProgress(mp.getCurrentPosition());
            mSeekbarUpdateHandler.postDelayed(this, 50);

                String out = "";
                int dur = mp.getCurrentPosition();
                String seconds = String.valueOf((dur % 60000) / 1000);
                String minutes = String.valueOf(dur / 60000);
                out = minutes + ":" + seconds;
                if (seconds.length() == 1) {
                    tvSongStart.setText("0" + minutes + ":0" + seconds);
                }else {
                    tvSongStart.setText("0" + minutes + ":" + seconds);
                }
            }
        }
    };

    private void init(){
        this.btNext=(Button)findViewById(R.id.bt_next);
        this.btNext.setOnClickListener(this);
        this.btNext.setBackground(getResources().getDrawable(R.drawable.ic_skip_next));

        this.btBack=(Button)findViewById(R.id.bt_back_play_song);
        this.btBack.setOnClickListener(this);

        this.btPlay=(Button)findViewById(R.id.bt_play);
        this.btPlay.setOnClickListener(this);
        this.btPlay.setBackground(getResources().getDrawable(R.drawable.ic_play));


        this.btPause=(Button)findViewById(R.id.bt_pause);
        this.btPause.setOnClickListener(this);
        this.btPause.setBackground(getResources().getDrawable(R.drawable.ic_pause));

        this.btRepeat=(Button)findViewById(R.id.bt_repeat);
        this.btRepeat.setOnClickListener(this);



        this.btPrevious=(Button)findViewById(R.id.bt_privious);
        this.btPrevious.setOnClickListener(this);
        this.btPrevious.setBackground(getResources().getDrawable(R.drawable.ic_skip_previous));

        this.tvSongInfo=(TextView) findViewById(R.id.tv_song_info);
        this.tvSinger=(TextView) findViewById(R.id.tv_singer);

        this.rel=(RelativeLayout)findViewById(R.id.rel);

        this.seekBar=(SeekBar)findViewById(R.id.seekBar);

        this.seekBar.setMax(Integer.parseInt(songObj.getDuration()));
        seekBar.getProgressDrawable().setColorFilter(Color.rgb(58,159,229), PorterDuff.Mode.SRC_IN);
        seekBar.getThumb().setColorFilter(Color.rgb(58,159,229), PorterDuff.Mode.SRC_IN);


        this.tvSongStart=(TextView)findViewById(R.id.tv_song_start);
        this.tvSongEnd=(TextView)findViewById(R.id.tv_song_end);
        this.seekBar.setOnSeekBarChangeListener(this);

    }

private void playNextSong(){
    if(mp!=null){
        mp.stop();
        mp.release();
        mp=null;
        updateUi();
        mp=MediaPlayer.create(getApplicationContext(),Uri.parse(songObj.getData()));
        startMediaPlayer();


    }
    else{
        updateUi();
//        mp=MediaPlayer.create(getApplicationContext(),Uri.parse(songObj.getData()));
//        mp.start();
    }

}

private void playPreviousSong(){
    if(mp!=null){
        mp.stop();
        mp.release();
        mp=null;
        updateUi();
        mp=MediaPlayer.create(getApplicationContext(),Uri.parse(songObj.getData()));
        startMediaPlayer();
    }
    else{
        updateUi();
//        mp=MediaPlayer.create(getApplicationContext(),Uri.parse(songObj.getData()));
//        mp.start();
    }
}

private void startMediaPlayer(){
    if(isPlaying && isPlayBtEnable){
         mp.start();
    }
}

    @Override
    public void onClick(View v) {
     switch (v.getId()){
         case R.id.bt_play:
             if(!isPlayBtEnable){
                 isPlayBtEnable=true;
             this.btPlay.setBackground(getResources().getDrawable(R.drawable.ic_pause));
             this.isPlaying=true;
             mp=MediaPlayer.create(getApplicationContext(),Uri.parse(songObj.getData()));

                 mp.seekTo(length);
                 startMediaPlayer();


                 mSeekbarUpdateHandler.postDelayed(mUpdateSeekbar, 0);

             }
             else if(isPlayBtEnable){
            isPlayBtEnable=false;
            isPlaying=false;
           this.btPlay.setBackground(getResources().getDrawable(R.drawable.ic_play));
                 mp.pause();
                 length=mp.getCurrentPosition();
                 mSeekbarUpdateHandler.removeCallbacks(mUpdateSeekbar);

             }
             break;

         case R.id.bt_privious:
             if(songSerialNumber>0){

                 this.songObj=songList.get(this.songSerialNumber-1);
                 this.songSerialNumber=this.songSerialNumber-1;
                 playPreviousSong();
             }
             else{
                 Toast.makeText(PlaySongActivity.this,"No nePrevious available",Toast.LENGTH_SHORT).show();
                 this.songObj=songList.get(songList.size()-1);
                 this.songSerialNumber=songList.size()-1;
                 playNextSong();
             }
             break;

         case R.id.bt_next:
             if(songList.size()-1>songSerialNumber){

            this.songObj=songList.get(this.songSerialNumber+1);
            this.songSerialNumber=this.songSerialNumber+1;
             playNextSong();
             }
             else{
                 Toast.makeText(PlaySongActivity.this,"No next available",Toast.LENGTH_SHORT).show();
                 this.songObj=songList.get(0);
                 this.songSerialNumber=0;
                 playNextSong();
             }
             break;

         case R.id.bt_repeat:
             if(!isRepeat){
                 isRepeat=true;
                 this.btRepeat.setBackground(getResources().getDrawable(R.drawable.ic_repeat_one_white_24dp));
                 //SongRepeatOne();
                 mp.setLooping(true);
                 startMediaPlayer();
             }
             else if(isRepeat){
                 isRepeat=false;
                 this.btRepeat.setBackground(getResources().getDrawable(R.drawable.ic_repeat_white_24dp));
                 mp.setLooping(false);

             }
             break;

         case R.id.bt_back_play_song:
             finish();
             break;
     }


    }

    private void SongPlayAll(){

    if(mp!=null){

    }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        progressValue=progress;
        if (fromUser){
            mp.seekTo(progress);}
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {


    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
       // Toast.makeText(PlaySongActivity.this,"value:"+progressValue,Toast.LENGTH_SHORT).show();
        mp.seekTo(progressValue);
        startMediaPlayer();
    }
}

