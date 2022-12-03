package com.example.houseops_revamped.models

data class UsersCollection(
    var userName: String?,
    var userEmail: String?,
    var userPassword: String?,
    var userImageUri: String?,
    var userIsCaretaker: Boolean = false
) {
    //  empty constructor
    constructor() : this("", "", "", "", false)
}
