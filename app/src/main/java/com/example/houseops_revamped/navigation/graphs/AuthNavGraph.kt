package com.example.houseops_revamped.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.houseops_revamped.core.utils.Constants.AUTHENTICATION_ROUTE
import com.example.houseops_revamped.navigation.Screens
import com.example.houseops_revamped.feature_authentication.login.presentation.LoginScreen
import com.example.houseops_revamped.feature_authentication.sign_up.presentation.SignUpScreen

fun NavGraphBuilder.authNavGraph(
    navHostController: NavHostController
) {

    //  login and sign up screen
    navigation(
        startDestination = Screens.Login.route,
        route = AUTHENTICATION_ROUTE
    ) {

        //  login screen
        composable(route = Screens.Login.route) {
            LoginScreen(navHostController = navHostController)
        }

        //  signup screen
        composable(route = Screens.SignUp.route) {
            SignUpScreen(navHostController = navHostController)
        }

    }
}