package com.fajaryga.doyog;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fajaryga.doyog.room.AppDatabase;
import com.fajaryga.doyog.room.Wisata;
import com.fajaryga.doyog.util.PreferencesHelper;
import com.fajaryga.doyog.util.RecycleAdapter;


import java.util.ArrayList;
import java.util.List;

import static com.fajaryga.doyog.AppApplication.db;

public class WslmActivity extends AppCompatActivity {
    CollapsingToolbarLayout judul;
    String[] namaWisata;
    String[] rating, kategori, deskripsi;
    TextView namaWisata1, kategori1, deskripsi1;
    int[] image;
    Button btn;

    private Button insertData;
    private EditText etNama;
    private EditText etTesti;
    Wisata wisata;

    RecyclerView myRecyclerview;
    RecycleAdapter recycleAdapter;
    List<Wisata> listWisatas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wslm);

        namaWisata1 = (TextView) findViewById(R.id.namaWisata);
        kategori1 = (TextView) findViewById(R.id.kategori);
        deskripsi1 = (TextView) findViewById(R.id.deskripsi);

        insertData = findViewById(R.id.btInsert);
        etTesti = findViewById(R.id.etTesti);
        etNama = findViewById(R.id.etNama);
        etNama.setText(PreferencesHelper.getInstance(getApplicationContext()).getName());

        myRecyclerview = findViewById(R.id.myRecyclerview);
        fetchDataFromRoom();
        initRecyclerView();
        setAdapter();

        namaWisata = new String[]{
                "Candi Ratu Boko",
                "Wisata Kaliurang",
                "Candi Prambanan",
                "Tebing Breksi",
                "Merapi Park",
                "Lost World Castle"
        };
        rating = new String[]{
                "4",
                "4.1",
                "4.3",
                "5",
                "3.8",
                "3.5"
        };

        kategori = new String[]{
                ":\tKabupaten Sleman",
                ":\tKabupaten Sleman",
                ":\tKabupaten Sleman",
                ":\tKabupaten Sleman",
                ":\tKabupaten Sleman",
                ":\tKabupaten Sleman"
        };

        image = new int[]{
                R.drawable.candiboko,
                R.drawable.wisatakaliurang,
                R.drawable.prambanan,
                R.drawable.breksi,
                R.drawable.merapipark,
                R.drawable.lostworldcastle

        };

        deskripsi = new String[]{
               "\tlorem ipsum dolor",
                "\tlorem ipsum dolor",
                "\tlorem ipsum dolor",
                "\tlorem ipsum dolor",
                "\tlorem ipsum dolor",
                "\tlorem ipsum dolor"
        };
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final int position = getIntent().getIntExtra("position",0);
        judul = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        judul.setTitle(namaWisata[position]);
        judul.setBackgroundResource(image[position]);
        judul.setExpandedTitleColor(Color.argb(0,0,0,0));
        judul.setCollapsedTitleTextColor(Color.rgb(255,255,255));

        namaWisata1.setText(namaWisata[position]);

        kategori1.setText(kategori[position]);

        deskripsi1.setText(deskripsi[position]);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(WslmActivity.this, "Kamu menyukai film ini",Toast.LENGTH_SHORT).show();
            }
        });


        btn = (Button) findViewById(R.id.btn_lokasi);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(WslmActivity.this, MainActivity.class);
                back.putExtra("position", position);
                startActivity(back);
            }
        });


        insertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etNama.getText().toString().isEmpty()&&!etTesti.getText().toString().isEmpty()){

                    wisata = new Wisata();
                    wisata.setNama(etNama.getText().toString());
                    wisata.setTesti(etTesti.getText().toString());
                    //Insert data in database
                    db.userDao().insertAll(wisata);
                    startActivity(new Intent(WslmActivity.this,WslmActivity.class));
                }else {
                    Toast.makeText(getApplicationContext(), "Mohon masukkan dengan benar", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private void fetchDataFromRoom() {
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,"wisata").allowMainThreadQueries().build();
        listWisatas = db.userDao().getAll();

        //just checking data from db
        for (int i = 0; i < listWisatas.size(); i++){
            Log.e("Aplikasi", listWisatas.get(i).getNama()+i);
            Log.e("Aplikasi", listWisatas.get(i).getTesti()+i);
        }
        Log.e("cek list",""+ listWisatas.size());
    }

    private void initRecyclerView() {
        myRecyclerview.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        myRecyclerview.setLayoutManager(llm);
        recycleAdapter =new RecycleAdapter(this, listWisatas);

    }

    private void setAdapter() {
        myRecyclerview.setAdapter(recycleAdapter);
    }
}
