package com.kenstarry.houseops_revamped.feature_tenant.feature_booked.domain.repository

import com.kenstarry.houseops_revamped.core.domain.model.HouseModel
import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.feature_tenant.feature_booked.domain.model.BookedHouseModel

interface BookedRepository {

    suspend fun getAllBookedHouses(
        houseIds: List<String>,
        bookedHouses: (houses: List<HouseModel>) -> Unit
    )

    suspend fun deleteBookedHouseCategory(
        email: String,
        bookedHousesUnderCategory: List<BookedHouseModel>,
        onResponse: (repsonse: Response<*>) -> Unit
    )

}