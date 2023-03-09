package com.example.houseops_revamped.feature_agent.feature_home.domain.repository

import com.example.houseops_revamped.core.domain.model.Response

interface AgentHomeRepository {

    suspend fun getAgentApartments(
        email: String,
        onResponse: (response: Response<*>) -> Unit
    )
}