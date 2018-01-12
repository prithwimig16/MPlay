package com.example.prithwi.mplay.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.prithwi.mplay.R;
import com.example.prithwi.mplay.fragments.ArtistFragment;
import com.example.prithwi.mplay.fragments.PlayListFragment;
import com.example.prithwi.mplay.fragments.SongsFragment;

/**
 * Created by Prithwi on 31/12/17.
 */

public class MainActivityAdapter extends FragmentPagerAdapter {
    private Context mContext;
    SongsFragment songsFragment;
    PlayListFragment playListFragment;
    ArtistFragment artistFragment;

    public MainActivityAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                this.songsFragment=new SongsFragment();
                return this.songsFragment;
            case 1:
                this.playListFragment=new PlayListFragment();
                return this.playListFragment;
            case 2:
                this.artistFragment=new ArtistFragment();
                return this.artistFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }


}
