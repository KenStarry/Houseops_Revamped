package com.kenstarry.houseops_revamped.feature_admin.feature_home.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.core.domain.model.UsersCollection
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants
import com.kenstarry.houseops_revamped.feature_admin.feature_home.domain.repository.AdminHomeRepository
import com.kenstarry.houseops_revamped.feature_authentication.domain.utils.AuthConstants
import javax.inject.Inject

class AdminHomeRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
) : AdminHomeRepository {

    override suspend fun getLandlords(
        landlords: (landlords: List<UsersCollection>) -> Unit,
        response: (response: Response<*>) -> Unit
    ) {

        try {
            db.collection(Constants.USERS_COLLECTION)
                .whereArrayContains("userType", AuthConstants.userTypes[0].userTitle)
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
                    landlords(landlordsList)
                    response(Response.Success(landlordsList))
                }
        } catch (e: Exception) {
            response(Response.Failure(e.message))
        }
    }
}

















