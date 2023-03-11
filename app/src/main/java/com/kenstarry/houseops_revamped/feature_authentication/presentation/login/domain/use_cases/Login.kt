package com.kenstarry.houseops_revamped.feature_authentication.presentation.login.domain.use_cases

import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.feature_authentication.presentation.login.domain.repository.LoginRepository

class Login(
    private val repo: LoginRepository
) {

    suspend operator fun invoke(
        email: String,
        password: String,
        onResponse: (res: Response<*>) -> Unit
    ) {

        repo.loginUser(
            email = email,
            password = password,
            onResponse = {
                onResponse(it)
            }
        )

    }
}