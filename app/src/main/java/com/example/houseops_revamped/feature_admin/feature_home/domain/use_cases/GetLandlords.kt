package com.example.houseops_revamped.feature_admin.feature_home.domain.use_cases

import com.example.houseops_revamped.core.domain.model.Response
import com.example.houseops_revamped.feature_admin.feature_home.domain.repository.AdminHomeRepository

class GetLandlords(
    private val repo: AdminHomeRepository
) {
    suspend operator fun invoke(
        response: (response: Response<*>) -> Unit
    ) = repo.getLandlords(response = { response(it) })
}