package com.kenstarry.houseops_revamped.feature_landlord.feature_home.feature_add_apartment.domain.use_case

import com.kenstarry.houseops_revamped.core.domain.model.Apartment
import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.feature_landlord.feature_home.feature_add_apartment.domain.repository.LndAddApartmentRepository

class AddApartment(
    private val repository: LndAddApartmentRepository
) {

    suspend operator fun invoke(
        apartment: Apartment,
        response: (response: Response<*>) -> Unit
    ) = repository.addApartmentToFirestore(
        apartment = apartment,
        response = { response(it) }
    )
}