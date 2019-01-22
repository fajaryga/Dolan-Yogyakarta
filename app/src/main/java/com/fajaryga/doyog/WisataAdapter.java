package com.fajaryga.doyog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class WisataAdapter extends BaseAdapter {
    Context context;
    String[] namaWisata;
    String rating[];
    String[] kategori;
    int[] image;
    LayoutInflater inflater;
    public WisataAdapter(Context context, String[] namaWisata, int[] image, String[]rating, String[] kategori) {
        this.context = context;
        this.namaWisata = namaWisata;
        this.image = image;
        this.rating = rating;
        this.kategori = kategori;
    }

    static class ViewHolder {
        ImageView icon;
        TextView title;
        TextView rating;
        TextView genre;
    }

    @Override
    public int getCount() {
        return namaWisata.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

        if (row == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.grid_item_wisata,parent,false);

            holder = new ViewHolder();
            holder.icon = (ImageView) row.findViewById(R.id.grid_img);
            holder.title = (TextView) row.findViewById(R.id.grid_text);
            holder.rating = (TextView) row.findViewById(R.id.grid_rating);
            holder.genre = (TextView) row.findViewById(R.id.grid_genre);
            row.setTag(holder);

        }else {

            holder = (ViewHolder)row.getTag();

        }


        holder.title.setText(namaWisata[position]);
        holder.rating.setText(rating[position]);
        holder.genre.setText(kategori[position]);
        holder.icon.setImageResource(image[position]);
        return row;
    }
}

