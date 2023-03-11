package com.kenstarry.houseops_revamped.core.domain.use_cases

import com.kenstarry.houseops_revamped.core.domain.model.UsersCollection
import com.kenstarry.houseops_revamped.core.domain.repository.CoreRepository

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