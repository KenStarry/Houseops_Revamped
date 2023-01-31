package com.example.houseops_revamped.feature_bookmark.data.repository

import android.util.Log
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
        bookmarkModelList: List<LikedHouse>,
        houses: (MutableList<HouseModel>) -> Unit
    ) {

        val housesList = ArrayList<HouseModel>()

        bookmarkModelList.forEach {

            db.collectionGroup(Constants.HOUSES_SUB_COLLECTION)
                .whereEqualTo("houseApartmentName", it.apartmentName)
                .whereEqualTo("houseCategory", it.houseCategory)
                .addSnapshotListener { querySnapshot, error ->

                    if (error != null)
                        return@addSnapshotListener

                    querySnapshot?.forEach { snapshot ->
                        snapshot.toObject(HouseModel::class.java).let { house ->
                            housesList.add(house)
                        }
                    }

                    Log.d("bookmarks", "Added ${housesList.size} houses")

                    Log.d("bookmarks", "HouseList size ${housesList.size} houses")
                    houses(housesList)
                }
        }
    }
}













