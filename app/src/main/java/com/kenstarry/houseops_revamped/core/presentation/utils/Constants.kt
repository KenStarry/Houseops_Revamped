package com.kenstarry.houseops_revamped.core.presentation.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material.icons.outlined.Hotel
import androidx.compose.material.icons.outlined.LocalCafe
import androidx.compose.material.icons.outlined.LocalHotel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.kenstarry.houseops_revamped.core.domain.model.HouseCategoryModel
import com.kenstarry.houseops_revamped.core.presentation.model.AccentColor

object Constants {
    //  routes
    //  constants for the routes
    const val ROOT_ROUTE = "root_route"
    const val SPLASH_ROUTE = "splash_route"
    const val HOME_ROUTE = "home_route"
    const val AUTHENTICATION_ROUTE = "authentication_route"
    const val LANDLORD_ROUTE = "landlord"
    const val ADMIN_ROUTE = "admin_route"
    const val AGENT_ROUTE = "agent_route"
    const val LOADING_ROUTE = "loading"

    //  Arguments
    const val CURRENT_USER = "user"

    //  Cloud firestore
    const val USERS_COLLECTION = "users"
    const val CARETAKER_COLLECTION = "caretakers"
    const val LANDLORD_COLLECTION = "landlords"
    const val APARTMENTS_COLLECTION = "apartments"
    const val HOUSES_SUB_COLLECTION = "houses"

    //  Cloud storage
    const val USER_IMAGES = "user_images"

    //  bottomsheet types
    const val CARETAKER_BOTTOM_SHEET_TYPE = "caretaker bottomsheet"

    //  ADMIN EMAILS
    val adminEmails = listOf(
        "kennethmichuki17@gmail.com",
        "sheillakemboi68@gmail.com"
    )

    val agentEmails = listOf(
        "starrycodes@gmail.com",
        "chibobo@gmail.com"
    )

    val priceCategories = listOf(
        "month",
        "year",
        "quarter",
        "6 months",
    )

    //  Alert Dialogs
    const val APARTMENT_FEATURES_ALERT_DIALOG = "apartment features alert dialog"
    const val APARTMENT_HOUSE_CATEGORIES_ALERT_DIALOG = "apartment house categories alert dialog"
    const val AGENT_DELETE_HOUSE_ALERT_DIALOG = "delete house alert dialog"

    val houseCategories = listOf(
        HouseCategoryModel("One Bedroom", Icons.Outlined.Apartment),
        HouseCategoryModel("Two Bedroom", Icons.Outlined.Hotel),
        HouseCategoryModel("Three Bedroom", Icons.Outlined.Hotel),
        HouseCategoryModel("Bedsitter", Icons.Outlined.LocalCafe),
        HouseCategoryModel("Single", Icons.Outlined.LocalHotel),
        HouseCategoryModel("Mansion", Icons.Outlined.LocalHotel),
        HouseCategoryModel("Hostel", Icons.Outlined.LocalHotel)
    )

    //  alert dialog types
    const val BOOK_HOUSE_ALERT = "book house alert dialog"

    //  colors
    val primaryCol = mutableStateOf(Color(0xFF3DB2EC))
    val tertiaryCol = mutableStateOf(Color(0xFF3DB2EC).copy(alpha = 0.1f))

    val accentColors = listOf(
        AccentColor(
            darkColor = Color(0xFF3DB2EC).toArgb(),
            lightColor = Color(0xFF3DB2EC).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFF2AABEB).toArgb(),
            lightColor = Color(0xFF2AABEB).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFF11A0E7).toArgb(),
            lightColor = Color(0xFF11A0E7).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFF00A3F3).toArgb(),
            lightColor = Color(0xFF00A3F3).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFFFF4309).toArgb(),
            lightColor = Color(0xFFFF4309).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFFFF5825).toArgb(),
            lightColor = Color(0xFFFF5825).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFFFF6C40).toArgb(),
            lightColor = Color(0xFFFF6C40).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFFFF8C69).toArgb(),
            lightColor = Color(0xFFFF8C69).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFF288B0D).toArgb(),
            lightColor = Color(0xFF288B0D).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFF3ED515).toArgb(),
            lightColor = Color(0xFF3ED515).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFF54CF32).toArgb(),
            lightColor = Color(0xFF54CF32).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFF74D15B).toArgb(),
            lightColor = Color(0xFF74D15B).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFFFF0A5B).toArgb(),
            lightColor = Color(0xFFFF0A5B).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFFFF3A7B).toArgb(),
            lightColor = Color(0xFFFF3A7B).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFFFF5990).toArgb(),
            lightColor = Color(0xFFFF5990).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFFFF80AA).toArgb(),
            lightColor = Color(0xFFFF80AA).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFF012BFF).toArgb(),
            lightColor = Color(0xFF012BFF).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFF2247FF).toArgb(),
            lightColor = Color(0xFF2247FF).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFF4A68FF).toArgb(),
            lightColor = Color(0xFF4A68FF).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFF7088FF).toArgb(),
            lightColor = Color(0xFF7088FF).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFFDDEE0B).toArgb(),
            lightColor = Color(0xFFDDEE0B).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFFDEEC34).toArgb(),
            lightColor = Color(0xFFDEEC34).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFFDDE952).toArgb(),
            lightColor = Color(0xFFDDE952).copy(alpha = 0.1f).toArgb()
        ),
        AccentColor(
            darkColor = Color(0xFFDDE676).toArgb(),
            lightColor = Color(0xFFDDE676).copy(alpha = 0.1f).toArgb()
        ),
    )
}





























