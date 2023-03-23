package com.kenstarry.houseops_revamped.feature_tenant.feature_booked.domain.repository

import com.kenstarry.houseops_revamped.core.domain.model.HouseModel

interface BookedRepository {

    suspend fun getAllBookedHouses(
        houseIds: List<String>,
        bookedHouses: (houses: List<HouseModel>) -> Unit
    )

}