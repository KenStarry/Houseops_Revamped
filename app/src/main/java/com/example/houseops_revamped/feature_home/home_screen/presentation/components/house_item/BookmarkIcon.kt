package com.example.houseops_revamped.feature_home.home_screen.presentation.components.house_item

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.houseops_revamped.core.domain.model.BookmarkHouse
import com.example.houseops_revamped.core.domain.model.CoreEvents
import com.example.houseops_revamped.core.domain.model.LikedHouse
import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.example.houseops_revamped.core.utils.Constants
import com.example.houseops_revamped.feature_home.home_screen.domain.model.HouseModel

@Composable
fun BookmarkIcon(
    house: HouseModel,
    user: UsersCollection?
) {

    val coreVM: CoreViewModel = hiltViewModel()

    var isBookmarked by remember {
        mutableStateOf(false)
    }

    if (user?.userBookmarks?.contains(
            LikedHouse(
                apartmentName = house.houseApartmentName,
                houseCategory = house.houseCategory
            )
        ) == true
    ) {
        isBookmarked = true
    }

    IconButton(
        onClick = {
            isBookmarked = !isBookmarked

            coreVM.onEvent(
                CoreEvents.UpdateArrayField(
                    collectionName = Constants.USERS_COLLECTION,
                    documentName = user?.userEmail ?: "none",
                    fieldName = "userBookmarks",
                    fieldValue = LikedHouse(
                        apartmentName = house.houseApartmentName,
                        houseCategory = house.houseCategory
                    ),
                    isAddItem = isBookmarked
                )
            )
        },
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.tertiary
        )
    ) {
        Icon(
            imageVector = if (isBookmarked)
                Icons.Filled.Bookmark
            else
                Icons.Outlined.BookmarkBorder,
            contentDescription = "Bookmark",
            tint = MaterialTheme.colorScheme.primary
        )
    }
}


















