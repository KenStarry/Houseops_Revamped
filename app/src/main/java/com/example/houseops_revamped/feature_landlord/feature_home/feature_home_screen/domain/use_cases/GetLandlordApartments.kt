package com.example.houseops_revamped.feature_landlord.feature_home.feature_home_screen.domain.use_cases

import com.example.houseops_revamped.core.domain.model.Response
import com.example.houseops_revamped.feature_landlord.feature_home.feature_home_screen.domain.repository.LndHomeRepository
import com.example.houseopscaretakers.feature_landlord.core.model.Apartment

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