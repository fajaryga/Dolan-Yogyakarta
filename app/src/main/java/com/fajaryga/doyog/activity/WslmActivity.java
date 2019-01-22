package com.fajaryga.doyog.activity;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fajaryga.doyog.MainActivity;
import com.fajaryga.doyog.R;
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
    TextView namaWisata1, kategori1, deskripsi1, rating1;
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
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        namaWisata1 = (TextView) findViewById(R.id.namaWisata);
        kategori1 = (TextView) findViewById(R.id.kategori);
        deskripsi1 = (TextView) findViewById(R.id.deskripsi);
        rating1 = (TextView) findViewById(R.id.rating);

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
               "\tRatu Boko terletak sekitar 3 km ke arah selatan dari Candi Prambanan. Kawasan Ratu Boko yang berlokasi di atas sebuah bukit dengan ketinggian ± 195.97 m diatas permukaan laut. Situs Ratu Boko sebenarnya bukan sebuah candi, melainkan reruntuhan sebuah kerajaan. Oleh karena itu, Candi Ratu Boko sering disebut juga Kraton Ratu Boko. Disebut Kraton Boko, karena menurut legenda situs tersebut merupakan istana Ratu Boko, ayah Lara Jonggrang. Diperkirakan situs Ratu Boko dibangun pada abad ke-8 oleh Wangsa Syailendra yang beragama Buddha, namun kemudian diambil alih oleh raja-raja Mataram Hindu. Peralihan ‘pemilik’ tersebut menyebabkan bangunan Kraton Boko dipengaruhi oleh Hinduisme dan Buddhisme.",
                "\tWisata Kaliurang adalah objek wisata andalan Kabupaten Sleman yang berada di lereng selatan Merapi. Kawasan wisata ini terletak di bagian utara Provinsi DIY, 25 km dari Kota Yogyakarta. Tepatnya Desa Hargobinangun, Kecamatan Pakem, Sleman. Jika dibandingkan dengan kawasan wisata yang serupa di luar DIY, Kaliurang mirip dengan kawasan Puncak di Bogor. Berikut akan kita bahas kenapa harus berkunjung ke kawasan wisata kaliurang. Biaya masuk ke Kaliurang Rp 2000 untuk dewasa dan Rp 1000 untuk anak-anak. Pada hari libur Rp 3000 dewasa dan Rp 1500 untuk anak. Untuk kendaraan juga dikenakan biaya masuk, yaitu Rp. 1000 untuk motor dan Rp. 2000 untuk mobil Rp. 3000 untuk bus atau truk.",
                "\tKompleks candi ini terletak di kecamatan Prambanan, Sleman dan kecamatan Prambanan, Klaten, kurang lebih 17 kilometer timur laut Yogyakarta, 50 kilometer barat daya Surakarta dan 120 kilometer selatan Semarang, persis di perbatasan antara provinsi Jawa Tengah dan Daerah Istimewa Yogyakarta. Letaknya sangat unik, Candi Prambanan terletak di wilayah administrasi desa Bokoharjo, Prambanan, Sleman, sedangkan pintu masuk kompleks Candi Prambanan terletak di wilayah adminstrasi desa Tlogo, Prambanan, Klaten.\n" +
                        "\n" +
                        "Candi ini adalah termasuk Situs Warisan Dunia UNESCO, candi Hindu terbesar di Indonesia, sekaligus salah satu candi terindah di Asia Tenggara. Arsitektur bangunan ini berbentuk tinggi dan ramping sesuai dengan arsitektur Hindu pada umumnya dengan candi Siwa sebagai candi utama memiliki ketinggian mencapai 47 meter menjulang di tengah kompleks gugusan candi-candi yang lebih kecil. Sebagai salah satu candi termegah di Asia Tenggara, candi Prambanan menjadi daya tarik kunjungan wisatawan dari seluruh dunia.",
                "\tTaman wisata Tebing Breksi Jogja adalah tempat wisata alam di Jogja. Sesuai dengan namanya, tempat wisata ini merupakan perbukitan batuan breksi. Tebing batuan breksi yang memiliki corak yang indah menjadi daya tarik tersendiri dari tempat wisata ini. Kehadiran tebing yang sekaligus dijadikan sebagai salah satu lokasi tempat wisata ini pasti mempunyai alasan tertentu yang membuat objek wisata ini disebut menjadi salah satu tempat wisata yang menarik untuk dikunjungi. Pasalnya, potensi wisata alam yang dimilikinya menawarkan banyak hal yang tidak boleh dilewatkan, diantaranya adalah pemandangan dinding tebing dengan ornamen patahan yang terlihat begitu artistik. Sebab, pada dasarnya tebing ini memang sudah terbentuk jutaan tahun yang lalu dan dijadikan sebagai tempat penambangan. Walaupun saat ini sudah tidak lagi dijadikan sebagai tempat penambangan, tapi sisa-sisa dari aktivitas penambangan tersebut mampu menghadirkan ornamen pahatan yang membuat tebing tersebut nampak seperti kue lapis.",
                "\tMerapi Park The World Landmarks merupakan salah satu tempat wisata yang ada di Yogyakarta. Terletak di Jl. Kaliurang Km 22, Hargobinangun, Kecamatan Pakem, Kabupaten Sleman, Yogyakarta. Terdapat spot foto menarik di tempat wisata yang telah dibuka sejak tanggal 25 Juni 2017 ini. Spot foto terdiri dari miniatur icon beberapa negara dan bangunan - bangunan yang terkenal di dunia" +
                        "Untuk menikmati beberapa spot foto yang ada di Merapi Park, pengunjung dikenakan biaya sebesar Rp 15.000 Fasilitas yang tersedia di Merapi Park antara lain, area parkir yang luas, toilet, mushola, restoran, tempat duduk santai dan gazebo. Adapun fasilitas yang tersedia untuk penyandang difabel yakni kursi roda gratis",
                "\tKabupaten Sleman di Daerah Istimewa Yogyakarta mempunyai tempat wisata yang cukup Indah. Namanya, The Lost World Castle. Terletak di Kepuharjo, Cangkringan, tempat wisata yang menjual keindahan alam di lereng gunung Merapi, dipadu dengan fasilitas untuk foto selfie, The Lost World Castle, banyak dikunjungi wisatawan.\n" +
                        "Dari luar, tempat wisata tersebut seperti dikelilingi tembok kokoh. Tidak heran, kalau sebagian pengunjung menjulukinya Benteng Takeshi, dan ada pula yang menyebutnya Tembok Besar China."
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
