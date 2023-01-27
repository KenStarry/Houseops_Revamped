package com.example.houseops_revamped.feature_home.presentation.components.house_item

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.houseops_revamped.feature_home.domain.model.HouseModel

@Composable
fun BookmarkIcon(
    house: HouseModel
) {

    var isBookmarked by remember {
        mutableStateOf(false)
    }

    IconButton(
        onClick = {
            isBookmarked = !isBookmarked
        },
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.tertiary
        )
    ) {
        Icon(
            imageVector = Icons.Outlined.BookmarkBorder,
            contentDescription = "Bookmark",
            tint = MaterialTheme.colorScheme.primary
        )
    }
}


















