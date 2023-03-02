package com.example.houseops_revamped.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.houseops_revamped.core.presentation.utils.Constants.AUTHENTICATION_ROUTE
import com.example.houseops_revamped.core.presentation.utils.Constants.HOME_ROUTE
import com.example.houseops_revamped.core.presentation.utils.Constants.LANDLORD_ROUTE
import com.example.houseops_revamped.core.presentation.utils.Constants.LOADING_ROUTE
import com.example.houseops_revamped.core.presentation.utils.Constants.ROOT_ROUTE
import com.example.houseops_revamped.feature_authentication.domain.utils.AuthConstants

@Composable
fun RootNavGraph(
    navHostController: NavHostController,
    isLoggedIn: Boolean,
    userType: String?
) {

    NavHost(
        navController = navHostController,
        startDestination = if (isLoggedIn && userType == AuthConstants.userTypes[0].userTitle)
            LANDLORD_ROUTE
        else if (isLoggedIn && userType == AuthConstants.userTypes[1].userTitle)
            HOME_ROUTE
        else if (!isLoggedIn)
            AUTHENTICATION_ROUTE
        else
            LOADING_ROUTE,

        route = ROOT_ROUTE
    ) {

        loadingNavGraph(navHostController)
        authNavGraph(navHostController = navHostController)
        homeNavGraph(navHostController = navHostController)
        landlordNavGraph(navHostController = navHostController)
    }

}