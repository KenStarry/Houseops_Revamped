package com.example.houseops_revamped.feature_agent.feature_apartment_view.data.repository

import androidx.lifecycle.ViewModel
import com.example.houseops_revamped.core.domain.model.Response
import com.example.houseops_revamped.core.presentation.utils.Constants
import com.example.houseops_revamped.feature_agent.feature_apartment_view.domain.repository.AgentApartmentRepository
import com.example.houseops_revamped.feature_tenant.feature_home.home_screen.domain.model.HouseModel
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class AgentApartmentRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
) : AgentApartmentRepository {

    override suspend fun getApartmentHouses(
        apartmentName: String,
        houses: (housesList: List<HouseModel>) -> Unit,
        onResponse: (response: Response<*>) -> Unit
    ) {
        try {

            db.collection(Constants.APARTMENTS_COLLECTION)
                .document(apartmentName)
                .collection(Constants.HOUSES_SUB_COLLECTION)
                .addSnapshotListener { querySnapshot, error ->

                    if (error != null) {
                        onResponse(Response.Failure(error.localizedMessage))
                        return@addSnapshotListener
                    }

                    val housesList = mutableListOf<HouseModel>()

                    querySnapshot?.forEach { snapshot ->
                        snapshot?.toObject(HouseModel::class.java)?.let { house ->
                            housesList.add(house)
                        }
                    }

                    houses(housesList)
                }
        } catch (e: Exception) {
            onResponse(Response.Failure(e.localizedMessage))
        }
    }
}



























