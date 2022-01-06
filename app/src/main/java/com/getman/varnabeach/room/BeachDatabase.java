package com.getman.varnabeach.room;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.getman.varnabeach.R;

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
                    INSTANCE = Room.databaseBuilder(context, BeachDatabase.class, "beach_database")
                            .addCallback(new Callback(context))
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract BeachDAO beachDao();

    private static class Callback extends RoomDatabase.Callback {

        private final Context context;

        private Callback(Context context) {
            this.context = context;
        }

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            String packageName = context.getPackageName();
            String centralImagePath = "android.resource://" + packageName + "/" + R.drawable.central;
            String asparuhovoImagePath = "android.resource://" + packageName + "/" + R.drawable.asparuhovo;
            String galataImagePath = "android.resource://" + packageName + "/" + R.drawable.galata;
            String goldenSandsImagePath = "android.resource://" + packageName + "/" + R.drawable.golden_sands;


            databaseWriteExecutor.execute(() -> {
                BeachDAO dao = INSTANCE.beachDao();
                dao.deleteAll();

                dao.insertAll(
                        new Beach(
                                context.getString(R.string.central),
                                context.getString(R.string.central_description),
                                "43.199786",
                                "27.923486",
                                centralImagePath
                        ),
                        new Beach(
                                context.getString(R.string.asparuhovo),
                                context.getString(R.string.asparuhovo_description),
                                "43.177172",
                                "27.913052",
                                asparuhovoImagePath
                        ),
                        new Beach(
                                context.getString(R.string.galata),
                                context.getString(R.string.galata_description),
                                "43.156304",
                                "27.943984",
                                galataImagePath
                        ),
                        new Beach(
                                context.getString(R.string.golden_sands),
                                context.getString(R.string.golden_sands_description),
                                "43.283441",
                                "28.045274",
                                goldenSandsImagePath
                        )
                );
            });
        }
    }
}
