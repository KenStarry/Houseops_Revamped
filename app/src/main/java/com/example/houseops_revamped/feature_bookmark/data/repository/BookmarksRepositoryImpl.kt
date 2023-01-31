package com.example.houseops_revamped.feature_bookmark.data.repository

import com.example.houseops_revamped.core.domain.model.BookmarkHouse
import com.example.houseops_revamped.core.domain.model.LikedHouse
import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.example.houseops_revamped.core.utils.Constants
import com.example.houseops_revamped.feature_bookmark.domain.repository.BookmarksRepository
import com.example.houseops_revamped.feature_home.home_screen.domain.model.HouseModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import javax.inject.Inject

class BookmarksRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
) : BookmarksRepository {

    override suspend fun getBookmarks(
        userEmail: String,
        bookmarks: (List<LikedHouse>) -> Unit
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
        apartmentName: String,
        houseCategory: String,
        houses: (MutableList<HouseModel>) -> Unit
    ) {
        db.collectionGroup(Constants.HOUSES_SUB_COLLECTION)
            .whereEqualTo("houseApartmentName", apartmentName)
            .whereEqualTo("houseCategory", houseCategory)
            .addSnapshotListener { querySnapshot, error ->

                if (error != null)
                    return@addSnapshotListener

                val housesList = ArrayList<HouseModel>()

                querySnapshot?.forEach { snapshot ->
                    snapshot.toObject(HouseModel::class.java).let {house ->
                        housesList.add(house)
                    }
                }

                houses(housesList)
            }
    }
}













