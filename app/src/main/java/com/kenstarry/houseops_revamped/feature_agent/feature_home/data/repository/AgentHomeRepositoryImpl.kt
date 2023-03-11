package com.kenstarry.houseops_revamped.feature_agent.feature_home.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.kenstarry.houseops_revamped.core.domain.model.Apartment
import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants
import com.kenstarry.houseops_revamped.feature_agent.feature_home.domain.repository.AgentHomeRepository
import javax.inject.Inject

class AgentHomeRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
) : AgentHomeRepository {

    override suspend fun getAgentApartments(
        email: String,
        apartments: (apartmentsList: List<Apartment>) -> Unit,
        onResponse: (response: Response<*>) -> Unit
    ) {

        try {

            db.collection(Constants.APARTMENTS_COLLECTION)
                .whereEqualTo("apartmentAgentAssigned", email)
                .addSnapshotListener { querySnapshot, error ->

                    if (error != null) {
                        onResponse(Response.Failure(error.localizedMessage))
                        return@addSnapshotListener
                    }

                    val apartmentsList = mutableListOf<Apartment>()

                    querySnapshot?.forEach { snapshot ->
                        snapshot?.toObject(Apartment::class.java)?.let { apartment ->
                            apartmentsList.add(apartment)
                        }
                    }

                    apartments(apartmentsList)
                    onResponse(Response.Success(apartmentsList))
                }

        } catch (e: Exception) {
            onResponse(Response.Failure(e.localizedMessage))
        }
    }
}