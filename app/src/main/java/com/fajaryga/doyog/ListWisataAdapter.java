package com.fajaryga.doyog;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class ListWisataAdapter extends ArrayAdapter<ModelList> {
    Context context;
    String[] Version;
    String rating[];
    int image;
    int layoutResourceId;
    LayoutInflater inflater;
    ArrayList<ModelList> arrayList;

    public ListWisataAdapter(Context context, int layoutResourceId, ArrayList<ModelList> arrayList) {
        super(context,layoutResourceId,arrayList);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.arrayList = arrayList;
    }

    static class ViewHolder {
        ImageView icon;
        TextView title;
        TextView rating;
        TextView genre;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if (row == null){
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId,parent,false);

            holder = new ViewHolder();
            holder.icon = (ImageView) row.findViewById(R.id.list_img);
            holder.title = (TextView)row.findViewById(R.id.list_text);
            holder.rating = (TextView)row.findViewById(R.id.list_rating);
            holder.genre = (TextView)row.findViewById(R.id.list_genre);
            row.setTag(holder);
        }else {
            holder = (ViewHolder)row.getTag();
        }
        ModelList model = arrayList.get(position);
        holder.icon.setImageResource(model.list_img);
        holder.title.setText(model.list_text);
        holder.rating.setText(model.list_rating);
        holder.genre.setText(model.kategori);

        return row;
    }
}
