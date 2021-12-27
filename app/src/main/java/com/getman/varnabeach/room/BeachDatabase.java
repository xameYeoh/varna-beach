package com.getman.varnabeach.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Beach.class}, version = 1)
public abstract class BeachDatabase extends RoomDatabase {
    private static volatile BeachDatabase INSTANCE;

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static BeachDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (BeachDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, BeachDatabase.class, "beach_database").build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract BeachDAO beachDao();


}
