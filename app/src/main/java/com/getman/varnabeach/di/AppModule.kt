package com.getman.varnabeach.di

import android.content.Context
import com.getman.varnabeach.util.ErrorHandler
import com.getman.varnabeach.util.ErrorToastDisplayer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideErrorHandler(@ApplicationContext context: Context): ErrorHandler =
        ErrorToastDisplayer(context)
}