package com.example.houseops_revamped.feature_home.house_view_screen.domain.model

sealed class HouseViewEvents {

    data class AddToBookedHouses(
        val houseId: String,
        val email: String,
        val isAdd: Boolean
    ) : HouseViewEvents()
}
