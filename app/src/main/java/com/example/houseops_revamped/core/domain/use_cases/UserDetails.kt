package com.example.houseops_revamped.core.domain.use_cases

import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.example.houseops_revamped.core.domain.repository.CoreRepository

class UserDetails(
    private val repository: CoreRepository
) {

    suspend operator fun invoke(
        email: String,
        user: (user: UsersCollection?) -> Unit
    ) = repository.getUserDetails(
        email = email,
        user = {
            user(it)
        }
    )
}