package com.example.houseops_revamped.feature_admin.feature_landlord_view.domain.repository

import com.example.houseops_revamped.core.domain.model.Apartment
import com.example.houseops_revamped.core.domain.model.Response

interface AdminLandlordViewRepo {

    suspend fun getLandlordApartments(
        landlordEmail: String,
        apartments: (apartments: List<Apartment>) -> Unit,
        response: (response: Response<*>) -> Unit
    )
}