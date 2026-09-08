package com.atsz7.rm.roster.common.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.SubcomposeAsyncImage
import com.atsz7.rm.roster.common.R
import com.atsz7.rm.roster.common.ui.models.BasicBadge
import com.atsz7.rm.roster.common.ui.theme.RMRosterTheme

@Composable
fun BasicRow(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    imageUrl: String,
    badge: BasicBadge,
    shape: Shape
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = shape,
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = RMRosterTheme.dimens.extraExtraLargeSize
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = RMRosterTheme.dimens.mediumSize,
                    vertical = RMRosterTheme.dimens.smallSize
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(RMRosterTheme.dimens.extraExtraLargeSize)
                    .clip(RoundedCornerShape(RMRosterTheme.dimens.mediumSize))
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                SubcomposeAsyncImage(
                    modifier = Modifier
                        .matchParentSize()
                        .clip(RoundedCornerShape(RMRosterTheme.dimens.mediumSize)),
                    model = imageUrl,
                    contentDescription = title,
                    contentScale = ContentScale.Crop,
                    loading = {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(RMRosterTheme.dimens.largeSize),
                                strokeWidth = RMRosterTheme.dimens.extraTinySize,
                                color = MaterialTheme.colorScheme.onPrimaryContainer
                            )
                        }
                    },
                    error = {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.BrokenImage,
                                contentDescription = stringResource(R.string.broken_image_cd),
                                tint = MaterialTheme.colorScheme.onPrimaryContainer,
                                modifier = Modifier.size(RMRosterTheme.dimens.largeSize)
                            )
                        }
                    }
                )
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = RMRosterTheme.dimens.smallSize)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                BasicStatusBadge(badge = badge)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BasicRowPreview() {
    RMRosterTheme {
        BasicRow(
            title = "Rick Sanchez",
            subtitle = "Human",
            imageUrl = "",
            badge = BasicBadge.ALIVE,
            shape = RoundedCornerShape(RMRosterTheme.dimens.mediumSize)
        )
    }
}
