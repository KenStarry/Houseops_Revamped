package com.example.houseops_revamped.feature_bookmark.presentation.components

import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.houseops_revamped.core.domain.model.HouseCategoryModel
import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.example.houseops_revamped.core.domain.use_cases.CurrentUser
import com.example.houseops_revamped.feature_home.home_screen.domain.model.HouseModel

@Composable
fun BookmarkCategories(
    context: Context,
    houseCategories: List<HouseCategoryModel>,
    bookmarkedHouses: List<HouseModel>,
    currentUser: UsersCollection?
) {

    LazyColumn(
        content = {

            itemsIndexed(
                houseCategories
            ) { index, category ->

                //  category item
                CategoryItem(
                    context = context,
                    houseCategory = category,
                    bookmarkedHouses = bookmarkedHouses,
                    currentUser = currentUser
                )
            }
        },
        state = rememberLazyListState(),
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
    )
}




















