package com.example.houseops_revamped.feature_home.domain.model

data class ApartmentModel(
    val apartmentName: String,
    val houseModel: HouseModel
) {

    constructor() : this("", HouseModel())
}
