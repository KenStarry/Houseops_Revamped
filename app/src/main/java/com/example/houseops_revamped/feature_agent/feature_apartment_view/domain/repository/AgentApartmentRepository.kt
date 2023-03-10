package com.example.houseops_revamped.feature_agent.feature_apartment_view.domain.repository

import com.example.houseops_revamped.core.domain.model.Response
import com.example.houseops_revamped.feature_tenant.feature_home.home_screen.domain.model.HouseModel

interface AgentApartmentRepository {

    suspend fun getApartmentHouses(
        apartmentName: String,
        houses: (housesList: List<HouseModel>) -> Unit,
        onResponse: (response: Response<*>) -> Unit
    )
}