package com.example.houseops_revamped.navigation

import androidx.navigation.NavHostController

class Direction(
    navHostController: NavHostController
) {

    //  navigate to caretaker registration
    val navigateToCaretakerRegistration: (registeredStatus: Boolean) -> Unit = { status ->
        navHostController.navigate(Screens.CaretakerRegistration.hasCaretakerRegistred(status = status))
    }

    //  navigate to admin page
    val navigateToAdminPage: () -> Unit = {
        navHostController.navigate(Screens.Admin.route)
    }

    //  navigate back
    val navigateBack: () -> Unit = {
        navHostController.navigateUp()
    }
}