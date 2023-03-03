package com.example.houseops_revamped.navigation

import androidx.navigation.NavHostController
import com.example.houseops_revamped.navigation.screens.Screens

class Direction(
    navHostController: NavHostController
) {

    val navigateToHouseView: (
        apartment: String,
        category: String
    ) -> Unit = { apartment, category ->
        navHostController.navigate(Screens.HouseView.passHouse(apartment, category))
    }

    val navigateToCategory: (categoryTitle: String) -> Unit = {
        navHostController.navigate(Screens.Categories.passCategoryTitle(it))
    }

    //  navigate to route
    val navigateToRoute: (
        route: String,
        isPop: Boolean
    ) -> Unit = { route, isPop ->

        if (isPop) {

            navHostController.navigate(route = route) {
                popUpTo(route = route)
                launchSingleTop = true
            }

        } else {
            navHostController.navigate(route = route)
        }
    }

    //  navigate back
    val navigateUp: () -> Unit = {
        navHostController.navigateUp()
    }
}














