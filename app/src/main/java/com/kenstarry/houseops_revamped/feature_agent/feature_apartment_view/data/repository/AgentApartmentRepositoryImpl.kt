package com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants
import com.kenstarry.houseops_revamped.feature_agent.feature_apartment_view.domain.repository.AgentApartmentRepository
import com.kenstarry.houseops_revamped.core.domain.model.HouseModel
import javax.inject.Inject

class AgentApartmentRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
) : AgentApartmentRepository {

    override suspend fun addHouseToFirestore(
        apartmentName: String,
        houseModel: HouseModel,
        onResponse: (response: Response<*>) -> Unit
    ) {
        try {
            db.collection(Constants.APARTMENTS_COLLECTION).document(apartmentName)
                .collection(Constants.HOUSES_SUB_COLLECTION).document(houseModel.houseCategory)
                .set(houseModel)
                .addOnSuccessListener {
                    onResponse(Response.Success(true))
                }
                .addOnFailureListener {
                    onResponse(Response.Failure(it.localizedMessage))
                }
        } catch (e: Exception) {
            onResponse(Response.Failure(e.localizedMessage))
        }
    }

    override suspend fun updateHouseInFirestore(
        apartmentName: String,
        houseModel: HouseModel,
        onResponse: (response: Response<*>) -> Unit
    ) {
        try {
            db.collection(Constants.APARTMENTS_COLLECTION).document(apartmentName)
                .collection(Constants.HOUSES_SUB_COLLECTION).document(houseModel.houseCategory)
                .set(houseModel)
                .addOnSuccessListener {
                    onResponse(Response.Success(true))
                }
                .addOnFailureListener {
                    onResponse(Response.Failure(it.localizedMessage))
                }
        } catch (e: Exception) {
            onResponse(Response.Failure(e.localizedMessage))
        }
    }

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



























