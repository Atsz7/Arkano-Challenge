package com.atsz7.rm.roster.domain.usecases

import com.atsz7.rm.roster.domain.model.Character
import com.atsz7.rm.roster.domain.repositories.CharactersRepository
import kotlinx.collections.immutable.ImmutableList
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
) {
    operator fun invoke(): Flow<ImmutableList<Character>> =
        charactersRepository.getCharacters()
}
