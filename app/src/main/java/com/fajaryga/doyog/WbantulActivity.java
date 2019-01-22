package com.fajaryga.doyog;

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

public class WbantulActivity extends AppCompatActivity {

    CollapsingToolbarLayout judul;
    String[] namaWisata;
    String[] rating, kategori, deskripsi, lokasi;
    TextView namaWisata1, kategori1, deskripsi1;
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
        setContentView(R.layout.activity_wbantul);
        namaWisata1 = (TextView) findViewById(R.id.namaWisata);
        kategori1 = (TextView) findViewById(R.id.kategori);
        deskripsi1 = (TextView) findViewById(R.id.deskripsi);


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
                    startActivity(new Intent(WbantulActivity.this,WbantulActivity.class));
                }else {
                    Toast.makeText(getApplicationContext(), "Mohon masukkan dengan benar", Toast.LENGTH_SHORT).show();
                }
            }
        });

        namaWisata = new String[]{
                "Parangtritis",
                "Perengan Park",
                "Hutan Pinus",
                "Bukit Lintang",
                "Puncak Becici",
                "Tebing Watu Mabur"
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
                "Kabupaten Bantul",
                "Kabupaten Bantul",
                "Kabupaten Bantul",
                "Kabupaten Bantul",
                "Kabupaten Bantul",
                "Kabupaten Bantul"
        };

        image = new int[]{
                R.drawable.pantaiparangtritis,
                R.drawable.parenganpark,
                R.drawable.hutanpinusjpg,
                R.drawable.bukitlintang,
                R.drawable.puncakbecici,
                R.drawable.tebingwatumangunan

        };

        deskripsi = new String[]{
                "\tPantai Parangtritis merupakan obyek wisata pantai yang sangat terkenal diantara pantai-pantai lain yang tersebar di wilayah Yogyakarta ini.\n" +
                        "Pantai ini diyakini oleh masyarakat setempat sebagai perwujudan kesatuan dari Gunung Merapi, Keraton Jogja dan Parangtritis sendiri. Sehingga masyarakat selalu menghubungkan bilamana ada fenomena alam yang sedang terjadi di antara ketiga tempat hal tersebut.\n" +
                        "Parangtritis memiliki pemandangan yang unik yang tidak terdapat pada obyek wisata lain, yaitu pantai yang memiliki ombak yang besar dan terdapatnya gunung-gunung pasir disektar kawasana pantai tersebut yang disebut dengan gumuk. Pada musim kemarau angin biasanya akan bertiup lebih cepat dan ombak akan bisa mencapai ketinggian 2 – 3 meter. Karena ombaknya yang besar maka pengunjung Pantai Parangtritis dilarang untuk berenang di seputaran pantai, untuk itu sudah disediakan fasilitas pemandian umum yang bisa digunakan untuk para pengunjung yang ingin berenang dengan aman dan nyaman.\n",
                "\tDi Perengan Park ada jembatan bambu di atas persawahan, gapura berhias dan beragam aksesoris hiasan. Kamu bisa selfie di spot ini bisa dengan latar belakang sawah menghijau, hamparan bunga celosia berwarna-warni sambil menikmati sunset di petang hari. Begitu memesona. Tempat wisata yang baru dibuka ini berada di Jalur Pantai Selatan, sepaket dengan kawasan Pantai Samas, Pantai Goa Cemara, Pantai Kuwaru dan juga Pantai Baru Pandansimo. Jadi, jika kamu sedang berkunjung ke Pantai Goa Cemara dan sekitarnya, kamu bisa memasukkan Perengan Park ke dalam list yang kamu kunjungi berikutnya..",
                "\tHutan Pinus Mangunan merupakan daya tarik wisata alam yang berada di perbukitan Bantul sisi sebelah timur. Pada mulanya hutan pinus ini hanyalah wilayah hutan yang dikelola oleh Perhutani. Namun keberadaannya mulai dikenal sejak bermunculan tentang foto-foto keindahan hutan pinus tersebut. Deretan pohon pinus yang tumbuh dengan rapi membuat pengunjung tertarik untuk mengunjunginya dan perlahan-lahan dibuka menjadi kawasan wisata oleh masyarakat sekitar.\n",
                "\tPuncak Becici memiliki hutan pinus seluas 4,4 hektar dengan hawa yang sejuk. Pada sore hari, pengunjung bisa menikmati sunset. Dari sini pula, pengunjung bisa melihat kecantikan Candi Prambanan bagian utara serta indahnya pantai selatan\n" +
                        "\n",
                "\tMangunan merupakan salah satu kawasan wisata di Bantul yang memiliki beberapa spot wisata yang sayang kalau dilewatkan. Mulai dari kebun buah mangunan, hutan pinus, bukit panguk, bukit mojo, dan sekarang yang belum lama ‘opening’ adalah Watu Lawang..\n",
                "\tTempat wisata yang dibangun Januari 2016 ini memang belum se popular tempat wisata di samping-sampingnya seperti  Hutan Pinus, Kebun Buah Mangunan dan Puncak Becici. Bukit ini  Dulunya  terkenal dengan nama bukit Watu Asah-asah. Penamaan Bukit Lintang Sewu  karena pada malam hari terlihat lampu kota  dan apabila cuaca cerah kalian bisa melihat beribu bintang\n"
        };

        lokasi = new String[]{
                "Kretek,+Kec.+Kretek,+Bantul,+Daerah+Istimewa+Yogyakarta",
                "l.+Hutan+Pinus+Nganjir,+Mangunan,+Dlingo,+Bantul,+Daerah+Istimewa+Yogyakarta+55783",
                "Perengan+Park",
                "Gunungcilik, RT.07/RW.02, Gn. Cilik, Muntuk, Dlingo, Bantul, Daerah Istimewa Yogyakarta 55783",
                "Lemahbang RT28, Lembah Bang, Mangunan, Dlingo, Bantul, Daerah Istimewa Yogyakarta 55783",
                "Karang Asem, Muntuk, Dlingo, Bantul, Daerah Istimewa Yogyakarta 55783"
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
                Toast.makeText(WbantulActivity.this, "Kamu menyukai wisata ini",Toast.LENGTH_SHORT).show();
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