package com.example.prithwi.mplay.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.prithwi.mplay.R;
import com.example.prithwi.mplay.adapters.MainActivityAdapter;
import com.example.prithwi.mplay.utils.Config;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    Button searchView;
    TextView tvTitle;
    MainActivityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        android.support.v7.app.ActionBar bar = getSupportActionBar();
//        bar.setBackgroundDrawable(new ColorDrawable(Color.rgb(89,96,92)));

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        this.tvTitle=(TextView)findViewById(R.id.tv_title);
        this.searchView=(Button) findViewById(R.id.bt_searchview);
        Config.OpenSans_Regular = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        Config.OpenSans_Light = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");

        Config.OpenSans_Bold = Typeface.createFromAsset(getAssets(), "OpenSans-Semibold.ttf");


        tabLayout.addTab(tabLayout.newTab().setText("Songs"));
        tabLayout.addTab(tabLayout.newTab().setText("Playlist"));
        tabLayout.addTab(tabLayout.newTab().setText("Artist"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //tabLayout.setTabTextColors(Color.DKGRAY);


        this. adapter = new MainActivityAdapter(getSupportFragmentManager());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

       // final android.widget.Filter filter = inviteAndManageAdapter.getFilter();
        this.searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loadSearchScreen();
            }
        });


    }

      private void loadSearchScreen(){
            Intent i=new Intent(MainActivity.this, SearchActivity.class);
             startActivity(i);
     }
}
