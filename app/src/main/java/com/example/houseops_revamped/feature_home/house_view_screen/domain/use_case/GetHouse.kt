package com.example.houseops_revamped.feature_home.house_view_screen.domain.use_case

import com.example.houseops_revamped.feature_home.home_screen.domain.model.HouseModel
import com.example.houseops_revamped.feature_home.house_view_screen.domain.repository.HouseViewRepository

class GetHouse(
    private val repo: HouseViewRepository
) {

    suspend operator fun invoke(
        category: String,
        apartmentName: String,
        currentHouse: (house: HouseModel) -> Unit
    ) = repo.getCurrentHouse(
        category = category,
        apartmentName = apartmentName,
        currentHouse = {
            currentHouse(it)
        }
    )
}