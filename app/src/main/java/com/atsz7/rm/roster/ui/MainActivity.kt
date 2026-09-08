package com.atsz7.rm.roster.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.atsz7.rm.roster.common.ui.theme.RMRosterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val mainViewModel: MainViewModel by viewModels()

        setContent {
            RMRosterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        mainViewModel = mainViewModel
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier, mainViewModel: MainViewModel) {

    val mainState by mainViewModel.mainState.collectAsStateWithLifecycle()

    when (val state = mainState) {
        MainScreenState.Idle, MainScreenState.Loading -> {}
        MainScreenState.Error -> {}
        is MainScreenState.Success -> {
            LazyColumn(modifier = modifier) {
                items(state.characters, key = { it.id }) { character ->
                    Text(character.name)
                }
            }
        }
    }
}
