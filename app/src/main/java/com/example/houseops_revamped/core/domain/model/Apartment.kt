package com.example.houseops_revamped.core.domain.model

data class Apartment(
    val apartmentLandlordEmail: String,
    val apartmentName: String,
    val apartmentLocation: PlacesAPIResult?,
    val apartmentCaretakerId: String,
    val apartmentFeatures: List<ApartmentFeature>,
    val apartmentPurchaseType: String,
    val apartmentPrice: String?
) {
    constructor() : this(
        "", "", null, "",
        listOf(), "", null
    )
}
