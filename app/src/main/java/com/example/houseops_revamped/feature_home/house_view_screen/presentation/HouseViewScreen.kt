package com.example.houseops_revamped.feature_home.house_view_screen.presentation

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
import com.example.houseops_revamped.core.presentation.viewmodel.CoreViewModel
import com.example.houseops_revamped.feature_home.house_view_screen.presentation.components.view_pager.HouseViewPager
import com.example.houseops_revamped.feature_home.house_view_screen.presentation.components.house_view_details.HouseViewDetails
import com.example.houseops_revamped.feature_home.house_view_screen.presentation.viewmodel.HouseViewVM

@Composable
fun HouseViewScreen(
    navHostController: NavHostController,
    apartment: String,
    category: String
) {

    val houseViewVM: HouseViewVM = hiltViewModel()
    val coreVM: CoreViewModel = hiltViewModel()

    val currentUser = coreVM.currentUser()
    val userDetails = coreVM.getUserDetails(currentUser?.email ?: "no email")
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
                    .height(300.dp),
                navHostController = navHostController
            )
            
            Spacer(modifier = Modifier.height(24.dp))

            //  main content
            HouseViewDetails(
                context = context,
                house = it,
                userDetails = userDetails
            )
        }
    }

}


















