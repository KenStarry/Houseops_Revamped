package com.example.houseops_revamped.feature_home.presentation.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import com.example.houseops_revamped.feature_home.presentation.models.PillBtn

object HomeConstants {

    val homePills = listOf(
        PillBtn(Icons.Outlined.Apartment, "For Rent"),
        PillBtn(Icons.Outlined.Person3, "Caretakers"),
        PillBtn(Icons.Outlined.Paid, "For Sale"),
        PillBtn(Icons.Outlined.Hotel, "One Bedroom"),
        PillBtn(Icons.Outlined.Key, "Mansions"),
        PillBtn(Icons.Outlined.Apartment, "Two Bedroom")
    )
}