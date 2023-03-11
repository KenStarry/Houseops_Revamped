package com.kenstarry.houseops_revamped.feature_landlord.feature_home.feature_home_screen.domain.use_cases

import com.kenstarry.houseops_revamped.core.domain.model.Apartment
import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.feature_landlord.feature_home.feature_home_screen.domain.repository.LndHomeRepository

class GetLandlordApartments(
    private val repo: LndHomeRepository
) {
    suspend operator fun invoke(
        email: String,
        apartments: (apartments: List<Apartment>) -> Unit,
        response: (response: Response<*>) -> Unit
    ) = repo.getLandlordApartments(
        email = email,
        apartments = apartments,
        response = { response(it) }
    )
}