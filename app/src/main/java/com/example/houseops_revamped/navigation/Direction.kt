package com.example.houseops_revamped.navigation

import androidx.navigation.NavHostController

class Direction(
    navHostController: NavHostController
) {

    //  navigate to admin page
    val navigateToAdminPage: () -> Unit = {
        navHostController.navigate(Screens.Admin.route)
    }

    val navigateToHouseView: (
        apartment: String,
        category: String
    ) -> Unit = { apartment, category ->
        navHostController.navigate(Screens.HouseView.passHouse(apartment, category))
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














