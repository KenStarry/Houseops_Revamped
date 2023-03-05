package com.example.houseops_revamped.feature_admin.feature_landlord_view.data.repository

import com.example.houseops_revamped.core.domain.model.Apartment
import com.example.houseops_revamped.core.domain.model.Response
import com.example.houseops_revamped.core.presentation.utils.Constants
import com.example.houseops_revamped.feature_admin.feature_landlord_view.domain.repository.AdminLandlordViewRepo
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class AdminLandlordViewRepoImpl @Inject constructor(
    private val db: FirebaseFirestore
) : AdminLandlordViewRepo {

    override suspend fun getLandlordApartments(
        landlordEmail: String,
        apartments: (apartments: List<Apartment>) -> Unit,
        response: (response: Response<*>) -> Unit
    ) {
        try {

            db.collection(Constants.APARTMENTS_COLLECTION)
                .whereEqualTo("apartmentLandlordEmail", landlordEmail)
                .addSnapshotListener { querySnapshot, error ->

                    if (error != null) {
                        response(Response.Failure(error.localizedMessage))
                        return@addSnapshotListener
                    }

                    val apartmentList = mutableListOf<Apartment>()

                    querySnapshot?.forEach { snapshot ->
                        snapshot?.toObject(Apartment::class.java)?.let {
                            apartmentList.add(it)
                        }
                    }

                    apartments(apartmentList)
                    response(Response.Success(apartmentList))
                }

        } catch (e: Exception) {
            response(Response.Failure(e.localizedMessage))
        }
    }
}