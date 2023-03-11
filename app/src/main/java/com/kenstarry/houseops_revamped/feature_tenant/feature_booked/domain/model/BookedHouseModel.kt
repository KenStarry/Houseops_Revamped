package com.kenstarry.houseops_revamped.feature_tenant.feature_booked.domain.model

data class BookedHouseModel(
    val houseId: String,
    val dateBooked: String
) {
    constructor() : this("", "")
}
