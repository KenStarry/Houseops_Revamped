package com.example.houseops_revamped.feature_home.house_view_screen.domain.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import com.example.houseops_revamped.feature_home.house_view_screen.domain.model.HouseFeatures

object HouseViewConstants {

    val featuresList = listOf(
        HouseFeatures("Security", Icons.Outlined.Security),
        HouseFeatures("Water", Icons.Outlined.Water),
        HouseFeatures("Electricity", Icons.Outlined.Thunderstorm),
        HouseFeatures("Parking", Icons.Outlined.Park),
        HouseFeatures("Shops", Icons.Outlined.Shop),
        HouseFeatures("Rooftop", Icons.Outlined.Roofing)
    )

    const val HV_CARETAKER_BOTTOM_SHEET = "house view caretaker"
}