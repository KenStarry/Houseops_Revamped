package com.kenstarry.houseops_revamped.feature_landlord.feature_home.feature_add_apartment.domain.repository

import com.kenstarry.houseops_revamped.core.domain.model.Apartment
import com.kenstarry.houseops_revamped.core.domain.model.Response

interface LndAddApartmentRepository {

    suspend fun addApartmentToFirestore(
        apartment: Apartment,
        response: (response: Response<*>) -> Unit
    )
}