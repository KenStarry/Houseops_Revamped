package com.example.houseops_revamped.navigation

import androidx.navigation.NavHostController

class Direction(
    navHostController: NavHostController
) {

    //  navigate to caretaker registration
    val navigateToCaretakerRegistration: (registeredStatus: Boolean) -> Unit = { status ->
        navHostController.navigate(Screens.CaretakerRegistration.hasCaretakerRegistred(status = status))
    }

    //  navigate back
    val navigateBack: () -> Unit = {
        navHostController.navigateUp()
    }
}