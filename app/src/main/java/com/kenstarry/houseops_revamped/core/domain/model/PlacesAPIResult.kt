package com.kenstarry.houseops_revamped.core.domain.model

data class PlacesAPIResult(
    val address: String,
    val placeId: String
) {
    constructor() : this("", "")
}
