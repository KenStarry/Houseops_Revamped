package com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.domain.use_case

import com.kenstarry.houseops_revamped.core.domain.model.Apartment
import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.domain.repository.HouseViewRepository

class GetApartment(
    private val repository: HouseViewRepository
) {
    suspend operator fun invoke(
        apartmentName: String,
        apartment: (apartment: Apartment) -> Unit,
        response: (response: Response<*>) -> Unit
    ) = repository.getApartmentDetails(apartmentName, apartment, response)
}