package com.kenstarry.houseops_revamped.feature_authentication.presentation.login.domain.use_cases

import android.content.Context
import android.content.Intent
import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.feature_authentication.presentation.login.domain.repository.LoginRepository

class LoginWithGoogle(
    private val repo: LoginRepository
) {
    suspend operator fun invoke(
        context: Context,
        intent: (intent: Intent) -> Unit,
        response: (response: Response<*>) -> Unit
    ) = repo.loginWithGoogle(context, intent, response)
}