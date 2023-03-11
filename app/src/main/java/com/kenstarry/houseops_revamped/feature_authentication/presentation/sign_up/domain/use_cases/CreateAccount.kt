package com.kenstarry.houseops_revamped.feature_authentication.presentation.sign_up.domain.use_cases

import android.util.Log
import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.feature_authentication.presentation.sign_up.domain.repository.SignUpRepository

class CreateAccount(
    private val repository: SignUpRepository
) {
    suspend operator fun invoke(
        email: String,
        password: String,
        response: (response: Response<*>) -> Unit
    ) = repository.createAccount(
        email = email,
        password = password,
        response = {
            response(it)
            Log.d("signUp", "use case res -> $it")
        }
    )
}