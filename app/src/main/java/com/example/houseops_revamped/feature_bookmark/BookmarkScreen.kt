package com.example.houseops_revamped.feature_bookmark

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.houseops_revamped.feature_bookmark.presentation.components.BookmarksAppBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookmarkScreen(
    navHostController: NavHostController
) {

    Scaffold(
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




        }

    }

}