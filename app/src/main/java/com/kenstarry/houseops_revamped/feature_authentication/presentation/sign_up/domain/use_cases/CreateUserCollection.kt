package com.kenstarry.houseops_revamped.feature_authentication.presentation.sign_up.domain.use_cases

import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.feature_authentication.presentation.sign_up.domain.repository.SignUpRepository

class CreateUserCollection(
    private val repository: SignUpRepository
) {
    suspend operator fun <T> invoke(
        user: T,
        response: (response: Response<*>) -> Unit
    ) = repository.createUserCollection(
        user = user,
        response = { response(it) }
    )
}