package com.example.houseops_revamped.network

import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.navigation.NavHostController
import com.example.houseops_revamped.core.utils.Constants
import com.example.houseops_revamped.core.utils.Constants.AUTHENTICATION_ROUTE
import com.example.houseops_revamped.core.utils.Constants.HOME_ROUTE
import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

//  create a user account in FirebaseAuth
suspend fun createAccount(
    auth: FirebaseAuth,
    context: Context,
    navHostController: NavHostController,
    email: String,
    password: String
) {

    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->

            if (task.isSuccessful) {

                Toast.makeText(context, "Account Created Successfully!", Toast.LENGTH_SHORT).show()

                //  navigate to home screen
                navHostController.navigate(HOME_ROUTE) {
                    popUpTo(AUTHENTICATION_ROUTE)
                    launchSingleTop = true
                }

            } else {

                Toast.makeText(context, "Something went wrong...", Toast.LENGTH_SHORT).show()
            }
        }
}

//  create user collection
suspend fun createUserCollection(
    userName: String,
    userEmail: String,
    userImageUri: String,
    userPassword: String,
    userIsCaretaker: Boolean,
    userExtraDetails: List<String>?,
    userHasMadeRequest: Boolean
) {

    val user = UsersCollection(
        userName = userName,
        userEmail = userEmail,
        userImageUri = userImageUri,
        userPassword = userPassword,
        userLikedHouses = listOf()
    )

    val db: FirebaseFirestore = Firebase.firestore

    //  create users collection
    db.collection(Constants.USERS_COLLECTION).document(userEmail).set(user)
        .addOnSuccessListener {

        }
        .addOnFailureListener {

        }
}

//  add image to firestore
suspend fun uploadImageToFirestore(
    imageUri: Uri,
    email: String,
    context: Context,
    onUploadFailure: () -> Unit,
    onUploadProgress: () -> Unit
) {
    val storageRef = FirebaseStorage.getInstance().getReference(Constants.USER_IMAGES)
    val dbRef = Firebase.firestore

    val fileRef = storageRef.child(
        "${System.currentTimeMillis()}.${getFileExtension(imageUri, context)}"
    )

    fileRef.putFile(imageUri)
        .addOnSuccessListener {
            //  download url
            fileRef.downloadUrl.addOnSuccessListener { url ->

                //  set the url in the user collection document
                val userDocumentRef = dbRef.collection(Constants.USERS_COLLECTION).document(email)
                userDocumentRef.update("userImageUri", url)
            }
        }
        .addOnFailureListener { onUploadFailure() }
        .addOnProgressListener { onUploadProgress() }
}

//  get file extension
fun getFileExtension(uri: Uri, context: Context): String? {
    val cr = context.contentResolver
    val mimeTypeMap = MimeTypeMap.getSingleton()

    return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri))
}

//  logout user
suspend fun logoutUser(
    auth: FirebaseAuth,
    context: Context,
    navHostController: NavHostController,
    onLoggedOut: () -> Unit
) {

//    val myAuth = FirebaseAuth.getInstance()
//    myAuth.addAuthStateListener {
//        //  logout user
//        it.signOut()
//    }
    onLoggedOut()
}

//  query user details in mainactivity
suspend fun queryUserDetails(
    db: FirebaseFirestore,
    currentUserEmail: String,
    onQuerySuccess: (user: UsersCollection) -> Unit
) {

    db.collection(Constants.USERS_COLLECTION)
        .document(currentUserEmail)
        .addSnapshotListener { snapshot, error ->

            if (error != null)
                return@addSnapshotListener

            if (snapshot != null) {
                val user: UsersCollection = snapshot.toObject()!!
                onQuerySuccess(user)

            }
        }


}

//  query users based on a criteria
suspend fun queryRegisteredCaretakers(
    db: FirebaseFirestore,
    onQuerySuccess: (userList: List<UsersCollection>) -> Unit
) {

    db.collection(Constants.USERS_COLLECTION)
        .whereEqualTo("userHasMadeRequest", true)
        .addSnapshotListener { querySnapshot, error ->

            if (error != null)
                return@addSnapshotListener

            val usersList: ArrayList<UsersCollection> = ArrayList()

            if (querySnapshot != null) {
                for (snapshot in querySnapshot) {

                    val user: UsersCollection = snapshot.toObject()
                    usersList.add(user)
                }
            }

            onQuerySuccess(usersList)
        }
}














