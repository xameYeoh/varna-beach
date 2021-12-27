package com.getman.varnabeach.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BeachDAO {
    @Query("SELECT * FROM beach")
    LiveData<List<Beach>> getAll();

    @Insert
    void insertAll(Beach... beaches);
}
