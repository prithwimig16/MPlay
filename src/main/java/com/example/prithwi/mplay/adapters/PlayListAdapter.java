package com.example.prithwi.mplay.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.prithwi.mplay.R;

import java.util.ArrayList;

/**
 * Created by Prithwi on 10/01/18.
 */

public class PlayListAdapter extends ArrayAdapter {

    int resource;
    Context context;
    LayoutInflater layoutInflater;
    public ArrayList<String> playListNameArray;

    public PlayListAdapter(@NonNull Context context, int resource) {
        super(context, resource);

        this.resource = resource;
        this.context =context;
        this.playListNameArray=new ArrayList<>();
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return this.playListNameArray.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if (convertView == null) {
            convertView = this.layoutInflater.inflate(resource, null);


            holder.tvFolderName = (TextView) convertView.findViewById(R.id.tv_create_folder);
            holder.btCreateFolder=(Button) convertView.findViewById(R.id.bt_create_folder);


            convertView.setTag(holder);
        }else {
            holder = (PlayListAdapter.ViewHolder) convertView.getTag();

            System.gc();

        }

        holder.tvFolderName.setText(this.playListNameArray.get(position));

        return convertView;
    }

    static class ViewHolder {

        public Button btCreateFolder;
        public TextView tvFolderName;

    }
}
