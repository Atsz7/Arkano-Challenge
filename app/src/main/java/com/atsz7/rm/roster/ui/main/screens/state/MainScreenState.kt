package com.atsz7.rm.roster.ui.main.screens.state

import com.atsz7.rm.roster.domain.model.Character
import kotlinx.collections.immutable.ImmutableList

sealed class MainScreenState {

    data class Success(
        val characters: ImmutableList<Character>,
        val isRefreshing: Boolean
    ) : MainScreenState()

    data object Error : MainScreenState()
}
