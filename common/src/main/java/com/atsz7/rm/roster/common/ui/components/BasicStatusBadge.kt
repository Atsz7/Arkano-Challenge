package com.atsz7.rm.roster.common.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.atsz7.rm.roster.common.ui.extensions.color
import com.atsz7.rm.roster.common.ui.extensions.label
import com.atsz7.rm.roster.common.ui.models.BasicBadge
import com.atsz7.rm.roster.common.ui.theme.RMRosterTheme

@Composable
fun BasicStatusBadge(
    badge: BasicBadge,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(RMRosterTheme.dimens.smallSize))
            .background(badge.color)
            .padding(all = RMRosterTheme.dimens.tinySize)
    ) {
        Text(
            text = stringResource(badge.label),
            color = Color.White,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BasicStatusBadgePreview() {
    RMRosterTheme {
        BasicStatusBadge(
            modifier = Modifier,
            badge = BasicBadge.ALIVE
        )
    }
}
