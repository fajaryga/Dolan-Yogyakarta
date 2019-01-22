package com.fajaryga.doyog.activity;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
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

import com.fajaryga.doyog.R;
import com.fajaryga.doyog.room.AppDatabase;
import com.fajaryga.doyog.room.Wisata;
import com.fajaryga.doyog.util.PreferencesHelper;
import com.fajaryga.doyog.util.RecycleAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.fajaryga.doyog.AppApplication.db;

public class WisataActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_wisata);
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
                    startActivity(new Intent(WisataActivity.this,WisataActivity.class));
                }else {
                    Toast.makeText(getApplicationContext(), "Mohon masukkan dengan benar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        namaWisata = new String[]{
                "Keraton Yogyakarta",
                "Wisata Kaliurang",
                "Pantai Glagah",
                "Tebing Breksi",
                "Kalibiru",
                "Hutan Pinus Mangunan"
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
                "\tKota Jogja",
                "\tKabupaten Sleman",
                "\tKabupaten Kulonprogo",
                "\tKabupaten Sleman",
                "\tKabupaten Kulonprogo",
                "\tKabupaten Bantul"
        };

        image = new int[]{
                R.drawable.keratonyogyakarta,
                R.drawable.wisatakaliurang,
                R.drawable.pantaiglagah,
                R.drawable.breksi,
                R.drawable.kalibiru,
                R.drawable.pinusmangunan

        };

        deskripsi = new String[]{
                "\tDestinasi yang wajib dikunjungi saat berlibur ke Jogja adalah Keraton. Tempat ini merupakan Istana Kesultanan Ngayogyakarta Hadiningrat dan masih menjadi hunian keluarga kerajaan hingga saat ini. Keraton bisa digolongkan sebagai salah satu situs sejarah yang menyimpan banyak kisah dari masa lampau.\n" +
                        "Wisatawan bisa mengelilingi area Keraton dengan membayar karcis masuk sebesar Rp7.000 saja. Setiap hari, tempat wisata di Jogja ini dibuka untuk masyarakat umum pada pukul 09.00 WIB hingga 14.00 WIB. Lokasinya tepat di Jl. Rotowijayan Blok No.1 Panembahan, Kraton. Pengunjung bisa menggunakan transportasi umum seperti becak atau delman maupun kendaraan pribadi untuk tiba di objek wisata sejarah ini.\n" +
                        "Saat berkunjung ke Keraton, wisatawan akan disuguhi pemandangan elok khas arsitektur zaman dahulu. Gedung-gedung yang indah dan artistik menjadi latar berfoto yang eksotis. Di dalam Keraton, wisatawan juga bisa melihat-lihat berbagai koleksi warisan dari masa lalu yang dipamerkan di ruang khusus.\n",
                "\tKaliurang adalah kawasan desa wisata Sleman yang berhawa sejuk karena letaknya berada di lereng Gunung Merapi. Kaliurang selalu ramai dikunjungi saat musim libiran. Beberapa hal yang menyenangkan yang bisa kamu lakukan disini adalah melihat puncak Gunung Merapi di gardu pandang Gunung Merapi. Dengan menggunakan teropong, kamu bisa melihat puncak Gunung Merapi yang pernah meletus pada tahun 2010 silam. Disini juga terdapat Resot Kaliurang yang mempunyai agrowisata. Jangan lupa untuk mampir ke wisata kuliner Sleman¸ Kalimilk yang menyajikan susu murni dan juga berbagai menu makanan lainnya yang asik untuk teman nongkrong.",
                "\tPantai Glagah merupakan primadona di Kulonprogo. Di pantai ini pengunjung dapat bermain kano di laguna, naik motorcross di hamparan pasir besi, memancing di atas tetrapod yang berfungsi sebagai pemecah ombak, hingga memetik buah naga di agrowisata.\n" +
                        "Selama ini Kabupaten Kulonprogo seringkali terlupa di kalangan wisatawan. Padahal di Kabupaten ini terdapat sebuah pantai yang indah dengan fasilitas yang sangat beragam, Pantai Glagah namanya. Destinasi wisata ini hanya berjarak sekitar 40 km arah barat Yogyakarta, berbatasan dengan Kabupaten Purworejo, Jawa Tengah.\n" +
                        "Berbeda dengan pantai-pantai di Gunungkidul yang dibatasi bukit karang, tepian Pantai Glagah terlihat sangat panjang tanpa penghalang. Sejauh mata memandang yang terlihat adalah hamparan pasir besi kehitaman. Ombak di Pantai Glagah terbilang cukup besar. Karena itu di kawasan pantai ini dibangun sebuah dermaga panjang dengan tetrapod di kanan kirinya. Tetrapod tersebut berfungsi sebagai pemecah ombak.  Untuk memasuki kawasan wisata Pantai Glagah, wisatawan cukup merogoh kocek sebesar Rp 4.000.\n",
                "\tHadirnya Taman Tebing Breksi ini tentu saja menjadi alternatif wisata lain yang makin menambah minat wisatawan untuk berkunjung ke Jogja. Pasalnya, Tebing Breksi menawarkan keindahan alam yang cukup indah. Suguhan utama yang disajikan oleh Taman Tebing Breksi adalah deretan tebing batu breksi yang menjulang gagah. Tebing ini sejatinya sudah terbentuk jutaan tahun lalu, namun karena aktivitas penambangan berubah menjadi memiliki ornamen ornamen pahatan. Selain itu, bekas penambangan juga membuka penampang dinding tebing sehingga terlihat struktur batuan yang mneyusunnya seperti kue lapis. Hal itu menjadi keindahan tersediri yang membuat para pengunjung berfoto dengan background tebing breksi yang sangat artistik. Tak sedikit yang menjadikan ini sebagai spot hunting foto, bahkan untuk momen momen spesial seperti pre wedding.\n" +
                        "Selain menyajikan keindahan geo heritage, dari atas tebing Breksi pengunjung juga dapat melihat lanscape kota jogja, candi Prambanan, hingga hilir mudik pesawat di Bandara Adi Sucipto. Yang paling istimewa adalah saat di mana matahari mulai tenggelam. Tebing Breksi ini memberikan pemandangan sunset yang begitu epic.\n" +
                        "\n" +
                        "Lokasi objek wisata yang instagramable ini tak jauh dari pusat kota Jogja. Letak Tebing Breksi berdekatan dengan Candi Ijo yang juga lagu ngehits.Lokasi Tebing Breksi ini tepatnya berada di Dusun Groyokan Desa Sambirejo Kecamatan Prambanan Kabupaten Sleman. Rute menuju Tebing Breksi juga mudah dijangkau karena terletak di jalur utama, Prambanan Piyungan sekitar 1 km sebelum sampai ke Candi Ijo." +
                        "Rute menuju Tebing Breksi yang paling mudah ditempuh adalah dari Candi Prambanan. Jadi kalau datang dari Jogja, langsung menuju arah Candi Prambanan, kemudian di pertigaan pasar Prambanan ambil kanan arah ke Piyungan. Dari situ sekitar 3 km menuju Tebing breksi. Cari petunjuk arah ke Candi Ijo kemudian ambil kiri (pertigaan ini sebelum SDN Sambirejo). Dari pertigaan ini masih harus menempuh perjalanan sekitar 1 km. Jalan menuju Tebing Breksi ini cukup menanjak dan kondisinya juga tak terlalu mulus.\n" +
                        "\n" +
                        "Untuk masuk ke area wisata Taman Tebing Breksi, pengunjung belum dikenakan biaya retribusi, hanya cukup membayar biaya parkir Rp. 2.000 untuk motor dan Rp. 5.000 untuk mobil.",
                "\tDesa Wisata Kalibiru merupakan salah satu tempat wisata di Jogja yang sedang fenomenal di kalangan anak muda belakangan ini. Letaknya di Jl. Waduk Sermo, Kalibiru, Kulonprogo. Tempat wisata ini menjadi perbincangan karena sebuah pemandangan indah yang pernah terekam melalui salah satu kamera pengunjung dan di-posting di Instagram.\n" +
                        "Di Kalibiru, pengunjung memang bisa berpose di atas sebuah pohon pinus yang tinggi. Indahnya warna hijau pepohonan di lereng bukit menjadi latar belakang yang sangat alami. Untuk memasuki area wisata ini, Anda harus membayar Rp10.000 per orang. Biaya ini belum termasuk biaya parkir dan biaya foto. Kalibiru mulai dibuka untuk umum pada jam 06.00 WIB hingga 17.00 WIB setiap hari.\n",
                "\tTempat wisata lain yang juga sempat menjadi perbincangan adalah Hutan Pinus Mangunan. Lokasinya berada sekitar 23 km dari pusat kota. Pengunjung bisa tiba di sana dengan menyusuri jalan Imogiri Timur dan mencari Desa Mangunan, Dlingo.\n" +
                        "Apa yang menarik dari tempat wisata di Jogja ini? Seperti namanya, di Hutan Pinus Mangunan ini terdapat deretan pohon-pohon pinus yang menjulang tinggi hingga menutupi seluruh kawasan. Suasana alam yang segar dan teduh semakin membuat pengalaman memasuki hutan menjadi lebih menyenangkan sekaligus menantang. Di sini bahkan terdapat sebuah panggung terbuka yang materialnya secara keseluruhan terbuat dari kayu. Ada pula tempat duduk melingkar yang terbuat dari batang pohon. Di panggung unik ini, sering diadakan acara sastra, teater, dan pertunjukan musik.\n" +
                        "Waktu yang paling tepat untuk berkunjung ke Hutan Pinus Mangunan adalah pada pagi hari sekitar 06.00 WIB hingga 10.00 WIB atau sore hari pada 15.00 WIB hingga 18.00 WIB. Biaya masuk tidak ada, Anda hanya perlu membayar parkir sebesar Rp3.000 untuk motor dan Rp10.000 untuk mobil. Selain itu, Anda diperbolehkan memotret sepuasnya.\n"
        };

         lokasi = new String[]{
                "keraton+yogyakarta",
                "bunker+kaliadem",
                "Pantai+Glagah,+Daerah+Istimewa+Yogyakarta",
                "Taman+Tebing+Breksi,+Jl.+Candi+Ijo,+Gunung+Ciuk,+Sambirejo,+Kabupaten+Sleman,+Daerah+Istimewa+Yogyakarta",
                "Kalibiru,+Hargowilis,+Kokap,+Kabupaten+Kulon+Progo,+Daerah+Istimewa+Yogyakarta",
                "Hutan+Pinus+Mangunan+Dlingo,+Sukorame,+Mangunan,+Dlingo,+Bantul,+Daerah+Istimewa+Yogyakarta"
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
                Toast.makeText(WisataActivity.this, "Kamu menyukai wisata ini",Toast.LENGTH_SHORT).show();
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