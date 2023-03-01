package com.example.houseopscaretakers.feature_landlord.core.model

data class Apartment(
    val apartmentLandlordEmail: String,
    val apartmentName: String,
    val apartmentLocation: PlacesAPIResult?,
    val apartmentCaretakerId: String,
    val apartmentFeatures: List<ApartmentFeature>
) {
    constructor() : this("", "", null, "", listOf())
}
