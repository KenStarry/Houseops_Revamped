package com.example.houseops_revamped.feature_tenant.feature_booked.domain.repository

import com.example.houseops_revamped.feature_tenant.feature_home.home_screen.domain.model.HouseModel

interface BookedRepository {

    suspend fun getAllBookedHouses(
        houseIds: List<String>,
        bookedHouses: (houses: List<HouseModel>) -> Unit
    )

}