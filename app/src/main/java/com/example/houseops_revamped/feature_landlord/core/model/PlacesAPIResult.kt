package com.example.houseopscaretakers.feature_landlord.core.model

data class PlacesAPIResult(
    val address: String,
    val placeId: String
) {
    constructor() : this("", "")
}
