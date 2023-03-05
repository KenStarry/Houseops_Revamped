package com.example.houseops_revamped.feature_admin.feature_landlord_view.domain.use_case

import com.example.houseops_revamped.core.domain.model.Apartment
import com.example.houseops_revamped.core.domain.model.Response
import com.example.houseops_revamped.feature_admin.feature_landlord_view.domain.repository.AdminLandlordViewRepo

class GetApartments(
    private val repo: AdminLandlordViewRepo
) {

    suspend operator fun invoke(
        landlordEmail: String,
        apartments: (apartments: List<Apartment>) -> Unit,
        response: (response: Response<*>) -> Unit
    ) = repo.getLandlordApartments(landlordEmail, apartments, response)
}