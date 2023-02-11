package com.example.houseops_revamped.feature_booked.domain.repository

import com.example.houseops_revamped.feature_home.home_screen.domain.model.HouseModel

interface BookedRepository {

    suspend fun getAllBookedHouses(
        houseId: String,
        bookedHouses: (houses: List<HouseModel>) -> Unit
    )

}