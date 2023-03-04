package com.example.houseops_revamped.feature_landlord.core.model

import com.example.houseopscaretakers.feature_landlord.core.model.ApartmentFeature
import com.example.houseopscaretakers.feature_landlord.core.model.PlacesAPIResult

data class Apartment(
    val apartmentLandlordEmail: String,
    val apartmentName: String,
    val apartmentLocation: PlacesAPIResult?,
    val apartmentCaretakerId: String,
    val apartmentFeatures: List<ApartmentFeature>
) {
    constructor() : this("", "", null, "", listOf())
}
