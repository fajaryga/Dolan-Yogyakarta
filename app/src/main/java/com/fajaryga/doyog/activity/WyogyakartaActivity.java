package com.fajaryga.doyog.activity;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fajaryga.doyog.R;
import com.fajaryga.doyog.room.AppDatabase;
import com.fajaryga.doyog.room.Wisata;
import com.fajaryga.doyog.util.PreferencesHelper;
import com.fajaryga.doyog.util.RecycleAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.fajaryga.doyog.AppApplication.db;

public class WyogyakartaActivity extends AppCompatActivity {
    CollapsingToolbarLayout judul;
    String[] namaWisata;
    String[] rating, kategori, deskripsi, lokasi;
    TextView namaWisata1, kategori1, deskripsi1, rating1;
    int[] image;
    Button btn;

    RecyclerView myRecyclerview;
    RecycleAdapter recycleAdapter;
    List<Wisata> listWisatas = new ArrayList<>();

    private Button insertData;
    private EditText etNama;
    private EditText etTesti;
    Wisata wisata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wyogyakarta);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        namaWisata1 = (TextView) findViewById(R.id.namaWisata);
        kategori1 = (TextView) findViewById(R.id.kategori);
        deskripsi1 = (TextView) findViewById(R.id.deskripsi);
        rating1 = (TextView) findViewById(R.id.rating);

        myRecyclerview = findViewById(R.id.myRecyclerview);
        fetchDataFromRoom();
        initRecyclerView();
        setAdapter();

        insertData = findViewById(R.id.btInsert);
        etTesti = findViewById(R.id.etTesti);
        etNama = findViewById(R.id.etNama);
        etNama.setText(PreferencesHelper.getInstance(getApplicationContext()).getName());

        insertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etNama.getText().toString().isEmpty()&&!etTesti.getText().toString().isEmpty()){

                    wisata = new Wisata();
                    wisata.setNama(etNama.getText().toString());
                    wisata.setTesti(etTesti.getText().toString());
                    //Insert data in database
                    db.userDao().insertAll(wisata);
                    startActivity(new Intent(WyogyakartaActivity.this,WyogyakartaActivity.class));
                }else {
                    Toast.makeText(getApplicationContext(), "Mohon masukkan dengan benar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        namaWisata = new String[]{
                "Taman Sari",
                "Keraton Yogyakarta",
                "Benteng Vredeburg",
                "Gembira Loka Zoo",
                "Taman Pintar",
                "Malioboro"
        };
        rating = new String[]{
                "4.1",
                "4",
                "3.8",
                "5",
                "5",
                "4.1"
        };

        kategori = new String[]{
                "Kota Jogja",
                "Kota Jogja",
                "Kota Jogja",
                "Kota Jogja",
                "Kota Jogja",
                "Kota Jogja"
        };

        image = new int[]{
                R.drawable.tamansari,
                R.drawable.keratonyogyakarta,
                R.drawable.vredeburg,
                R.drawable.gembiraloka,
                R.drawable.tamanpintar,
                R.drawable.malioboro

        };

        deskripsi = new String[]{
                "\tTaman Sari adalah Taman Istana yang memiliki gaya arsitektur unik karena merupakan perpaduan antara gaya arsitektur Jawa dan Portugis, Sultan Hamengkubuwono I dulunya memang dibantu oleh arsitek-artitek dari Portugis saat hendak membangun Taman Sari ini, Hal pertama yang mengesankan ketika Anda berkunjung ke Taman Sari adalah gapura unik dengan ukiran-ukiran cantik yang menjadi pintu masuk di Taman Sari. Memasuki dua pintu kedalam Anda akan menemukan dua kolam berair biru. Pada masanya kolam tersebut adalah tempat pemandian para putri dan selir-selir raja.\n",
                "\tKeraton yogyakarta merupakan istana resmi Kesultanan Ngayogyakarta Hadiningrat yang kini berlokasi di Kota Yogyakarta, Daerah Istimewa Yogyakarta, Indonesia. Walaupun kesultanan tersebut secara resmi telah menjadi bagian Republik Indonesia pada tahun 1950, kompleks bangunan keraton ini masih berfungsi sebagai tempat tinggal sultan dan rumah tangga istananya yang masih menjalankan tradisi kesultanan hingga saat ini. Keraton ini kini juga merupakan salah satu objek wisata di Kota Yogyakarta.\n",
                "\tMuseum Benteng Vredeburg adalah salah satu sisa peninggalan bangunan Belanda di Yogyakarta. Di benteng ini kamu bisa belajar sejarah di museumnya, menikmati aneka hidangan lezat di Indische Koffie, hingga berswafoto di tamannya yang asri, Benteng Vredeburg ini merupakan loji (bangunan besar khas Belanda) tertua dari semua bangunan Indis yang ada di kawasan Malioboro dan Titik Nol Kilometer. Bangunan megah ini memiliki rangkaian kisah serta catatan sejarah yang panjang, sehingga sayang jika terlewatkan untuk dikunjungi.\n",
                "\tTaman pintar adalah  adalah wahana wisata yang terdapat di pusat Kota Yogyakarta, tepatnya di Jalan Panembahan Senopati No. 1-3, Yogyakarta, di kawasan Benteng Vredeburg. Taman ini memadukan tempat wisata rekreasi maupun edukasi dalam satu lokasi. Taman Pintar memiliki arena bermain sekaligus sarana edukasi yang terbagi dalam beberapa zona. Akses langsung kepada pusat buku eks Shopping Centre juga menambah nilai lebih Taman Pintar. Tempat rekreasi ini sangat baik untuk anak-anak pada masa perkembangan. \n",
                "\tKebun Binatang Gembira Loka Yogyakarta adalah kawasan yang bisa dibilang super lengkap. Dimana, Sobat Native akan disuguhkan berbagai macam hewan yang menggemaskan, lucu, dan menakutkan. Ada pula atraksi hewan-hewan yang menarik untuk disaksikan. Tidak hanya manusia saja yang bisa melakukan sebuah akrobatik, melainkan hewan pun juga bisa.\n",
                "\tMalioboro adalah sebuah Jalan sepanjang tidak lebih dari 2 Kilo Meter yang membentang mulai dari persimpangan Rel Kereta Api Stasiun Tugu Yogyakarta diujung utara hingga pertigaan pojokan Gedung Agung diujung Selatan, Malioboro adalah sebuah Jalan legendaris yang menjadi ikon Kota Yogyakarta dengan kehidupan kontras antara siang dan malamnya."
        };

        lokasi = new String[]{
                "Kampung+Wisata+Taman+Sari",
                "Keraton+Yogyakarta",
                "Museum+Benteng+Vredeburg",
                "Gembira+Loka+Zoo",
                "Taman+Pintar+Yogyakarta",
                "Jl.+Malioboro,+Sosromenduran,+Gedong+Tengen,+Kota+Yogyakarta,+Daerah+Istimewa+Yogyakarta"
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
        rating1.setText(rating[position]);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(WyogyakartaActivity.this, "Kamu menyukai wisata ini",Toast.LENGTH_SHORT).show();
            }
        });

        String lokasi1 = lokasi.toString();

        btn = (Button) findViewById(R.id.btn_lokasi);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri gmmIntentUri = Uri.parse("google.navigation:q="+lokasi1);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);

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