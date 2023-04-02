package com.kenstarry.houseops_revamped.feature_tenant.feature_booked.data.repository

import android.util.Log
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants
import com.kenstarry.houseops_revamped.feature_tenant.feature_booked.domain.repository.BookedRepository
import com.kenstarry.houseops_revamped.core.domain.model.HouseModel
import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.feature_tenant.feature_booked.domain.model.BookedHouseModel
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.house_view_screen.domain.model.UserBooked
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

    override suspend fun deleteBookedHouseCategory(
        email: String,
        bookedHousesUnderCategory: List<BookedHouseModel>,
        onResponse: (repsonse: Response<*>) -> Unit
    ) {
        try {

            val docRef = db.collection(Constants.USERS_COLLECTION)
                .document(email)

            bookedHousesUnderCategory.forEach { category ->

                Log.d("batch", "date -> ${category.dateBooked}\nhouseId -> ${category.houseId}")

                db.collectionGroup(Constants.HOUSES_SUB_COLLECTION)
                    .whereEqualTo("houseId", category.houseId)
                    .addSnapshotListener { documents, error ->

                        if (error != null)
                            return@addSnapshotListener

                        documents?.forEach { document ->
                            //  house with the specific id
                            val houseRef = document.reference

                            db.runBatch {  batch ->
                                batch.update(
                                    houseRef,
                                    "houseUsersBooked",
                                    FieldValue.arrayRemove(UserBooked(email, category.dateBooked))
                                )
                            }.addOnSuccessListener { Log.d("batch", "Batch completed successfully") }
                                .addOnFailureListener { Log.d("batch", "An error occurred") }
                        }
                    }

                docRef
                    .update(
                        "userBookedHouses", FieldValue.arrayRemove(category)
                    )
                    .addOnSuccessListener { onResponse(Response.Success(true)) }
                    .addOnFailureListener { onResponse(Response.Failure(it.localizedMessage)) }
            }

        } catch (e: Exception) {
            onResponse(Response.Failure(e.localizedMessage))
        }
    }
}