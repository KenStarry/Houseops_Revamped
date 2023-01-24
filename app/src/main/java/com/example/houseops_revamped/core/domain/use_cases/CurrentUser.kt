package com.example.houseops_revamped.core.domain.use_cases

import com.example.houseops_revamped.core.domain.repository.CoreRepository

class CurrentUser(
    private val repo: CoreRepository
) {
    suspend operator fun invoke() = repo.currentUser()
}