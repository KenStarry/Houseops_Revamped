package com.example.houseops_revamped.feature_home.house_view_screen.data.repository

import android.util.Log
import com.example.houseops_revamped.core.utils.Constants
import com.example.houseops_revamped.feature_home.home_screen.domain.model.HouseModel
import com.example.houseops_revamped.feature_home.house_view_screen.domain.repository.HouseViewRepository
import com.google.firebase.firestore.FirebaseFirestore

class HouseViewRepositoryImpl(
    private val db: FirebaseFirestore
) : HouseViewRepository {

    override suspend fun getCurrentHouse(
        category: String,
        apartmentName: String,
        currentHouse: (house: HouseModel) -> Unit
    ) {

        try {

            db.collection(Constants.APARTMENTS_COLLECTION).document(apartmentName)
                .collection(Constants.HOUSES_SUB_COLLECTION).document(category)
                .addSnapshotListener { snapshot, error ->

                    if (error != null)
                        return@addSnapshotListener

                    snapshot?.toObject(HouseModel::class.java)?.let {
                        currentHouse(it)
                    }
                }

        } catch (e: Exception) {
            Log.d("house", "$e")
        }
    }
}