package com.kenstarry.houseops_revamped.feature_admin.feature_agents.domain.repository

import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.core.domain.model.UsersCollection

interface AdminAgentsRepository {

    suspend fun getAgents(
        agents: (agents: List<UsersCollection>) -> Unit,
        response: (response: Response<*>) -> Unit
    )

}