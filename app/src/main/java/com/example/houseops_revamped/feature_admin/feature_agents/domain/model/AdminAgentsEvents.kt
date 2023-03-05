package com.example.houseops_revamped.feature_admin.feature_agents.domain.model

import com.example.houseops_revamped.core.domain.model.Response
import com.example.houseops_revamped.core.domain.model.UsersCollection

sealed class AdminAgentsEvents {

    data class GetAgents(
        val response: (response: Response<*>) -> Unit
    ) : AdminAgentsEvents()
}
