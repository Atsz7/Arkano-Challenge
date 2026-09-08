package com.atsz7.rm.roster.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.atsz7.rm.roster.R
import com.atsz7.rm.roster.common.ui.theme.RMRosterTheme

fun LazyListScope.mainErrorSection(onRetryClick: () -> Unit) {

    item {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(RMRosterTheme.dimens.mediumSize),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Icon(
                modifier = Modifier.size(RMRosterTheme.dimens.extraExtraLargeSize),
                imageVector = Icons.Default.ErrorOutline,
                contentDescription = stringResource(R.string.main_error_icon_cd),
                tint = MaterialTheme.colorScheme.error
            )

            Text(
                modifier = Modifier.padding(top = RMRosterTheme.dimens.smallSize),
                text = stringResource(R.string.main_error_message),
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center
            )

            Button(
                modifier = Modifier.padding(top = RMRosterTheme.dimens.largeSize),
                onClick = onRetryClick
            ) {
                Text(text = stringResource(R.string.main_error_retry_button))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainErrorSectionPreview() {
    RMRosterTheme {
        LazyColumn {
            mainErrorSection(
                onRetryClick = { }
            )
        }
    }
}
