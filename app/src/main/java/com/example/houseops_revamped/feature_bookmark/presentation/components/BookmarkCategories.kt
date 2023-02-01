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

    val pagerState = rememberPagerState()

    val listOfCategories = ArrayList<HouseCategoryModel>()

    houseCategories.forEach { category ->
        if (bookmarkedHouses.map { it.houseCategory }.contains(category.title)) {
            listOfCategories.add(category)
        }
    }

    Log.d("category", listOfCategories.toString())

    HorizontalPager(
        count = listOfCategories.size,
        state = pagerState,
        itemSpacing = 24.dp
    ) {page ->
        //  category item
        CategoryItem(
            context = context,
            houseCategory = listOfCategories[page],
            bookmarkedHouses = bookmarkedHouses,
            currentUser = currentUser
        )
    }

//    LazyColumn(
//        content = {
//
//            itemsIndexed(
//                houseCategories
//            ) { index, category ->
//
//                AnimatedVisibility(
//                    visible = bookmarkedHouses.map { it.houseCategory }
//                        .contains(category.title),
//                    enter = fadeIn() + scaleIn(
//                        animationSpec = tween(
//                            durationMillis = 500
//                        )
//                    ),
//                    exit = fadeOut() + scaleOut(
//                        animationSpec = tween(
//                            durationMillis = 500
//                        )
//                    ),
//                    label = "Bookmarks animated visibility"
//                ) {
//
//                    //  category item
//                    CategoryItem(
//                        context = context,
//                        houseCategory = category,
//                        bookmarkedHouses = bookmarkedHouses,
//                        currentUser = currentUser
//                    )
//                }
//            }
//        },
//        state = rememberLazyListState(),
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(500.dp)
//    )
}




















