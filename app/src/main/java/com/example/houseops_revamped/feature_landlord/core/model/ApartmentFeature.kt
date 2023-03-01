package com.example.houseopscaretakers.feature_landlord.core.model

data class ApartmentFeature(
    val title: String,
    val description: String
) {
    constructor() : this("", "")
}
