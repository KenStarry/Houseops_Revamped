package com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.domain.repository

import com.kenstarry.houseops_revamped.feature_tenant.feature_booked.domain.model.BookedHouseModel
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.domain.model.HouseModel
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.domain.model.UserBooked

interface HouseViewRepository {

    suspend fun getCurrentHouse(
        category: String,
        apartmentName: String,
        currentHouse: (house: HouseModel) -> Unit
    )

    suspend fun addHouseToBookedHouses(
        bookedHouse: BookedHouseModel,
        email: String,
        isAdd: Boolean
    )

    suspend fun addUserToHouseBooked(
        apartmentName: String,
        houseCategory: String,
        userBooked: UserBooked,
        isAdd: Boolean
    )

}