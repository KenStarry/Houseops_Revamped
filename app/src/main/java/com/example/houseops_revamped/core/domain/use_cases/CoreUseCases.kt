package com.example.houseops_revamped.core.domain.use_cases

data class CoreUseCases(
    val isUserLoggedIn: IsUserLoggedIn,
    val currentUser: CurrentUser,
    val userDetails: UserDetails
) {
}