package com.fajaryga.doyog.room;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Wisata {

    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "nama")
    String nama;
    @ColumnInfo(name = "testimoni")
    String testimoni;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTesti() {
        return testimoni;
    }

    public void setTesti(String nim) {
        this.testimoni = nim;
    }


}
