package com.example.houseops_revamped.core.domain.model

data class UsersCollection(
    var userName: String?,
    var userEmail: String?,
    var userPassword: String?,
    var userImageUri: String?,
    var userLikedHouses: List<LikedHouse>?,
    var userBookmarks: List<String>?
) {
    //  empty constructor
    constructor() : this("", "", "",
        "", listOf(), listOf()
    )
}
