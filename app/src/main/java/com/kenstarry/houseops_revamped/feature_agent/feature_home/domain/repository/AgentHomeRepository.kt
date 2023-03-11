package com.kenstarry.houseops_revamped.feature_agent.feature_home.domain.repository

import com.kenstarry.houseops_revamped.core.domain.model.Apartment
import com.kenstarry.houseops_revamped.core.domain.model.Response

interface AgentHomeRepository {

    suspend fun getAgentApartments(
        email: String,
        apartments: (apartmentsList: List<Apartment>) -> Unit,
        onResponse: (response: Response<*>) -> Unit
    )
}