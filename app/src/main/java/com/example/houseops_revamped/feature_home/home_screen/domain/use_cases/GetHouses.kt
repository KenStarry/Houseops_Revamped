package com.example.houseops_revamped.feature_home.home_screen.domain.use_cases

import com.example.houseops_revamped.feature_home.home_screen.domain.model.HouseModel
import com.example.houseops_revamped.feature_home.home_screen.domain.repository.HomeRepository

class GetHouses(
    private val repo: HomeRepository
) {

    suspend operator fun invoke(houses: (ArrayList<HouseModel>) -> Unit) = repo.getHouses {
        houses(it)
    }
}