package com.example.houseops_revamped.feature_landlord.feature_home.feature_add_apartment.domain.repository

import com.example.houseops_revamped.core.domain.model.Response
import com.example.houseops_revamped.feature_landlord.core.model.Apartment

interface LndAddApartmentRepository {

    suspend fun addApartmentToFirestore(
        apartment: Apartment,
        response: (response: Response<*>) -> Unit
    )
}