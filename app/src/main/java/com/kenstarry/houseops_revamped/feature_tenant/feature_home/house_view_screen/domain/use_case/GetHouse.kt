package com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.domain.use_case

import android.util.Log
import com.kenstarry.houseops_revamped.core.domain.model.HouseModel
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.domain.repository.HouseViewRepository

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

            Log.d("usecase", "$it")
        }
    )
}