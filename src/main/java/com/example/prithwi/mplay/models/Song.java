/*
 * Copyright (C) 2015 Naman Dwivedi
 *
 * Licensed under the GNU General Public License v3
 *
 * This is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 */

package com.example.prithwi.mplay.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Song implements Parcelable {

    public Song() {

    }

    public Song(Parcel in) {
        Id = in.readString();
        artist = in.readString();
        title = in.readString();
        data = in.readString();
        dispalyName = in.readString();
        duration = in.readString();
        albumId = in.readString();
        songImage=in.readString();
        isAlarm = in.readByte() != 0;
        isMusic = in.readByte() != 0;
    }

    public static final Creator<Song> CREATOR = new Creator<Song>() {
        @Override
        public Song createFromParcel(Parcel in) {
            return new Song(in);
        }

        @Override
        public Song[] newArray(int size) {
            return new Song[size];
        }
    };



    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDispalyName() {
        return dispalyName;
    }

    public void setDispalyName(String dispalyName) {
        this.dispalyName = dispalyName;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public boolean isAlarm() {
        return isAlarm;
    }

    public void setAlarm(boolean alarm) {
        isAlarm = alarm;
    }
    public boolean isMusic() {
        return isMusic;
    }

    public void setMusic(boolean music) {
        isMusic = music;
    }

    protected   String Id;
    protected   String artist ;
    protected   String title ;
    protected   String data ;
    protected   String dispalyName ;
    protected   String duration;
    protected   String albumId;

    public String getSongImage() {
        return songImage;
    }

    public void setSongImage(String songImage) {
        this.songImage = songImage;
    }

    protected   String songImage;
    protected   boolean isAlarm;

    protected   boolean isMusic;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Id);
        dest.writeString(artist);
        dest.writeString(title);
        dest.writeString(data);
        dest.writeString(dispalyName);
        dest.writeString(duration);
        dest.writeString(albumId);
        dest.writeString(songImage);
        dest.writeByte((byte) (isAlarm ? 1 : 0));
        dest.writeByte((byte) (isMusic ? 1 : 0));
    }
}
