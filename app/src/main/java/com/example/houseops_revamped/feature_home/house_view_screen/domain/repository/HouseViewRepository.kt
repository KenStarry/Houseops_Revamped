package com.example.houseops_revamped.feature_home.house_view_screen.domain.repository

import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.example.houseops_revamped.feature_booked.domain.model.BookedHouseModel
import com.example.houseops_revamped.feature_home.home_screen.domain.model.HouseModel

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
        userEmail: String,
        isAdd: Boolean
    )

}