package com.example.houseops_revamped.feature_home.domain.model

data class HouseModel(
    val houseCategory: String,
    val houseImageUris: List<String>,
    val houseUnits: String,
    val houseFeatures: List<String>,
    val houseDescription: String
) {

    constructor() : this ("", emptyList(), "", emptyList(), "")
}
