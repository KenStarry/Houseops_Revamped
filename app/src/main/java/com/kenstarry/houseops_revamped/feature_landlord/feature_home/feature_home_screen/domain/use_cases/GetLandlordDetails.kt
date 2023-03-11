package com.kenstarry.houseops_revamped.feature_landlord.feature_home.feature_home_screen.domain.use_cases

import com.kenstarry.houseops_revamped.core.domain.model.Landlord
import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.feature_landlord.feature_home.feature_home_screen.domain.repository.LndHomeRepository

class GetLandlordDetails(
    private val repository: LndHomeRepository
) {
    suspend operator fun invoke(
        email: String,
        landlord: (landlord: Landlord) -> Unit,
        response: (response: Response<*>) -> Unit
    ) = repository.getLandlordDetails(
        email = email,
        landlord = { landlord(it) },
        response = { response(it) }
    )
}