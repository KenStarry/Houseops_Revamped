package com.kenstarry.houseops_revamped.feature_tenant.feature_bookmark

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants
import com.kenstarry.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.kenstarry.houseops_revamped.feature_tenant.feature_bookmark.presentation.components.BookmarkCategories
import com.kenstarry.houseops_revamped.feature_tenant.feature_bookmark.presentation.components.BookmarksAppBar
import com.kenstarry.houseops_revamped.feature_tenant.feature_bookmark.presentation.viewmodel.BookmarksViewModel
import com.kenstarry.houseops_revamped.navigation.Direction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkScreen(
    mainNavHostController: NavHostController,
    navHostController: NavHostController
) {

    val coreVM: CoreViewModel = hiltViewModel()
    val bookmarksVM: BookmarksViewModel = hiltViewModel()
    val direction = Direction(mainNavHostController)
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }

    val primaryColor = Color(
        coreVM.primaryAccentFlow.collectAsState(
            initial = Constants.accentColors[0].darkColor
        ).value ?: Constants.accentColors[0].darkColor
    )

    val tertiaryColor = Color(
        coreVM.tertiaryAccentFlow.collectAsState(
            initial = Constants.accentColors[0].lightColor
        ).value ?: Constants.accentColors[0].lightColor
    )

    val currentUser = coreVM.currentUser()
    val userDetails = coreVM.getUserDetails(currentUser?.email ?: "no email")

    bookmarksVM.getBookmarks(currentUser?.email ?: "no email")
    bookmarksVM.getBookmarkedHouses(bookmarksVM.bookmarks.value)

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            BookmarksAppBar(
                onBackPressed = {
                    //  go back to home screen
                    direction.navigateToRoute(
                        Constants.HOME_ROUTE,
                        Constants.HOME_ROUTE
                    )
                },
                onViewClicked = {

                },
                primaryColor = primaryColor,
                tertiaryColor = tertiaryColor
            )
        }
    ) { contentPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(contentPadding)
        ) {
            BookmarkCategories(
                houseCategories = Constants.houseCategories,
                navHostController = navHostController,
                bookmarkedHouses = bookmarksVM.bookmarkedHouses.value,
                currentUser = userDetails,
                context = context,
                bookmarksVM = bookmarksVM,
                snackbarHostState = snackbarHostState,
                primaryColor = primaryColor,
                tertiaryColor = tertiaryColor
            )
        }

    }

}

















