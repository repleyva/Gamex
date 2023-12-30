package com.repleyva.gamexapp.di

import android.content.Context
import androidx.room.Room
import com.repleyva.data.datasource.local.dao.GameDao
import com.repleyva.data.datasource.local.database.GameDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideGameDatabase(@ApplicationContext context: Context): GameDatabase =
         Room.databaseBuilder(
            context,
            GameDatabase::class.java,
            GameDatabase::javaClass.name
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    @Provides
    @Singleton
    fun provideGameDao(database: GameDatabase): GameDao = database.gameDao()

}