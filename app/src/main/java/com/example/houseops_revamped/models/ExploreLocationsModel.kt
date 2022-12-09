package com.example.houseops_revamped.models

import androidx.compose.ui.graphics.painter.Painter

data class ExploreLocationsModel(
    val locationImage: Painter?,
    val locationName: String,
    val locationDistance: String
) {
    constructor() : this (null, "", "")
}
