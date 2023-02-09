package com.example.houseops_revamped.core.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material.icons.outlined.Hotel
import androidx.compose.material.icons.outlined.LocalCafe
import androidx.compose.material.icons.outlined.LocalHotel
import com.example.houseops_revamped.core.domain.model.HouseCategoryModel

object Constants {

    //  routes
    //  constants for the routes
    const val ROOT_ROUTE = "root_route"
    const val SPLASH_ROUTE = "splash_route"
    const val HOME_ROUTE = "home_route"
    const val AUTHENTICATION_ROUTE = "authentication_route"
    const val CARETAKER_ROUTE = "caretaker_route"

    //  route arguments
    //  caretaker registration
    const val CARETAKER_HAS_REGISTERED = "has_registered"

    //  Arguments
    const val CURRENT_USER = "user"

    //  Cloud firestore
    const val USERS_COLLECTION = "users"
    const val CARETAKER_COLLECTION = "caretakers"
    const val APARTMENTS_COLLECTION = "apartments"
    const val HOUSES_SUB_COLLECTION = "houses"

    //  Cloud storage
    const val USER_IMAGES = "user_images"

    //  bottomsheet types
    const val CARETAKER_BOTTOM_SHEET_TYPE = "caretaker bottomsheet"

    val priceCategories = listOf(
        "month",
        "year",
        "quarter",
        "6 months",
    )

    val houseCategories = listOf(
        HouseCategoryModel("One Bedroom", Icons.Outlined.Apartment),
        HouseCategoryModel("Two Bedroom", Icons.Outlined.Hotel),
        HouseCategoryModel("Bedsitter", Icons.Outlined.LocalCafe),
        HouseCategoryModel("Single", Icons.Outlined.LocalHotel),
    )

    //  alert dialog types
    const val BOOK_HOUSE_ALERT = "book house alert dialog"
}