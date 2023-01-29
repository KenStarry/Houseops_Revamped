package com.example.houseops_revamped.feature_home.house_view_screen.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.houseops_revamped.feature_home.house_view_screen.presentation.components.HouseViewPager
import com.example.houseops_revamped.feature_home.house_view_screen.presentation.components.house_view_details.HouseViewDetails
import com.example.houseops_revamped.feature_home.house_view_screen.presentation.viewmodel.HouseViewVM

@Composable
fun HouseViewScreen(
    navHostController: NavHostController,
    apartment: String,
    category: String
) {

    val houseViewVM: HouseViewVM = hiltViewModel()
    val context = LocalContext.current

    houseViewVM.getHouse(apartment, category)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.onPrimary)
            .verticalScroll(rememberScrollState())
            .padding(12.dp)
    ) {

        houseViewVM.currentHouse?.let {

            //  view pager
            HouseViewPager(
                house = it,
                context = context,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )
            
            Spacer(modifier = Modifier.height(24.dp))

            //  main content
            HouseViewDetails(
                house = it
            )
        }
    }

}


















