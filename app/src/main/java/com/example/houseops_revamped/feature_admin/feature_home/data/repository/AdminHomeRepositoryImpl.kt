package com.example.houseops_revamped.feature_admin.feature_home.data.repository

import com.example.houseops_revamped.core.domain.model.Response
import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.example.houseops_revamped.core.presentation.utils.Constants
import com.example.houseops_revamped.feature_admin.feature_home.domain.repository.AdminHomeRepository
import com.example.houseops_revamped.feature_authentication.domain.utils.AuthConstants
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class AdminHomeRepositoryImpl(
    private val db: FirebaseFirestore
) : AdminHomeRepository {

    override suspend fun getLandlords(response: (response: Response<*>) -> Unit) {

        try {
            db.collection(Constants.USERS_COLLECTION)
                .whereEqualTo("userType", AuthConstants.userTypes[0].userTitle)
                .addSnapshotListener { querySnapshot, error ->

                    if (error != null) {
                        response(Response.Failure(error))
                        return@addSnapshotListener
                    }

                    val landlordsList = ArrayList<UsersCollection>()

                    querySnapshot?.forEach { snapshot ->
                        snapshot.toObject(UsersCollection::class.java).let {
                            landlordsList.add(it)
                        }
                    }

                    response(Response.Success(landlordsList))
                }
        } catch (e: Exception) {
            response(Response.Failure(e.message))
        }
    }
}

















