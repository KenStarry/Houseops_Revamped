package com.kenstarry.houseops_revamped.core.domain.use_cases

import android.util.Log
import com.kenstarry.houseops_revamped.core.domain.model.Apartment
import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.core.domain.model.UsersCollection
import com.kenstarry.houseops_revamped.core.domain.repository.CoreRepository

class GetAllAgents(
    private val repository: CoreRepository
) {
    suspend operator fun invoke(
        agents: (agents: List<UsersCollection>) -> Unit,
        response: (response: Response<*>) -> Unit
    ) = repository.getAllAgents(
        agents = {
            agents(it)
            Log.d("agents", it.size.toString())
        }, response
    )
}