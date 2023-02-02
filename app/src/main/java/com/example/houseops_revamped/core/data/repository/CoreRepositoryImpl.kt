package com.example.houseops_revamped.core.data.repository

import android.util.Log
import com.example.houseops_revamped.core.domain.model.BookmarkHouse
import com.example.houseops_revamped.core.domain.model.Caretaker
import com.example.houseops_revamped.core.domain.model.LikedHouse
import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.example.houseops_revamped.core.domain.repository.CoreRepository
import com.example.houseops_revamped.core.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class CoreRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore,
    private val auth: FirebaseAuth
) : CoreRepository {

    override suspend fun isUserLoggedIn(): Boolean = auth.currentUser != null

    override suspend fun currentUser(): FirebaseUser? = auth.currentUser

    override suspend fun getUserDetails(
        email: String,
        user: (user: UsersCollection?) -> Unit
    ) {

        try {
            db.collection(Constants.USERS_COLLECTION)
                .document(email)
                .addSnapshotListener { snapshot, error ->

                    if (error != null)
                        return@addSnapshotListener

                    snapshot?.toObject(UsersCollection::class.java)?.let {
                        user(it)
                    }
                }
        } catch (e: Exception) {
            Log.d("userDetails", e.message.toString())
        }
    }

    override suspend fun getCaretakerDetails(
        apartmentName: String,
        caretaker: (caretaker: Caretaker?) -> Unit
    ) {
        db.collection(Constants.CARETAKER_COLLECTION)
            .whereEqualTo("caretakerApartment", apartmentName)
            .get()
            .addOnSuccessListener { documents ->
                documents.forEach {

                    it.toObject(Caretaker::class.java).let { caretaker ->
                        caretaker(caretaker)

                        Log.d("caretaker", "${caretaker.caretakerEmail}")
                    }
                }
            }
            .addOnFailureListener {
                Log.d("caretaker", "$it")
            }
    }

    override suspend fun updateFirestoreField(
        collectionName: String,
        documentName: String,
        subCollectionName: String,
        subCollectionDocument: String,
        fieldName: String,
        fieldValue: String
    ) {

        try {
            val collectionRef = db
                .collection(collectionName)
                .document(documentName)
                .collection(subCollectionName)
                .document(subCollectionDocument)

            collectionRef.update(
                fieldName, fieldValue
            )

        } catch (e: Exception) {
            Log.d("update", "$e")
        }
    }

    override suspend fun updateUserArrayField(
        collectionName: String,
        documentName: String,
        fieldName: String,
        fieldValue: String,
        isAddItem: Boolean
    ) {
        try {
            val collectionRef = db
                .collection(collectionName)
                .document(documentName)

            if (isAddItem)
                collectionRef.update(
                    fieldName, FieldValue.arrayUnion(fieldValue)
                )
            else
                collectionRef.update(
                    fieldName, FieldValue.arrayRemove(fieldValue)
                )

        } catch (e: Exception) {
            Log.d("update", "$e")
        }
    }

    override suspend fun getAllCaretakers(caretakers: (List<Caretaker>?) -> Unit) {

        try {
            db.collection(Constants.CARETAKER_COLLECTION)
                .addSnapshotListener { querySnapshot, error ->

                    if (error != null)
                        return@addSnapshotListener

                    val caretakersList = ArrayList<Caretaker>()

                    querySnapshot?.let {
                        it.forEach { snapshot ->
                            snapshot.toObject(Caretaker::class.java).let { caretaker ->
                                caretakersList.add(caretaker)
                            }
                        }
                    }

                    caretakers(caretakersList)

                }
        } catch (e: Exception) {
            Log.d("caretakers", "$e")
        }
    }

    override suspend fun getApartments() {

    }
}

















