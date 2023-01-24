package com.example.houseops_revamped.feature_home.domain.use_cases

import com.example.houseops_revamped.feature_home.domain.model.HouseModel
import com.example.houseops_revamped.feature_home.domain.repository.HomeRepository

class GetHouses(
    private val repo: HomeRepository
) {

    suspend operator fun invoke(houses: (List<HouseModel>) -> Unit) = repo.getHouses {
        houses(it)
    }
}