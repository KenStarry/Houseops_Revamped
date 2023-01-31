package com.example.houseops_revamped.feature_home.home_screen.domain.repository

import com.example.houseops_revamped.feature_home.home_screen.domain.model.HouseModel

interface HomeRepository {

    //  get houses
    suspend fun getHouses(
        houses: (MutableList<HouseModel>) -> Unit
    )

}