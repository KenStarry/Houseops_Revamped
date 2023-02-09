package com.example.houseops_revamped.feature_booked.domain.model

data class BookedHouseModel(
    val houseId: String,
    val dateBooked: String
) {
    constructor() : this("", "")
}
