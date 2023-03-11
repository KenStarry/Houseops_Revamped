package com.kenstarry.houseops_revamped.feature_landlord.feature_home.feature_add_apartment.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.kenstarry.houseops_revamped.core.domain.model.Apartment
import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants
import com.kenstarry.houseops_revamped.feature_landlord.feature_home.feature_add_apartment.domain.repository.LndAddApartmentRepository

class LndAddApartmentRepositoryImpl(
    private val db: FirebaseFirestore
) : LndAddApartmentRepository {

    override suspend fun addApartmentToFirestore(
        apartment: Apartment,
        response: (response: Response<*>) -> Unit
    ) {

        try {

            val documentRef = db.collection(Constants.APARTMENTS_COLLECTION)
                .document(apartment.apartmentName)

            documentRef.set(apartment)
                .addOnSuccessListener { response(Response.Success(true)) }
                .addOnFailureListener { response(Response.Failure(it)) }

        } catch (e: Exception) {
            response(Response.Failure(e))
        }
    }
}