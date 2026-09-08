package com.atsz7.rm.roster.core.di.modules

import android.content.Context
import androidx.room.Room
import com.atsz7.rm.roster.core.data.local.daos.CharactersDao
import com.atsz7.rm.roster.core.data.local.database.RMRosterDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): RMRosterDatabase =
        Room.databaseBuilder(context, RMRosterDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration(dropAllTables = true)
            .build()

    @Provides
    fun provideCharactersDao(database: RMRosterDatabase): CharactersDao {
        return database.charactersDao()
    }

    private const val DATABASE_NAME = "rm_roster.db"
}
