package com.example.houseops_revamped.feature_tenant.feature_home.home_screen.data.repository

import com.example.houseops_revamped.core.presentation.utils.Constants
import com.example.houseops_revamped.feature_tenant.feature_home.home_screen.domain.model.HouseModel
import com.example.houseops_revamped.feature_tenant.feature_home.home_screen.domain.repository.HomeRepository
import com.google.firebase.firestore.FirebaseFirestore

class HomeRepositoryImpl(
    private val db: FirebaseFirestore
) : HomeRepository {

    override suspend fun getHouses(houses: (MutableList<HouseModel>) -> Unit) {

        db.collectionGroup(Constants.HOUSES_SUB_COLLECTION)
            .addSnapshotListener { querySnapshot, error ->

                if (error != null)
                    return@addSnapshotListener

                val housesList = ArrayList<HouseModel>()

                querySnapshot?.forEach { snapshot ->
                    snapshot.toObject(HouseModel::class.java).let {
                        housesList.add(it)
                    }
                }

                //  pass the houses to our function
                houses(housesList)
            }
    }
}























