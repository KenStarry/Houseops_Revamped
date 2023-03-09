package com.example.houseops_revamped.feature_agent.feature_home.data.repository

import androidx.lifecycle.ViewModel
import com.example.houseops_revamped.core.domain.model.Apartment
import com.example.houseops_revamped.core.domain.model.Response
import com.example.houseops_revamped.core.presentation.utils.Constants
import com.example.houseops_revamped.feature_agent.feature_home.domain.repository.AgentHomeRepository
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AgentHomeRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
) : AgentHomeRepository {

    override suspend fun getAgentApartments(
        email: String,
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

                    val apartments = mutableListOf<Apartment>()

                    querySnapshot?.forEach { snapshot ->
                        snapshot?.toObject(Apartment::class.java)?.let { apartment ->
                            apartments.add(apartment)
                        }
                    }

                    onResponse(Response.Success(apartments))
                }

        } catch (e: Exception) {
            onResponse(Response.Failure(e.localizedMessage))
        }
    }
}