package com.atsz7.rm.roster.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
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
    MainScreen(
        mainState = mainState,
        onRefresh = mainViewModel::onRefresh
    )
}

@Composable
private fun MainScreen(mainState: MainScreenState, onRefresh: () -> Unit) {

    val isRefreshing = (mainState as? MainScreenState.Success)?.isRefreshing ?: false

    Scaffold(
        topBar = { MainTopBar() }
    ) { innerPadding ->

        PullToRefreshBox(
            modifier = Modifier.padding(innerPadding),
            isRefreshing = isRefreshing,
            onRefresh = onRefresh
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(RMRosterTheme.dimens.mediumSize)
            ) {
                when (mainState) {

                    MainScreenState.Error -> {
                        mainErrorSection(onRetryClick = onRefresh)
                    }

                    is MainScreenState.Success -> charactersListSection(mainState.characters)
                }
            }
        }
    }
}
