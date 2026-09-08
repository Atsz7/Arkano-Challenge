package com.atsz7.rm.roster.ui.main.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.atsz7.rm.roster.common.ui.components.BasicRow
import com.atsz7.rm.roster.common.ui.extensions.statusToBadge
import com.atsz7.rm.roster.common.ui.theme.RMRosterTheme
import com.atsz7.rm.roster.common.ui.utils.getShapeByIndex
import com.atsz7.rm.roster.domain.model.Character
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

fun LazyListScope.charactersListSection(characters: ImmutableList<Character>) {

    itemsIndexed(
        items = characters,
        key = { _, character -> character.id }
    ) { index, character ->

        val shape = remember(index, characters.size) {
            getShapeByIndex(
                index = index,
                size = characters.size
            )
        }

        BasicRow(
            modifier = Modifier.fillMaxSize(),
            title = character.name,
            subtitle = character.specie,
            imageUrl = character.imageUrl,
            badge = character.status.statusToBadge(),
            shape = shape
        )

        if (index < characters.size - 1) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(RMRosterTheme.dimens.extraTinySize)
                    .padding(horizontal = RMRosterTheme.dimens.mediumSize)
                    .background(MaterialTheme.colorScheme.background)
            )
        }
    }
}

private val previewCharacters: ImmutableList<Character> = persistentListOf(
    Character(
        id = 1,
        name = "Rick Sanchez",
        status = "Alive",
        specie = "Human",
        gender = "Male",
        originName = "Earth",
        locationName = "Earth",
        imageUrl = ""
    ),
    Character(
        id = 2,
        name = "Morty Smith",
        status = "Alive",
        specie = "Human",
        gender = "Male",
        originName = "Earth",
        locationName = "Earth",
        imageUrl = ""
    ),
    Character(
        id = 3,
        name = "Summer Smith",
        status = "Unknown",
        specie = "Human",
        gender = "Female",
        originName = "Earth",
        locationName = "Earth",
        imageUrl = ""
    )
)

@Preview(showBackground = true)
@Composable
private fun MainCharactersListSectionPreview() {
    RMRosterTheme {
        LazyColumn {
            charactersListSection(previewCharacters)
        }
    }
}
