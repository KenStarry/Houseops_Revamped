package com.example.houseops_revamped.feature_authentication.presentation.sign_up.domain.use_cases

import com.example.houseops_revamped.core.domain.model.Response
import com.example.houseops_revamped.core.domain.model.UsersCollection
import com.example.houseops_revamped.feature_authentication.presentation.sign_up.domain.repository.SignUpRepository

class CreateUserCollection(
    private val repository: SignUpRepository
) {
    suspend operator fun invoke(
        user: UsersCollection,
        response: (response: Response) -> Unit
    ) = repository.createUserCollection(
        user = user,
        response = { response(it) }
    )
}