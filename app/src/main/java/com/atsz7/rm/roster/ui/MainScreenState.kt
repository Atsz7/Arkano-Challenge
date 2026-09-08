package com.atsz7.rm.roster.ui

import com.atsz7.rm.roster.domain.model.Character

sealed class MainScreenState {
    data object Idle : MainScreenState()
    data object Loading : MainScreenState()
    data class Success(val characters: List<Character>) : MainScreenState()
    data object Error : MainScreenState()
}
