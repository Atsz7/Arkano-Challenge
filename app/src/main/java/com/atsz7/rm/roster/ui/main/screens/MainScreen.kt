package com.atsz7.rm.roster.ui.main.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.atsz7.rm.roster.common.ui.theme.RMRosterTheme
import com.atsz7.rm.roster.ui.main.screens.state.MainScreenState
import com.atsz7.rm.roster.ui.main.viewmodels.MainViewModel
import kotlinx.coroutines.launch

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

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val showScrollToTopButton by remember {
        derivedStateOf { listState.firstVisibleItemIndex > 0 }
    }

    Scaffold(
        topBar = { MainTopBar() },
        floatingActionButton = {
            MainScrollToTopButton(
                visible = showScrollToTopButton,
                onClick = {
                    coroutineScope.launch { listState.animateScrollToItem(0) }
                }
            )
        }
    ) { innerPadding ->

        PullToRefreshBox(
            modifier = Modifier.padding(innerPadding),
            isRefreshing = isRefreshing,
            onRefresh = onRefresh
        ) {
            LazyColumn(
                state = listState,
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
