package com.example.houseops_revamped.feature_booked.presentation

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
import com.example.houseops_revamped.core.utils.Constants
import com.example.houseops_revamped.feature_booked.presentation.components.BookedAppBar
import com.example.houseops_revamped.feature_bookmark.presentation.components.BookmarkCategories

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookedScreen(
    navHostController: NavHostController
) {

    Scaffold(
        topBar = {
            BookedAppBar(
                onBackPressed = {
                    //  go back to home screen
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