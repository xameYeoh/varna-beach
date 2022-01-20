package com.getman.varnabeach.room;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.getman.varnabeach.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(
        version = 1,
        entities = {Beach.class}
)
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
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            String packageName = context.getPackageName();
            String centralImagePath = "android.resource://" + packageName + "/" + R.drawable.central;
            String asparuhovoImagePath = "android.resource://" + packageName + "/" + R.drawable.asparuhovo;
            String galataImagePath = "android.resource://" + packageName + "/" + R.drawable.galata;
            String goldenSandsImagePath = "android.resource://" + packageName + "/" + R.drawable.golden_sands;
            String nesebrImagePath = "android.resource://" + packageName + "/" + R.drawable.burgas;
            String sozopolImagePath = "android.resource://" + packageName + "/" + R.drawable.sozopol;
            String primorskoImagePath = "android.resource://" + packageName + "/" + R.drawable.primorsko;
            String sunnyBeachImagePath = "android.resource://" + packageName + "/" + R.drawable.sunny_beach;

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
                        ),
                        new Beach(
                                context.getString(R.string.sunny_beach),
                                context.getString(R.string.sunny_beach_description),
                                "42.688149",
                                "27.716088",
                                sunnyBeachImagePath
                        ),
                        new Beach(
                                context.getString(R.string.burgas),
                                context.getString(R.string.burgas_description),
                                "42.499452",
                                "27.484705",
                                nesebrImagePath
                        ),
                        new Beach(
                                context.getString(R.string.sozopol),
                                context.getString(R.string.sozopol_description),
                                "42.420744",
                                "27.696514",
                                sozopolImagePath
                        ),
                        new Beach(
                                context.getString(R.string.primorsko),
                                context.getString(R.string.primorsko_description),
                                "42.262990",
                                "27.753534",
                                primorskoImagePath
                        )
                );
            });
        }
    }
}
