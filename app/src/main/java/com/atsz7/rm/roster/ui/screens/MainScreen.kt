package com.atsz7.rm.roster.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.atsz7.rm.roster.common.ui.theme.RMRosterTheme
import com.atsz7.rm.roster.ui.screens.state.MainScreenState
import com.atsz7.rm.roster.ui.viewmodels.MainViewModel

@Composable
fun MainScreen(mainViewModel: MainViewModel) {
    val mainState by mainViewModel.mainState.collectAsStateWithLifecycle()
    MainScreen(mainState)
}

@Composable
private fun MainScreen(mainState: MainScreenState) {

    Scaffold(
        topBar = { MainTopBar() }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(RMRosterTheme.dimens.mediumSize)
        ) {
            when (mainState) {

                MainScreenState.Idle, MainScreenState.Loading -> {
                    // TODO: Use pull to refresh indicator here.
                }

                MainScreenState.Error -> {
                    mainErrorSection(
                        onRetryClick = {
                            // TODO: Call refresh method here.
                        }
                    )
                }

                is MainScreenState.Success -> charactersListSection(mainState.characters)
            }
        }
    }
}
