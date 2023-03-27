package com.kenstarry.houseops_revamped.core.domain.use_cases

import com.kenstarry.houseops_revamped.core.domain.model.Apartment
import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.core.domain.repository.CoreRepository

class GetApartmentDetails(
    private val repository: CoreRepository
) {
    suspend operator fun invoke(
        apartmentName: String,
        apartment: (apartment: Apartment) -> Unit,
        response: (response: Response<*>) -> Unit
    ) = repository.getApartmentDetails(apartmentName, apartment, response)
}