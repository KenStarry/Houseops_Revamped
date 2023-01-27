package com.example.houseops_revamped.feature_home.presentation.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import com.example.houseops_revamped.feature_home.presentation.models.PillBtn

object HomeConstants {

    val homePills = listOf(
        PillBtn(Icons.Outlined.Key, "For Rent"),
        PillBtn(Icons.Outlined.Apartment, "Apartments"),
        PillBtn(Icons.Outlined.Paid, "For Sale"),
        PillBtn(Icons.Outlined.LocationOn, "Near You"),
        PillBtn(Icons.Outlined.Person3, "Caretakers"),
        PillBtn(Icons.Outlined.Apartment, "House Categories")
    )
}