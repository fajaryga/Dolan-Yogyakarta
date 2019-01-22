package com.fajaryga.doyog.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.fajaryga.doyog.ListWisataAdapter;
import com.fajaryga.doyog.ModelList;
import com.fajaryga.doyog.R;
import com.fajaryga.doyog.WisataAdapter;

import java.util.ArrayList;

public class GundulActivity extends AppCompatActivity {
    GridView grid;
    ListView list;
    ArrayList<ModelList> arrayList = new ArrayList<>();
    WisataAdapter adapter;
    ListWisataAdapter listAdapter;
    String[] namaWisata;
    String[] rating;
    int[] image;
    Menu a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gundul);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Dolan Yogyakarta");
        getSupportActionBar().setSubtitle("Destinasi Wisata di Gunungkidul");
        namaWisata = new String[]{
                "Gunung Api Purba",
                "Goa Pindul",
                "Pantai Krakal",
                "Bukit Bintang",
                "Air Terjun Sri Gethuk",
                "Pantai Pok Tunggal"
        };
        rating = new String[]{
                "4",
                "4.2",
                "3.8",
                "5",
                "5",
                "4.1"
        };
        image = new int[]{
                R.drawable.gunung,
                R.drawable.goapindul,
                R.drawable.krakal,
                R.drawable.bintang,
                R.drawable.terjun,
                R.drawable.pok

        };

        String[] genre = new String[]{
                "Kabupaten Gunungkidul",
                "Kabupaten Gunungkidul",
                "Kabupaten Gunungkidul",
                "Kabupaten Gunungkidul",
                "Kabupaten Gunungkidul",
                "Kabupaten Gunungkidul"
        };

        list = (ListView) findViewById(R.id.list);

        for (int a = 0; a<6; a++){
            arrayList.add(new ModelList(image[a], namaWisata[a],rating[a], genre[a]));
        }

        listAdapter = new ListWisataAdapter(this,R.layout.list_adapter, arrayList);
        list.setAdapter(listAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent mov = new Intent(GundulActivity.this,WgundulActivity.class);
                mov.putExtra("position",i);
                startActivity(mov);
            }
        });



        grid = (GridView) findViewById(R.id.gv_wisata);
        adapter = new WisataAdapter(GundulActivity.this, namaWisata, image,rating,genre);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent mov = new Intent(GundulActivity.this,WgundulActivity.class);
                mov.putExtra("position",position);
                startActivity(mov);
            }
        });

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            grid.setNumColumns(2);
        }else if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            grid.setNumColumns(3);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.change_wisata, menu);
        this.a = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.list2) {
            a.findItem(R.id.grid2).setVisible(true);
            a.findItem(R.id.list2).setVisible(false);
            grid.setVisibility(View.GONE);
            list.setVisibility(View.VISIBLE);

        } else if (id == R.id.grid2) {
            a.findItem(R.id.grid2).setVisible(false);
            a.findItem(R.id.list2).setVisible(true);
            grid.setVisibility(View.VISIBLE);
            list.setVisibility(View.GONE);
        }
        return super.onOptionsItemSelected(item);
    }


}
