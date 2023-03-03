package com.example.houseops_revamped.feature_tenant.feature_home.house_view_screen.domain.model

data class UserBooked(
    val userEmail: String,
    val dateBooked: String
) {
    constructor() : this("", "")
}
