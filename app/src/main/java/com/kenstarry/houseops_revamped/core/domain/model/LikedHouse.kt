package com.kenstarry.houseops_revamped.core.domain.model

data class LikedHouse(
    val apartmentName: String,
    val houseCategory: String
) {
    constructor() : this("", "")
}
