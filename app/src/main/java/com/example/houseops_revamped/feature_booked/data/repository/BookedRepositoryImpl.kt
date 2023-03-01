package com.example.houseops_revamped.feature_booked.data.repository

import android.util.Log
import com.example.houseops_revamped.core.presentation.utils.Constants
import com.example.houseops_revamped.feature_booked.domain.repository.BookedRepository
import com.example.houseops_revamped.feature_home.home_screen.domain.model.HouseModel
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class BookedRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
) : BookedRepository {

    override suspend fun getAllBookedHouses(
        houseIds: List<String>,
        bookedHouses: (houses: List<HouseModel>) -> Unit
    ) {

        try {

            db.collectionGroup(Constants.HOUSES_SUB_COLLECTION)
                .whereIn("houseId", houseIds)
                .addSnapshotListener { querySnaphot, error ->

                    if (error != null)
                        return@addSnapshotListener

                    val housesList = ArrayList<HouseModel>()

                    querySnaphot?.forEach { snapshot ->
                        snapshot.toObject(HouseModel::class.java).let {
                            housesList.add(it)
                        }
                    }

                    Log.d("bookedRepo", "all booked houses = ${housesList.size}")

                    //  pass the houses to our function
                    bookedHouses(housesList)
                }

        } catch (e: Exception) {
            Log.d("booked", "$e")
        }
    }
}