package com.kenstarry.houseops_revamped.feature_tenant.feature_bookmark.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.kenstarry.houseops_revamped.core.domain.model.UsersCollection
import com.kenstarry.houseops_revamped.core.presentation.utils.Constants
import com.kenstarry.houseops_revamped.feature_tenant.feature_bookmark.domain.repository.BookmarksRepository
import com.kenstarry.houseops_revamped.core.domain.model.HouseModel
import javax.inject.Inject

class BookmarksRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
) : BookmarksRepository {

    override suspend fun getBookmarks(
        userEmail: String,
        bookmarks: (List<String>) -> Unit
    ) {

        db.collection(Constants.USERS_COLLECTION)
            .document(userEmail)
            .get()
            .addOnSuccessListener { document ->

                document?.let {
                    val user = it.toObject(UsersCollection::class.java)

                    bookmarks(user?.userBookmarks ?: emptyList())
                }

            }
            .addOnFailureListener {

            }
    }

    override suspend fun getBookmarkedHouses(
        bookmarkModelList: List<String>,
        houses: (MutableList<HouseModel>) -> Unit
    ) {

        val housesList = ArrayList<HouseModel>()

        if (bookmarkModelList.isNotEmpty()) {

            db.collectionGroup(Constants.HOUSES_SUB_COLLECTION)
                .whereIn("houseId", bookmarkModelList.map { it })
                .addSnapshotListener { querySnapshot, error ->

                    if (error != null)
                        return@addSnapshotListener

                    querySnapshot?.forEach { snapshot ->
                        snapshot.toObject(HouseModel::class.java).let { house ->
                            housesList.add(house)
                        }
                    }

                    Log.d("bookmarks", "${bookmarkModelList.size}")

                    houses(housesList)
                }
        }
    }
}













