package com.example.houseops_revamped.feature_home.house_view_screen.domain.use_case

import com.example.houseops_revamped.feature_booked.domain.model.BookedHouseModel
import com.example.houseops_revamped.feature_home.house_view_screen.domain.repository.HouseViewRepository

class AddToBooked(
    private val repo: HouseViewRepository
) {

    suspend operator fun invoke(
        bookedHouse: BookedHouseModel,
        email: String,
        isAdd: Boolean
    ) = repo.addHouseToBookedHouses(bookedHouse, email, isAdd)
}