package com.atsz7.rm.roster.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.atsz7.rm.roster.R
import com.atsz7.rm.roster.common.ui.theme.RMRosterTheme

private const val ANIMATION_DURATION = 200

@Composable
fun MainScrollToTopButton(visible: Boolean, onClick: () -> Unit) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(animationSpec = tween(ANIMATION_DURATION))
                + scaleIn(animationSpec = tween(ANIMATION_DURATION)),
        exit = fadeOut(animationSpec = tween(ANIMATION_DURATION))
                + scaleOut(tween(ANIMATION_DURATION))
    ) {
        FloatingActionButton(
            modifier = Modifier.padding(RMRosterTheme.dimens.smallSize),
            onClick = onClick
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = stringResource(R.string.main_scroll_to_top_cd)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScrollToTopButtonPreview() {
    RMRosterTheme {
        MainScrollToTopButton(
            visible = true,
            onClick = { }
        )
    }
}
