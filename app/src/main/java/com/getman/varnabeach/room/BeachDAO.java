package com.getman.varnabeach.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BeachDAO {
    @Query("SELECT * FROM beach ORDER BY name ASC")
    LiveData<List<Beach>> getAllOrderByName();

    @Query("DELETE FROM beach")
    void deleteAll();

    @Insert
    void insertAll(Beach... beaches);
}
