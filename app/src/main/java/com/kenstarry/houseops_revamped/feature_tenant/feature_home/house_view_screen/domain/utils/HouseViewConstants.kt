package com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.domain.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.domain.model.HouseFeatures

object HouseViewConstants {

    val featuresList = listOf(
        HouseFeatures("Security", Icons.Outlined.Security),
        HouseFeatures("Water", Icons.Outlined.Water),
        HouseFeatures("Electricity", Icons.Outlined.Thunderstorm),
        HouseFeatures("Parking", Icons.Outlined.Park),
        HouseFeatures("Shops", Icons.Outlined.Shop),
        HouseFeatures("Rooftop", Icons.Outlined.Roofing)
    )

    const val HV_CARETAKER_BOTTOM_SHEET = "hv caretaker"
    const val HV_BOOK_HOUSE_BOTTOM_SHEET = "hv book house"
    const val HV_BOOK_DATE_BOTTOM_SHEET = "hv book house date picker"
}