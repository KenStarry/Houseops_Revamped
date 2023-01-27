package com.example.houseops_revamped.core.domain.model

data class BookmarkHouse(
    val apartmentName: String,
    val houseCategory: String
) {
    constructor() : this("", "")
}
