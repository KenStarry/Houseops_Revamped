package com.kenstarry.houseops_revamped.core.domain.model

import com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.domain.model.UserBooked

data class HouseModel(
    val houseId: String,
    val houseCategory: String,
    val housePurchaseType: String,
    val houseImageUris: List<ImageModel>,
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
