package com.example.houseops_revamped.feature_admin.feature_landlord_view.domain.model

import com.example.houseops_revamped.core.domain.model.Apartment
import com.example.houseops_revamped.core.domain.model.Response

sealed class AdminLandlordViewEvents {

    data class GetApartments(
        val landlordEmail: String,
        val apartments: (apartments: List<Apartment>) -> Unit,
        val response: (response: Response<*>) -> Unit
    ) : AdminLandlordViewEvents()
}
