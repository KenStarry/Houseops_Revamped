package com.example.houseops_revamped.feature_home.domain.repository

import com.example.houseops_revamped.feature_home.domain.model.HouseModel

interface HomeRepository {

    //  get houses
    suspend fun getHouses(
        houses: (List<HouseModel>) -> Unit
    )

}