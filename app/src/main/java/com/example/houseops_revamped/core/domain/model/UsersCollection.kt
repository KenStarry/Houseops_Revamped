package com.example.houseops_revamped.core.domain.model

import com.example.houseops_revamped.feature_booked.domain.model.BookedHouseModel

data class UsersCollection(
    var userName: String?,
    var userEmail: String?,
    var userPassword: String?,
    var userImageUri: String?,
    var userLikedHouses: List<LikedHouse>?,
    var userBookmarks: List<String>?,
    var userBookedHouses: List<BookedHouseModel>?
) {
    //  empty constructor
    constructor() : this("", "", "",
        "", listOf(), listOf(), listOf()
    )
}
