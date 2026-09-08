package com.atsz7.rm.roster.domain.usecases

import com.atsz7.rm.roster.domain.repositories.CharactersRepository
import javax.inject.Inject

class DownloadCharactersUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository
) {
    suspend operator fun invoke(forceRefresh: Boolean = false) =
        charactersRepository.downloadCharacters(forceRefresh)
}
