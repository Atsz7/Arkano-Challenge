package com.atsz7.rm.roster.common.ui.extensions

import androidx.compose.ui.graphics.Color
import com.atsz7.rm.roster.common.R
import com.atsz7.rm.roster.common.ui.models.BasicBadge

private const val ALIVE_STATUS = "Alive"
private const val DEAD_STATUS = "Dead"

/**
 * Converts a [String] status to a [BasicBadge].
 * @return [BasicBadge].
 */
fun String.statusToBadge(): BasicBadge {
    return when (this) {
        ALIVE_STATUS -> BasicBadge.ALIVE
        DEAD_STATUS -> BasicBadge.DEAD
        else -> BasicBadge.UNKNOWN
    }
}

/**
 * Returns a string resource [Int] depending on [BasicBadge] type.
 */
val BasicBadge.label: Int
    get() = when (this) {
        BasicBadge.ALIVE -> R.string.badge_alive_label
        BasicBadge.DEAD -> R.string.badge_dead_label
        BasicBadge.UNKNOWN -> R.string.badge_unknown_label
    }

/**
 * Returns a [Color] depending on [BasicBadge] type.
 */
val BasicBadge.color: Color
    get() = when (this) {
        BasicBadge.ALIVE -> Color(0xFF1B5E20)
        BasicBadge.DEAD -> Color(0xFF7A0C0C)
        BasicBadge.UNKNOWN -> Color(0xFF8C6D00)
    }
