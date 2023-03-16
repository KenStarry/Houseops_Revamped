package com.kenstarry.houseops_revamped.feature_authentication.presentation.login.domain.use_cases

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.feature_authentication.presentation.login.domain.repository.LoginRepository

class FirebaseAuthWithGoogle(
    private val repo: LoginRepository
) {
    suspend operator fun invoke(
        account: GoogleSignInAccount,
        shouldCreateCollection: (shouldCreateCollection: Boolean) -> Unit,
        response: (response: Response<*>) -> Unit
    ) = repo.firebaseAuthWithGoogle(account, shouldCreateCollection, response)
}