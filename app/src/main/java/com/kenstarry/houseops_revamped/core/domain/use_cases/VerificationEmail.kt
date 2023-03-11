package com.kenstarry.houseops_revamped.core.domain.use_cases

import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.core.domain.repository.CoreRepository

class VerificationEmail(
    private val repo: CoreRepository
) {
    suspend operator fun invoke(
        response: (response: Response<*>) -> Unit
    ) = repo.sendVerificationEmail(response)

}