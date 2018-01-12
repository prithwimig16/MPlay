package com.example.prithwi.mplay.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.prithwi.mplay.R;
import com.example.prithwi.mplay.adapters.SongAdapter;
import com.example.prithwi.mplay.fragments.SongsFragment;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    SongAdapter adapter;
    ListView listView;
    SongsFragment songsFragment;
    Button btBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        init();
        this.songsFragment=new SongsFragment();
    }
    private void init(){
//        this.listView=(ListView)findViewById(R.id.list_view);
//        this.adapter=new SongAdapter(this,R.layout.single_song_view);
//        this.listView.setAdapter(adapter);
        this.btBack=(Button)findViewById(R.id.bt_back);
        this.btBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_back:
                finish();
                break;
        }
    }
}
