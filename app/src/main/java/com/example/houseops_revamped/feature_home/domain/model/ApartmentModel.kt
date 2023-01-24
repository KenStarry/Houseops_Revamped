package com.example.houseops_revamped.feature_home.domain.model

data class ApartmentModel(
    val apartmentName: String,
    val houseModel: List<HouseModel>?
) {

    constructor() : this("", emptyList())
}
