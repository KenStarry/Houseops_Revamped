package com.example.houseops_revamped.feature_categories

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.example.houseops_revamped.feature_categories.presentation.components.CategoriesTopBar
import com.example.houseops_revamped.feature_home.home_screen.presentation.utils.HomeConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriesScreen(
    navHostController: NavHostController,
    categoryTitle: String
) {

    val coreVM: CoreViewModel = hiltViewModel()
    val categories = HomeConstants.homePills.map { it.title }

    Scaffold(
        topBar = {
            CategoriesTopBar(
                title = categoryTitle,
                onBackPressed = {}
            )
        }
    ) { contentPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onPrimary)
                .padding(contentPadding)
        ) {

            when (categoryTitle) {

                //  House Categories screen
                categories[0] -> {

                }
                //  For Rent screen
                categories[1] -> {

                }
                //  Apartments screen
                categories[2] -> {

                }
                //  For Sale screen
                categories[3] -> {

                }
                //  Near You screen
                categories[4] -> {

                }
                //  Caretakers screen
                categories[5] -> {

                }

            }

        }


    }
}