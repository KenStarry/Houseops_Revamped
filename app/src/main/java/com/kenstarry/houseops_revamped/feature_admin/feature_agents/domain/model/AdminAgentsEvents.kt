package com.kenstarry.houseops_revamped.feature_admin.feature_agents.domain.model

import com.kenstarry.houseops_revamped.core.domain.model.Response

sealed class AdminAgentsEvents {

    data class GetAgents(
        val response: (response: Response<*>) -> Unit
    ) : AdminAgentsEvents()
}
