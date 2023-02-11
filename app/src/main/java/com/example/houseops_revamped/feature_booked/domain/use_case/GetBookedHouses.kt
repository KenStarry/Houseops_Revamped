package com.example.houseops_revamped.feature_booked.domain.use_case

import com.example.houseops_revamped.feature_booked.domain.repository.BookedRepository
import com.example.houseops_revamped.feature_home.home_screen.domain.model.HouseModel

class GetBookedHouses(
    private val repo: BookedRepository
) {

    suspend operator fun invoke(
        houseIds: List<String>,
        bookedHouses: (houses: List<HouseModel>) -> Unit
    ) = repo.getAllBookedHouses(
        houseIds = houseIds,
        bookedHouses = {
            bookedHouses(it)
        }
    )
}