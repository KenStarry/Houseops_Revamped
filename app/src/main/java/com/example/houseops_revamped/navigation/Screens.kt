package com.example.houseops_revamped.navigation

import com.example.houseops_revamped.core.utils.Constants

sealed class Screens(
    val route: String
) {

    //  main screen
    object Main : Screens(route = "main_screen")

    //  login screen
    object Login : Screens(route = "login_screen")

    //  signup screen
    object SignUp : Screens(route = "sign_up_screen")

    //  admin screen
    object Admin : Screens(route = "admin_screen")
}













