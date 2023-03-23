package com.kenstarry.houseops_revamped.feature_tenant.feature_categories.domain.use_case

import com.kenstarry.houseops_revamped.feature_tenant.feature_categories.domain.repository.CategoriesRepository
import com.kenstarry.houseops_revamped.core.domain.model.HouseModel

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