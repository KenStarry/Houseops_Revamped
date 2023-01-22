package com.example.houseops_revamped.core.domain.model

import java.util.Objects

data class UsersCollection(
    var userName: String?,
    var userEmail: String?,
    var userPassword: String?,
    var userImageUri: String?,
    var userIsCaretaker: Boolean = false,
    var userExtraDetails: List<String>?,
    var userHasMadeRequest: Boolean = false
) {
    //  empty constructor
    constructor() : this("", "", "", "", false, listOf())
}
