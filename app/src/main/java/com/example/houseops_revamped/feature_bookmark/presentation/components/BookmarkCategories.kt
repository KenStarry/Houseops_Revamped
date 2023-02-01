package com.example.houseops_revamped.feature_bookmark.presentation.components

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.houseops_revamped.core.domain.model.HouseCategoryModel
import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.example.houseops_revamped.feature_bookmark.domain.model.BookmarkEvents
import com.example.houseops_revamped.feature_bookmark.presentation.viewmodel.BookmarksViewModel
import com.example.houseops_revamped.feature_home.home_screen.domain.model.HouseModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
fun BookmarkCategories(
    bookmarksVM: BookmarksViewModel,
    context: Context,
    houseCategories: List<HouseCategoryModel>,
    bookmarkedHouses: List<HouseModel>,
    currentUser: UsersCollection?
) {

    bookmarksVM.onEvent(BookmarkEvents.FilterCategories(
        houseCategories, bookmarkedHouses
    ))

    HorizontalPager(
        count = bookmarksVM.listOfCategories.size,
        state = rememberPagerState(),
        itemSpacing = 24.dp
    ) {page ->
        //  category item
        CategoryItem(
            context = context,
            houseCategory = bookmarksVM.listOfCategories[page],
            bookmarkedHouses = bookmarkedHouses,
            currentUser = currentUser
        )
    }
}




















