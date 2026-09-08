package com.atsz7.rm.roster.data.repositories

import com.atsz7.rm.roster.core.data.local.daos.CharactersDao
import com.atsz7.rm.roster.core.data.local.entities.CharacterEntity
import com.atsz7.rm.roster.core.data.remote.api.RickAndMortyApi
import com.atsz7.rm.roster.data.mappers.toDomain
import com.atsz7.rm.roster.data.mappers.toEntity
import com.atsz7.rm.roster.domain.model.Character
import com.atsz7.rm.roster.domain.repositories.CharactersRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val rickAndMortyApi: RickAndMortyApi,
    private val charactersDao: CharactersDao
) : CharactersRepository {

    override fun getCharacters(): Flow<List<Character>> =
        charactersDao.getAll().map { rows ->
            rows.map { it.toDomain() }
        }

    override suspend fun downloadCharacters() {

        // Clearing database
        charactersDao.deleteAll()

        val characters = (MIN_PAGES_TO_DOWNLOAD..MAX_PAGES_TO_DOWNLOAD).flatMap { page ->
            delay(timeMillis = DOWNLOAD_DELAY)
            downloadPage(page)
        }
        charactersDao.upsertAll(characters)
    }

    private suspend fun downloadPage(page: Int): List<CharacterEntity> {
        val response = rickAndMortyApi.getCharacters(page)
        return response.results.map { it.toEntity() }
    }

    companion object {
        private const val DOWNLOAD_DELAY = 100L
        private const val MIN_PAGES_TO_DOWNLOAD = 1
        private const val MAX_PAGES_TO_DOWNLOAD = 3
    }
}
