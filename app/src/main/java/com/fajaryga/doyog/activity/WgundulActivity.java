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

public class WgundulActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_wgundul);
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
                    startActivity(new Intent(WgundulActivity.this,WgundulActivity.class));
                }else {
                    Toast.makeText(getApplicationContext(), "Mohon masukkan dengan benar", Toast.LENGTH_SHORT).show();
                }
            }
        });

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

        kategori = new String[]{
                ":\tKabupaten Gunungkidul",
                ":\tKabupaten Gunungkidul",
                ":\tKabupaten Gunungkidul",
                ":\tKabupaten Gunungkidul",
                ":\tKabupaten Gunungkidul",
                ":\tKabupaten Gunungkidul"
        };

        image = new int[]{
                R.drawable.gunung,
                R.drawable.goapindul,
                R.drawable.krakal,
                R.drawable.bintang,
                R.drawable.terjun,
                R.drawable.pok

        };

        deskripsi = new String[]{
                "\tJika ingin mencari tempat camping untuk menghabiskan malam minggu bersama sahabat-sahabat di Gunung Kidul, cobalah datang ke Gunung Nglanggeran. Tidak perlu skill mendaki yang tinggi untuk bisa sampai ke puncak Gunung Nglanggeran karna tingginya hanya sekitar 900 mdpl. Tidak sampai sejam kamu sudah akan tiba di puncak\n" +
                        "\n" +
                        "Ada beberapa puncak di Gunung Nglanggeran yang bisa kamu jadikan tempat untuk foto-foto dan ngeksis. Di bawahnya, ada area camping ground yang cukup untuk menampung puluhan tenda. Gunung Nglanggeran berbeda dengan gunung-gunung pada umumnya. Udara di gunung ini tidak terlalu dingin namun kamu harus tetap membawa pakaian hangat yang cukup untuk berjaga-jaga. Dari atas puncak Gunung ini kita bisa menyaksikan pemandangan Embung Nglanggeran yang namanya juga mulai terkenal\n",
                "\tTerlepas dari buruknya pengelolaan Goa Pindul hingga menyebabkan membludaknya jumlah pengunjung beberapa waktu lalu, hal itu bisa menjadi bukti bahwa alam di Gunung Kidul memang menggoda\n" +
                        "\n" +
                        "Goa Pindul merupakan tempat wisata yang belakangan menjadi salah satu yang paling terkenal di Gunung Kidul. Dulu, tempat ini mungkin masih dianggap sebelah mata. Selain tempatnya yang konon memiliki banyak aura mistisnya, keberadaan goa ini juga dulunya tidak terlalu diketahui banyak orang. Kini, saat kita bekendara menuju pantai-pantai di Gunung Kidul, kita akan dengan mudah menjumpai banyak jasa guide di sepanjang jalan yang menawarkan diri untuk mengantar ke Goa Pindul.",
                "\tPantai Krakal juga merupakan pantai yang cukup terkenal di Gunung Kidul. Suasana di pantai ini sendiri tidak terlalu berbeda dengan pantai-pantai di Gunung Kidul pada umumnya. Pantainya memiliki pasir putih serta air laut yang bersih\n" +
                        "\n" +
                        "Ombak yang ada di pantai ini cukup bisa diandalkan untuk kamu yang memiliki hobby berselancar. Tidak terlalu tinggi, memang. Namun cukup untuk menggerakkan papan selancar dan bermain-main\n",
                "\tSebagai penutup jalan-jalanmu di Gunung Kidul, mampirlah ke Bukit Bintang yang berada di Jalan Jogja-Wonosari. Sekitar 15 km dari Jogja. Saat malam hari, tempat ini akan menyajikan lautan gemerlap lampu kota Jogja. Terlihat sangat indah\n" +
                        "\n" +
                        "Sambil melihat indahnya pemandangan yang ada di depan mata, kita bisa menikmati kopi atau teh hangat di warung-warung yang sengaja di bangun dia atas tebing agar kita bisa menikmati pemandangan yang ada dengan leluasa. Jangan heran kalau kamu akan menemui muda-mudi yang datang kesini bersama pasangan mereka karna tempat ini memang dataeble banget",
                "\tAda sebuah wisata air terjun yang sangat unik dan indah di Gunung Kidul. Namanya adalah Air Terjun Sri Gethuk. Air yang jatuh dari ketinggian di air terjun ini mengalir melalui batu-batu untuk kemudian bermuara ke Sungai Oya. Di Sungai Oya inilah banyak pengunjung yang rela berbasah-basah ria demi menikmati airnya yang segar\n" +
                        "\n" +
                        "Tempat wisata ini mulai terkenal sekitar tahun 2010 lalu. Jika kita menyaksikan sendiri bagaimana suasana di sana, rasanya memang tidak tahan untuk segera melepas baju dan berenang di air sungainya yang alami\n",
                "\tBeberapa tahun lalu mungkin masih segelintir orang saja yang mengetahui nama Pantai Pok Tunggal. Apalagi lokasinya. Namun kini, nama pantai ini semakin melejit dan menjadi salah satu pantai paling dicari wisatawan berkat keindahan yang ditawarkannya\n" +
                        "\n" +
                        "Dengan pasir putihnya, Pantai Pok Tunggal berhasil mengusik hati para wisatawan untuk segera datang kesana. Padahal, akses masuk menuju pantai tidak terlalu mudah. Sebuah pohon yang seakan menjadi landmark alami pantai juga turut melengkapi keindahan pantai Pok Tunggal yang dikenal memiliki pemandangan sunset yang sangat indah\n"
        };

        lokasi = new String[]{
                "Nglanggeran,+Patuk,+Kabupaten+Gunung+Kidul,+Daerah+Istimewa+Yogyakarta",
                "Wirawisata+Goa+Pindul",
                "Pantai+Krakal",
                "Bukit+Bintang",
                "Air+Terjun+Sri+Gethuk",
                "Pantai+Pok+Tunggal"
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
                Toast.makeText(WgundulActivity.this, "Kamu menyukai wisata ini",Toast.LENGTH_SHORT).show();
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