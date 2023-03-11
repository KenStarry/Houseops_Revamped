package com.kenstarry.houseops_revamped.navigation.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants.AUTHENTICATION_ROUTE
import com.kenstarry.houseops_revamped.feature_authentication.presentation.forgot_password.presentation.ForgotPasswordScreen
import com.kenstarry.houseops_revamped.feature_authentication.presentation.login.presentation.LoginScreen
import com.kenstarry.houseops_revamped.feature_authentication.presentation.sign_up.presentation.SignUpScreen
import com.kenstarry.houseops_revamped.navigation.screens.Screens

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

        //  forgot password screen
        composable(route = Screens.ForgotPassword.route) {
            ForgotPasswordScreen(navHostController = navHostController)
        }

        //  signup screen
        composable(route = Screens.SignUp.route) {
            SignUpScreen(navHostController = navHostController)
        }

    }
}