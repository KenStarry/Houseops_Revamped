package com.example.houseops_revamped.feature_landlord.feature_home.feature_add_apartment.presentation.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Hotel
import androidx.compose.material.icons.outlined.MonetizationOn
import com.example.houseops_revamped.core.presentation.model.OptionsToggleModel

object LndApartmentConstants {

    //  bottomsheet types
    const val PLACES_BOTTOM_SHEET = "places bottomsheet"
    const val FEATURES_BOTTOM_SHEET = "house categories bottomsheet"
    const val TERMS_BOTTOM_SHEET = "apartment terms and conditions bottomsheet"

    val optionsList = listOf(
        OptionsToggleModel(Icons.Outlined.Hotel, "For Rent"),
        OptionsToggleModel(Icons.Outlined.MonetizationOn, "For Sale"),
    )
}