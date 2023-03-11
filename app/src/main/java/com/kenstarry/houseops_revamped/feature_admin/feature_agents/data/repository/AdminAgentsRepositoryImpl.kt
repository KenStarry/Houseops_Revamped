package com.kenstarry.houseops_revamped.feature_admin.feature_agents.data.repository

import android.provider.SyncStateContract
import com.google.firebase.firestore.FirebaseFirestore
import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.core.domain.model.UsersCollection
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants
import com.kenstarry.houseops_revamped.feature_admin.feature_agents.domain.repository.AdminAgentsRepository
import com.kenstarry.houseops_revamped.feature_authentication.domain.utils.AuthConstants
import javax.inject.Inject

class AdminAgentsRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
): AdminAgentsRepository {

    override suspend fun getAgents(
        agents: (agents: List<UsersCollection>) -> Unit,
        response: (response: Response<*>) -> Unit
    ) {
        try {

            db.collection(Constants.USERS_COLLECTION)
                .whereEqualTo("userType", AuthConstants.userTypes[3].userTitle)
                .addSnapshotListener { querySnapshot, error ->

                    if (error != null) {
                        response(Response.Failure(error.localizedMessage))
                        return@addSnapshotListener
                    }

                    val agentsList = mutableListOf<UsersCollection>()

                    querySnapshot?.forEach { snapshot ->
                        snapshot?.toObject(UsersCollection::class.java)?.let {
                            agentsList.add(it)
                        }
                    }

                    agents(agentsList)
                    response(Response.Success(agentsList))
                }

        } catch (e: Exception) {
            response(Response.Failure(e.localizedMessage))
        }
    }
}