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

public class WkulonprogoActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_wkulonprogo);
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
                    startActivity(new Intent(WkulonprogoActivity.this,WkulonprogoActivity.class));
                }else {
                    Toast.makeText(getApplicationContext(), "Mohon masukkan dengan benar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        namaWisata = new String[]{
                "Kedung Pedut",
                "Waduk Sermo",
                "Kalibiru",
                "Kebun Teh Nglinggo",
                "Sungai Mudal",
                "Pantai Glagah"
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
                "Kabupaten Kulonprogo",
                "Kabupaten Kulonprogo",
                "Kabupaten Kulonprogo",
                "Kabupaten Kulonprogo",
                "Kabupaten Kulonprogo",
                "Kabupaten Kulonprogo"
        };

        image = new int[]{
                R.drawable.kedungpedut,
                R.drawable.waduksermo,
                R.drawable.kalibiru,
                R.drawable.kbnteh,
                R.drawable.sungaimudal,
                R.drawable.pantaiglagah

        };

        deskripsi = new String[]{
                "\tAir Terjun Kedung Pedut atau biasa disebut Curug Kedung Pedut, berasal dari istilah bahasa Jawa. Curug yang berarti air terjun, kedung berarti kubangan atau kolam, dan pedut berarti kabut. Air Terjun Kedung Pedut merupakan salah satu wisata alam yang di Kulonprogo yang dikenal karena keindahan warna airnya. Warna air yang sangat cantik terdiri dari dua komponen warna yaitu putih jernih dan hijau. Warna putih jernih terjadi karena aliran air deras yang berasal dari air terjun di samping kedung ini, sedangkan warna hijau tosca terbentuk dari pantulan batuan di dasar sungai yang terpancar sinar matahari.\n",
                "\tWaduk Sermo adalah sebuah waduk yang berada di Kabupaten Kulonprogo, Daerah Istimewa Yogyakarta, Indonesia. Waduk ini dibangun mulai tahun 1994 dan diresmikan oleh Presiden Soeharto 20 November 1996. Tujuan pembangunan waduk ini adalah untuk suplesi sistem irigasi daerah Kalibawang yang memiliki cakupan areal seluas 7.152 Ha. Sistem irigasi tersebut merupakan interkoneksi dari beberapa daerah irigasi.\n.",
                "\tWisata taman sungai  mudal ini berupa kolam pemandian yang airnya sangat jernih dan berwarna biru toska yang sangat memanjakan mata. Airnya  wisata taman sungai ini berasal dari mata air mudal yang berada di daerah mudal itu sendiri. Keindahan  taman sungai  mudal ini yang menjadi daya tarik wisatawan asing atau lokal, dan sangat cocok untuk update foto di instagram kalian.\n",
                "\tKebun Teh Nglinggo terletak di wilayah Perbukitan Menoreh. Lokasinya di Desa Wisata Nglinggo, Nglinggo Barat, Pagerharjo, Samigaluh, Kabupaten Kulon Progo, DIY. Wisata ini menawarkan objek wisata berupa hamparan perkebunan teh dengan luas sekitar 136 hektare di ketinggian 900-1000 mdpl.\n",
                "\tDesa Wisata Kalibiru merupakan salah satu tempat wisata di Jogja yang sedang fenomenal di kalangan anak muda belakangan ini. Letaknya di Jl. Waduk Sermo, Kalibiru, Kulonprogo. Tempat wisata ini menjadi perbincangan karena sebuah pemandangan indah yang pernah terekam melalui salah satu kamera pengunjung dan di-posting di Instagram.\n",
                        "Di Kalibiru, pengunjung memang bisa berpose di atas sebuah pohon pinus yang tinggi. Indahnya warna hijau pepohonan di lereng bukit menjadi latar belakang yang sangat alami. Untuk memasuki area wisata ini, Anda harus membayar Rp10.000 per orang. Biaya ini belum termasuk biaya parkir dan biaya foto. Kalibiru mulai dibuka untuk umum pada jam 06.00 WIB hingga 17.00 WIB setiap hari.\n",
                "\tPantai yang paling terkenal di Kulon Progo adalah Pantai Glagah. Pantai ini dikenal memiliki laguna dan tetrapod beton yang dapat memecah ombak secara maksimal. Ombak yang menghantam tetrapod inilah yang akan menghasilkan deburan ombak di pinggir pantai. \n"
        };

        lokasi = new String[]{
                "Air+Terjun+Kedung+Pedut",
                "Waduk+Sermo",
                "Taman+Sungai+Mudal",
                "Kebun+Teh+Nglinggo",
                "Kali+Biru+Kulon+Progo",
                "Pantai+Glagah"
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
                Toast.makeText(WkulonprogoActivity.this, "Kamu menyukai wisata ini",Toast.LENGTH_SHORT).show();
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