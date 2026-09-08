package com.atsz7.rm.roster.ui.screens

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.atsz7.rm.roster.R
import com.atsz7.rm.roster.common.ui.theme.RMRosterTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.titleLarge
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun MainTopBarPreview() {
    RMRosterTheme {
        MainTopBar()
    }
}
