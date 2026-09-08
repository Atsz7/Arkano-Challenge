package com.atsz7.rm.roster.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.atsz7.rm.roster.common.ui.theme.RMRosterTheme
import com.atsz7.rm.roster.ui.screens.MainScreen
import com.atsz7.rm.roster.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val mainViewModel: MainViewModel by viewModels()

        setContent {
            RMRosterTheme {
                MainScreen(mainViewModel = mainViewModel)
            }
        }
    }
}
