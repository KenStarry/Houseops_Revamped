package com.example.houseops_revamped.core.domain.model

data class ApartmentFeature(
    val title: String,
    val description: String
) {
    constructor() : this("", "")
}
