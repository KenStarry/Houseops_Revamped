package com.example.houseops_revamped.feature_agent.feature_apartment_view.domain.model

import com.example.houseops_revamped.core.domain.model.Response
import com.example.houseops_revamped.feature_tenant.feature_home.home_screen.domain.model.HouseModel

sealed class AgentApartmentEvents {

    data class GetApartmentHouses(
        val apartmentName: String,
        val onResponse: (response: Response<*>) -> Unit
    ) : AgentApartmentEvents()
}
