package com.example.houseops_revamped.feature_home.home_screen.domain.model

import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.example.houseops_revamped.feature_home.house_view_screen.domain.model.UserBooked

data class HouseModel(
    val houseId: String,
    val houseCategory: String,
    val housePurchaseType: String,
    val houseImageUris: List<String>,
    val houseUnits: String,
    val houseFeatures: List<String>,
    val houseDescription: String,
    val houseLikes: String,
    val houseApartmentName: String,
    val housePrice: String,
    val housePriceCategory: String,
    val houseComments: String,
    val houseUsersBooked: List<UserBooked>
) {

    constructor() : this ("", "", "For Rent", emptyList(), "", emptyList(), "", "0",
        "Pangani Palace", "23,000", "month", "", emptyList()
    )
}
