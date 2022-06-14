package com.getman.varnabeach.hilt

import android.content.Context
import androidx.room.Room
import com.getman.varnabeach.room.BeachDAO
import com.getman.varnabeach.room.BeachDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideBeachDatabase(@ApplicationContext context: Context): BeachDatabase {
        return BeachDatabase.getInstance(context)
    }

    @Provides
    fun provideBeachDAO(db: BeachDatabase): BeachDAO = db.beachDao()
}