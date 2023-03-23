package com.kenstarry.houseops_revamped.feature_tenant.feature_categories.domain.repository

import com.kenstarry.houseops_revamped.core.domain.model.HouseModel

interface CategoriesRepository {

    suspend fun getCaretakerHouses(
        apartmentName: String,
        houses: (List<HouseModel>) -> Unit
    )
}