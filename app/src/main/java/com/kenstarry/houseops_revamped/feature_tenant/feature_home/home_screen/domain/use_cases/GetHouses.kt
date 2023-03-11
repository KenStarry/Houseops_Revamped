package com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.domain.use_cases

import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.domain.model.HouseModel
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.domain.repository.HomeRepository

class GetHouses(
    private val repo: HomeRepository
) {

    suspend operator fun invoke(houses: (MutableList<HouseModel>) -> Unit) = repo.getHouses {
        houses(it)
    }
}