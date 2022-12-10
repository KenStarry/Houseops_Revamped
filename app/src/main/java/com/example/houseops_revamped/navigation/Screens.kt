package com.example.houseops_revamped.navigation

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

    //  caretaker registration screen
    object CaretakerRegistration : Screens(route = "caretaker_registration")
}
