package com.example.houseops_revamped.models.firestore

data class UsersCollection(
    var userName: String?,
    var userEmail: String?,
    var userPassword: String?,
    var userImageUri: String?,
    var userIsCaretaker: Boolean = false,
    var userExtraDetails: ArrayList<String>?,
    var userHasMadeRequest: Boolean = false
) {
    //  empty constructor
    constructor() : this("", "", "", "", false, ArrayList())
}
