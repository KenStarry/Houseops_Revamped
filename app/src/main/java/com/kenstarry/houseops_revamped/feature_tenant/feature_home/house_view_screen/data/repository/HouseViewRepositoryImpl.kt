package com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.data.repository

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.kenstarry.houseops_revamped.core.domain.model.Apartment
import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants
import com.kenstarry.houseops_revamped.feature_tenant.feature_booked.domain.model.BookedHouseModel
import com.kenstarry.houseops_revamped.core.domain.model.HouseModel
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.domain.model.UserBooked
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.domain.repository.HouseViewRepository
import javax.inject.Inject
import javax.inject.Named

class HouseViewRepositoryImpl @Inject constructor(
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

    override suspend fun getApartmentDetails(
        apartmentName: String,
        apartment: (apartment: Apartment) -> Unit,
        response: (response: Response<*>) -> Unit
    ) {
        try {

            db.collection(Constants.APARTMENTS_COLLECTION)
                .document(apartmentName)
                .addSnapshotListener { snapshot, error ->
                    if (error != null) {
                        response(Response.Failure(error.localizedMessage))
                        return@addSnapshotListener
                    }

                    snapshot?.toObject(Apartment::class.java)?.let { aprtmt ->
                        apartment(aprtmt)
                        response(Response.Success(aprtmt))
                    }
                }

        } catch (e: Exception) {
            response(Response.Failure(e.localizedMessage))
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

            //  converting the value to a single string
//            val bookedHouseStringModel = "${bookedHouse.houseId}(${bookedHouse.dateBooked})"

            if (isAdd) {
                documentRef.update(
                    "userBookedHouses",
                    FieldValue.arrayUnion(bookedHouse)
                )
                    .addOnSuccessListener { }
                    .addOnFailureListener { }

            } else {
                documentRef.update(
                    "userBookedHouses", FieldValue.arrayRemove(bookedHouse)
                )
                    .addOnSuccessListener {
                        Log.d("bookedRemove", "Removed successfully")
                    }
                    .addOnFailureListener {
                        Log.d("bookedRemove", "Removed error : $it")
                    }
            }

        } catch (e: Exception) {
            Log.d("booked", "$e")
        }
    }

    override suspend fun addUserToHouseBooked(
        apartmentName: String,
        houseCategory: String,
        userBooked: UserBooked,
        isAdd: Boolean
    ) {

        try {

            val docRef = db.collection(Constants.APARTMENTS_COLLECTION)
                .document(apartmentName)
                .collection(Constants.HOUSES_SUB_COLLECTION)
                .document(houseCategory)

            if (isAdd)
                docRef.update("houseUsersBooked", FieldValue.arrayUnion(userBooked))
                    .addOnSuccessListener {
                        Log.d("booked", "repo -> $apartmentName, $houseCategory")
                    }
                    .addOnFailureListener { }
            else
                docRef.update("houseUsersBooked", FieldValue.arrayRemove(userBooked))
                    .addOnSuccessListener { }
                    .addOnFailureListener { }


        } catch (e: Exception) {
            Log.d("booked", "$e")
        }
    }
}












