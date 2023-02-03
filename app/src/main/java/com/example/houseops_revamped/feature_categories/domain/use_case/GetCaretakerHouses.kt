package com.example.houseops_revamped.feature_categories.domain.use_case

import com.example.houseops_revamped.feature_categories.domain.repository.CategoriesRepository
import com.example.houseops_revamped.feature_home.home_screen.domain.model.HouseModel

class GetCaretakerHouses(
    private val repo: CategoriesRepository
) {

    suspend operator fun invoke(
        apartmentName: String,
        houses: (List<HouseModel>) -> Unit
    ) = repo.getCaretakerHouses(
        apartmentName = apartmentName,
        houses = { houses(it) }
    )
}