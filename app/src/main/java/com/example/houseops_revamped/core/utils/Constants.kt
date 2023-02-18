package com.example.houseops_revamped.core.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material.icons.outlined.Hotel
import androidx.compose.material.icons.outlined.LocalCafe
import androidx.compose.material.icons.outlined.LocalHotel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import com.example.houseops_revamped.core.domain.model.HouseCategoryModel
import com.example.houseops_revamped.core.presentation.model.AccentColor

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

    //  colors
    val primaryCol = mutableStateOf(Color(0xFF3DB2EC))
    val tertiaryCol = mutableStateOf(Color(0xFF3DB2EC).copy(alpha = 0.1f))

    val accentColors = listOf(
        AccentColor(
            darkColor = Color(0xFF3DB2EC),
            lightColor = Color(0xFF3DB2EC).copy(alpha = 0.1f)
        ),
        AccentColor(
            darkColor = Color(0xFFFF4309),
            lightColor = Color(0xFFFF4309).copy(alpha = 0.1f)
        ),
        AccentColor(
            darkColor = Color(0xFF288B0D),
            lightColor = Color(0xFF288B0D).copy(alpha = 0.1f)
        ),
        AccentColor(
            darkColor = Color(0xFFFF0A5B),
            lightColor = Color(0xFFFF0A5B).copy(alpha = 0.1f)
        ),
        AccentColor(
            darkColor = Color(0xFF0BCDE6),
            lightColor = Color(0xFF0BCDE6).copy(alpha = 0.1f)
        ),
        AccentColor(
            darkColor = Color(0xffcf94da),
            lightColor = Color(0xffcf94da).copy(alpha = 0.1f)
        ),
        AccentColor(
            darkColor = Color(0xFFDDEE0B),
            lightColor = Color(0xFFDDEE0B).copy(alpha = 0.1f)
        ),
    )
}





























