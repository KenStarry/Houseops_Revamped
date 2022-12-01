package com.example.houseops_revamped.navigation

//  constants for the routes
const val ROOT_ROUTE = "root_route"
const val SPLASH_ROUTE = "splash_route"
const val HOME_ROUTE = "home_route"
const val AUTHENTICATION_ROUTE = "authentication_route"

sealed class Screens(
    val route: String
) {

    //  splash screen
    object Splash : Screens(route = "splash_screen")

    //  main screen
    object Main : Screens(route = "main_screen")

    //  login screen
    object Login : Screens(route = "login_screen")

    //  signup screen
    object SignUp : Screens(route = "sign_up_screen")
}
