package com.example.houseops_revamped.feature_landlord.feature_home.feature_home_screen.domain.repository

import com.example.houseops_revamped.core.domain.model.Landlord
import com.example.houseops_revamped.core.domain.model.Response
import com.example.houseops_revamped.core.domain.model.Apartment

interface LndHomeRepository {

    suspend fun getLandlordDetails(
        email: String,
        landlord: (landlord: Landlord) -> Unit,
        response: (response: Response<*>) -> Unit
    )

    suspend fun getLandlordApartments(
        email: String,
        apartments: (apartments: List<Apartment>) -> Unit,
        response: (response: Response<*>) -> Unit
    )
}