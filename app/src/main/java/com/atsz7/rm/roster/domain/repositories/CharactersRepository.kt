package com.atsz7.rm.roster.domain.repositories

import com.atsz7.rm.roster.domain.model.Character
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    fun getCharacters(): Flow<ImmutableList<Character>>

    suspend fun hasCachedCharacters(): Boolean

    suspend fun refreshCharacters()
}
