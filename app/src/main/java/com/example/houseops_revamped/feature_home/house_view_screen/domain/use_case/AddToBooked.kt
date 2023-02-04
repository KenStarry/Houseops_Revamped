package com.example.houseops_revamped.feature_home.house_view_screen.domain.use_case

import com.example.houseops_revamped.feature_home.house_view_screen.domain.repository.HouseViewRepository

class AddToBooked(
    private val repo: HouseViewRepository
) {

    suspend operator fun invoke(
        houseId: String,
        email: String,
        isAdd: Boolean
    ) = repo.addHouseToBookedHouses(houseId, email, isAdd)
}