package com.kenstarry.houseops_revamped.core.domain.model

import com.kenstarry.houseops_revamped.feature_tenant.feature_booked.domain.model.BookedHouseModel

data class UsersCollection(
    var userName: String?,
    var userEmail: String?,
    var userPassword: String?,
    var userImageUri: ImageModel?,
    var userLikedHouses: List<LikedHouse>?,
    var userBookmarks: List<String>?,
    var userBookedHouses: List<BookedHouseModel>?,
    var userType: List<String>,
    var userIsVerified: Boolean
) {
    //  empty constructor
    constructor() : this("", "", "",
        null, listOf(), listOf(), listOf(), listOf(), false
    )
}
