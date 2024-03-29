package com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.components.house_item

import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.kenstarry.houseops_revamped.core.domain.model.UsersCollection
import com.kenstarry.houseops_revamped.core.domain.model.events.CoreEvents
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants
import com.kenstarry.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.houseops_revamped.core.domain.model.HouseModel

@Composable
fun BookmarkIcon(
    house: HouseModel,
    user: UsersCollection?,
    primaryColor: Color,
    tertiaryColor: Color
) {

    val coreVM: CoreViewModel = hiltViewModel()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var isBookmarked by remember {
        mutableStateOf(false)
    }

    if (user?.userBookmarks?.contains(house.houseId) == true) {
        isBookmarked = true
    }

    IconButton(
        onClick = {
            isBookmarked = !isBookmarked

            if (isBookmarked) {
                Toast.makeText(context, "Bookmark Added Successfully!", Toast.LENGTH_SHORT).show()
            } else
                Toast.makeText(context, "Bookmark Removed", Toast.LENGTH_SHORT).show()

            coreVM.onEvent(
                CoreEvents.UpdateArrayField(
                    collectionName = Constants.USERS_COLLECTION,
                    documentName = user?.userEmail ?: "none",
                    fieldName = "userBookmarks",
                    fieldValue = house.houseId,
                    isAddItem = isBookmarked
                )
            )
        },
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = tertiaryColor
        )
    ) {
        if (isBookmarked) {

            Icon(
                imageVector = Icons.Filled.Bookmark,
                contentDescription = "Bookmark",
                tint = primaryColor
            )
        } else {
            Icon(
                imageVector = Icons.Outlined.BookmarkBorder,
                contentDescription = "Bookmark",
                tint = primaryColor
            )
        }

    }
}


















