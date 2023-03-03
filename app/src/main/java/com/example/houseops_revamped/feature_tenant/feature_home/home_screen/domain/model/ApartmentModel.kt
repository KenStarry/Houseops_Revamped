package com.example.houseops_revamped.feature_tenant.feature_home.home_screen.domain.model

data class ApartmentModel(
    val apartmentName: String,
    val houseModel: List<HouseModel>?
) {

    constructor() : this("", emptyList())
}
