package com.atsz7.rm.roster.domain.repositories

import com.atsz7.rm.roster.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    fun getCharacters(): Flow<List<Character>>

    suspend fun hasCachedCharacters(): Boolean

    suspend fun refreshCharacters()
}
