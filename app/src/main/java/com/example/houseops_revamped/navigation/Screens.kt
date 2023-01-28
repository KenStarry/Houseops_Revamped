package com.example.houseops_revamped.navigation

import com.example.houseops_revamped.core.utils.Constants
import com.example.houseops_revamped.feature_home.home_screen.domain.model.HouseModel

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

    //  house view screen
    object HouseView : Screens(route = "house_view_screen/{house}") {
        fun passHouse(houseModel: HouseModel): String = "house_view_screen/$houseModel"
    }
}













