package com.kenstarry.houseops_revamped.core.domain.use_cases

import com.kenstarry.houseops_revamped.core.domain.repository.CoreRepository

class IsUserLoggedIn(
    private val repository: CoreRepository
) {
    suspend operator fun invoke(): Boolean = repository.isUserLoggedIn()
}