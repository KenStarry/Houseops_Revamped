package com.kenstarry.houseops_revamped.core.domain.use_cases

import com.kenstarry.houseops_revamped.core.domain.model.Response
import com.kenstarry.houseops_revamped.core.domain.repository.CoreRepository

class LogoutUser(
    private val repository: CoreRepository
) {
    suspend operator fun invoke(
        response: (response: Response<*>) -> Unit

    ) = repository.logoutUser { response(it) }
}