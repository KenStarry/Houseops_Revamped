package com.kenstarry.houseops_revamped.feature_tenant.feature_categories.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants
import com.kenstarry.houseops_revamped.feature_tenant.feature_categories.domain.repository.CategoriesRepository
import com.kenstarry.houseops_revamped.feature_tenant.feature_home.home_screen.domain.model.HouseModel
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
) : CategoriesRepository {

    override suspend fun getCaretakerHouses(apartmentName: String,  houses: (List<HouseModel>) -> Unit) {

        try {

            db.collection(Constants.APARTMENTS_COLLECTION)
                .document(apartmentName)
                .collection(Constants.HOUSES_SUB_COLLECTION)
                .addSnapshotListener { querySnapshot, error ->

                    if (error != null)
                        return@addSnapshotListener

                    val housesList = ArrayList<HouseModel>()

                    querySnapshot?.forEach { snapshot ->
                        snapshot?.toObject(HouseModel::class.java)?.let {
                            housesList.add(it)
                        }
                    }

                    houses(housesList)
                }

        } catch (e: Exception) {
            Log.d("categories", "$e")
        }
    }
}