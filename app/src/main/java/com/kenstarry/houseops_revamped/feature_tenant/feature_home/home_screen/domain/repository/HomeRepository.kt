package com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.domain.repository

import com.kenstarry.houseops_revamped.core.domain.model.HouseModel

interface HomeRepository {

    //  get houses
    suspend fun getHouses(
        houses: (MutableList<HouseModel>) -> Unit
    )

}