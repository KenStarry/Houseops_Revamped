package com.kenstarry.houseops_revamped.navigation.screens

sealed class Screens(
    val route: String
) {

    //  main screen
    object Main : Screens(route = "main_screen")

    object Dashboard : Screens(route = "dashboard_screen")

    //  login screen
    object Login : Screens(route = "login_screen")

    object Loading : Screens(route = "loading_screen/{email}") {
        fun passEmail(email: String) = "loading_screen/$email"
    }

    object ForgotPassword : Screens(route = "forgot_password_screen")

    //  signup screen
    object SignUp : Screens(route = "sign_up_screen")

    //  house view screen
    object HouseView : Screens(route = "house_view_screen/{apartment}/{category}") {
        fun passHouse(
            apartment: String,
            category: String
        ): String = "house_view_screen/$apartment/$category"
    }

    //  caretakers screen
    object Categories : Screens(route = "categories_screen/{categoryTitle}") {
        fun passCategoryTitle(title: String) = "categories_screen/$title"
    }
}













