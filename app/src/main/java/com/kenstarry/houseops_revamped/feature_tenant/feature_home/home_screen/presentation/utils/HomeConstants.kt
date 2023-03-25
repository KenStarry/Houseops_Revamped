package com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.presentation.models.PillBtn

object HomeConstants {

    val homePills = listOf(
        PillBtn(Icons.Outlined.Apartment, "House Categories"),
        PillBtn(Icons.Outlined.Money, "Plans"),
        PillBtn(Icons.Outlined.Apartment, "Apartments"),
        PillBtn(Icons.Outlined.LocationOn, "Near You"),
        PillBtn(Icons.Outlined.Person3, "Agents"),
    )
}