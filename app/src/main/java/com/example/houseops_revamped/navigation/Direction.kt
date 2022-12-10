package com.example.houseops_revamped.navigation

import androidx.navigation.NavHostController

class Direction(
    navHostController: NavHostController
) {

    //  navigate to caretaker registration
    val navigateToCaretakerRegistration: () -> Unit = {
        navHostController.navigate(Screens.CaretakerRegistration.route)
    }

    //  navigate back
    val navigateBack: () -> Unit = {
        navHostController.navigateUp()
    }
}