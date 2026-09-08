package com.atsz7.rm.roster.core.data.remote.api

import com.atsz7.rm.roster.core.data.remote.dto.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RickAndMortyApi {

    @GET("character")
    suspend fun getCharacters(
        @Query("page") page: Int
    ): CharacterResponse
}
