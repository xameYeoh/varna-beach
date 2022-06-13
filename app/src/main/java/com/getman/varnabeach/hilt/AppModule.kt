package com.getman.varnabeach.hilt

import android.content.Context
import androidx.room.Room
import com.getman.varnabeach.VarnaBeachApplication
import com.getman.varnabeach.room.BeachDAO
import com.getman.varnabeach.room.BeachDatabase
import com.getman.varnabeach.util.ErrorHandler
import com.getman.varnabeach.util.ErrorToastDisplayer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext context: Context): VarnaBeachApplication {
        return context as VarnaBeachApplication
    }

    @Singleton
    @Provides
    fun provideBeachDatabase(@ApplicationContext context: Context): BeachDatabase {
        return Room.databaseBuilder(context, BeachDatabase::class.java, "beach_database")
            .addCallback(BeachDatabase.Callback(context))
            .build()
    }

    @Provides
    fun provideBeachDAO(db: BeachDatabase): BeachDAO = db.beachDao()

    @Provides
    fun provideErrorHandler(@ApplicationContext context: Context): ErrorHandler =
        ErrorToastDisplayer(context)
}