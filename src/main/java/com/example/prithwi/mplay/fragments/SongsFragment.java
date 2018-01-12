package com.example.prithwi.mplay.fragments;


import android.content.Intent;
import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.prithwi.mplay.R;
import com.example.prithwi.mplay.activities.PlaySongActivity;
import com.example.prithwi.mplay.adapters.SongAdapter;
import com.example.prithwi.mplay.models.Song;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SongsFragment extends ListFragment implements AdapterView.OnItemClickListener {

    SongAdapter adapter;
    Song audioModel;


    private List<Song> songList = new ArrayList<>();

    public SongsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_songs, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
         init();
        getSongs();


    }

    private void init(){

        this.adapter = new SongAdapter(getActivity(), R.layout.single_song_view);

        this.setListAdapter(this.adapter);
        this.getListView().setOnItemClickListener(this);
        this.getListView().setDivider(null);
        this.getListView().setDividerHeight(0);

    }

    private void getSongs(){

        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projection = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DATA,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.ALBUM_ID,
                MediaStore.Audio.Media.IS_ALARM,
                MediaStore.Audio.Media.IS_MUSIC,
               // MediaStore.Audio.Albums.ALBUM_ART
        };
        Cursor c = getActivity().getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection, null, null, null);
        boolean isAlarm=false,isMusic=false;
        if (c != null) {
            while (c.moveToNext()) {
//                MediaMetadataRetriever metaRetriver;
//                metaRetriver = new MediaMetadataRetriever();
//                metaRetriver.setDataSource(Environment.getExternalStorageDirectory().getPath());
//                byte[] art= metaRetriver.getEmbeddedPicture();
                this. audioModel = new Song();

                String id = c.getString(0);
                String artist = c.getString(1);
                String title = c.getString(2);
                String data = c.getString(3);
                String dispalyName = c.getString(4);
                String duration = c.getString(5);
                String albumId = c.getString(6);
                String strAlarm=c.getString(7);
                String strMusic=c.getString(8);
               // String strImg=c.getString(9);
                if(strAlarm.matches("0")){
                    isAlarm=false;
                }
                else if(strAlarm.matches("1")){
                    isAlarm=true;
                }
                if(strMusic.matches("0")){
                    isMusic=false;
                }
                else if(strMusic.matches("1")){
                    isMusic=true;
                }
                audioModel.setId(id);
                audioModel.setArtist(artist);
                audioModel.setTitle(title);
                audioModel.setData(data);
                audioModel.setDispalyName(dispalyName);
                //audioModel.setSongImage(strImg);
                audioModel.setDuration(duration);
                audioModel.setAlbumId(albumId);
                audioModel.setAlarm(isAlarm);
                audioModel.setMusic(isMusic);


                if(audioModel.isMusic()&&!audioModel.isAlarm()){
                    this.adapter.songListArray.add(audioModel);
                }
            }
            c.close();
        }

        this.songList=this.adapter.songListArray;


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position < this.adapter.songListArray.size()){
            Song songObj=this.adapter.songListArray.get(position);

            Intent intent = new Intent(this.getActivity(), PlaySongActivity.class);
            intent.putExtra("songDetails", songObj);
            intent.putExtra("songSerialNumber",position);
            intent.putParcelableArrayListExtra("allSongs", (ArrayList<? extends Parcelable>) this.songList);
            startActivity(intent);
        }
    }
}
