package com.atsz7.rm.roster.core.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.atsz7.rm.roster.core.data.local.daos.CharactersDao
import com.atsz7.rm.roster.core.data.local.entities.CharacterEntity

@Database(
    entities = [CharacterEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RMRosterDatabase : RoomDatabase() {
    abstract fun charactersDao(): CharactersDao
}
