package com.atsz7.rm.roster.core.data.local.daos

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.atsz7.rm.roster.core.data.local.entities.CharacterEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {

    @Query("SELECT * FROM characters ORDER BY id ASC")
    fun getAll(): Flow<List<CharacterEntity>>

    @Upsert
    suspend fun upsertAll(characters: List<CharacterEntity>)

    @Query("DELETE FROM characters")
    suspend fun deleteAll()
}
