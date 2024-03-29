package com.kenstarry.houseops_revamped.feature_tenant.feature_booked.domain.use_case

import android.util.Log
import com.kenstarry.houseops_revamped.feature_tenant.feature_booked.domain.repository.BookedRepository
import com.kenstarry.houseops_revamped.core.domain.model.HouseModel

class GetBookedHouses(
    private val repo: BookedRepository
) {

    suspend operator fun invoke(
        houseIds: List<String>,
        bookedHouses: (houses: List<HouseModel>) -> Unit
    ) = repo.getAllBookedHouses(
        houseIds = houseIds,
        bookedHouses = {
            bookedHouses(it)

            Log.d("bookedUseCase", "all booked houses = ${it.size}")
        }
    )
}