package com.example.prithwi.mplay.fragments;


import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.prithwi.mplay.R;
import com.example.prithwi.mplay.adapters.PlayListAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayListFragment extends ListFragment implements View.OnClickListener, AdapterView.OnItemClickListener {
Button btCreatePlaylist;
TextView tvCreatePlaylist;
PlayListAdapter adapter;
ArrayList<String> playListNameArray;

    public PlayListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_play_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        //getSongs();


    }

    private void init(){
        this.btCreatePlaylist=(Button)getActivity().findViewById(R.id.bt_create_playlist);
        this.btCreatePlaylist.setOnClickListener(this);

        this.tvCreatePlaylist=(TextView)getActivity().findViewById(R.id.tv_create_playlist);
        this.tvCreatePlaylist.setOnClickListener(this);

        this.adapter = new PlayListAdapter(getActivity(), R.layout.single_view_playlist);

        this.setListAdapter(this.adapter);
        this.getListView().setOnItemClickListener(this);
        this.getListView().setDivider(null);
        this.getListView().setDividerHeight(0);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_create_playlist:
                createPlayList();
                break;

            case R.id.tv_create_playlist:
                createPlayList();
                break;
        }


    }


    private void createPlayList(){
        final AlertDialog alertDialog = new AlertDialog.Builder(this.getContext()).create();
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        final EditText input = new EditText(this.getActivity());
        input.setHeight(100);
        input.setWidth(340);

        input.setGravity(Gravity.CENTER);
        input.setImeOptions(EditorInfo.IME_ACTION_DONE);
        alertDialog.setView(input);
        alertDialog.setTitle("MPlay");
        alertDialog.setMessage("Give a name");


        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String s=input.getText().toString();
                playListNameArray=new ArrayList<>();
                adapter.playListNameArray.add(s);
                adapter.notifyDataSetChanged();
                if(s.length()>0)
                {
                  dialog.dismiss();
                }


            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                 dialog.dismiss();




            }
        });
        alertDialog.show();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}

