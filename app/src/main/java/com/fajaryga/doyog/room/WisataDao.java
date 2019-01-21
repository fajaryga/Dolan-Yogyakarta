package com.fajaryga.doyog.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface WisataDao {

    @Query("SELECT * FROM Wisata")
    List<Wisata> getAll();

    @Query("SELECT * FROM Wisata WHERE nama LIKE :nama ")
    Wisata findByName(String nama);

    @Insert
    void insertAll(Wisata wisata);

    @Delete
    public void deleteUsers(Wisata users);

    @Update
    public void update(Wisata wisata);

    @Delete
    public void deleteAll(Wisata user1, Wisata user2);

}
