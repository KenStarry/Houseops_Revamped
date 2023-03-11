package com.kenstarry.houseops_revamped.feature_admin.feature_agents.domain.use_case

import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.core.domain.model.UsersCollection
import com.kenstarry.houseops_revamped.feature_admin.feature_agents.domain.repository.AdminAgentsRepository

class GetAgents(
    private val repository: AdminAgentsRepository
) {
    suspend operator fun invoke(
        agents: (agents: List<UsersCollection>) -> Unit,
        response: (response: Response<*>) -> Unit
    ) = repository.getAgents(agents, response)
}