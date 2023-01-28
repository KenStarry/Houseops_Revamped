package com.example.houseops_revamped.feature_home.house_view_screen.domain.repository

import com.example.houseops_revamped.feature_home.home_screen.domain.model.HouseModel

interface HouseViewRepository {

    suspend fun getCurrentHouse(
        category: String,
        apartmentName: String,
        currentHouse: (house: HouseModel) -> Unit
    )

}