package com.example.houseops_revamped.feature_agent.feature_home.domain.use_cases

import com.example.houseops_revamped.core.domain.model.Apartment
import com.example.houseops_revamped.core.domain.model.Response
import com.example.houseops_revamped.feature_agent.feature_home.domain.repository.AgentHomeRepository

class GetAgentApartments(
    private val repo: AgentHomeRepository
) {
    suspend operator fun invoke(
        email: String,
        apartments: (apartmentsList: List<Apartment>) -> Unit,
        onResponse: (response: Response<*>) -> Unit
    ) = repo.getAgentApartments(email, apartments, onResponse)
}