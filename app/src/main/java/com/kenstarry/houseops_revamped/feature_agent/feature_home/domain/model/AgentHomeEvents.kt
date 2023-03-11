package com.kenstarry.houseops_revamped.feature_agent.feature_home.domain.model

import com.kenstarry.houseops_revamped.core.domain.model.Response

sealed class AgentHomeEvents {

    data class GetAgentApartments(
        val email: String,
        val onResponse: (response: Response<*>) -> Unit
    )  : AgentHomeEvents()
}
