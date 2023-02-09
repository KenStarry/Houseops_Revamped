package com.example.houseops_revamped.feature_home.house_view_screen.data.repository

import android.util.Log
import com.example.houseops_revamped.core.utils.Constants
import com.example.houseops_revamped.feature_booked.domain.model.BookedHouseModel
import com.example.houseops_revamped.feature_home.home_screen.domain.model.HouseModel
import com.example.houseops_revamped.feature_home.house_view_screen.domain.repository.HouseViewRepository
import com.google.firebase.firestore.FieldValue
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

                        Log.d("viewRepo", "$currentHouse")
                    }
                }

        } catch (e: Exception) {
            Log.d("viewRepo", "$e")
        }
    }

    override suspend fun addHouseToBookedHouses(
        bookedHouse: BookedHouseModel,
        email: String,
        isAdd: Boolean
    ) {

        try {

            val documentRef = db.collection(Constants.USERS_COLLECTION)
                .document(email)

            if (isAdd) {
                documentRef.update("userBookedHouses", FieldValue.arrayUnion(bookedHouse))
                    .addOnSuccessListener { }
                    .addOnFailureListener { }
            } else {
                documentRef.update("userBookedHouses", FieldValue.arrayRemove(bookedHouse))
                    .addOnSuccessListener { }
                    .addOnFailureListener { }
            }

        } catch (e: Exception) {
            Log.d("booked", "$e")
        }
    }

    override suspend fun addUserToHouseBooked(
        apartmentName: String,
        houseCategory: String,
        userEmail: String,
        isAdd: Boolean
    ) {

        try {

            val docRef = db.collection(Constants.APARTMENTS_COLLECTION)
                .document(apartmentName)
                .collection(Constants.HOUSES_SUB_COLLECTION)
                .document(houseCategory)

            if (isAdd)
                docRef.update("houseUsersBooked", FieldValue.arrayUnion(userEmail))
                    .addOnSuccessListener {
                        Log.d("booked", "repo -> $apartmentName, $houseCategory")
                    }
                    .addOnFailureListener {  }
            else
                docRef.update("houseUsersBooked", FieldValue.arrayRemove(userEmail))
                    .addOnSuccessListener {  }
                    .addOnFailureListener {  }


        } catch (e: Exception) {
            Log.d("booked", "$e")
        }
    }
}












