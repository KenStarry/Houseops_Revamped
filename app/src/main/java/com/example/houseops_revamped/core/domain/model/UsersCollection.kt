package com.example.houseops_revamped.core.domain.model

import java.util.Objects

data class UsersCollection(
    var userName: String?,
    var userEmail: String?,
    var userPassword: String?,
    var userImageUri: String?,
    var userLikedHouses: List<LikedHouse>?,
    var userBookmarks: List<BookmarkHouse>?
) {
    //  empty constructor
    constructor() : this("", "", "",
        "", listOf(), listOf()
    )
}
