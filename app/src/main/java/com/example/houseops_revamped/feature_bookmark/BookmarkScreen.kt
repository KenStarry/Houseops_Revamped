package com.example.houseops_revamped.feature_bookmark

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.example.houseops_revamped.core.utils.Constants
import com.example.houseops_revamped.feature_bookmark.presentation.components.BookmarkCategories
import com.example.houseops_revamped.feature_bookmark.presentation.components.BookmarksAppBar
import com.example.houseops_revamped.feature_bookmark.presentation.viewmodel.BookmarksViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkScreen(
    navHostController: NavHostController
) {

    val coreVM: CoreViewModel = hiltViewModel()
    val bookmarksVM: BookmarksViewModel = hiltViewModel()
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }

    val currentUser = coreVM.currentUser()
    val userDetails = coreVM.getUserDetails(currentUser?.email ?: "no email")

    bookmarksVM.getBookmarks(currentUser?.email ?: "no email")
    bookmarksVM.getBookmarkedHouses(bookmarksVM.bookmarks.value)

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            BookmarksAppBar(
                onBackPressed = {
                    //  navigate to home screen
                }
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
                snackbarHostState = snackbarHostState
            )
        }

    }

}

















