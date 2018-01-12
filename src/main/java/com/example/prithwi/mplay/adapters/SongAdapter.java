package com.example.prithwi.mplay.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prithwi.mplay.R;
import com.example.prithwi.mplay.models.Song;
import com.example.prithwi.mplay.utils.Config;

import java.util.ArrayList;

//import wseemann.media.FFmpegMediaMetadataRetriever;

/**
 * Created by Prithwi on 02/01/18.
 */

public class SongAdapter extends ArrayAdapter {
    int resource;
    Context context;
    LayoutInflater layoutInflater;

    public ArrayList<Song> songListArray;
    public SongAdapter(@NonNull Context context, int resource) {
        super(context, resource);

        this.resource = resource;
        this.context =context;
        this.songListArray = new ArrayList<Song>();
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return songListArray.size();
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = this.layoutInflater.inflate(resource, null);


            holder.mp3Img = (ImageView) convertView.findViewById(R.id.img_song);
            holder.name = (TextView) convertView.findViewById(R.id.tv_title);
            holder.artistName=(TextView) convertView.findViewById(R.id.tv_artist_name);


            convertView.setTag(holder);
        }else {
            holder = (SongAdapter.ViewHolder) convertView.getTag();

            System.gc();

        }
        if(this.songListArray.size()>position) {
            Song mPlaySong = this.songListArray.get(position);
            if(mPlaySong.isMusic()&&!mPlaySong.isAlarm()){
              String songName=mPlaySong.getTitle();
              if(songName.length()>26){
                  holder.name.setText(songName.substring(0,26)+"...");
              }else{
                  holder.name.setText(mPlaySong.getTitle());
              }
             holder.name.setTypeface(Config.OpenSans_Bold);


            holder.artistName.setText(mPlaySong.getArtist());
                holder.artistName.setTypeface(Config.OpenSans_Regular);
            String s=mPlaySong.getData();
//
//                FFmpegMediaMetadataRetriever retriever = new FFmpegMediaMetadataRetriever();
//                retriever.setDataSource(String.valueOf(Uri.parse(mPlaySong.getData())));
//                byte [] data = retriever.getEmbeddedPicture();
//                if(data!=null){
//
//// convert the byte array to a bitmap
//                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//// do something with the image ...
//                holder.mp3Img.setImageBitmap(bitmap);
//
//                retriever.release();
//                }

            }
        }


            return convertView;
    }

    static class ViewHolder {
        public ImageView mp3Img;
        public TextView name;
        public TextView artistName;
        public ImageButton edit;
    }


    }
