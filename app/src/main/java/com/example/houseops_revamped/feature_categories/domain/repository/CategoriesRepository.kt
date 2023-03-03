package com.example.houseops_revamped.feature_categories.domain.repository

import com.example.houseops_revamped.feature_tenant.feature_home.home_screen.domain.model.HouseModel

interface CategoriesRepository {

    suspend fun getCaretakerHouses(
        apartmentName: String,
        houses: (List<HouseModel>) -> Unit
    )
}