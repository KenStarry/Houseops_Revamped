package com.kenstarry.houseops_revamped.feature_tenant.feature_bookmark.presentation.components

import android.content.Context
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.kenstarry.houseops_revamped.R
import com.kenstarry.houseops_revamped.core.domain.model.HouseCategoryModel
import com.kenstarry.houseops_revamped.core.domain.model.UsersCollection
import com.kenstarry.houseops_revamped.core.presentation.components.Lottie
import com.kenstarry.houseops_revamped.feature_tenant.feature_bookmark.domain.model.BookmarkEvents
import com.kenstarry.houseops_revamped.feature_tenant.feature_bookmark.presentation.viewmodel.BookmarksViewModel
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.domain.model.HouseModel

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BookmarkCategories(
    bookmarksVM: BookmarksViewModel,
    navHostController: NavHostController,
    context: Context,
    houseCategories: List<HouseCategoryModel>,
    bookmarkedHouses: List<HouseModel>,
    currentUser: UsersCollection?,
    snackbarHostState: SnackbarHostState,
    primaryColor: Color,
    tertiaryColor: Color
) {

    val pagerState = rememberPagerState()

    bookmarksVM.onEvent(
        BookmarkEvents.FilterCategories(
            houseCategories, bookmarkedHouses
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        HorizontalPagerIndicator(
            pagerState = pagerState,
            activeColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f),
            inactiveColor = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.1f),
            indicatorHeight = 5.dp,
            indicatorWidth = 5.dp,
            indicatorShape = CircleShape,
            spacing = 4.dp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )

        //  horizontal pager and indicators
        AnimatedVisibility(
            visible = bookmarksVM.listOfCategories.value.isNotEmpty()
        ) {

            HorizontalPager(
                count = bookmarksVM.listOfCategories.value.size,
                state = pagerState,
                itemSpacing = 16.dp,
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.onPrimary)
            ) { page ->
                //  category item
                CategoryItem(
                    context = context,
                    navHostController = navHostController,
                    houseCategory = bookmarksVM.listOfCategories.value[page],
                    bookmarkedHouses = bookmarkedHouses,
                    currentUser = currentUser,
                    snackbarHostState = snackbarHostState,
                    primaryColor = primaryColor,
                    tertiaryColor = tertiaryColor
                )
            }
        }

        //  no bookmarks svg
        AnimatedVisibility(visible = bookmarksVM.listOfCategories.value.isEmpty()) {

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                //  display lottie animation
                Lottie(
                    rawFile = R.raw.paperplane,
                    isPlaying = true,
                    iterations = Integer.MAX_VALUE,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(250.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = "No Bookmarks yet...",
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.8f)
                )

            }
        }
    }
}




















