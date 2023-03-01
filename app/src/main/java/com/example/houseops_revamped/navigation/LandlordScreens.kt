package com.example.houseops_revamped.navigation

sealed class LandlordScreens(
    val route: String
) {

    object Main : LandlordScreens(NavConstants.LANDLORD_MAIN_SCREEN_ROUTE)

    object AddApartment : LandlordScreens(NavConstants.LANDLORD_ADD_APARTMENT_SCREEN_ROUTE)

}
